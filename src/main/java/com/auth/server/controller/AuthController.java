package com.auth.server.controller;


import com.auth.server.annotations.binding.BindingManager;
import com.auth.server.api.AuthApi;
import com.auth.server.entity.role.Role;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.entity.webuser.response.WebUserShortResponse;
import com.auth.server.enums.AuthProvider;
import com.auth.server.exception.EmailAlreadyUsedException;
import com.auth.server.exception.OldPasswordErrorException;
import com.auth.server.exception.RoleNameNotExistException;
import com.auth.server.exception.UserNotFoundException;
import com.auth.server.payload.ApiResponse;
import com.auth.server.payload.AuthResponse;
import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import com.auth.server.payload.UserResponse;
import com.auth.server.repository.RoleRepository;
import com.auth.server.repository.WebUserRepository;
import com.auth.server.security.TokenProvider;
import com.auth.server.util.CookieUtils;
import com.auth.server.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.auth.server.util.CookieUtils.HEADER_NAME;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController<T> implements AuthApi {

    private final AuthenticationManager authenticationManager;

    private final WebUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RoleRepository roleRepository;

    private final BindingManager bindingManager;

    private final UserUtils userUtils;


    @SneakyThrows
    @Override
    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<WebUser> webUser = userRepository.findByEmail(loginRequest.getEmail());
        String token = tokenProvider.createToken(authentication);
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.info("authorities {} ", authorities);
        AuthResponse authResponse = AuthResponse
                .builder()
                .accessToken(CookieUtils.serialize(token))
                .tokenType("Bearer")
                .webUser(WebUserShortResponse
                        .builder()
                        .id(webUser.get().getId())
                        .email(webUser.get().getEmail())
                        .firstName(webUser.get().getFirstName())
                        .lastName(webUser.get().getLastName())
                        .imageUrl(webUser.get().getImageUrl())
                        .phoneNumber(webUser.get().getPhoneNumber())
                        .roles(webUser.get().getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .build())
                .build();
        log.info("AuthResponse authResponse = AuthResponse");
        return ResponseEntity.ok()
                .body(authResponse);

    }

    @Override
    public ResponseEntity<?> registerUser(@Valid SignUpRequest signUpRequest,
                                          BindingResult bindingResult) {
        bindingManager.bindingCheck(bindingResult);
        if (userRepository.existsByEmail(signUpRequest.getEmail())) throw new EmailAlreadyUsedException();
        if (roleRepository.existsByName(signUpRequest.getRoles().getName()) == null)
            throw new RoleNameNotExistException();

        WebUser user = new WebUser();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        if (signUpRequest.getRoles().getName().toLowerCase().contains("admin")) {
            user.setEmailVerified(true);
        }
        user.setEmailVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName(signUpRequest.getRoles().getName())));
        WebUser result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully."));
    }

    @Async
    @Override
    public CompletableFuture<ResponseEntity<?>> checkUser() {
        WebUser webUser = userUtils.getUserId();
        return CompletableFuture.completedFuture(ResponseEntity.accepted()
                .body(new ApiResponse(true, webUser.getEmail())));
    }


    @Override
    public ResponseEntity<?> logout(HttpServletRequest request) {
        WebUser webUser = userUtils.getUserId();
        String token = tokenProvider.createLogoutToken(webUser);
        log.info("token {} ", token);
        String bearerToken = request.getHeader(HEADER_NAME);
        String accessTokens = bearerToken.substring(7, bearerToken.length());
        String logoutTokenSerialize = CookieUtils.serialize(token);
        return token.equals(accessTokens) ? ResponseEntity.accepted().body(new ApiResponse(false, "Logout not successful")) :
                ResponseEntity.ok().body(new ApiResponse(true, "logout successfully.", logoutTokenSerialize));
    }

    @Override
    public ResponseEntity<?> changePass(HttpServletRequest request,
                                        WebUserRequest webUserRequest,
                                        BindingResult bindingResult) {
        WebUser webUser = userUtils.getUserId();
        bindingManager.bindingCheck(bindingResult);
        WebUser oldUser = userRepository.findById(webUser.getId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User id not found"));

        if (!passwordEncoder.matches(webUserRequest.getOldPassword(), webUser.getPassword()))
            throw new OldPasswordErrorException();

        oldUser.setPassword(passwordEncoder.encode(webUserRequest.getPassword()));
        userRepository.save(oldUser);

        logout(request);
        return  ResponseEntity.accepted().body(new ApiResponse(true, "Password changed successfully."));


    }



    @Override
    public ResponseEntity<?> userDetails() {
        WebUser webUser = userUtils.getUserId();
        log.info("webUser {} ", webUser);
        return ResponseEntity.ok().body(
                UserResponse
                        .builder()
                        .webUser(WebUserShortResponse
                                .builder()
                                .id(webUser.getId())
                                .email(webUser.getEmail())
                                .firstName(webUser.getFirstName())
                                .lastName(webUser.getLastName())
                                .imageUrl(webUser.getImageUrl())
                                .phoneNumber(webUser.getPhoneNumber())
                                .roles(webUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                                .build())
                        .build());
    }

}

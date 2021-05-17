package com.auth.server.controller;


import com.auth.server.annotations.binding.BindingManager;
import com.auth.server.annotations.validator.UserValidator;
import com.auth.server.api.AuthApi;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.entity.webuser.response.WebUserResponse;
import com.auth.server.enums.AuthProvider;
import com.auth.server.exception.EmailAlreadyUsedException;
import com.auth.server.exception.OldPasswordErrorException;
import com.auth.server.exception.RoleNameNotExistException;
import com.auth.server.exception.UserNotFoundException;
import com.auth.server.payload.ApiResponse;
import com.auth.server.payload.AuthResponse;
import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import com.auth.server.repository.RoleRepository;
import com.auth.server.repository.WebUserRepository;
import com.auth.server.security.TokenProvider;
import lombok.RequiredArgsConstructor;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;

    private final WebUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RoleRepository roleRepository;

    private final BindingManager bindingManager;
    private final UserValidator userValidator;


    @Override
    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<WebUser> userData = userRepository.findByEmail(loginRequest.getEmail());
        String token = tokenProvider.createToken(authentication);
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .accessToken(token)
                        .tokenType("Bearer")
                        .webUser(WebUserResponse
                                .builder()
                                .id(userData.get().getId())
                                .email(userData.get().getEmail())
                                .firstName(userData.get().getFirstName())
                                .lastName(userData.get().getLastName())
                                .imageUrl(userData.get().getImageUrl())
                                .emailVerified(userData.get().getEmailVerified())
                                .provider(userData.get().getProvider())
                                .providerId(userData.get().getProviderId())
                                .phoneNumber(userData.get().getPhoneNumber())
                                .phoneNumberVerified(userData.get().getPhoneNumberVerified())
                                .roles(authorities)
                                .build())
                        .build());
    }

    @Override
    public ResponseEntity<?> registerUser(@Valid SignUpRequest signUpRequest,BindingResult bindingResult) {
        bindingManager.bindingCheck(bindingResult);
        if (userRepository.existsByEmail(signUpRequest.getEmail())) throw new EmailAlreadyUsedException();
        if (roleRepository.existsByName(signUpRequest.getRoles().getName()) == null) throw new RoleNameNotExistException();

        // Creating user's account
        WebUser user = new WebUser();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        if(signUpRequest.getRoles().getName().toLowerCase().contains("admin")){
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
    public CompletableFuture<ResponseEntity<?>> checkUser(String accessToken) {
        return CompletableFuture.completedFuture(ResponseEntity.accepted()
                .body(new ApiResponse(true, accessToken)));
    }

    @Override
    public ResponseEntity<?> logout(String accessToken) {
        WebUser webUser = userValidator.getCurrentUser(accessToken);
        String token = tokenProvider.createLogoutToken(webUser);
        return token.equals(accessToken) ? ResponseEntity.accepted().body(new ApiResponse(false, "Logout not successful")) : ResponseEntity.accepted().body(new ApiResponse(true, "logout successfully."));
    }

    @Override
    public ResponseEntity<?> changePass( String accessToken, WebUserRequest request, BindingResult bindingResult) {
        WebUser webUser = userValidator.getCurrentUser(accessToken);
        bindingManager.bindingCheck(bindingResult);
        WebUser oldUser = userRepository.findById(webUser.getId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User id not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), webUser.getPassword())) throw new OldPasswordErrorException();

        oldUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(oldUser);
        WebUser userWithNewPassword = userRepository.findById(webUser.getId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User id not found"));
        tokenProvider.createLogoutToken(webUser);
        return !passwordEncoder.matches(request.getOldPassword(), userWithNewPassword.getPassword()) ? ResponseEntity.accepted().body(new ApiResponse(false, "Password did not changed.")) : ResponseEntity.accepted().body(new ApiResponse(true, "password changed successfully."));

    }
}

package com.auth.server.controller;


import com.auth.server.annotations.CurrentUser;
import com.auth.server.annotations.validator.JwtValidator;
import com.auth.server.api.AuthApi;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.enums.AuthProvider;
import com.auth.server.exception.BadRequestException;
import com.auth.server.exception.UserNotFoundException;
import com.auth.server.exception.constance.ExceptionConstance;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;

    private final WebUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RoleRepository roleRepository;

    private final JwtValidator jwtValidator;


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
                .webUser(WebUser
                        .builder()
                        .email(userData.get().getEmail())
                        .firstName(userData.get().getFirstName())
                        .lastName(userData.get().getLastName())
                        .imageUrl(userData.get().getImageUrl())
                        .emailVerified(userData.get().getEmailVerified())
                        .provider(userData.get().getProvider())
                        .providerId(userData.get().getProviderId())
                        .phoneNumber(userData.get().getPhoneNumber())
                        .phoneNumberVerified(userData.get().getPhoneNumberVerified())
                        .build())
                        .role(authorities)
                .build());
    }

    @Override
    public ResponseEntity<?> registerUser(@Valid SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        WebUser user = new WebUser();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        WebUser result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully."));
    }

    @Async
    @Override
    public ResponseEntity<?> checkUser(@CurrentUser String accessToken) {
        getCurrentUser(accessToken);
        return ResponseEntity.accepted()
                .body(new ApiResponse(true, accessToken));
    }

    public WebUser getCurrentUser(String userAccessController) {
        return userRepository
                .findById((Long.parseLong(
                        jwtValidator.validate(userAccessController))))
                .orElseThrow(() -> new UserNotFoundException(
                        ExceptionConstance.USER_NOT_FOUND_EXCEPTION));
    }
}

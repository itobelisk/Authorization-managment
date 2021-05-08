package com.auth.server.api;

import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest);

    @PostMapping("/check")
    ResponseEntity<?> checkUser(@RequestHeader(name = "Authorization") String accessToken);
}

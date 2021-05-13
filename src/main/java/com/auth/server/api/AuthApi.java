package com.auth.server.api;

import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.entity.webuser.response.WebUserResponse;
import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                   BindingResult bindingResult);

    @PostMapping("/check")
    CompletableFuture<ResponseEntity<?>> checkUser(@RequestHeader(name = "Authorization") String accessToken);

    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String accessToken);

    @PutMapping("/admin/change/password")
    ResponseEntity<?> changePass(@RequestHeader(name = "Authorization") String accessToken,
                                 @Valid @RequestBody WebUserRequest webUserRequest,
                                 BindingResult bindingResult);
}

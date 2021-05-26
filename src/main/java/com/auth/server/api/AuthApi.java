package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                   BindingResult bindingResult);

    @PostMapping("/check")
    CompletableFuture<ResponseEntity<?>> checkUser(@Valid @RequestHeader(name = "Authorization") String accessToken);

    @PostMapping("/logout")
    ResponseEntity<?> logout(@Valid @RequestHeader(name = "Authorization") String accessToken);

    @PutMapping("/admin/change/password")
    ResponseEntity<?> changePass(@Valid @RequestHeader(name = "Authorization") String accessToken,
                                 @Valid @RequestBody WebUserRequest webUserRequest,
                                 BindingResult bindingResult);

    @GetMapping("/user")
    ResponseEntity<?> userDetails(@Valid @RequestHeader(name = "Cookie") String accessCookie);
}

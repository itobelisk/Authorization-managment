package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.payload.LoginRequest;
import com.auth.server.payload.SignUpRequest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                       HttpServletResponse response);

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                   BindingResult bindingResult);

    @PostMapping("/check")
    CompletableFuture<ResponseEntity<?>> checkUser();

    @PostMapping("/logout")
    ResponseEntity<?> logout(HttpServletRequest request,
                             HttpServletResponse response);

    @PutMapping("/admin/change/password")
    ResponseEntity<?> changePass(HttpServletRequest request,HttpServletResponse response,
                                 @Valid @RequestBody WebUserRequest webUserRequest,
                                 BindingResult bindingResult);

    @GetMapping("/user")
    ResponseEntity<?> userDetails();
}

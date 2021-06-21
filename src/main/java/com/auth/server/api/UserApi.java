package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.webuser.request.WebUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/email")
    ResponseEntity<BaseResponse<?>> checkEmail(@RequestBody WebUserRequest webUserRequest);

    @GetMapping("/phone")
    ResponseEntity<BaseResponse<?>> checkPhone(@RequestBody WebUserRequest webUserRequest);
}

package com.auth.server.controller;

import com.auth.server.api.UserApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.base.BaseResponseMessage;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.entity.webuser.request.WebUserRequest;
import com.auth.server.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserUtils userUtils;

    @Override
    public ResponseEntity<BaseResponseMessage<?>> checkEmail(WebUserRequest webUserRequest) {
        WebUser webUser = userUtils.getUserId();
        BaseResponseMessage<?> response;
        boolean isEmail = webUser.getEmail().equals(webUserRequest.getEmail());
        response = checkEmailTest(isEmail);
        return new ResponseEntity<>(response,response.getStatus());
    }

    private BaseResponseMessage<?> checkEmailTest(Boolean check) {
        return check ?
                new BaseResponseMessage<>(new Date(), true,HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value(), "You dont need verification.",check):
                new BaseResponseMessage<>(new Date(), false, HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"You need to repass verification.",check);
    }

    @Override
    public ResponseEntity<BaseResponseMessage<?>> checkPhone(WebUserRequest webUserRequest) {
       WebUser webUser = userUtils.getUserId();
        BaseResponseMessage<?> response;
        boolean isPhoneNumber = webUser.getPhoneNumber().equals(webUserRequest.getPhoneNumber());
        response = checkEmailTest(isPhoneNumber);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}

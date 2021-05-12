package com.auth.server.exception.base;

import com.auth.server.base.BaseResponse;
import com.auth.server.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerManager {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> UserNotFoundException(UserNotFoundException userNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,userNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(NotAuthorizedUserAccessException.class)
    public ResponseEntity<BaseResponse<?>> NotAuthorizedUserAccessException(NotAuthorizedUserAccessException notAuthorizedUserAccessException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,notAuthorizedUserAccessException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(RoleAlreadyCreatedException.class)
    public ResponseEntity<BaseResponse<?>> RoleAlreadyCreatedException(RoleAlreadyCreatedException roleAlreadyCreatedException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleAlreadyCreatedException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(BadRequestsException.class)
    public ResponseEntity<BaseResponse<?>> BadRequestException(BadRequestsException badRequestException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,badRequestException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
    }
    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> RoleNameNotFoundException(RoleNameNotFoundException roleNameNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleNameNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.ACCEPTED);
    }
}

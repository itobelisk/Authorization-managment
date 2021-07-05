package com.auth.server.exception.base;

import com.auth.server.base.BaseResponse;
import com.auth.server.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandlerManager {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> UserNotFoundException(UserNotFoundException userNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,userNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(NotAuthorizedUserAccessException.class)
    public ResponseEntity<BaseResponse<?>> NotAuthorizedUserAccessException(NotAuthorizedUserAccessException notAuthorizedUserAccessException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,notAuthorizedUserAccessException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(RoleAlreadyCreatedException.class)
    public ResponseEntity<BaseResponse<?>> RoleAlreadyCreatedException(RoleAlreadyCreatedException roleAlreadyCreatedException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleAlreadyCreatedException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BadRequestsException.class)
    public ResponseEntity<BaseResponse<?>> BadRequestException(BadRequestsException badRequestException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,badRequestException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> RoleNameNotFoundException(RoleNameNotFoundException roleNameNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleNameNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @ExceptionHandler(OldPasswordErrorException.class)
    public ResponseEntity<BaseResponse<?>> OldPasswordErrorException(OldPasswordErrorException oldPasswordErrorException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,oldPasswordErrorException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<?>> AuthenticationException(AuthenticationException authenticationException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,authenticationException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @ExceptionHandler(PasswordRulesException.class)
    public ResponseEntity<BaseResponse<?>> PasswordRulesException(PasswordRulesException passwordRulesException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,passwordRulesException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<BaseResponse<?>> EmailAlreadyUsedException(EmailAlreadyUsedException emailAlreadyUsedException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,emailAlreadyUsedException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(AdminCanNotBeDeleteException.class)
    public ResponseEntity<BaseResponse<?>> AdminCanNotBeDeleteException(AdminCanNotBeDeleteException adminCanNotBeDeleteException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,adminCanNotBeDeleteException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(RoleIdNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> RoleIdNotFoundException(RoleIdNotFoundException roleIdNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleIdNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(RoleNameNotExistException.class)
    public ResponseEntity<BaseResponse<?>> RoleNameNotExistException(RoleNameNotExistException roleNameNotExistException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,roleNameNotExistException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(AdminCanNotBeUpdateException.class)
    public ResponseEntity<BaseResponse<?>> AdminCanNotBeUpdateException(AdminCanNotBeUpdateException adminCanNotBeUpdateException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,adminCanNotBeUpdateException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(CookiesNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> CookiesNotFoundException(CookiesNotFoundException cookiesNotFoundException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,cookiesNotFoundException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<?>> IllegalArgumentException(IllegalArgumentException illegalArgumentException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.BAD_REQUEST,illegalArgumentException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(UnAuthorizesAccessException.class)
    public ResponseEntity<BaseResponse<?>> UnAuthorizesAccessException(UnAuthorizesAccessException authorizesAccessException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.UNAUTHORIZED,authorizesAccessException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicatedPositionException.class)
    public ResponseEntity<BaseResponse<?>> DuplicatedPositionException(DuplicatedPositionException duplicatedPositionException){
        BaseResponse<?> baseResponse = new BaseResponse<>(new Date(),false,HttpStatus.UNAUTHORIZED,duplicatedPositionException.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}

package com.auth.server.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userNotFoundException) {
        super("User not registered exception.");
    }

}

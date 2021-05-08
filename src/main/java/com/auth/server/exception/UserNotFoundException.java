package com.auth.server.exception;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userNotFoundException) {
        super("User not registered exception.");
    }

}

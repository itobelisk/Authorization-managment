package com.auth.server.exception;

public class NotAuthorizedUserAccessException extends RuntimeException{

    public NotAuthorizedUserAccessException() {
        super("User not authorized.");
    }
}

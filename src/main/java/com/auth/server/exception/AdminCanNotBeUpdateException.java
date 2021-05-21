package com.auth.server.exception;

public class AdminCanNotBeUpdateException extends RuntimeException{
    public AdminCanNotBeUpdateException() {
        super("Sorry Admin role can not be updated.");
    }
}

package com.auth.server.exception;

public class AdminCanNotBeDeleteException extends RuntimeException{
    public AdminCanNotBeDeleteException() {
        super("Admin Can not be delete.");
    }
}

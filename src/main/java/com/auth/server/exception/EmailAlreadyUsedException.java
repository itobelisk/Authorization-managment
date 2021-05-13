package com.auth.server.exception;

public class EmailAlreadyUsedException extends RuntimeException{
    public EmailAlreadyUsedException() {
        super("Email address already registered.");
    }
}

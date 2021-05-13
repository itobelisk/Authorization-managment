package com.auth.server.exception;

public class OldPasswordErrorException extends RuntimeException{
    public OldPasswordErrorException() {
        super("Old password not matches.");
    }
}
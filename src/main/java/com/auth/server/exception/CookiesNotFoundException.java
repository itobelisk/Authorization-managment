package com.auth.server.exception;

public class CookiesNotFoundException extends RuntimeException{
    public CookiesNotFoundException() {
        super("Cookie not found.");
    }
}

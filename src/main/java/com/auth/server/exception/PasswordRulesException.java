package com.auth.server.exception;

public class PasswordRulesException extends RuntimeException{
    public PasswordRulesException() {
        super("Password rules are missing, please insure that you have more than 8 characters, uppercase and symbol your password.");
    }
}

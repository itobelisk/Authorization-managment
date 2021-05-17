package com.auth.server.exception;

public class RoleIdNotFoundException extends RuntimeException{
    public RoleIdNotFoundException() {
        super("Role id not found.");
    }
}

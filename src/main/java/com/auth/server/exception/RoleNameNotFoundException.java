package com.auth.server.exception;

public class RoleNameNotFoundException extends RuntimeException{
    public RoleNameNotFoundException() {
        super("Role not found exception.");
    }
}

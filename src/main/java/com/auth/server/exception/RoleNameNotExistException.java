package com.auth.server.exception;

public class RoleNameNotExistException extends RuntimeException{
    public RoleNameNotExistException() {
        super("Role name not found.");
    }
}

package com.auth.server.exception;

public class RoleAlreadyCreatedException extends RuntimeException{
    public RoleAlreadyCreatedException() {
        super("Already found, please try another name.");
    }
}

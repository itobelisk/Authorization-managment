package com.auth.server.exception;

public class DuplicatedPositionException extends RuntimeException{

    public DuplicatedPositionException() {
        super("You have duplicated values in the request.");
    }
}

package com.footman.footballmanager.exception;

public class InvalidPlayerStateException extends RuntimeException {
    public InvalidPlayerStateException(String message) {
        super(message);
    }
}
package com.footman.footballmanager.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String field, Object value) {
        super(String.format("%s with %s %s not found", entity, field, value));
    }
}

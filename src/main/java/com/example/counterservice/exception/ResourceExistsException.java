package com.example.counterservice.exception;

/**
 * Exception used when a resource already exists
 */
public class ResourceExistsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public ResourceExistsException(String message) {
        super(message);
    }
}
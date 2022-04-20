package com.tomaszjanik.zadanie.exceptions;

public class InvalidMappingException extends RuntimeException {
    public InvalidMappingException(Exception exception) {
        super(exception.getMessage(), exception.getCause());
    }

    public InvalidMappingException(String message) {
        super(message);
    }
}

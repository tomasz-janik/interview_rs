package com.tomaszjanik.zadanie.exceptions;

public class NotEnoughMappingException extends RuntimeException {
    public NotEnoughMappingException(String message) {
        super(message);
    }
}
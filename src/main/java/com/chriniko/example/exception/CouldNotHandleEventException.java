package com.chriniko.example.exception;

public class CouldNotHandleEventException extends RuntimeException {
    public CouldNotHandleEventException(String message) {
        super(message);
    }
}

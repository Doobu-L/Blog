package com.example.blog.exception;

// 400 error
public class InvalidDataException extends DomainException {
    public InvalidDataException(String message, Throwable error) {
        super(message, error);
    }
}

package com.example.blog.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainException extends RuntimeException {
    private final String message;
    private final Throwable error;
}

package com.example.blog.handler;

import com.example.blog.exception.KakaoBlogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KakaoBlogException.class)
    public ResponseEntity<KakaoBlogException> handleKakaoBlogApiException(KakaoBlogException ex) {
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


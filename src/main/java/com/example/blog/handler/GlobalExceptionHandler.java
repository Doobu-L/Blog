package com.example.blog.handler;

import com.example.blog.dto.BaseResponse;
import com.example.blog.exception.DomainException;
import com.example.blog.exception.KakaoBlogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity handleException(DomainException ex) {
        return new ResponseEntity<>(new BaseResponse(HttpStatus.OK, ex.getMessage()),HttpStatus.OK);
    }

    @ExceptionHandler(KakaoBlogException.class)
    public ResponseEntity handleException(KakaoBlogException ex) {
        return new ResponseEntity<>(new BaseResponse(HttpStatus.valueOf(ex.getErrorCode()), ex.getErrorMessage()),HttpStatus.OK);
    }
}


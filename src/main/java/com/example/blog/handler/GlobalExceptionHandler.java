package com.example.blog.handler;

import com.example.blog.dto.BaseResponse;
import com.example.blog.exception.DomainException;
import com.example.blog.exception.InvalidDataException;
import com.example.blog.exception.KakaoBlogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public BaseResponse handleKakaoBlogApiException(InvalidDataException ex) {
        return new BaseResponse(HttpStatus.BAD_REQUEST, ex);
    }
}


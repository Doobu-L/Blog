package com.example.blog.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
public class BaseResponse {
    private final HttpStatus status;
    private final String message;

}

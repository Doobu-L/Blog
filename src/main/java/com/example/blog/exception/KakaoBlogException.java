package com.example.blog.exception;

import com.example.blog.dto.feign.KakaoErrorResponse;
import lombok.Getter;

@Getter
public class KakaoBlogException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public KakaoBlogException(int errorCode,KakaoErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorCode = errorCode;
        this.errorMessage = errorResponse.getMessage();
    }
}

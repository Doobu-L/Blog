package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoErrorResponse {
    @JsonProperty("errorType")
    private String errorType;
    @JsonProperty("message")
    private String message;

}

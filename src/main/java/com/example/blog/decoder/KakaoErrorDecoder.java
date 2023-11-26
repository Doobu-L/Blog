package com.example.blog.decoder;

import com.example.blog.dto.feign.KakaoErrorResponse;
import com.example.blog.exception.KakaoBlogException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;

@RequiredArgsConstructor
public class KakaoErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public KakaoBlogException decode(String methodKey, Response response) {
        KakaoErrorResponse kakaoError = null;
        try {
            kakaoError = objectMapper.readValue(response.body().asInputStream(), KakaoErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new KakaoBlogException(response.status(),kakaoError);
    }
}
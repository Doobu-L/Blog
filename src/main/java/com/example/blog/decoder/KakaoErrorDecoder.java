package com.example.blog.decoder;

import com.example.blog.dto.feign.KakaoErrorResponse;
import com.example.blog.exception.KakaoBlogException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class KakaoErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoErrorResponse kakaoError = objectMapper.readValue(response.body().asInputStream(), KakaoErrorResponse.class);
        return new KakaoBlogException(response.status(),kakaoError);
    }
}
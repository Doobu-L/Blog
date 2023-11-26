package com.example.blog.config;

import com.example.blog.decoder.KakaoErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${third-part.kakao.rest-api-key}")
    private String kakaoRestApiKey;
    @Value("${third-part.naver.client-id}")
    private String naverClientId;
    @Value("${third-part.naver.secret-key}")
    private String naverSecretKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                if ("kakaoBlogClient".equals(template.feignTarget().name())) {
                    template.header("Authorization", "KakaoAK " + kakaoRestApiKey);
                }
                if ("naverBlogClient".equals(template.feignTarget().name())) {
                    template.header("X-Naver-Client-Id", naverClientId);
                    template.header("X-Naver-Client-Secret", naverSecretKey);
                }
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new KakaoErrorDecoder(objectMapper);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
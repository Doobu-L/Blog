package com.example.blog.feignclient;

import com.example.blog.config.FeignConfig;
import com.example.blog.dto.feign.KakaoBlogFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name = "kakaoBlogClient", url = "${third-part.kakao.url}" ,configuration = FeignConfig.class)
public interface KakaoBlogClient  {

    @GetMapping(value = "/v2/search/blog")
    ResponseEntity<KakaoBlogFeignResponse> searchBlogs(
            @RequestParam("query") String query,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort
            );
}
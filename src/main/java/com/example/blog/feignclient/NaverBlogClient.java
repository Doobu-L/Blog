package com.example.blog.feignclient;

import com.example.blog.config.FeignConfig;
import com.example.blog.dto.feign.NaverBlogFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverBlogClient", url = "${third-part.naver.url}" ,configuration = FeignConfig.class)
public interface NaverBlogClient {

    @GetMapping(value = "/v1/search/blog")
    ResponseEntity<NaverBlogFeignResponse> searchBlogs(
            @RequestParam("query") String query,
            @RequestParam("start") int start,
            @RequestParam("display") int display,
            @RequestParam("sort") String sort
    );
}
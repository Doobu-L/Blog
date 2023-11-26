package com.example.blog.feignclient;

import com.example.blog.dto.BlogResponse;
import com.example.blog.dto.feign.NaverBlogFeignResponse;
import com.example.blog.enums.BlogSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class NaverBlogAdaper {
    private final NaverBlogClient naverBlogClient;

    public BlogResponse searchBlog(String query, int page, int size, BlogSortType sort){
        ResponseEntity<NaverBlogFeignResponse> feignResponse = naverBlogClient.searchBlogs(query,page,size,sort.getNaverSortKey());
        if(feignResponse == null)
            return null;
        return new BlogResponse(Objects.requireNonNull(feignResponse.getBody()));
    }

}

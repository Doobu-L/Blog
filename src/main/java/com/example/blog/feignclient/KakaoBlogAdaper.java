package com.example.blog.feignclient;

import com.example.blog.dto.BlogResponse;
import com.example.blog.dto.feign.KakaoBlogFeignResponse;
import com.example.blog.enums.BlogSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KakaoBlogAdaper {
    private final KakaoBlogClient kakaoBlogClient;

    public BlogResponse searchBlog(String query, int page, int size, BlogSortType sort){
        ResponseEntity<KakaoBlogFeignResponse> feignResponse = kakaoBlogClient.searchBlogs(query,page,size,sort.getKakaoSortKey());
        if(feignResponse == null)
            return null;
        return new BlogResponse(Objects.requireNonNull(feignResponse.getBody()));
    }

}

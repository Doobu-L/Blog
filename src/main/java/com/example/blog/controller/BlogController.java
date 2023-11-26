package com.example.blog.controller;

import com.example.blog.dto.BlogResponse;
import com.example.blog.enums.BlogSortType;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping(value = "/search")
    public ResponseEntity<BlogResponse> getBlogList(
            @RequestParam("query") String query,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") BlogSortType sort){
        return ResponseEntity.ok(blogService.searchBlogs(query,page,size,sort));
    }
}

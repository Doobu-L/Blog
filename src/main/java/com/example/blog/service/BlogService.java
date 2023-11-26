package com.example.blog.service;

import com.example.blog.dto.BlogResponse;
import com.example.blog.enums.BlogSortType;

public interface BlogService {

    BlogResponse searchBlogs(String query, int page, int size, BlogSortType sort);
}

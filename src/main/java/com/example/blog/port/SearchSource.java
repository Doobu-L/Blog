package com.example.blog.port;

import com.example.blog.domain.SearchResult;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;

public interface SearchSource {
    SearchResult search(String query, int page, int size, BlogSortType sort) throws SearchSourceException;
}

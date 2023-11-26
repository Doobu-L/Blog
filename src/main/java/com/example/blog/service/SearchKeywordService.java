package com.example.blog.service;

import com.example.blog.dto.SearchKeywordResponse;

import java.util.List;

public interface SearchKeywordService {

    List<SearchKeywordResponse> getTop10();
}

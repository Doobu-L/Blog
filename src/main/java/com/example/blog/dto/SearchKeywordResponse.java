package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchKeywordResponse {
    private final String keyword;
    private final int count;
}

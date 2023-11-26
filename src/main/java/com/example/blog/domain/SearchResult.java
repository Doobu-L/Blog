package com.example.blog.domain;

import java.util.List;

public record SearchResult(
        Integer totalCount,
        Integer page,
        Integer size,
        Boolean isEnd,
        List<SearchResultItem> items
) {
}
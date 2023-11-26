package com.example.blog.domain;

public record SearchResultItem(
        String title,
        String description,
        String bloggerName,
        String bloggerLink,
        String thumbnail,
        String postDate,
        String url
) {
}

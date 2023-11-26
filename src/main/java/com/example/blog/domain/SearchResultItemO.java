package com.example.blog.domain;

public record SearchResultItemO(
        String title,
        String description,
        String bloggerName,
        String bloggerLink,
        String thumbnail,
        String postDate,
        String url
) {
}

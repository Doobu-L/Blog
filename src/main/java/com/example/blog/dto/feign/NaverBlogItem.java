package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverBlogItem(
        @JsonProperty("title") String title,
        @JsonProperty("link") String url,
        @JsonProperty("description") String description,
        @JsonProperty("bloggername") String bloggername,
        @JsonProperty("bloggerlink") String bloggerlink,
        @JsonProperty("postdate") String postdate
){}
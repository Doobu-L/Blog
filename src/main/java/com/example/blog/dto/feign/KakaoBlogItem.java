package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoBlogItem(
        @JsonProperty("title") String title,
        @JsonProperty("contents") String description,
        @JsonProperty("blogname") String bloggername,
        @JsonProperty("url") String bloggerlink,
        @JsonProperty("thumbnail") String thumbnail,
        @JsonProperty("datetime") String postdate
) {
}
package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoResultItem {


    @JsonProperty("title")
    private final String title;
    @JsonProperty("contents")
    private final String description;
    @JsonProperty("blogname")
    private final String bloggerName;
    @JsonProperty("url")
    private final String bloggerLink;
    @JsonProperty("thumbnail")
    private final String thumbnail;
    @JsonProperty("datetime")
    private final String postDate;

}
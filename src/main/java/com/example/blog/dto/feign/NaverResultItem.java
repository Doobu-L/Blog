package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverResultItem {
    @JsonProperty("title")
    private final String title;
    @JsonProperty("link")
    private final String url;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("bloggername")
    private final String bloggerName;
    @JsonProperty("bloggerlink")
    private final String bloggerLink;
    @JsonProperty("postdate")
    private final String postDate;
}

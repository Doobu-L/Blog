package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverResultItem {
    @JsonProperty("title")
    String title;
    @JsonProperty("link")
    String url;
    @JsonProperty("description")
    String description;
    @JsonProperty("bloggername")
    String bloggerName;
    @JsonProperty("bloggerlink")
    String bloggerLink;
    @JsonProperty("postdate")
    String postDate;
}

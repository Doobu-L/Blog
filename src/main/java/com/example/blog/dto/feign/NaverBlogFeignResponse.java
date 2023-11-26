package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class NaverBlogFeignResponse extends BlogFeignResponse {
    @JsonProperty("total")
    private int total;
    @JsonProperty("start")
    private int start;
    @JsonProperty("display")
    private int display;

    @JsonProperty("items")
    private List<NaverBlogItem> items;
}

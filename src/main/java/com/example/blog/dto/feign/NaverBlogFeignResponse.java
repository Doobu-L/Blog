package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NaverBlogFeignResponse {
    @JsonProperty("total")
    private final Integer total;
    @JsonProperty("start")
    private final Integer start;
    @JsonProperty("display")
    private final Integer display;

    @JsonProperty("items")
    private final List<NaverResultItem> items;
}

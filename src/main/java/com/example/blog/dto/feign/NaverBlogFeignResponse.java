package com.example.blog.dto.feign;

import com.example.blog.domain.SearchResultItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class NaverBlogFeignResponse {
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("display")
    private Integer display;

    @JsonProperty("items")
    private List<NaverResultItem> items;
}

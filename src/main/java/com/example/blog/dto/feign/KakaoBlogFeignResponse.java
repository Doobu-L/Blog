package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class KakaoBlogFeignResponse extends BlogFeignResponse {

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("documents")
    private List<KakaoBlogItem> items;

    @Getter
    public static class Meta {
        @JsonProperty("total_count")
        private int totalCount;

        @JsonProperty("pageable_count")
        private int pageableCount;

        @JsonProperty("is_end")
        private boolean isEnd;

    }
}

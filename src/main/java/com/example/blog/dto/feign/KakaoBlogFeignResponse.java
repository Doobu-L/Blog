package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class KakaoBlogFeignResponse {

    @JsonProperty("meta")
    private final Meta meta;
    @JsonProperty("documents")
    private final List<KakaoResultItem> items;

    @Getter
    @RequiredArgsConstructor
    public static class Meta {
        @JsonProperty("total_count")
        private final int totalCount;

        @JsonProperty("pageable_count")
        private final int pageableCount;

        @JsonProperty("is_end")
        private final boolean isEnd;

    }
}

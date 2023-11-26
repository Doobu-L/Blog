package com.example.blog.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class KakaoBlogFeignResponse {

    @JsonProperty("meta")
    Meta meta;
    @JsonProperty("documents")
    List<KakaoResultItem> items;

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

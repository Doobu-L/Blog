package com.example.blog.port;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

public interface SearchKeywordRepository {
    void incrementSearchCount(String keyword);

    Set<ZSetOperations.TypedTuple<String>> getTop10();
}

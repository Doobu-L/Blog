package com.example.blog.adaptor;

import com.example.blog.port.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class SearchKeywordRedisRepository implements SearchKeywordRepository {

    private static final long EXPIRATION_TIME_IN_MINUTES = 60;


    private final StringRedisTemplate redisTemplate;

    public void incrementSearchCount(String keyword) {
        redisTemplate.opsForZSet().incrementScore("KEYWORD_RANK", keyword, 1);
        String memberKey = "KEYWORD_RANK:" + keyword;
        redisTemplate.expire(memberKey, EXPIRATION_TIME_IN_MINUTES, TimeUnit.MINUTES);
    }

    public Set<ZSetOperations.TypedTuple<String>> getTop10() {
        return redisTemplate.opsForZSet().reverseRangeWithScores("KEYWORD_RANK", 0, 9);
    }
}

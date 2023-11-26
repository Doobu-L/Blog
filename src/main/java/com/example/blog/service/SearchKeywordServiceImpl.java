package com.example.blog.service;

import com.example.blog.dto.SearchKeywordResponse;
import com.example.blog.repository.SearchKeywordRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchKeywordServiceImpl implements SearchKeywordService {

    private final SearchKeywordRedisRepository searchKeywordRedisRepository;

    @Override
    public List<SearchKeywordResponse> getTop10() {
        return searchKeywordRedisRepository.getTop10()
                .stream()
                .map(it->new SearchKeywordResponse(it.getValue(),it.getScore().intValue()))
                .collect(Collectors.toList());
    }
}

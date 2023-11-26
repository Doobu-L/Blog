package com.example.blog.domain;

import com.example.blog.dto.SearchKeywordResponse;
import com.example.blog.port.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchKeywordService {

    private final SearchKeywordRepository searchKeywordRepository;

    public List<SearchKeywordResponse> getTop10() {
        return searchKeywordRepository.getTop10()
                .stream()
                .map(it -> new SearchKeywordResponse(it.getValue(), Objects.requireNonNull(it.getScore()).intValue()))
                .collect(Collectors.toList());
    }
}

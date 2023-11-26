package com.example.blog.adaptor;

import com.example.blog.domain.SearchKeywordService;
import com.example.blog.dto.SearchKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
public class SearchKeywordRankController {

    private final SearchKeywordService searchKeywordService;

    @GetMapping("/top10")
    public List<SearchKeywordResponse> getTop10Keyword() {
        return searchKeywordService.getTop10();
    }
}

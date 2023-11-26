package com.example.blog.controller;

import com.example.blog.service.SearchKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
public class SearchKeywordRankController {

    private final SearchKeywordService searchKeywordService;

    @GetMapping("/top10")
    public ResponseEntity getTop10Keyword(){
        return ResponseEntity.ok(searchKeywordService.getTop10());
    }
}

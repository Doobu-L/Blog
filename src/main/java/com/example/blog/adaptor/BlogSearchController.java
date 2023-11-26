package com.example.blog.adaptor;

import com.example.blog.domain.SearchResult;
import com.example.blog.enums.BlogSortType;
import com.example.blog.domain.BlogSearchService;
import com.example.blog.exception.DomainException;
import com.example.blog.exception.KakaoBlogException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blogs")
public class BlogSearchController {

    private final BlogSearchService blogService;

    @GetMapping(value = "/search")
    public SearchResult getBlogList(
            @RequestParam("query") String query,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") BlogSortType sort) throws DomainException, KakaoBlogException {
        return blogService.search(query,page,size,sort);
    }
}

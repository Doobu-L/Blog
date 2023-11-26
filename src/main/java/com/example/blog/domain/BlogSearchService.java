package com.example.blog.domain;

import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.port.SearchKeywordRepository;
import com.example.blog.port.SearchSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final List<SearchSource> searchSources;
    private final SearchKeywordRepository searchKeywordRepository;

    public SearchResult search(String query, int page, int size, BlogSortType sort) {

        SearchResult result = null;
        for (SearchSource searchSource : searchSources) {
            try {
                result = searchSource.search(query, page, size, sort);
                break;
            } catch (SearchSourceException sse) {
                continue;
            }
        }

        searchKeywordRepository.incrementSearchCount(query);
        return result;

    }
}

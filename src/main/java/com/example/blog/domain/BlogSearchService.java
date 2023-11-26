package com.example.blog.domain;

import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.DomainException;
import com.example.blog.exception.KakaoBlogException;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.port.SearchKeywordRepository;
import com.example.blog.port.SearchSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSearchService{
    private final List<SearchSource> searchSources;
    private final SearchKeywordRepository searchKeywordRepository;

    public SearchResult search(String query, int page, int size, BlogSortType sort) throws DomainException , KakaoBlogException {
        validationParameter(query,page,size);
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

    private void validationParameter(String query, int page, int size) {
        if(query.length() > 100)
            throw new DomainException("INVALID_PARAMETER:query");
        if(page==0)
            throw new DomainException("INVALID_PARAMETER:page");
        if(size>100)
            throw new DomainException("INVALID_PARAMETER:size");
    }
}

package com.example.blog.adaptor.searchsource;

import com.example.blog.domain.SearchResult;
import com.example.blog.domain.SearchResultItem;
import com.example.blog.dto.feign.NaverBlogFeignResponse;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.feignclient.NaverBlogClient;
import com.example.blog.port.SearchSource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Order(2)
public class NaverSearchSource implements SearchSource {
    private final NaverBlogClient naverBlogClient;

    @Override
    public SearchResult search(String query, int page, int size, BlogSortType sort) throws SearchSourceException {
        ResponseEntity<NaverBlogFeignResponse> feignResponse = naverBlogClient.searchBlogs(query, page, size, sort.getNaverSortKey());

        if (feignResponse.getStatusCode().isError()) {
            throw new SearchSourceException();
        }

        NaverBlogFeignResponse body = feignResponse.getBody();
        if (body == null) return null;

        List<SearchResultItem> items = body.getItems().stream().map(i -> new SearchResultItem(
                i.getTitle(),
                i.getDescription(),
                i.getBloggerName(),
                i.getBloggerLink(),
                null,
                i.getPostDate(),
                i.getUrl()
        )).collect(Collectors.toList());

        return new SearchResult(
                body.getTotal(),
                page,
                size,
                false,
                items
        );
    }
}

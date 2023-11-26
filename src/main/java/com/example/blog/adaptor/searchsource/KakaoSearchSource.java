package com.example.blog.adaptor.searchsource;

import com.example.blog.domain.SearchResult;
import com.example.blog.domain.SearchResultItem;
import com.example.blog.dto.feign.KakaoBlogFeignResponse;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.feignclient.KakaoBlogClient;
import com.example.blog.port.SearchSource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class KakaoSearchSource implements SearchSource {
    private final KakaoBlogClient kakaoBlogClient;

    @Override
    public SearchResult search(String query, int page, int size, BlogSortType sort) throws SearchSourceException {
        ResponseEntity<KakaoBlogFeignResponse> response = kakaoBlogClient.searchBlogs(query, page, size, sort.getKakaoSortKey());

        if (response.getStatusCode().isError()) {
            throw new SearchSourceException();
        }

        if (response == null)
            return null;

        KakaoBlogFeignResponse body = response.getBody();

        if (body == null) return null;
        List<SearchResultItem> items = body.getItems().stream().map(i ->
                new SearchResultItem(
                        i.getTitle(),
                        i.getDescription(),
                        i.getBloggerName(),
                        i.getBloggerLink(),
                        i.getThumbnail(),
                        i.getPostDate(),
                        null
                )
        ).toList();
        return new SearchResult(
                body.getMeta().getTotalCount(),
                page,
                size,
                false,
                items
        );
    }
}

package com.example.blog.adaptor.searchsource;

import com.example.blog.domain.SearchResult;
import com.example.blog.domain.SearchResultItem;
import com.example.blog.dto.feign.NaverBlogFeignResponse;
import com.example.blog.dto.feign.NaverResultItem;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.feignclient.NaverBlogClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("(외부API)_네이버_소스_검색_테스트")
class NaverSearchSourceTest {

    @Mock
    private NaverBlogClient naverBlogClient;

    @InjectMocks
    private NaverSearchSource naverSearchSource;

    @Test
    @DisplayName("(외부API)_네이버_블로그조회_조회_성공_후_SearchResult로_생성_후_리턴_성공")
    void search_SuccessfulSearch_ReturnsSearchResult() throws SearchSourceException {
        String query = "test";
        int page = 1;
        int size = 10;
        BlogSortType sort = BlogSortType.ACCURACY;

        List<NaverResultItem> mockResultItems = List.of(new NaverResultItem("test","test","test","test","test","0000-00-00"));
        NaverBlogFeignResponse mockResponse = new NaverBlogFeignResponse(100,1,10,mockResultItems);

        ResponseEntity<NaverBlogFeignResponse> mockFeignResponse = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(naverBlogClient.searchBlogs(query,page,size, sort.getNaverSortKey())).thenReturn(mockFeignResponse);

        SearchResult result = naverSearchSource.search(query,page,size,sort);

        assertEquals(100, result.totalCount());
        assertEquals(1, result.page());
        assertEquals(10, result.size());
        assertEquals(false, result.isEnd());
        assertEquals(1, result.items().size());

        SearchResultItem resultItem = result.items().get(0);
        assertEquals("test", resultItem.title());
        assertEquals("test", resultItem.description());
        assertEquals("test", resultItem.bloggerName());
        assertEquals("test", resultItem.bloggerLink());
        assertEquals("0000-00-00", resultItem.postDate());
        assertEquals("test", resultItem.url());
    }

}
package com.example.blog.adaptor.searchsource;

import com.example.blog.domain.SearchResult;
import com.example.blog.dto.feign.KakaoBlogFeignResponse;
import com.example.blog.dto.feign.KakaoResultItem;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.feignclient.KakaoBlogClient;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("(외부API)_카카오_소스_검색_테스트")
public class KakaoSearchSourceTest {

    @Mock
    private KakaoBlogClient kakaoBlogClient;

    @InjectMocks
    private KakaoSearchSource kakaoSearchSource;

    @Test
    @DisplayName("(외부API)_카카오_블로그조회_요청_후_SearchResult로_생성_후_리턴_성공")
    void search_SuccessfulSearch_ReturnsSearchResult() throws SearchSourceException {
        String query = "test";
        int page = 1;
        int size = 10;
        BlogSortType sort = BlogSortType.ACCURACY;

        KakaoBlogFeignResponse.Meta mockMeta = new KakaoBlogFeignResponse.Meta(100,2,false);
        List<KakaoResultItem> mockItems = List.of(new KakaoResultItem("test","test","test","test","test","0000-00-00"));
        KakaoBlogFeignResponse mockResponse = new KakaoBlogFeignResponse(mockMeta,mockItems);


        ResponseEntity<KakaoBlogFeignResponse> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(kakaoBlogClient.searchBlogs(query,page,size,sort.getKakaoSortKey()))
                .thenReturn(mockResponseEntity);

        SearchResult result = kakaoSearchSource.search(query, page, size, sort);

        verify(kakaoBlogClient, times(1)).searchBlogs(query, page, size, sort.getKakaoSortKey());
        assertNotNull(result);
        assertEquals(1, result.items().size());
        assertEquals(100, result.totalCount());
        assertEquals(1, result.page());
        assertEquals(10, result.size());
        assertFalse(result.isEnd());
    }

    @Test
    @DisplayName("(외부API)_카카오_블로그조회_요청_실패_후_Exception_throw_성공")
    void search_ErrorInSearch_ThrowsSearchSourceException() {
        String query = "test";
        int page = 1;
        int size = 10;
        BlogSortType sort = BlogSortType.ACCURACY;

        ResponseEntity<KakaoBlogFeignResponse> mockErrorResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(kakaoBlogClient.searchBlogs(any(), anyInt(), anyInt(), any()))
                .thenReturn(mockErrorResponseEntity);

        assertThrows(SearchSourceException.class, () -> kakaoSearchSource.search(query, page, size, sort));

        verify(kakaoBlogClient, times(1)).searchBlogs(query, page, size, sort.getKakaoSortKey());
    }
}

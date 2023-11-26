package com.example.blog.domain;

import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.DomainException;
import com.example.blog.exception.SearchSourceException;
import com.example.blog.port.SearchKeywordRepository;
import com.example.blog.port.SearchSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("블로그_검색_서비스_테스트")
class BlogSearchServiceTest {

        @Mock
        private SearchSource searchSource1;

        @Mock
        private SearchSource searchSource2;

        @Mock
        private List<SearchSource> searchSources;

        @Mock
        private SearchKeywordRepository searchKeywordRepository;

        @InjectMocks
        private BlogSearchService blogSearchService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            searchSources = List.of(searchSource1,searchSource2);
            blogSearchService = new BlogSearchService(searchSources,searchKeywordRepository);
        }

        @Test
        @DisplayName("SearchSource_1(kakao)가_정상동작시_성공")
        void search_SuccessfulSearchFromFirstSource_ReturnsResult() throws SearchSourceException {

            String query = "test";
            int page = 1;
            int size = 10;
            BlogSortType sort = BlogSortType.ACCURACY;

            SearchResult expectedResult = new SearchResult(100,1,0,false,List.of());

            when(searchSource1.search(query, page, size, sort)).thenReturn(expectedResult);

            SearchResult result = blogSearchService.search(query, page, size, sort);

            assertEquals(expectedResult, result);
            verify(searchSource1, times(1)).search(query, page, size, sort);
            verify(searchSource2, never()).search(query, page, size, sort);
            verify(searchKeywordRepository, times(1)).incrementSearchCount(any());
        }

        @Test
        @DisplayName("SearchSource_1(kakao)가_정상동작_하지_않는_경우_SearchSource_2(naver)_성공")
        void search_FallbackToSecondSource_ReturnsResult() throws SearchSourceException {
            String query = "test";
            int page = 1;
            int size = 10;
            BlogSortType sort = BlogSortType.ACCURACY;

            SearchResult expectedResult = new SearchResult(100,1,0,false,List.of());
            when(searchSource1.search(query, page, size, sort)).thenThrow(new SearchSourceException());
            when(searchSource2.search(query, page, size, sort)).thenReturn(expectedResult);

            SearchResult result = blogSearchService.search(query, page, size, sort);

            assertEquals(expectedResult, result);
            verify(searchSource1, times(1)).search(query, page, size, sort);
            verify(searchSource2, times(1)).search(query, page, size, sort);
            verify(searchKeywordRepository, times(1)).incrementSearchCount(query);
        }

    @Test
    @DisplayName("검색어_길이_유효성_테스트_throw_DomainException_성공")
    void validationParameter_QueryLengthExceedsLimit_ThrowsDomainException() {
        List<SearchSource> searchSources = Arrays.asList(mock(SearchSource.class));
        SearchKeywordRepository searchKeywordRepository = mock(SearchKeywordRepository.class);
        BlogSearchService blogSearchService = new BlogSearchService(searchSources, searchKeywordRepository);

        DomainException exception = assertThrows(DomainException.class, () -> {
            blogSearchService.search(
                    "testest4esttestest4esttestest4esttestest4esttes" +
                            "tetestest4esttestest4esttesttesttesttesttesttesttesttest", 1, 10, BlogSortType.ACCURACY);
        });

        assertEquals("INVALID_PARAMETER:query", exception.getMessage());
    }

    @Test
    @DisplayName("page_유효성_테스트_throw_DomainException_성공")
    void validationParameter_ZeroPage_ThrowsDomainException() {
        List<SearchSource> searchSources = Arrays.asList(mock(SearchSource.class));
        SearchKeywordRepository searchKeywordRepository = mock(SearchKeywordRepository.class);
        BlogSearchService blogSearchService = new BlogSearchService(searchSources, searchKeywordRepository);

        DomainException exception = assertThrows(DomainException.class, () -> {
            blogSearchService.search("test", 0, 10, BlogSortType.ACCURACY);
        });

        assertEquals("INVALID_PARAMETER:page", exception.getMessage());
    }

    @Test
    @DisplayName("size_유효성_테스트_throw_DomainException_성공")
    void validationParameter_SizeExceedsLimit_ThrowsDomainException() {
        List<SearchSource> searchSources = Arrays.asList(mock(SearchSource.class));
        SearchKeywordRepository searchKeywordRepository = mock(SearchKeywordRepository.class);
        BlogSearchService blogSearchService = new BlogSearchService(searchSources, searchKeywordRepository);

        DomainException exception = assertThrows(DomainException.class, () -> {
            blogSearchService.search("test", 1, 101, BlogSortType.ACCURACY);
        });

        assertEquals("INVALID_PARAMETER:size", exception.getMessage());
    }
}
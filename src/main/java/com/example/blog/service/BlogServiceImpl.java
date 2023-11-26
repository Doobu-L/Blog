package com.example.blog.service;

import com.example.blog.dto.BlogResponse;
import com.example.blog.enums.BlogSortType;
import com.example.blog.exception.KakaoBlogException;
import com.example.blog.feignclient.KakaoBlogAdaper;
import com.example.blog.feignclient.NaverBlogAdaper;
import com.example.blog.repository.SearchKeywordRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private final KakaoBlogAdaper kakaoBlogAdaper;
    private final NaverBlogAdaper naverBlogAdaper;
    private final SearchKeywordRedisRepository searchKeywordRedisRepository;

    private final Set<Integer> INTERNAL_SERVER_ERROR_CODE = Set.of(500,502,503,401);

    @Override
    public BlogResponse searchBlogs(String query, int page, int size , BlogSortType sort) {
            searchKeywordRedisRepository.incrementSearchCount(query);
            try {
                return kakaoBlogAdaper.searchBlog(query,page,size,sort);
            }catch (KakaoBlogException exception){
                if(INTERNAL_SERVER_ERROR_CODE.contains(exception.getErrorCode()))
                    return naverBlogAdaper.searchBlog(query, page, size,sort);
                else
                    throw exception;
            }
    }
}

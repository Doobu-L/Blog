package com.example.blog.dto;


import com.example.blog.dto.feign.KakaoBlogFeignResponse;
import com.example.blog.dto.feign.NaverBlogFeignResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BlogResponse {
    private int totalCount;
    private int page;
    private int size;
    private boolean isEnd;

    private List<BlogItemResponse> items;

    public BlogResponse(KakaoBlogFeignResponse in){
        this.totalCount = in.getMeta().getPageableCount();
        this.isEnd = in.getMeta().isEnd();
        this.items = in.getItems().stream().map(BlogItemResponse::new).collect(Collectors.toList());
    }
    public BlogResponse(NaverBlogFeignResponse in){
        this.totalCount = in.getTotal();
        this.page = in.getStart();
        this.size = in.getDisplay();
        this.isEnd = totalCount <= page*size;
        this.items = in.getItems().stream().map(BlogItemResponse::new).collect(Collectors.toList());
    }
    public void settingPagingData(int page,int size){
        this.page = page;
        this.size = size;
    }
}

package com.example.blog.dto;

import com.example.blog.dto.feign.KakaoBlogItem;
import com.example.blog.dto.feign.NaverBlogItem;
import lombok.Getter;

@Getter
public class BlogItemResponse {
    private final String title;
    private final String description;
    private final String bloggername;
    private final String bloggerlink;
    private final String thumbnail;
    private final String postdate;
    private final String url;

    public BlogItemResponse(KakaoBlogItem item){
        this.title = item.title();
        this.description = item.description();
        this.bloggername = item.bloggername();
        this.bloggerlink = item.bloggerlink();
        this.thumbnail = item.thumbnail();
        this.postdate = item.postdate();
        this.url = null;
    }
    public BlogItemResponse(NaverBlogItem item){
        this.title = item.title();
        this.description = item.description();
        this.bloggername = item.bloggername();
        this.bloggerlink = item.bloggerlink();
        this.thumbnail = null;
        this.postdate = item.postdate();
        this.url = item.url();
    }
}

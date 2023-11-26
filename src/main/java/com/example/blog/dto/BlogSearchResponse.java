package com.example.blog.dto;

import java.util.List;

public interface BlogSearchResponse {
    int getTotalCount();
    int getPage();
    int getSize();
    boolean isEnd();

    List<BlogItemSearchResponse> getItems();
}


/**
 *
 * config
 * adaptor
 *   RedisDomainRepository
 *   MySQLDomainRepository
 * port
 *   domainRepository
 * domain
 *   domainEntity
 *   domainService
 * router (controller)
 *   domainController
 *
 *
 *
 */
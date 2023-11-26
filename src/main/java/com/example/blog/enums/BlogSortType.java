package com.example.blog.enums;

public enum BlogSortType {
    ACCURACY("accuracy","sim"),
    RECENCY("recency","date");

    private final String kakaoSortKey;
    private final String naverSortKey;

    BlogSortType(String kakaoSortKey, String naverSortKey){
        this.kakaoSortKey = kakaoSortKey;
        this.naverSortKey = naverSortKey;
    }

    public String getKakaoSortKey(){
        return this.kakaoSortKey;
    }

    public String getNaverSortKey(){
        return this.naverSortKey;
    }
}

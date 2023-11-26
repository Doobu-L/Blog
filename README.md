# Blog

FileDownLoad url - https://drive.google.com/file/d/1mnzoer_KuHEEYGy4oqQyYFXCfP0PJjAr/view?usp=drive_link

### Project 주요 기술스택
SpringBoot - v3.1.5
Java - OpenJDK18
DB - Redis(Embedded)

Redis 채택 이유
높은 성능, 낮은 지연시간 , 동시성 이슈 헷징, 다양한 자료구조(ex - sortedSet)


## 프로젝트 완성도

### 블로그 조회 API - 100% 
 - kakao blog 조회 errorCode 반환 시 Naver 조회
### 키워드 카운트 증가 - 완료 (sortedSet - member의 score increment)
 - 키워드 Key별 TTL 설정 x. 많은고민을 했지만 구현하지 못했습니다. (정책이 주어진다면 구성할 자신은 있습니다)
### 인기검색어 TOP10 조회 - 100%완료
### 에러핸들링 50%
 - 상세하게 구현하지 못한 부분이 아쉽습니다.
### 테스트케이스 Coverage 100%
 - Service 단위테스트
 - Adapter 단위테스트

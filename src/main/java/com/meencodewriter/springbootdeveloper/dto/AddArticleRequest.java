package com.meencodewriter.springbootdeveloper.dto;

import com.meencodewriter.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // 빌더패턴 사용하요 DTO 를 엔티티로 만들어주는 메소드
    // 블로그 글을 추가할 떄 조장할 엔티티로 변환하는 용도
    public Article toEntity() {
        // 생성자를 사용해 객체 생성 후 리턴
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}

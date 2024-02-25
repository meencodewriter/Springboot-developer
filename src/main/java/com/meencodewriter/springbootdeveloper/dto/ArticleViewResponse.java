package com.meencodewriter.springbootdeveloper.dto;


import com.meencodewriter.springbootdeveloper.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;

    public ArticleViewResponse(Article article) {

    }
}

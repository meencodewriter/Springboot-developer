package com.meencodewriter.springbootdeveloper.controller;

import com.meencodewriter.springbootdeveloper.domain.Article;
import com.meencodewriter.springbootdeveloper.dto.AddArticleRequest;
import com.meencodewriter.springbootdeveloper.dto.ArticleResponse;
import com.meencodewriter.springbootdeveloper.dto.UpdateArticleRequest;
import com.meencodewriter.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/article")
    public ResponseEntity<Article> addArticle(
            @RequestBody AddArticleRequest request) {
        Article saveArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(
            @PathVariable(value = "id") long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable(value = "id") long id) {

        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateArticleRequest updateArticleRequest) {

        Article modifiedArticle = blogService.update(id, updateArticleRequest);

        return ResponseEntity.ok()
                .body(modifiedArticle);
    }

}
package com.meencodewriter.springbootdeveloper.service;

import com.meencodewriter.springbootdeveloper.domain.Article;
import com.meencodewriter.springbootdeveloper.dto.AddArticleRequest;
import com.meencodewriter.springbootdeveloper.dto.UpdateArticleRequest;
import com.meencodewriter.springbootdeveloper.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Article by ["+id+"]"));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Article by ["+id+"]"));
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}

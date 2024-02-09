package com.meencodewriter.springbootdeveloper.service;

import com.meencodewriter.springbootdeveloper.domain.Article;
import com.meencodewriter.springbootdeveloper.dto.AddArticleRequest;
import com.meencodewriter.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}

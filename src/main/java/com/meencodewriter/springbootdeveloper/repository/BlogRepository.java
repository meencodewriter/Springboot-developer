package com.meencodewriter.springbootdeveloper.repository;

import com.meencodewriter.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
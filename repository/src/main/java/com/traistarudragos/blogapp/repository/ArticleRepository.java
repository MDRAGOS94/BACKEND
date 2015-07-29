package com.traistarudragos.blogapp.repository;

import com.traistarudragos.blogapp.model.Article;
import com.traistarudragos.blogapp.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {

    @Query("{'title' :?0}")
    Article findByTitle(String title);
}

package com.miki.blog.test;

import com.traistarudragos.blogapp.ApplicationConfig;
import com.traistarudragos.blogapp.model.Article;
import com.traistarudragos.blogapp.model.Comment;
import com.traistarudragos.blogapp.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ArticleRepositoryMongoDBTest {

    @Resource
    ArticleRepository articleRepository;

    @Test
    public void testSaveArticle() {
        Article article = new Article();
        article.setTitle("TITLE ARTICLE 3");
        article.setContent("CONTENT ARTICLE 3");

        final Comment comment1 = new Comment();
        comment1.setText("COMMENT 1 AT ARTICLE 3");
        final Comment comment2 = new Comment();
        comment2.setText("COMMENT 2 AT ARTICLE 3");
        article.setListOfComments(new ArrayList<Comment>() {{ add(comment1); add(comment2); }});

        articleRepository.save(article);
    }

}

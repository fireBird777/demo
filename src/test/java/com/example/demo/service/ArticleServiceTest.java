package com.example.demo.service;

import com.example.demo.dao.ArticleDaoI;
import com.example.demo.model.Article;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleServiceTest {

    @Mock
    ArticleService articleService;

    @Test
    void findAll() {
        Article article1 = new Article();
        article1.setArticleId(1);
        article1.setActive(true);
        article1.setPublished(false);
        article1.setNoOfPages(10);
        article1.setAuthorName("njns");
        article1.setTitle("cnn");
        article1.setShortTitle("jcj");
        article1.setAuthorEmailAddress("cjn");

        Article article2 = new Article();
        article2.setArticleId(2);
        article2.setActive(true);
        article2.setPublished(false);
        article2.setNoOfPages(101);
        article2.setAuthorName("jkl");
        article2.setTitle("abc");
        article2.setShortTitle("def");
        article2.setAuthorEmailAddress("mno");
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);

        doReturn(articles).when(articleService).findAll();

        List<Article> returnedList = articleService.findAll();

        assertEquals(2,returnedList.size());

        verify(articleService,times(1)).findAll();


    }

    @Test
    void findById() {
        Article article = new Article();
        article.setArticleId(1);
        article.setActive(true);
        article.setPublished(false);
        article.setNoOfPages(10);
        article.setAuthorName("njns");
        article.setTitle("cnn");
        article.setShortTitle("jcj");
        article.setAuthorEmailAddress("cjn");
        doReturn(Optional.of(article)).when(articleService).findById(1);

        Optional<Article> returnedArticle = articleService.findById(1);

        assertEquals(1,returnedArticle.get().getArticleId(),"article not present");

        verify(articleService,times(1)).findById(1);


    }

    @Test
    void save() {
        Article article = new Article();
        article.setArticleId(1);
        article.setActive(true);
        article.setPublished(false);
        article.setNoOfPages(10);
        article.setAuthorName("njns");
        article.setTitle("cnn");
        article.setShortTitle("jcj");
        article.setAuthorEmailAddress("cjn");
        doReturn(new ResponseEntity<String>( "saved",HttpStatus.OK)).when(articleService).save(article);
        ResponseEntity<String> returnedArticle = articleService.save(article);
        assertEquals(HttpStatus.OK, returnedArticle.getStatusCode());
        verify(articleService,times(1)).save(article);
    }

    @Test
    void deleteById() {
        articleService.deleteById(2);
        verify(articleService,times(1)).deleteById(any());
    }
}
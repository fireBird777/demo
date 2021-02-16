package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleDao articleDao;

    @Test
    void findAll() {

        // Setup our mock repository
        Article article1 = new Article(1, "Abc","vdvhj",10,"sghj","ysgdj",true,false);
        Article article2 = new Article(2, "Abc","vdvhj",4,"sghj","ysgdj",true,false);
        doReturn(Arrays.asList(article1, article2)).when(articleDao).findAll();

        // Execute the service call
        List<Article> articles = articleService.findAll();

        // Assert the response
        Assertions.assertEquals(2, articles.size(), "findAll should return 2 widgets");
    }

    @Test
    void findById() {
        // Setup our mock repository
        Article article = new Article(1,"cnn","jcj",10,"njns","cjn",true,false);
        //doReturn(article).when(articleDao).findById(1);

        // Execute the service call
        Article returnedArticle = articleService.findById(1);

        // Assert the response
        Assertions.assertSame(returnedArticle, article, "The widget returned was not the same as the mock");
    }

    //@Test
    /*void save() {
        //creating article mock repository
        Article article = new Article(1,"cnn","jcj",10,"njns","cjn",true,false);
        doReturn(article).when(articleDao).save(article);

        // Execute the service call
        Article returnedArticle = articleService.save(article);

        // Assert the response
        Assertions.assertNotNull(returnedArticle, "The saved article should not be null");
        Assertions.assertEquals(returnedArticle,article , "returned article should be same as article");
    }*/
}
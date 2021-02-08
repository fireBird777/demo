package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    public  void insertArticle(Article article)
    {
        articleService.insertArticle(article);
    }

    @GetMapping("/Articles")
    public List<Article> getAllArticles()
    {
        return articleService.getAllArticles() ;
    }

    @GetMapping("/Articles/{articleId}")
    public Article getAllArticlesById(@PathVariable int articleId)
    {
        return articleService.getArticlesById(articleId);
    }
}

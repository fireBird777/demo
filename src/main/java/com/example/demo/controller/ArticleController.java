package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    public  void save(Article article)
    {
        articleService.save(article);
    }

    @GetMapping("/Articles")
    public List<Article> findAll()
    {
        return articleService.findAll() ;
    }

    @GetMapping("/Articles/{articleId}")
    public Article getAllArticlesById(@PathVariable int articleId)
    {
        return articleService.findById(articleId);
    }
}

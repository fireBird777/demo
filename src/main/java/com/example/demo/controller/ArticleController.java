package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @PostMapping("/SaveArticle")
    public  void save(
            @RequestBody Article article)
    {
        articleService.save(article);
    }

    @GetMapping("/Articles")
    public List<Article> findAll()
    {
        return articleService.findAll() ;
    }

    @GetMapping("/Articles/{articleId}")
    public Article findById(@PathVariable int articleId)
    {
        return articleService.findById(articleId);
    }
}

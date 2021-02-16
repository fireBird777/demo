package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;



    @PostMapping("/SaveArticle")
    public  ResponseEntity<String> save(@RequestBody Article article)
    {
        return articleService.save(article);
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


    @DeleteMapping("Articles/{articleId}")
    public ResponseEntity<String> deleteById(@PathVariable int articleId)
    {
         return articleService.deleteById(articleId);
    }
}

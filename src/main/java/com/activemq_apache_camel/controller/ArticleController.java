package com.activemq_apache_camel.controller;

import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @ApiIgnore
    @PostMapping("/articles")
    public  ResponseEntity<String> save(@RequestBody ArticleDTO article)
    {
            Article theArticle = Article.builder()
                    .articleId((Integer) article.getArticleId())
                    .authorEmailAddress((String) article.getAuthorEmailAddress())
                    .isActive((Boolean) article.getIsActive())
                    .authorName((String) article.getAuthorName())
                    .isPublished((Boolean) article.getIsPublished())
                    .noOfPages((Integer) article.getNoOfPages())
                    .shortTitle((String) article.getShortTitle())
                    .title((String) article.getTitle()).build();
        return articleService.save(theArticle);
    }


    @GetMapping("/articles")
    public List<Article> findAll()
    {
        return articleService.findAll() ;
    }


    @GetMapping("/articles/{id}")
    public Article findById(@PathVariable int id)
    {
        return articleService.findById(id);
    }


    @DeleteMapping("articles/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)
    {
         return articleService.deleteById(id);
    }
}

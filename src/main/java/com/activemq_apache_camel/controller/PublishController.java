package com.activemq_apache_camel.controller;

import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/publisher")
@RestController
public class PublishController {

    @Autowired
    PublishService publishService;


    @PostMapping("/articles")
    public ResponseEntity<String> publishArticle(@RequestBody ArticleDTO article)
    {
        Article theArticle = Article.builder()
                .articleId(article.getArticleId())
                .authorEmailAddress(article.getAuthorEmailAddress())
                .isActive(article.isActive())
                .authorName(article.getAuthorName())
                .isPublished(article.isPublished())
                .noOfPages(article.getNoOfPages())
                .shortTitle(article.getShortTitle())
                .title(article.getTitle()).build();

      return publishService.publishArticle(theArticle);

    }




}

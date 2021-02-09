package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    PublishService publishService;


    @PostMapping("/UpdateArticle")
    public ResponseEntity<String> publishArticle(@RequestBody Article article)
    {
      return publishService.publishArticle(article);

    }




}

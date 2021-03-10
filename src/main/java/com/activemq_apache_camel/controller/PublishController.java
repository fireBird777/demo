package com.activemq_apache_camel.controller;

import com.activemq_apache_camel.exception.InvalidInputException;
import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.model.CustomErrorResponse;
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
    public ResponseEntity<String> publishArticle(@RequestBody ArticleDTO articleDTO) throws Exception {

      return publishService.publishArticle(articleDTO);

    }




}

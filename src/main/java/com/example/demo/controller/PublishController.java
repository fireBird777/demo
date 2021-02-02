package com.example.demo.controller;

import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/publishArticle")
    public ResponseEntity<String> publishArticle(@RequestBody Article article)
    {
        try
        {
            jmsTemplate.convertAndSend("MESSAGE_QUEUE",article);
            return new ResponseEntity<>("sent",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

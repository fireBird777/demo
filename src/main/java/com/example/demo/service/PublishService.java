package com.example.demo.service;

import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PublishService {

    @Autowired
    JmsTemplate jmsTemplate;



    public ResponseEntity<String> publishArticle(Article article)
    {

        try
        {
            jmsTemplate.convertAndSend("IBOUND",article);
            return new ResponseEntity<>("sent", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

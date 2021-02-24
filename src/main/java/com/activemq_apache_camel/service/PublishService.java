package com.activemq_apache_camel.service;

import com.activemq_apache_camel.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class PublishService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${messageQueue}")
    private  String messageQueue ;

    private static final String responseMessage = "sent";


    public ResponseEntity<String> publishArticle(Article article)
    {

        try
        {
            jmsTemplate.convertAndSend(messageQueue,article);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

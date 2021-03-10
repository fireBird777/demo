package com.activemq_apache_camel.service;


import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.schema_validator.SchemaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import org.everit.json.schema.ValidationException;



@Service
public class PublishService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    SchemaValidator schemaValidator;

    @Value("${messageQueue}")
    private String messageQueue;



    public void publishArticle(ArticleDTO articleDTO) throws ValidationException {


        schemaValidator.validateArticleAgainstSchema(articleDTO);

        Article article = Article.builder()
                .articleId((Integer) articleDTO.getArticleId())
                .authorEmailAddress((String) articleDTO.getAuthorEmailAddress())
                .isActive((Boolean) articleDTO.getIsActive())
                .authorName((String) articleDTO.getAuthorName())
                .isPublished((Boolean) articleDTO.getIsPublished())
                .noOfPages((Integer) articleDTO.getNoOfPages())
                .shortTitle((String) articleDTO.getShortTitle())
                .title((String) articleDTO.getTitle()).build();


        jmsTemplate.convertAndSend(messageQueue, article);


    }
}




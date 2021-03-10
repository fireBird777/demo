package com.activemq_apache_camel.service;

import com.activemq_apache_camel.exception.InvalidInputException;
import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.model.CustomErrorResponse;
import com.activemq_apache_camel.schema_validator.SchemaValidator;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;


@Service
public class PublishService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    SchemaValidator schemaValidator;

    @Value("${messageQueue}")
    private  String messageQueue ;

    private static final String responseMessage = "sent";


    public ResponseEntity<String> publishArticle(ArticleDTO articleDTO) throws InvalidInputException {




        try
        {
            schemaValidator.validateArticleAgainstSchema(articleDTO);

            Article theArticle = Article.builder()
                    .articleId((Integer) articleDTO.getArticleId())
                    .authorEmailAddress((String) articleDTO.getAuthorEmailAddress())
                    .isActive((Boolean)articleDTO.getIsActive())
                    .authorName((String) articleDTO.getAuthorName())
                    .isPublished((Boolean) articleDTO.getIsPublished())
                    .noOfPages((Integer) articleDTO.getNoOfPages())
                    .shortTitle((String)articleDTO.getShortTitle())
                    .title((String)articleDTO.getTitle()).build();


            jmsTemplate.convertAndSend(messageQueue,theArticle);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }catch (Exception e)
        {
            //return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

            throw new InvalidInputException(e);
        }

    }
}

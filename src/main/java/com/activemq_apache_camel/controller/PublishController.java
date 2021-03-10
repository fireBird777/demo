package com.activemq_apache_camel.controller;


import com.activemq_apache_camel.exception.InvalidInputException;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.model.CustomErrorResponse;
import com.activemq_apache_camel.model.CustomSuccessResponse;
import com.activemq_apache_camel.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RequestMapping("/publisher")
@RestController
public class PublishController {

    @Autowired
    PublishService publishService;


    @PostMapping("/articles")
    public ResponseEntity<String> publishArticle(@RequestBody ArticleDTO articleDTO) throws InvalidInputException, JsonProcessingException {
        try{
            publishService.publishArticle(articleDTO);
            CustomSuccessResponse response = new CustomSuccessResponse();
            ObjectMapper om = new ObjectMapper();
            String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(response);

            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (ValidationException e){
            String errors = e.getCausingExceptions().stream().map(ValidationException::getMessage)
                    .collect(Collectors.joining(","));
            throw new InvalidInputException(errors,e);
        }
        }


    }






package com.activemq_apache_camel.service;

import com.activemq_apache_camel.exception.ArticleNotFoundException;
import com.activemq_apache_camel.repository.ArticleRepository;
import com.activemq_apache_camel.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;


    /*
    ToDo
    throw an exception and return response body when there are no articles present
     */
    @Transactional
    public List<Article> findAll()
    {
        return  articleRepository.findAll();
    }

    @Transactional
    public Article findById(int articleId) {

        return  articleRepository.findById(articleId).orElseThrow(()->new ArticleNotFoundException(articleId));

    }

    @Transactional
    public  ResponseEntity<String> save(Article article)
    {

        try
        {
            articleRepository.save(article);
            return new ResponseEntity<>("saved", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public ResponseEntity<String> deleteById(int articleId) {

        try
        {
            articleRepository.deleteById(articleId);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }catch (Exception e)
        {
            throw new ArticleNotFoundException(articleId);
        }



    }
}


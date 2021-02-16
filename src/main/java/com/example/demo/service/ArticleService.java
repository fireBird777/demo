package com.example.demo.service;

import com.example.demo.dao.ArticleDaoI;
import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleDaoI articleDao;

    @Transactional
    public List<Article> findAll()
    {
        return  articleDao.findAll();
    }

    @Transactional
    public Optional<Article> findById(int articleId)
    {
        return  articleDao.findById(articleId);
    }

    @Transactional
    public  ResponseEntity<String> save(Article article)
    {

        try
        {
            articleDao.save(article);
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
            articleDao.deleteById(articleId);
            return new ResponseEntity<>("saved", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
}


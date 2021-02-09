package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Transactional
    public List<Article> findAll()
    {
        return  articleDao.findAll();
    }

    @Transactional
    public Article findById(int articleId)
    {
        return  articleDao.findById(articleId);
    }

    @Transactional
    public  void save(Article article)
    {
        articleDao.save(article);
    }
}


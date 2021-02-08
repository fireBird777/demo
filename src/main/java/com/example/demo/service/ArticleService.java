package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Transactional
    public List<Article> getAllArticles()
    {
        return  articleDao.getAllArticles();
    }

    @Transactional
    public Article getArticlesById(int articleId)
    {
        return  articleDao.getArticleById(articleId);
    }

    @Transactional
    public  void insertArticle(Article article)
    {
        articleDao.insertArticle(article);
    }
}


package com.example.demo.service;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Transactional
    public void saveOrUpdateArticle(Article article)
    {
        articleDAO.saveOrUpdateArticle(article);
    }
}

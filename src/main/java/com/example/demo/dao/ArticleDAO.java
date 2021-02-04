package com.example.demo.dao;

import com.example.demo.model.Article;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class ArticleDAO {

    @Autowired
    private EntityManager entityManager;

    public void saveOrUpdateArticle(Article article)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(article);
         System.out.println("data stored successfully");
    }
}

package com.example.demo.dao;

import com.example.demo.model.Article;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ArticleDao {

    @Autowired
    EntityManager entityManager;

    public List<Article> getAllArticles()
    {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Article",Article.class);
        List<Article> articles = query.getResultList();

        for (Article article: articles
             ) {

        }
        return articles;
    }

    public  Article getArticleById(int articlesId)
    {
        Session session = entityManager.unwrap(Session.class);
        Article article = session.get(Article.class,articlesId);

        return article;
    }
}

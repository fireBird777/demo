package com.example.demo.dao;

import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ArticleDao {

    @Autowired
    EntityManager entityManager;

    public List<Article> findAll()
    {
        Query query = entityManager.createQuery("from Article");
        List<Article> articles = query.getResultList();

        return articles;
    }

    public  Article findById(int articlesId)
    {
        Article article = entityManager.find(Article.class,articlesId);

        return article;
    }

    public  void save(Article article)
    {
       Article theArticle = entityManager.merge(article);
       theArticle.setArticleId(article.getArticleId());

    }
}

package com.example.demo.dao;

import com.example.demo.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleDaoTest {




    @Autowired
    private ArticleDao articleDao;

    @Test
    void findAll() {
        List<Article> articles = articleDao.findAll();
        System.out.print(articles.size());
        assertEquals(11,articles.size());
    }

    @Test
    void findById() {
        Article article = articleDao.findById(11);
        assertEquals(11,article.getArticleId(),"no article found at given id");

    }

    //where is the data with id 1 going? if not in database
    @Test
    @Transactional
    void save() {
        Article article = new Article(12,"cnn","jcj",10,"njns","cjn",true,false);
        Article theArticle = articleDao.save(article);
        assertNotNull(theArticle);
        assertEquals(article.getArticleId(),theArticle.getArticleId());
    }
}
package com.example.demo.dao;

import com.example.demo.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticleDaoTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    ArticleDao articleDao;
    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Article article = articleDao.findById(1);
        assertEquals(1,article.getArticleId());
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}
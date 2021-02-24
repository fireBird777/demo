package com.activemq_apache_camel.repository;

import com.activemq_apache_camel.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}

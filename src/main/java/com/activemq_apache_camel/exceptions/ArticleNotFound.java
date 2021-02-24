package com.activemq_apache_camel.exceptions;

public class ArticleNotFound extends RuntimeException{
    public ArticleNotFound(int id)
    {
        super("Article not found :"+id);
    }
}

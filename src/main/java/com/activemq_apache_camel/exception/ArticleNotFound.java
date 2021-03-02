package com.activemq_apache_camel.exception;

import java.util.function.Supplier;

public class ArticleNotFound extends RuntimeException{
    public ArticleNotFound(int id)
    {
        super("Article not found :"+id);
    }
}

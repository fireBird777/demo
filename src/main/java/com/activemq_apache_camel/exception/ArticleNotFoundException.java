package com.activemq_apache_camel.exception;

import java.util.function.Supplier;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(int id)
    {
        super("Article not found :"+id);
    }
}

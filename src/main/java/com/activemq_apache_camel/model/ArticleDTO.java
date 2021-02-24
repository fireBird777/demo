package com.activemq_apache_camel.model;


import lombok.Getter;



@Getter
public class ArticleDTO {

    private int articleId;
    private String title;
    private String shortTitle;
    private int noOfPages;
    private String authorName;
    private String authorEmailAddress;
    private boolean isActive;
    private boolean isPublished;
}

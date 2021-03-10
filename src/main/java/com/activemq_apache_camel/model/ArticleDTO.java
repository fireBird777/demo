package com.activemq_apache_camel.model;


import lombok.*;


@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class ArticleDTO {

    private Object articleId;
    private Object title;
    private Object shortTitle;
    private Object noOfPages;
    private Object authorName;
    private Object authorEmailAddress;
    private Object isActive;
    private Object isPublished;



}

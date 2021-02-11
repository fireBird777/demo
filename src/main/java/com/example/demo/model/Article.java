package com.example.demo.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Getter @Setter @ToString
@Table(name= "`Article`")
public class Article implements Serializable {
    @Id
    @Column(name = "article_id")
    private int articleId;

    @Column(name = "title")
    private String title;

    @Column(name = "short_title")
    private String shortTitle;

    @Column(name = "no_of_pages")
    private int noOfPages;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_email_address")
    private String authorEmailAddress;

    @Column(name = "is_active" ,nullable = false)
    private boolean isActive;

    @Column(name = "is_published", nullable = false)
    private boolean isPublished;

    public Article(int articleId, String title, String shortTitle, int noOfPages, String authorName, String authorEmailAddress, boolean isActive, boolean isPublished) {
        this.articleId = articleId;
        this.title = title;
        this.shortTitle = shortTitle;
        this.noOfPages = noOfPages;
        this.authorName = authorName;
        this.authorEmailAddress = authorEmailAddress;
        this.isActive = isActive;
        this.isPublished = isPublished;
    }

    public Article() {
    }
}

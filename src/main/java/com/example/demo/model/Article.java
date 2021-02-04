package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
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

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_published")
    private boolean isPublished;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmailAddress() {
        return authorEmailAddress;
    }

    public void setAuthorEmailAddress(String authorEmailAddress) {
        this.authorEmailAddress = authorEmailAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", noOfPages=" + noOfPages +
                ", authorName='" + authorName + '\'' +
                ", authorEmailAddress='" + authorEmailAddress + '\'' +
                ", isActive=" + isActive +
                ", isPublished=" + isPublished +
                '}';
    }
}

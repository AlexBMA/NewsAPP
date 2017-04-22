package model;

import java.util.Date;

/**
 * Created by Alexandru on 4/22/2017.
 */

public class Article {

    private String title;
    private Date dateOfArticle;
    private String url;
    private String author;
    private String sectionName;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfArticle() {
        return dateOfArticle;
    }

    public void setDateOfArticle(Date dateOfArticle) {
        this.dateOfArticle = dateOfArticle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}


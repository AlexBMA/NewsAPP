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
    private String quickInfo;
    private String thumnailUrl;

    public Article() {
        dateOfArticle = new Date();
        author = "";
        title = "";
        url = "";
        thumnailUrl = "";
        quickInfo = "";
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


    public String getQuickInfo() {
        return quickInfo;
    }

    public void setQuickInfo(String quickInfo) {
        this.quickInfo = quickInfo;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", dateOfArticle=" + dateOfArticle +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", quickInfo='" + quickInfo + '\'' +
                ", thumnailUrl='" + thumnailUrl + '\'' +
                '}';
    }
}


package com.traistarudragos.blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Article extends AbstractEntity{
    private String title;
    private String content;
    private Author author;
    private List<Rate> listOfRates = new ArrayList<Rate>();
    private List<Comment> listOfComments = new ArrayList<Comment>();

    public Article() {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public List<Rate> getListOfRates() {
        return listOfRates;
    }
    public void setListOfRates(List<Rate> listOfRates) {
        this.listOfRates = listOfRates;
    }
    public List<Comment> getListOfComments() {
        return listOfComments;
    }
    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }

}

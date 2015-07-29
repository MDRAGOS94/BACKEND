package com.traistarudragos.blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
    private String text;

    public Comment() {
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

}

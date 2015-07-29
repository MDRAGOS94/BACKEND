package com.traistarudragos.blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Rate {
    private int value;

    public Rate() {
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}

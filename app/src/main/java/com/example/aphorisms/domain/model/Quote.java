package com.example.aphorisms.domain.model;

public class Quote {
    private final String id;
    private final String content;
    private final String author;

    public Quote(String id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public static final Quote empty = new Quote("", "", "");
}

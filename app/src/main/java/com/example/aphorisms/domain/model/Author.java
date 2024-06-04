package com.example.aphorisms.domain.model;

public class Author {
    private final String name;
    private final String description;

    public Author(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

package com.example.aphorisms.data.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes")
public class LocalQuote {
    @PrimaryKey
    @NonNull
    public String id = "";

    public String content;
    public String author;
}

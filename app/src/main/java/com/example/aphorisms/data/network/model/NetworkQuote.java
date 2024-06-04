package com.example.aphorisms.data.network.model;

import com.google.gson.annotations.SerializedName;

public class NetworkQuote {
    @SerializedName("_id")
    public String id;

    @SerializedName("content")
    public String content;

    @SerializedName("author")
    public String author;
}

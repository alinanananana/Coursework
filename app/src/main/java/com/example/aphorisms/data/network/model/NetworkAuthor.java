package com.example.aphorisms.data.network.model;

import com.google.gson.annotations.SerializedName;

public class NetworkAuthor {
    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;
}

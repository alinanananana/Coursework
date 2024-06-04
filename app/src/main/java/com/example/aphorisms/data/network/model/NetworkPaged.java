package com.example.aphorisms.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkPaged<T> {
    @SerializedName("page")
    public int page;

    @SerializedName("totalPages")
    public int totalPages;

    @SerializedName("results")
    public List<T> results;
}

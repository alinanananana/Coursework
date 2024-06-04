package com.example.aphorisms.data.network;

import com.example.aphorisms.data.network.model.NetworkAuthor;
import com.example.aphorisms.data.network.model.NetworkPaged;
import com.example.aphorisms.data.network.model.NetworkQuote;
import com.example.aphorisms.data.network.model.NetworkTag;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApi {
    @GET("search/quotes")
    Single<NetworkPaged<NetworkQuote>> getQuotesByQuery(@Query("query") String query);

    @GET("random")
    Single<NetworkQuote> getRandomQuote();

    @GET("authors")
    Single<NetworkPaged<NetworkAuthor>> getAuthors(@Query("page") int page);

    @GET("tags")
    Single<List<NetworkTag>> getTags();

    @GET("quotes")
    Single<NetworkPaged<NetworkQuote>> getQuotesByAuthor(@Query("author") String author);

    @GET("quotes")
    Single<NetworkPaged<NetworkQuote>> getQuotesByTag(@Query("tags") String tag);
}

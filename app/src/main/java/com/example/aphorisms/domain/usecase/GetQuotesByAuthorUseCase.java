package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.data.network.QuoteApi;
import com.example.aphorisms.data.network.QuoteApiClient;
import com.example.aphorisms.data.network.mapper.NetworkPagedQuoteMapper;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetQuotesByAuthorUseCase {
    private final QuoteApi quoteApi = QuoteApiClient.create();

    public void execute(String author, SingleObserver<Paged<Quote>> observer) {
        quoteApi.getQuotesByAuthor(author)
                .map(NetworkPagedQuoteMapper::toDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

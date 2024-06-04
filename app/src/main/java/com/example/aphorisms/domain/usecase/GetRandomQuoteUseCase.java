package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.data.network.QuoteApi;
import com.example.aphorisms.data.network.QuoteApiClient;
import com.example.aphorisms.data.network.mapper.NetworkQuoteMapper;
import com.example.aphorisms.domain.model.Quote;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetRandomQuoteUseCase {
    private final QuoteApi quoteApi = QuoteApiClient.create();

    public void execute(SingleObserver<Quote> observer) {
        quoteApi.getRandomQuote()
                .map(NetworkQuoteMapper::toDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

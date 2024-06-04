package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.data.network.QuoteApi;
import com.example.aphorisms.data.network.QuoteApiClient;
import com.example.aphorisms.data.network.mapper.NetworkPagedAuthorMapper;
import com.example.aphorisms.domain.model.Author;
import com.example.aphorisms.domain.model.Paged;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetAuthorsUseCase {
    private final QuoteApi quoteApi = QuoteApiClient.create();

    public void execute(int page, SingleObserver<Paged<Author>> observer) {
        quoteApi.getAuthors(page)
                .map(NetworkPagedAuthorMapper::toDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

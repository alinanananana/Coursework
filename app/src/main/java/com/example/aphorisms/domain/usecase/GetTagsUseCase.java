package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.data.network.QuoteApi;
import com.example.aphorisms.data.network.QuoteApiClient;
import com.example.aphorisms.data.network.mapper.NetworkTagsMapper;
import com.example.aphorisms.domain.model.Tag;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetTagsUseCase {
    private final QuoteApi quoteApi = QuoteApiClient.create();

    public void execute(SingleObserver<List<Tag>> observer) {
        quoteApi.getTags()
                .map(NetworkTagsMapper::toDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

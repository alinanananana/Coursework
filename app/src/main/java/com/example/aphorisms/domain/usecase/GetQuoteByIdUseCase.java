package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.App;
import com.example.aphorisms.data.local.QuoteDao;
import com.example.aphorisms.data.local.mapper.LocalQuoteMapper;
import com.example.aphorisms.domain.model.Quote;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetQuoteByIdUseCase {
    private final QuoteDao quoteDao = App.getQuoteDatabase().quoteDao();

    public void execute(String id, MaybeObserver<Quote> observer) {
        quoteDao.getQuoteById(id)
                .map(LocalQuoteMapper::toDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

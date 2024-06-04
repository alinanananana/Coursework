package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.App;
import com.example.aphorisms.data.local.QuoteDao;
import com.example.aphorisms.data.local.mapper.LocalQuotesMapper;
import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetQuotesByFavoriteUseCase {
    private final QuoteDao quoteDao = App.getQuoteDatabase().quoteDao();

    public Observable<Resource<Paged<Quote>>> execute() {
        return quoteDao.getAll()
                .map(LocalQuotesMapper::toDomain)
                .map(LocalQuotesMapper::toOnePage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

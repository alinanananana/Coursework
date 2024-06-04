package com.example.aphorisms.domain.usecase;

import com.example.aphorisms.App;
import com.example.aphorisms.data.local.QuoteDao;
import com.example.aphorisms.data.local.mapper.LocalQuoteMapper;
import com.example.aphorisms.domain.model.Quote;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteQuoteFromFavoritesUseCase {
    private final QuoteDao quoteDao = App.getQuoteDatabase().quoteDao();

    public void execute(Quote quote) {
        quoteDao.delete(LocalQuoteMapper.toLocal(quote))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}

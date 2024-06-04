package com.example.aphorisms.ui.quotes.query;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.domain.usecase.GetQuotesByQueryUseCase;

import java.util.Collections;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class QuotesByQueryViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final GetQuotesByQueryUseCase getQuotesByQuery = new GetQuotesByQueryUseCase();
    private final BehaviorSubject<Resource<Paged<Quote>>> quotesState = BehaviorSubject.createDefault(Resource.empty());

    void findQuotes(String query) {
        getQuotesByQuery.execute(query, new QuotesObserver());
    }

    void clearQuotes() {
        Paged<Quote> paged = new Paged<>(0, 0, Collections.emptyList());

        quotesState.onNext(Resource.success(paged));
    }

    public Observable<Resource<Paged<Quote>>> getQuotesState() {
        return quotesState.hide();
    }

    private class QuotesObserver implements SingleObserver<Paged<Quote>> {
        @Override
        public void onSubscribe(Disposable d) {
            quotesState.onNext(Resource.loading());
            disposables.add(d);
        }

        @Override
        public void onSuccess(Paged<Quote> quotes) {
            quotesState.onNext(Resource.success(quotes));
        }

        @Override
        public void onError(Throwable e) {
            quotesState.onNext(Resource.empty());
        }
    }
}

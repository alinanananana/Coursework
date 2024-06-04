package com.example.aphorisms.ui.quotes.author;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.domain.usecase.GetQuotesByAuthorUseCase;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class QuotesByAuthorViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final GetQuotesByAuthorUseCase getQuotesByAuthor = new GetQuotesByAuthorUseCase();
    private final BehaviorSubject<Resource<Paged<Quote>>> quotesState = BehaviorSubject.createDefault(Resource.empty());

    public void loadQuotes(String author) {
        getQuotesByAuthor.execute(author, new QuotesObserver());
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

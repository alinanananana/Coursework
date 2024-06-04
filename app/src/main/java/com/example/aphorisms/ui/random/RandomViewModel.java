package com.example.aphorisms.ui.random;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.domain.usecase.GetRandomQuoteUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class RandomViewModel extends ViewModel {
    private final GetRandomQuoteUseCase getRandomQuote = new GetRandomQuoteUseCase();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final BehaviorSubject<Resource<Quote>> quoteState = BehaviorSubject.createDefault(Resource.empty());

    public RandomViewModel() {
        generateQuote();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposables.clear();
    }

    void generateQuote() {
        getRandomQuote.execute(new QuoteObserver());
    }

    public Observable<Resource<Paged<Quote>>> getQuotesState() {
        return quoteState.map(this::toOnePage);
    }

    private Resource<Paged<Quote>> toOnePage(Resource<Quote> resource) {
        List<Quote> quotes = new ArrayList<>();

        quotes.add(resource.getData());

        Paged<Quote> paged = new Paged<>(1, 1, quotes);

        return new Resource<>(paged, resource.isLoading());
    }

    private class QuoteObserver implements SingleObserver<Quote> {
        @Override
        public void onSubscribe(Disposable d) {
            quoteState.onNext(Resource.loading());
            disposables.add(d);
        }

        @Override
        public void onSuccess(Quote quote) {
            quoteState.onNext(Resource.success(quote));
        }

        @Override
        public void onError(Throwable e) {
            quoteState.onNext(Resource.empty());
        }
    }
}

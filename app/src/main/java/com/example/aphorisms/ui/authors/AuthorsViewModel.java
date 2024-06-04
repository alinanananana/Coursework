package com.example.aphorisms.ui.authors;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Author;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.usecase.GetAuthorsUseCase;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class AuthorsViewModel extends ViewModel {
    private final GetAuthorsUseCase getAuthors = new GetAuthorsUseCase();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final BehaviorSubject<Resource<Paged<Author>>> authorsState = BehaviorSubject.createDefault(Resource.empty());
    private final BehaviorSubject<Paged<Object>> pageState = BehaviorSubject.createDefault(Paged.empty());

    public AuthorsViewModel() {
        loadAuthors(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposables.clear();
    }

    public void loadAuthors(int page) {
        Paged<Object> paged = pageState.getValue();

        if (page < 1) {
            page = 1;
        } else if (page > paged.getTotalPages()) {
            page = paged.getTotalPages();
        }

        getAuthors.execute(page, new AuthorsObserver());
    }

    public void backPage() {
        Paged<Object> paged = pageState.getValue().back();

        loadAuthors(paged.getPage());
    }

    public void nextPage() {
        Paged<Object> paged = pageState.getValue().next();

        loadAuthors(paged.getPage());
    }

    public Observable<Resource<Paged<Author>>> getAuthorsState() {
        return authorsState.hide();
    }

    public Observable<Paged<Object>> getPageState() {
        return pageState.hide();
    }

    private class AuthorsObserver implements SingleObserver<Paged<Author>> {
        @Override
        public void onSubscribe(Disposable d) {
            authorsState.onNext(Resource.loading());
            pageState.onNext(Paged.empty());
            disposables.add(d);
        }

        @Override
        public void onSuccess(Paged<Author> pagedAuthors) {
            authorsState.onNext(Resource.success(pagedAuthors));
            pageState.onNext(Paged.toEmpty(pagedAuthors));
        }

        @Override
        public void onError(Throwable e) {
            authorsState.onNext(Resource.empty());
            pageState.onNext(Paged.empty());
        }
    }
}

package com.example.aphorisms.ui.tags;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Tag;
import com.example.aphorisms.domain.usecase.GetTagsUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class TagsViewModel extends ViewModel {
    private final GetTagsUseCase getTags = new GetTagsUseCase();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final BehaviorSubject<Resource<List<Tag>>> tagsState = BehaviorSubject.createDefault(Resource.empty());

    public TagsViewModel() {
        loadTags();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposables.clear();
    }

    void loadTags() {
        getTags.execute(new TagsObserver());
    }

    public Observable<Resource<List<Tag>>> getTagsState() {
        return tagsState.hide();
    }

    private class TagsObserver implements SingleObserver<List<Tag>> {
        @Override
        public void onSubscribe(Disposable d) {
            tagsState.onNext(Resource.loading());
            disposables.add(d);
        }

        @Override
        public void onSuccess(List<Tag> tags) {
            tagsState.onNext(Resource.success(tags));
        }

        @Override
        public void onError(Throwable e) {
            tagsState.onNext(Resource.empty());
        }
    }
}

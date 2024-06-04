package com.example.aphorisms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ViewItemsBinding;
import com.example.aphorisms.domain.Resource;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;

public abstract class BaseItemsView<T> extends FrameLayout {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private ViewItemsBinding binding;

    public BaseItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        binding = ViewItemsBinding.inflate(LayoutInflater.from(context), this, true);
        binding.recycler.setItemAnimator(new DefaultItemAnimator());
        binding.recycler.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        disposables.clear();
        binding.recycler.setAdapter(null);
        binding = null;
    }

    public void subscribe(Observable<Resource<T>> observable) {
        disposables.add(observable.subscribe(new ResourceObserver()));
    }

    protected RecyclerView getRecyclerView() {
        return binding.recycler;
    }

    protected abstract void onSuccess(T data);

    private class ResourceObserver implements Consumer<Resource<T>> {
        @Override
        public void accept(Resource<T> resource) {
            if (resource.isSuccess()) {
                T data = resource.getData();

                onSuccess(data);
            }

            binding.progress.setVisibility(resource.isLoading() ? View.VISIBLE : View.GONE);
        }
    }
}

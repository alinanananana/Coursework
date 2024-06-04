package com.example.aphorisms.ui.pagination;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aphorisms.databinding.ViewPaginationBinding;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.ui.MainActivity;

import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;

public class PaginationView extends FrameLayout {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private ViewPaginationBinding binding;

    public PaginationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        binding = ViewPaginationBinding.inflate(LayoutInflater.from(context), this, true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        disposables.clear();
        binding = null;
    }

    public void setListener(PaginationListener listener) {
        binding.pageBack.setOnClickListener(v -> listener.backPage());
        binding.pageNext.setOnClickListener(v -> listener.nextPage());
        binding.pageCurrent.setOnEditorActionListener(new EditorListener(listener));
    }

    public void subscribe(Observable<Paged<Object>> observable) {
        disposables.add(observable.subscribe(new PageObserver()));
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(MainActivity.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private class EditorListener implements TextView.OnEditorActionListener {
        private final PaginationListener listener;

        public EditorListener(PaginationListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                Editable editable = binding.pageCurrent.getText();

                int page = 1;
                if(editable != null){
                    if(!editable.toString().isEmpty()) {
                        page = Integer.parseInt(Objects.requireNonNull(editable).toString());
                    }
                }

                listener.jumpPage(page);
                binding.pageCurrent.clearFocus();
                hideKeyboard();

                return true;
            }

            return false;
        }
    }

    private class PageObserver implements Consumer<Paged<Object>> {
        @Override
        public void accept(Paged<Object> paged) {
            binding.pageCurrent.setText(String.valueOf(paged.getPage()));
            binding.pageTotal.setText(String.valueOf(paged.getTotalPages()));
        }
    }
}

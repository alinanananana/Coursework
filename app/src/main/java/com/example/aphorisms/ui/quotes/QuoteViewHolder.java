package com.example.aphorisms.ui.quotes;

import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemQuoteBinding;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.domain.usecase.GetQuoteByIdUseCase;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class QuoteViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
    private final GetQuoteByIdUseCase getQuoteById = new GetQuoteByIdUseCase();
    private final ItemQuoteBinding binding;
    private final QuoteClickListener listener;

    private Disposable disposable;
    private Quote quote;

    public QuoteViewHolder(ItemQuoteBinding binding, QuoteClickListener listener) {
        super(binding.getRoot());

        this.binding = binding;
        this.listener = listener;
        this.binding.favorite.setOnCheckedChangeListener(this);
    }

    public void bind(Quote quote) {
        if (quote == null) {
            quote = Quote.empty;
        }

        this.quote = quote;

        binding.content.setText(quote.getContent());
        binding.author.setText(quote.getAuthor());
        getQuoteById.execute(quote.getId(), new QuoteObserver());
    }

    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (binding.favorite.isEnabled()) {
            listener.onQuoteFavoriteClick(quote, isChecked);
        }
    }

    private class QuoteObserver implements MaybeObserver<Quote> {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            disposable = d;
            binding.favorite.setEnabled(false);
            binding.favorite.setChecked(false);
        }

        @Override
        public void onSuccess(@NonNull Quote quote) {
            binding.favorite.setChecked(true);
            binding.favorite.setEnabled(true);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            binding.favorite.setEnabled(true);
        }

        @Override
        public void onComplete() {
            binding.favorite.setEnabled(true);
        }
    }
}

package com.example.aphorisms.ui.quotes;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.ui.BaseItemsView;

import java.util.List;

public class QuotesView extends BaseItemsView<Paged<Quote>> {
    private QuotesAdapter adapter;

    public QuotesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(QuotesAdapter adapter) {
        this.adapter = adapter;

        getRecyclerView().setAdapter(adapter);
    }

    @Override
    protected void onSuccess(Paged<Quote> data) {
        List<Quote> quotes = data.getResults();

        adapter.setItems(quotes);
    }
}

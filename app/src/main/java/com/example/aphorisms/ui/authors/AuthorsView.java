package com.example.aphorisms.ui.authors;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.aphorisms.domain.model.Author;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.ui.BaseItemsView;

import java.util.List;

public class AuthorsView extends BaseItemsView<Paged<Author>> {
    private AuthorsAdapter adapter;

    public AuthorsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(AuthorsAdapter adapter) {
        this.adapter = adapter;

        getRecyclerView().setAdapter(adapter);
    }

    @Override
    protected void onSuccess(Paged<Author> data) {
        List<Author> authors = data.getResults();

        adapter.setItems(authors);
    }
}

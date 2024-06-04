package com.example.aphorisms.ui.tags;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.aphorisms.domain.model.Tag;
import com.example.aphorisms.ui.BaseItemsView;

import java.util.List;

public class TagsView extends BaseItemsView<List<Tag>> {
    private TagsAdapter adapter;

    public TagsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(TagsAdapter adapter) {
        this.adapter = adapter;

        getRecyclerView().setAdapter(adapter);
    }

    @Override
    protected void onSuccess(List<Tag> data) {
        adapter.setItems(data);
    }
}

package com.example.aphorisms.ui.tags;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemTagBinding;
import com.example.aphorisms.domain.model.Tag;

public class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ItemTagBinding binding;
    private final TagClickListener listener;

    private Tag tag;

    public TagViewHolder(ItemTagBinding binding, TagClickListener listener) {
        super(binding.getRoot());

        this.binding = binding;
        this.listener = listener;

        binding.getRoot().setOnClickListener(this);
    }

    public void bind(Tag tag) {
        this.tag = tag;

        binding.name.setText(tag.getName());
    }

    @Override
    public void onClick(View v) {
        listener.onTagClick(tag);
    }
}

package com.example.aphorisms.ui.authors;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemAuthorBinding;
import com.example.aphorisms.domain.model.Author;

public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ItemAuthorBinding binding;
    private final AuthorClickListener listener;

    private Author author;

    public AuthorViewHolder(ItemAuthorBinding binding, AuthorClickListener listener) {
        super(binding.getRoot());

        this.binding = binding;
        this.listener = listener;

        binding.getRoot().setOnClickListener(this);
    }

    public void bind(Author author) {
        this.author = author;

        binding.name.setText(author.getName());
        binding.description.setText(author.getDescription());
    }

    @Override
    public void onClick(View v) {
        listener.onAuthorClick(author);
    }
}

package com.example.aphorisms.ui.authors;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemAuthorBinding;
import com.example.aphorisms.domain.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorsAdapter extends RecyclerView.Adapter<AuthorViewHolder> {
    private final ArrayList<Author> items = new ArrayList<>();

    private final AuthorClickListener listener;

    public AuthorsAdapter(AuthorClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAuthorBinding binding = ItemAuthorBinding.inflate(inflater, parent, false);

        return new AuthorViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = items.get(position);

        holder.bind(author);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Author> authors) {
        items.clear();
        items.addAll(authors);
        notifyDataSetChanged();
    }
}

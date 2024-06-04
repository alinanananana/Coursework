package com.example.aphorisms.ui.tags;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemTagBinding;
import com.example.aphorisms.domain.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private final ArrayList<Tag> items = new ArrayList<>();

    private final TagClickListener listener;

    public TagsAdapter(TagClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTagBinding binding = ItemTagBinding.inflate(inflater, parent, false);

        return new TagViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        Tag tag = items.get(position);

        holder.bind(tag);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Tag> tags) {
        items.clear();
        items.addAll(tags);
        notifyDataSetChanged();
    }
}

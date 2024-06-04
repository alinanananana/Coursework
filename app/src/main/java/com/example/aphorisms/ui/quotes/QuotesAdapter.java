package com.example.aphorisms.ui.quotes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aphorisms.databinding.ItemQuoteBinding;
import com.example.aphorisms.domain.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {
    private final ArrayList<Quote> items = new ArrayList<>();
    private final QuoteClickListener listener;

    public QuotesAdapter(QuoteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemQuoteBinding binding = ItemQuoteBinding.inflate(inflater, parent, false);

        return new QuoteViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = items.get(position);

        holder.bind(quote);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewRecycled(@NonNull QuoteViewHolder holder) {
        super.onViewRecycled(holder);

        holder.dispose();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Quote> quotes) {
        items.clear();
        items.addAll(quotes);
        notifyDataSetChanged();
    }
}

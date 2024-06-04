package com.example.aphorisms.ui.quotes.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aphorisms.databinding.ViewQuotesBinding;
import com.example.aphorisms.ui.quotes.QuotesAdapter;
import com.example.aphorisms.ui.quotes.QuotesView;

public class QuotesByFavoriteFragment extends Fragment {
    private ViewQuotesBinding binding;
    private QuotesByFavoriteViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(QuotesByFavoriteViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewQuotesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        QuotesView quotesView = binding.getRoot();

        quotesView.setAdapter(new QuotesAdapter(viewModel));
        quotesView.subscribe(viewModel.getQuotesState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}

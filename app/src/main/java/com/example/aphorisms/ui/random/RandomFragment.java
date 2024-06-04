package com.example.aphorisms.ui.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aphorisms.databinding.FragmentRandomBinding;
import com.example.aphorisms.ui.quotes.QuotesAdapter;
import com.example.aphorisms.ui.quotes.QuotesView;
import com.example.aphorisms.ui.quotes.favorite.QuotesByFavoriteViewModel;

public class RandomFragment extends Fragment {
    private QuotesByFavoriteViewModel favoriteViewModel;
    private RandomViewModel viewModel;
    private FragmentRandomBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RandomViewModel.class);
        favoriteViewModel = new ViewModelProvider(this).get(QuotesByFavoriteViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRandomBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        QuotesView quotesView = binding.quotesView;

        quotesView.setAdapter(new QuotesAdapter(favoriteViewModel));
        quotesView.subscribe(viewModel.getQuotesState());

        binding.quoteGenerate.setOnClickListener(v -> viewModel.generateQuote());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}

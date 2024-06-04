package com.example.aphorisms.ui.quotes.author;

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
import com.example.aphorisms.ui.quotes.favorite.QuotesByFavoriteViewModel;

public class QuotesByAuthorFragment extends Fragment {
    private static final String PARAMETER_AUTHOR = "Author";

    private ViewQuotesBinding binding;
    private QuotesByAuthorViewModel viewModel;
    private QuotesByFavoriteViewModel favoriteViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(QuotesByAuthorViewModel.class);
        favoriteViewModel = new ViewModelProvider(this).get(QuotesByFavoriteViewModel.class);

        Bundle arguments = getArguments();

        if (savedInstanceState == null && arguments != null) {
            String author = getArguments().getString(PARAMETER_AUTHOR);

            viewModel.loadQuotes(author);
        }
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

        quotesView.setAdapter(new QuotesAdapter(favoriteViewModel));
        quotesView.subscribe(viewModel.getQuotesState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    public static QuotesByAuthorFragment newInstance(String author) {
        QuotesByAuthorFragment fragment = new QuotesByAuthorFragment();
        Bundle bundle = new Bundle();

        bundle.putString(PARAMETER_AUTHOR, author);
        fragment.setArguments(bundle);

        return fragment;
    }
}

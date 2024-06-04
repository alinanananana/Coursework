package com.example.aphorisms.ui.quotes.query;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aphorisms.databinding.FragmentSearchBinding;
import com.example.aphorisms.ui.quotes.QuotesAdapter;
import com.example.aphorisms.ui.quotes.favorite.QuotesByFavoriteViewModel;

public class QuotesByQueryFragment extends Fragment {
    private FragmentSearchBinding binding;
    private QuotesByQueryViewModel viewModel;
    private QuotesByFavoriteViewModel favoriteViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(QuotesByQueryViewModel.class);
        favoriteViewModel = new ViewModelProvider(this).get(QuotesByFavoriteViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.searchView.getEditText().addTextChangedListener(new QueryListener());
        binding.quotesView.setAdapter(new QuotesAdapter(favoriteViewModel));
        binding.quotesView.subscribe(viewModel.getQuotesState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    private class QueryListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String query = s.toString().trim();

            if (query.isEmpty()) {
                viewModel.clearQuotes();
            } else {
                viewModel.findQuotes(query);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}

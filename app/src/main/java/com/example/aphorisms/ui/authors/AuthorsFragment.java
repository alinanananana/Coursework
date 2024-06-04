package com.example.aphorisms.ui.authors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aphorisms.databinding.FragmentAuthorsBinding;
import com.example.aphorisms.domain.model.Author;
import com.example.aphorisms.ui.MainActivity;
import com.example.aphorisms.ui.pagination.PaginationListener;
import com.example.aphorisms.ui.pagination.PaginationView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AuthorsFragment extends Fragment implements AuthorClickListener, PaginationListener {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private FragmentAuthorsBinding binding;
    private AuthorsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AuthorsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthorsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthorsView authorsView = binding.authorsView;
        PaginationView paginationView = binding.paginationView;

        authorsView.setAdapter(new AuthorsAdapter(this));
        authorsView.subscribe(viewModel.getAuthorsState());

        paginationView.subscribe(viewModel.getPageState());
        paginationView.setListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
        disposables.clear();
    }

    @Override
    public void onAuthorClick(Author author) {
        MainActivity activity = (MainActivity) requireActivity();

        activity.navigateToQuotesByAuthor(author.getName());
    }

    @Override
    public void backPage() {
        viewModel.backPage();
    }

    @Override
    public void nextPage() {
        viewModel.nextPage();
    }

    @Override
    public void jumpPage(int page) {
        viewModel.loadAuthors(page);
    }
}

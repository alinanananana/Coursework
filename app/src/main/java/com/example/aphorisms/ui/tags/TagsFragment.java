package com.example.aphorisms.ui.tags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aphorisms.databinding.FragmentTagsBinding;
import com.example.aphorisms.domain.model.Tag;
import com.example.aphorisms.ui.MainActivity;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class TagsFragment extends Fragment implements TagClickListener {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private FragmentTagsBinding binding;
    private TagsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TagsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTagsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TagsView tagsView = binding.getRoot();

        tagsView.setAdapter(new TagsAdapter(this));
        tagsView.subscribe(viewModel.getTagsState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
        disposables.clear();
    }

    @Override
    public void onTagClick(Tag tag) {
        MainActivity activity = (MainActivity) requireActivity();

        activity.navigateToQuotesByTag(tag.getName());
    }
}

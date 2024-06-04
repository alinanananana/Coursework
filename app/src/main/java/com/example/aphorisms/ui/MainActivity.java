package com.example.aphorisms.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.aphorisms.R;
import com.example.aphorisms.databinding.ActivityMainBinding;
import com.example.aphorisms.ui.authors.AuthorsFragment;
import com.example.aphorisms.ui.quotes.author.QuotesByAuthorFragment;
import com.example.aphorisms.ui.quotes.favorite.QuotesByFavoriteFragment;
import com.example.aphorisms.ui.quotes.query.QuotesByQueryFragment;
import com.example.aphorisms.ui.quotes.tag.QuotesByTagFragment;
import com.example.aphorisms.ui.random.RandomFragment;
import com.example.aphorisms.ui.tags.TagsFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setBottomNavigation();

        if (savedInstanceState == null) {
            navigateTo(new QuotesByQueryFragment());
        }
    }

    private void setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private void navigateTo(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_holder, fragment);
        transaction.commit();
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.section_search) {
            navigateTo(new QuotesByQueryFragment());
        } else if (itemId == R.id.section_random) {
            navigateTo(new RandomFragment());
        } else if (itemId == R.id.section_authors) {
            navigateTo(new AuthorsFragment());
        } else if (itemId == R.id.section_tags) {
            navigateTo(new TagsFragment());
        } else if (itemId == R.id.section_favorite) {
            navigateTo(new QuotesByFavoriteFragment());
        } else {
            return false;
        }

        return true;
    }

    public void navigateToQuotesByAuthor(String author) {
        navigateTo(QuotesByAuthorFragment.newInstance(author));
    }

    public void navigateToQuotesByTag(String tag) {
        navigateTo(QuotesByTagFragment.newInstance(tag));
    }
}

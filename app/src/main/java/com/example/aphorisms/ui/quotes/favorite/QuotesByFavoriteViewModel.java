package com.example.aphorisms.ui.quotes.favorite;

import androidx.lifecycle.ViewModel;

import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;
import com.example.aphorisms.domain.usecase.DeleteQuoteFromFavoritesUseCase;
import com.example.aphorisms.domain.usecase.GetQuotesByFavoriteUseCase;
import com.example.aphorisms.domain.usecase.SaveQuoteToFavoritesUseCase;
import com.example.aphorisms.ui.quotes.QuoteClickListener;

import io.reactivex.rxjava3.core.Observable;

public class QuotesByFavoriteViewModel extends ViewModel implements QuoteClickListener {
    private final GetQuotesByFavoriteUseCase getQuotesByFavorite = new GetQuotesByFavoriteUseCase();
    private final SaveQuoteToFavoritesUseCase saveQuoteToFavorites = new SaveQuoteToFavoritesUseCase();
    private final DeleteQuoteFromFavoritesUseCase deleteQuoteFromFavorites = new DeleteQuoteFromFavoritesUseCase();

    @Override
    public void onQuoteFavoriteClick(Quote quote, boolean isChecked) {
        if (isChecked) {
            saveQuoteToFavorites.execute(quote);
        } else {
            deleteQuoteFromFavorites.execute(quote);
        }
    }

    public Observable<Resource<Paged<Quote>>> getQuotesState() {
        return getQuotesByFavorite.execute();
    }
}

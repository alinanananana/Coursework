package com.example.aphorisms.ui.quotes;

import com.example.aphorisms.domain.model.Quote;

public interface QuoteClickListener {
    void onQuoteFavoriteClick(Quote quote, boolean isChecked);
}

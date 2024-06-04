package com.example.aphorisms.data.local.mapper;

import com.example.aphorisms.data.local.model.LocalQuote;
import com.example.aphorisms.domain.model.Quote;

public class LocalQuoteMapper {
    public static Quote toDomain(LocalQuote localQuote) {
        return new Quote(localQuote.id, localQuote.content, localQuote.author);
    }

    public static LocalQuote toLocal(Quote quote) {
        LocalQuote localQuote = new LocalQuote();

        localQuote.id = quote.getId();
        localQuote.content = quote.getContent();
        localQuote.author = quote.getAuthor();

        return localQuote;
    }
}

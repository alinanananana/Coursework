package com.example.aphorisms.data.local.mapper;

import com.example.aphorisms.data.local.model.LocalQuote;
import com.example.aphorisms.domain.Resource;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;

import java.util.List;
import java.util.stream.Collectors;

public class LocalQuotesMapper {
    public static List<Quote> toDomain(List<LocalQuote> localQuotes) {
        return localQuotes.stream().map(LocalQuoteMapper::toDomain).collect(Collectors.toList());
    }

    public static Resource<Paged<Quote>> toOnePage(List<Quote> quotes) {
        Paged<Quote> paged = new Paged<>(1, 1, quotes);

        return Resource.success(paged);
    }
}

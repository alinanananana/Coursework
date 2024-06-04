package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkQuote;
import com.example.aphorisms.domain.model.Quote;

public class NetworkQuoteMapper {
    public static Quote toDomain(NetworkQuote networkQuote) {
        return new Quote(networkQuote.id, networkQuote.content, networkQuote.author);
    }
}

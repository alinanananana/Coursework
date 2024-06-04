package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkPaged;
import com.example.aphorisms.data.network.model.NetworkQuote;
import com.example.aphorisms.domain.model.Paged;
import com.example.aphorisms.domain.model.Quote;

import java.util.List;
import java.util.stream.Collectors;

public class NetworkPagedQuoteMapper {
    public static Paged<Quote> toDomain(NetworkPaged<NetworkQuote> networkPaged) {
        List<Quote> results = networkPaged
                .results
                .stream()
                .map(NetworkQuoteMapper::toDomain)
                .collect(Collectors.toList());

        return new Paged<>(networkPaged.page, networkPaged.totalPages, results);
    }
}

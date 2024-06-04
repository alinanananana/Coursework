package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkAuthor;
import com.example.aphorisms.data.network.model.NetworkPaged;
import com.example.aphorisms.domain.model.Author;
import com.example.aphorisms.domain.model.Paged;

import java.util.List;
import java.util.stream.Collectors;

public class NetworkPagedAuthorMapper {
    public static Paged<Author> toDomain(NetworkPaged<NetworkAuthor> networkPaged) {
        List<Author> results = networkPaged
                .results
                .stream()
                .map(NetworkAuthorMapper::toDomain)
                .collect(Collectors.toList());

        return new Paged<>(networkPaged.page, networkPaged.totalPages, results);
    }
}

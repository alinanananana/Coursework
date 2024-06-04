package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkAuthor;
import com.example.aphorisms.domain.model.Author;

public class NetworkAuthorMapper {
    public static Author toDomain(NetworkAuthor networkAuthor) {
        return new Author(networkAuthor.name, networkAuthor.description);
    }
}

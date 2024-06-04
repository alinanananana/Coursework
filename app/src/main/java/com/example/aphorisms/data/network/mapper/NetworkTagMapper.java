package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkTag;
import com.example.aphorisms.domain.model.Tag;

public class NetworkTagMapper {
    public static Tag toDomain(NetworkTag networkTag) {
        return new Tag(networkTag.name);
    }
}

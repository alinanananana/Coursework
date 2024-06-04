package com.example.aphorisms.data.network.mapper;

import com.example.aphorisms.data.network.model.NetworkTag;
import com.example.aphorisms.domain.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class NetworkTagsMapper {
    public static List<Tag> toDomain(List<NetworkTag> networkTags) {
        return networkTags
                .stream()
                .map(NetworkTagMapper::toDomain)
                .collect(Collectors.toList());
    }
}

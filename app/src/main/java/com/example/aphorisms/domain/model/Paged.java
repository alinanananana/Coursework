package com.example.aphorisms.domain.model;

import java.util.List;

public class Paged<T> {
    private final int page;
    private final int totalPages;
    private final List<T> results;

    public Paged(int page, int totalPages, List<T> results) {
        this.page = page;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public Paged<T> back() {
        return new Paged<>(page - 1, getTotalPages(), results);
    }

    public Paged<T> next() {
        return new Paged<>(page + 1, getTotalPages(), results);
    }

    public static Paged<Object> empty() {
        return new Paged<>(1, 1, null);
    }

    public static <T> Paged<Object> toEmpty(Paged<T> paged) {
        return new Paged<>(paged.getPage(), paged.getTotalPages(), null);
    }
}

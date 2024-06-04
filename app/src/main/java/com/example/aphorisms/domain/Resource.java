package com.example.aphorisms.domain;

public class Resource<T> {
    private final T data;
    private final boolean loading;

    public Resource(T data, boolean loading) {
        this.data = data;
        this.loading = loading;
    }

    public T getData() {
        return data;
    }

    public boolean isLoading() {
        return loading;
    }

    public boolean isSuccess() {
        return data != null;
    }

    public static <T> Resource<T> empty() {
        return new Resource<>(null, false);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(null, true);
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(data, false);
    }
}

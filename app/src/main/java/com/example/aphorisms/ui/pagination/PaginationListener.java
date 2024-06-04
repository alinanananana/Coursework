package com.example.aphorisms.ui.pagination;

public interface PaginationListener {
    void backPage();

    void nextPage();

    void jumpPage(int page);
}

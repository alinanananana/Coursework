package com.example.aphorisms.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aphorisms.data.local.model.LocalQuote;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(LocalQuote quote);

    @Delete
    Completable delete(LocalQuote quote);

    @Query("SELECT * FROM quotes WHERE id LIKE :id")
    Maybe<LocalQuote> getQuoteById(String id);

    @Query("SELECT * FROM quotes")
    Observable<List<LocalQuote>> getAll();
}

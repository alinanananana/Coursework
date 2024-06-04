package com.example.aphorisms.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.aphorisms.data.local.model.LocalQuote;

@Database(entities = {LocalQuote.class}, version = QuoteDatabase.VERSION)
public abstract class QuoteDatabase extends RoomDatabase {
    public static final String NAME = "aphorisms";
    public static final int VERSION = 1;

    public abstract QuoteDao quoteDao();
}

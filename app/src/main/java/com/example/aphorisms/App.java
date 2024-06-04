package com.example.aphorisms;

import android.app.Application;

import androidx.room.Room;

import com.example.aphorisms.data.local.QuoteDatabase;

public class App extends Application {
    private static QuoteDatabase quoteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        quoteDatabase = Room.databaseBuilder(this, QuoteDatabase.class, QuoteDatabase.NAME).build();
    }

    public static QuoteDatabase getQuoteDatabase() {
        return quoteDatabase;
    }
}

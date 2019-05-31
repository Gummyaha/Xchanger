package com.example.currencyapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorite",
        indices = {@Index("from_currency"), @Index("to_currency")})
public class FavoriteCurrency {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "from_currency")
    @NonNull
    public String from_currency;

    @NonNull
    public int from_currency_position;

    @ColumnInfo(name = "to_currency")
    @NonNull
    public String to_currency;

    @NonNull
    public int to_currency_position;

    public FavoriteCurrency(){

    }

    public FavoriteCurrency(@NonNull String from_currency, String to_currency, int from_currency_position, int to_currency_position) {
        this.from_currency = from_currency;
        this.to_currency = to_currency;
        this.from_currency_position=from_currency_position;
        this.to_currency_position=to_currency_position;

    }
}
package com.example.currencyapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteCurrencyDao {

    @Query("SELECT * FROM favorite WHERE id = :id LIMIT 1")
    FavoriteCurrency findDirectorById(int id);

    @Query("SELECT * FROM favorite WHERE from_currency = :from and to_currency = :to LIMIT 1")
    FavoriteCurrency findFavorite(String from,String to);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoriteCurrency... currency);

    @Delete
    int delete(FavoriteCurrency currency);

    @Query("DELETE FROM favorite")
    void deleteAll();

    @Query("SELECT * FROM favorite ")
    LiveData<List<FavoriteCurrency>> getAllFavorite();
}
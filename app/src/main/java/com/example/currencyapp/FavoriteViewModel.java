package com.example.currencyapp;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private FavoriteCurrencyDao favoriteCurrencyDao;
    private LiveData<List<FavoriteCurrency>> favoriteLiveData;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteCurrencyDao = AppDatabase.getDatabase(application).favoriteCurrencyDao();
        favoriteLiveData = favoriteCurrencyDao.getAllFavorite();
    }

    public LiveData<List<FavoriteCurrency>> getFavoriteList() {
        return favoriteLiveData;
    }

    public void insert(FavoriteCurrency... favorites) {
        favoriteCurrencyDao.insert(favorites);
    }

    public void update(FavoriteCurrency favorites) {
        favoriteCurrencyDao.update(favorites);
    }

    public void deleteAll() {
        favoriteCurrencyDao.deleteAll();
    }
}
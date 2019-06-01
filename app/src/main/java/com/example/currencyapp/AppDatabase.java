package com.example.currencyapp;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {FavoriteCurrency.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "appdatabse.db";

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("AppDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract FavoriteCurrencyDao favoriteCurrencyDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final FavoriteCurrencyDao favoriteCurrencyDao;

        public PopulateDbAsync(AppDatabase instance) {
            favoriteCurrencyDao = instance.favoriteCurrencyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteCurrencyDao.deleteAll();

            return null;
        }
    }
}
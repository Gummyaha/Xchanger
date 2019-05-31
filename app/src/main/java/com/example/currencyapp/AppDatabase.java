package com.example.currencyapp;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author Antonina
 */
@Database(entities = {FavoriteCurrency.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "movies.db";

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
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

    public void clearDb() {
        if (INSTANCE != null) {
            new PopulateDbAsync(INSTANCE).execute();
        }
    }

    public abstract FavoriteCurrencyDao favoriteCurrencyDao();

    //public abstract DirectorDao directorDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final FavoriteCurrencyDao favoriteCurrencyDao;
        //  private final DirectorDao directorDao;

        public PopulateDbAsync(AppDatabase instance) {
            favoriteCurrencyDao = instance.favoriteCurrencyDao();
            //directorDao = instance.directorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteCurrencyDao.deleteAll();
            /*directorDao.deleteAll();

            Director directorOne = new Director("Adam McKay");
            Director directorTwo = new Director("Denis Villeneuve");
            Director directorThree = new Director("Morten Tyldum");
            directorDao.insert(directorOne);
*/
           // FavoriteCurrency currencyOne = new FavoriteCurrency("USD", "SEK");
            //favoriteCurrencyDao.insert(currencyOne);
    /*        final int dIdTwo = (int) directorDao.insert(directorTwo);
            Movie movieTwo = new Movie("Arrival", dIdTwo);
            Movie movieThree = new Movie("Blade Runner 2049", dIdTwo);
            Movie movieFour = new Movie("Passengers", (int) directorDao.insert(directorThree));

            movieDao.insert(movieOne, movieTwo, movieThree, movieFour);*/

            return null;
        }
    }
}

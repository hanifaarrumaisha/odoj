package id.ac.ui.cs.mobileprogramming.hanifa.odoj.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.dao.TilawahDao;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.Tilawah;

@Database(entities = {Tilawah.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TilawahDao tilawahDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "jrsapp")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        PopulateDbAsync(AppDatabase db) {
//            define DAO
        }

        @Override
        protected Void doInBackground(final Void... params) {
            System.out.println("POPULATE DATABASE");
            return null;
        }
    }
}

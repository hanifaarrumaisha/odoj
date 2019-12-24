package id.ac.ui.cs.mobileprogramming.hanifa.odoj.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.dao.TilawahDao;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.Tilawah;

public class TilawahRepository {
    private TilawahDao tilawahDao;
    private LiveData<List<Tilawah>> mAllTilawah;

    public TilawahRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        tilawahDao = db.tilawahDao();

    }

    public void insert(Tilawah tilawah){
        new TilawahRepository.insertAsyncTask(tilawahDao).execute(tilawah);
    }

    public LiveData<List<Tilawah>> getAllTilawah() {
        return mAllTilawah;
    }

    private static class insertAsyncTask extends AsyncTask<Tilawah, Void, Void> {

        private TilawahDao mAsyncTaskDao;

        insertAsyncTask(TilawahDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Tilawah... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

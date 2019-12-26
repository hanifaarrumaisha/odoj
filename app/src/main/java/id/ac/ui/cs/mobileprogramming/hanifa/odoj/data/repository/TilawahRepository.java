package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.sql.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.AppDatabase;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dao.TilawahDao;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.Utils;

public class TilawahRepository {
    private TilawahDao tilawahDao;
    private LiveData<List<Tilawah>> mAllTilawah;
    private LiveData<Tilawah> mTodayTilawah;

    public TilawahRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        tilawahDao = db.tilawahDao();
        mAllTilawah = tilawahDao.getAllTilawahOrderByDate();
        mTodayTilawah = tilawahDao.getByDate(Utils.getDateTime());
    }

    public void insert(Tilawah tilawah){
        new TilawahRepository.insertAsyncTask(tilawahDao).execute(tilawah);
    }

    public LiveData<Tilawah> getTodayTilawah(){
        return tilawahDao.getByDate(Utils.getDateTime());
    }

    public LiveData<List<Tilawah>> getAllTilawah() {
        return mAllTilawah;
    }

    private static class insertAsyncTask extends AsyncTask<Tilawah, Void, LiveData<Tilawah>> {

        private TilawahDao mAsyncTaskDao;

        insertAsyncTask(TilawahDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected LiveData<Tilawah> doInBackground(Tilawah... params) {
            mAsyncTaskDao.deleteAll();
            System.out.println("COBA INSERT");
            System.out.println(params[0].getTanggal().toString());
            mAsyncTaskDao.insert(params[0]);
            return mAsyncTaskDao.getByDate(params[0].getTanggal());
        }
    }
}
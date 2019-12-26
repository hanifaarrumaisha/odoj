package id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.repository.TilawahRepository;

public class TilawahViewModel extends AndroidViewModel {
    private TilawahRepository tilawahRepository;
    private LiveData<List<Tilawah>> mAllTilawah;
    private LiveData<Tilawah> mTodayTilawah;

    public TilawahViewModel(Application application){
        super(application);
        tilawahRepository = new TilawahRepository(application);
        mAllTilawah = tilawahRepository.getAllTilawah();
        mTodayTilawah = tilawahRepository.getTodayTilawah();
    }

    public void insert(Tilawah tilawah){
        tilawahRepository.insert(tilawah);
    }

    public LiveData<List<Tilawah>> getAllTilawah() {
        return mAllTilawah;
    }

    public LiveData<Tilawah> getTodayTilawah(){
        return mTodayTilawah;
    };
}

package id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.Tilawah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.repository.TilawahRepository;

public class TilawahViewModel extends AndroidViewModel {
    private TilawahRepository tilawahRepository;
    private LiveData<List<Tilawah>> mAllTilawah;

    public TilawahViewModel(Application application){
        super(application);
        //TODO accountnya masih pake data dummy, harusnya ambil dari user saat ini
        tilawahRepository = new TilawahRepository(application);
        mAllTilawah = tilawahRepository.getAllTilawah();
    }

    public void insert(Tilawah tilawah){
        tilawahRepository.insert(tilawah);
    }
}

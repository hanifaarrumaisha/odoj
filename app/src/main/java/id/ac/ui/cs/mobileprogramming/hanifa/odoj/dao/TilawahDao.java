package id.ac.ui.cs.mobileprogramming.hanifa.odoj.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.Tilawah;

@Dao
public interface TilawahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tilawah tilawah);


}

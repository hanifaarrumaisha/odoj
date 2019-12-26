package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.sql.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;

@Dao
public interface TilawahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tilawah tilawah);

    @Query(value = "SELECT * FROM TILAWAH WHERE tilawah.tanggal=:date")
    LiveData<Tilawah> getByDate(Date date);

    @Query(value= "SELECT * FROM TiLAWAH ORDER BY tanggal")
    LiveData<List<Tilawah>> getAllTilawahOrderByDate();

    @Query("DELETE FROM tilawah")
    void deleteAll();
}
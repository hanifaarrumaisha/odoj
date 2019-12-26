package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "tilawah")
public class Tilawah {
    public Tilawah(@NonNull Date tanggal, Integer jmlHalaman) {
        this.tanggal = tanggal;
        this.jmlHalaman = jmlHalaman;
    }

    @PrimaryKey
    @NonNull
    private Date tanggal;

    @NonNull
    private Integer jmlHalaman;

    @NonNull
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(@NonNull Date tanggal) {
        this.tanggal = tanggal;
    }

    @NonNull
    public Integer getJmlHalaman() {
        return jmlHalaman;
    }

    public void setJmlHalaman(@NonNull Integer jmlHalaman) {
        this.jmlHalaman = jmlHalaman;
    }

    @NonNull
    @Override
    public String toString() {
        return "tanggal: "+tanggal.toString()+" jmlHalaman: "+String.valueOf(jmlHalaman);
    }
}

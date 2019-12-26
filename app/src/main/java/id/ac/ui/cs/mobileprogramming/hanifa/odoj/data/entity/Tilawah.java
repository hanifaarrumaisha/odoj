package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "tilawah")
public class Tilawah {
    public Tilawah(@NonNull Date tanggal, @NonNull Integer jmlHalaman, String surah, int juz, int ayah, int page) {
        this.tanggal = tanggal;
        this.jmlHalaman = jmlHalaman;
        this.surah = surah;
        this.juz = juz;
        this.ayah = ayah;
        this.page = page;
    }

    @PrimaryKey
    @NonNull
    private Date tanggal;

    @NonNull
    private Integer jmlHalaman;

    private String surah;
    private int juz;
    private int ayah;
    private int page;

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

    public String getSurah() {
        return surah;
    }

    public void setSurah(String surah) {
        this.surah = surah;
    }

    public int getJuz() {
        return juz;
    }

    public void setJuz(int juz) {
        this.juz = juz;
    }

    public int getAyah() {
        return ayah;
    }

    public void setAyah(int ayah) {
        this.ayah = ayah;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

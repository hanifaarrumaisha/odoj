package id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tilawah")
public class PageTilawah {
    public PageTilawah(int page, int juz, @NonNull String surah, int ayah) {
        this.page = page;
        this.juz = juz;
        this.surah = surah;
        this.ayah = ayah;
    }

    @NonNull
    @PrimaryKey
    private int page;

    @NonNull
    private int juz;

    @NonNull
    private String surah;

    @NonNull
    private int ayah;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getJuz() {
        return juz;
    }

    public void setJuz(int juz) {
        this.juz = juz;
    }

    @NonNull
    public String getSurah() {
        return surah;
    }

    public void setSurah(@NonNull String surah) {
        this.surah = surah;
    }

    public int getAyah() {
        return ayah;
    }

    public void setAyah(int ayah) {
        this.ayah = ayah;
    }
}

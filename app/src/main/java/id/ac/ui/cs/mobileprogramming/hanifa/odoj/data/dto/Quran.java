package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Quran {
    @Expose
    private int number;

    @Expose
    private List<Ayah> ayahs;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Ayah> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayah> ayahs) {
        this.ayahs = ayahs;
    }
}

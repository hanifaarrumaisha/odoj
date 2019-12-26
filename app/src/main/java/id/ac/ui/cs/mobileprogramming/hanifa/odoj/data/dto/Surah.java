package id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto;

import com.google.gson.annotations.Expose;

public class Surah {
    @Expose
    private int number;
    @Expose
    private String englishName;
    @Expose
    private String englishNameTranslation;
    @Expose
    private int numberOfAyahs;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public void setEnglishNameTranslation(String englishNameTranslation) {
        this.englishNameTranslation = englishNameTranslation;
    }

    public int getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public void setNumberOfAyahs(int numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }
}

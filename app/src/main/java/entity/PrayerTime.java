package entity;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PrayerTime {
    private Calendar date_for = Calendar.getInstance();
    private Calendar fajr = Calendar.getInstance();
    private Calendar shurooq = Calendar.getInstance();
    private Calendar dhuhr = Calendar.getInstance();
    private Calendar asr = Calendar.getInstance();
    private Calendar maghrib = Calendar.getInstance();
    private Calendar isha = Calendar.getInstance();

    public PrayerTime(PrayerTimeDTO DTO) {
        this.parseData(DTO);
    }

    private void parseData(PrayerTimeDTO dto) {
        HashMap<Integer, Integer> time = parseTime(dto.getFajr());
        setTime(fajr);
        time = parseTime(dto.getShurooq());
        setTime(shurooq);
        time = parseTime(dto.getDhuhr());
        setTime(dhuhr);
        time = parseTime(dto.getAsr());
        setTime(asr);
        time = parseTime(dto.getMaghrib());
        setTime(maghrib);
        time = parseTime(dto.getIsha());
        setTime(isha);
    }

    private int getAMPM(String ampm){
        switch (ampm){
            case "am":
                return Calendar.AM;
            case "pm":
                return Calendar.PM;
            default:
                return -1;
        }
    }

    private void setTime(Calendar time){
        time.set(Calendar.HOUR, time.get(Calendar.HOUR));
        time.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        time.set(Calendar.AM_PM, time.get(Calendar.AM_PM));
    }

    private HashMap parseTime(String time){
        HashMap map = new HashMap<Integer, Integer>();
        String[] time_clean = time.split(" ");
        map.put(Calendar.AM_PM, Integer.parseInt(time_clean[1]));
        time_clean = time_clean[0].split(":");
        map.put(Calendar.HOUR, Integer.parseInt(time_clean[0]));
        map.put(Calendar.MINUTE, Integer.parseInt(time_clean[1]));
        return map;
    }

    public Calendar getDate_for() {
        return date_for;
    }

    public void setDate_for(Calendar date_for) {
        this.date_for = date_for;
    }

    public Calendar getFajr() {
        return fajr;
    }

    public void setFajr(Calendar fajr) {
        this.fajr = fajr;
    }

    public Calendar getShurooq() {
        return shurooq;
    }

    public void setShurooq(Calendar shurooq) {
        this.shurooq = shurooq;
    }

    public Calendar getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(Calendar dhuhr) {
        this.dhuhr = dhuhr;
    }

    public Calendar getAsr() {
        return asr;
    }

    public void setAsr(Calendar asr) {
        this.asr = asr;
    }

    public Calendar getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(Calendar maghrib) {
        this.maghrib = maghrib;
    }

    public Calendar getIsha() {
        return isha;
    }

    public void setIsha(Calendar isha) {
        this.isha = isha;
    }
}

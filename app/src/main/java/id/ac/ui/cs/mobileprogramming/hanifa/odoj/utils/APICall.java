package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto.PrayerTime;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.PrayerNotification;

public class APICall {
    Context context;

    public static final int SHUROOQ = 1;
    public static final int DHUHR = 2;
    public static final int ASR = 3;
    public static final int MAGHRIB = 4;
    public static final int ISHA = 5;

    public APICall(Context context) {
        this.context = context;
    }

    public void requestPrayerTimes(){
        String url = "https://muslimsalat.com/jakarta/daily.json?key=283714fbf745c829c7163e0b9dfa0cbf";
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,(JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    PrayerTime[] prayerTimeDTO = gson.fromJson(response.getString("items"), PrayerTime[].class);
                    id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.PrayerTime prayerTime = new id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.PrayerTime(prayerTimeDTO[0]);
                    System.out.println("REQUEST API");
                    System.out.println(prayerTime.getDhuhr());
                    createNofication(prayerTime, context);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }},  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Can't retrieve API", Toast.LENGTH_LONG).show();
            }});
        rq.add(jsonObjectRequest);
    }

    public void requestQuranPage(int page){
        String url = "http://api.alquran.cloud/v1/page/"+String.valueOf(page)+"/en.asad";
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,(JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String quran = response.getString("data");
                    System.out.println("REQUEST API QURAN");
                    System.out.println(quran);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }},  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Can't retrieve API", Toast.LENGTH_LONG).show();
            }});
        rq.add(jsonObjectRequest);
    }

    private void createNofication(id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.PrayerTime prayerTime, Context context) {
        PrayerNotification notifShurooq = new PrayerNotification(context, prayerTime.getShurooq(), SHUROOQ);
        PrayerNotification notifDhuhr = new PrayerNotification(context, prayerTime.getDhuhr(), DHUHR);
        PrayerNotification notifAsr = new PrayerNotification(context, prayerTime.getAsr(), ASR);
        System.out.println("CEK PRAYER TIME NOTIF");
        System.out.println(prayerTime.getIsha().getTime());
        PrayerNotification notifMaghrib = new PrayerNotification(context, prayerTime.getMaghrib(), MAGHRIB);
        System.out.println(prayerTime.getIsha().getTime());
        PrayerNotification notifIsha = new PrayerNotification(context, prayerTime.getIsha(), ISHA);
    }
}

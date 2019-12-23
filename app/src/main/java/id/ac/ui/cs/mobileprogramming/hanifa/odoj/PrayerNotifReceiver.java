package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import entity.PrayerTime;
import entity.PrayerTimeDTO;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.PrayerNotification;

public class PrayerNotifReceiver extends BroadcastReceiver {
    private static final String TAG = "DateChangeReceiver";
    private static final int SHUROOQ = 1;
    private static final int DHUHR = 1;
    private static final int ASR = 1;
    private static final int MAGHRIB = 1;
    private static final int ISHA = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        testReceiver(context, intent);
        AsyncRequest asyncRequest = new AsyncRequest(context);
        asyncRequest.execute("");
    }

    public void testReceiver(Context context, Intent intent){
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        System.out.println("BROADCAST RECEIVER NYALA");
        Toast.makeText(context, "Broadcast Receiver Nyala", Toast.LENGTH_LONG).show();
    }


    private class AsyncRequest extends AsyncTask<String, String, String> {
        public Context context;

        public AsyncRequest(Context context)
        {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... String) {
            requestPrayerTimes(context);
            return "";
        }
    }

    public void requestPrayerTimes(final Context context){
        String url = "https://muslimsalat.com/jakarta/daily.json?key=283714fbf745c829c7163e0b9dfa0cbf";
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,(JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    PrayerTimeDTO prayerTimeDTO = gson.fromJson(response.getString("items"), PrayerTimeDTO.class);
                    PrayerTime prayerTime = new PrayerTime(prayerTimeDTO);
                    createNofication(prayerTime, context);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        rq.add(jsonObjectRequest);
    }


    private void createNofication(PrayerTime prayerTime, Context context) {
        PrayerNotification notifShurooq = new PrayerNotification(context, prayerTime.getShurooq(), SHUROOQ);
        PrayerNotification notifDhuhr = new PrayerNotification(context, prayerTime.getDhuhr(), DHUHR);
        PrayerNotification notifAsr = new PrayerNotification(context, prayerTime.getAsr(), ASR);
        PrayerNotification notifMaghrib = new PrayerNotification(context, prayerTime.getMaghrib(), MAGHRIB);
        PrayerNotification notifIsha = new PrayerNotification(context, prayerTime.getIsha(), ISHA);
    }
}

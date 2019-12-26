package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dao.TilawahDao;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.PrayerTime;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.PrayerTimeDTO;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.PrayerNotification;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.APICall;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.Utils;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel.TilawahViewModel;

public class PrayerNotifReceiver extends BroadcastReceiver {
    private static final String TAG = "DateChangeReceiver";
    private TilawahViewModel mViewModel;

    @Override
    public void onReceive(Context context, Intent intent) {
        testReceiver(context, intent);
//        TODO CHECK CONNECTION
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
            APICall apiCall = new APICall(context);
            apiCall.requestPrayerTimes();
            return "";
        }
    }




}

package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DateChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "DateChangeReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        testReceiver(context, intent);
        AsyncRequest asyncRequest = new AsyncRequest((Activity) context);
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
        public Activity mActivity;

        public AsyncRequest(Activity a)
        {
            this.mActivity = a;
        }

        @Override
        protected String doInBackground(String... String) {
            String url = "https://muslimsalat.com/jakarta/daily.json?key=283714fbf745c829c7163e0b9dfa0cbf";
            RequestQueue rq = Volley.newRequestQueue(mActivity);
            rq.start();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,(JSONObject) null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        System.out.println(response.getString("items"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            rq.add(jsonObjectRequest);
            return "";
        }
    }
}

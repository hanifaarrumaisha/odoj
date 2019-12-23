package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CALENDAR = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("OHOHO");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALENDAR)) {
                System.out.println("REQUEST PERMISSION UI");
            } else {
                // Check Permissions Now
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        REQUEST_READ_CALENDAR);
            }
        } else {
            // permission has been granted, continue as usual
            AsyncRequest asyncRequest = new AsyncRequest(this);
            asyncRequest.execute("");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CALENDAR:
                if (grantResults.length == 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("HEHEHEHE");
                    // We can now safely use the API we requested access to
                    AsyncRequest asyncRequest = new AsyncRequest(this);
                    asyncRequest.execute("");
                } else {
                    // Permission was denied or request was cancelled
                }

        }
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

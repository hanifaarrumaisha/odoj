package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.NotificationPublisher;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.PermissionManager;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.PermissionRationale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CALENDAR = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver();

        PermissionManager permissionManager = new PermissionManager(this, Manifest.permission.READ_CALENDAR);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALENDAR)) {
                System.out.println("RATIONAL");
                PermissionRationale permissionRationale = new PermissionRationale(this, Manifest.permission.READ_CALENDAR);
                runOnUiThread(permissionRationale);
            } else {
                // Check Permissions Now
                permissionManager.requestPermissions(this, Manifest.permission.READ_CALENDAR);
            }
        } else {
            // permission has been granted, continue as usual
            // TODO lakuin cek calendar
        }
    }

    private void registerReceiver() {
        BroadcastReceiver br = new PrayerNotifReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        this.registerReceiver(br, filter);

        this.registerReceiver(new NotificationPublisher(), new IntentFilter());
    }

    public AlertDialog.Builder showRationaleDialog(final String permission) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (permission){
            case Manifest.permission.READ_CALENDAR:
                builder.setMessage(R.string.dialog_rationale_read_calendar)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });
                // Create the AlertDialog object and return it
                return builder;
        }
        return null;
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
                    // TODO lakuin cek calendar
                } else {
                    // Permission was denied or request was cancelled
                }
        }
    }
}


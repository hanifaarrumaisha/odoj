package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.connectivity.ConnectionHelper;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.NotificationPublisher;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.openGL.OpenGLView;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.permission.PermissionHelper;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.permission.PermissionRationale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CALENDAR = 200;
    private BroadcastReceiver prayerNotifReceiver;
    private NotificationPublisher notificationPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver();

        PermissionHelper permissionManager = new PermissionHelper(this, Manifest.permission.READ_CALENDAR);

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
        ConnectionHelper connectionHelper = new ConnectionHelper(this);
        connectionHelper.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {

                    Toast.makeText(getApplicationContext(), "Connected to internet!", Toast.LENGTH_SHORT).show();

                    prayerNotifReceiver = new PrayerNotifReceiver();
                    IntentFilter filter = new IntentFilter();

                    filter.addAction(Intent.ACTION_DATE_CHANGED);
                    getApplicationContext().registerReceiver(prayerNotifReceiver, filter);

                    notificationPublisher = new NotificationPublisher();
                    getApplicationContext().registerReceiver(notificationPublisher, new IntentFilter());
                } else {
                    Toast.makeText(getApplicationContext(), "WARNING! You need to connect to internet to get some feature", Toast.LENGTH_SHORT).show();
                }
            }
        });

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


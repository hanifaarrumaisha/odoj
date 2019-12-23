package id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    public static final String NOTIFICATION_ID = "notification-id";
    public static final String NOTIFICATION = "notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationHelper notificationHelper = new NotificationHelper(this);

        Intent intent = getIntent();

        //to show the notification
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        notificationHelper.getManager().notify(id, notification);
        System.out.println("TEST");
    }
}

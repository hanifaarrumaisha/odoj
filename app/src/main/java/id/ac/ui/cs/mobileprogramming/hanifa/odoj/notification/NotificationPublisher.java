package id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class NotificationPublisher extends BroadcastReceiver {
    public static final String NOTIFICATION_ID = "notification-id";
    public static final String NOTIFICATION = "notification";

    public void onReceive(Context context, Intent intent) {

        NotificationHelper notificationHelper = new NotificationHelper(context);

        //to show the notification
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        notificationHelper.getManager().notify(id, notification);
    }
}

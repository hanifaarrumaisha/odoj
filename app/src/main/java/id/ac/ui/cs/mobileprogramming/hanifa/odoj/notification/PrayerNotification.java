package id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import static id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification.NotificationHelper.channelID;

public class PrayerNotification {
    private final int NotID = 1;
    Context context;

    public PrayerNotification(Context context, Calendar prayerTime, int prayerType) {
        this.context = context;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, prayerTime.get(Calendar.HOUR));
        intent.putExtra(NotificationPublisher.NOTIFICATION, getNotificationPrayerTime());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, prayerType, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, prayerTime.getTimeInMillis(),
                pendingIntent);
    }

    public Notification getNotificationPrayerTime() {
        System.out.println("COBA");
        // Create an explicit intent for an Activity in your app
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context.getApplicationContext(), channelID)
                .setContentTitle("It's Quran Time!")
                .setContentText("Yuk jangan lupa tilawah 2 lembar:)")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setAutoCancel(true);

        return nb.build();
    }
}

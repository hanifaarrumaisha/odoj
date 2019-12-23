package id.ac.ui.cs.mobileprogramming.hanifa.odoj.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class NotificationService extends Service {
    Context context;
    public static final String NOTIFICATION_ID = "notification-id";
    public static final String NOTIFICATION = "notification";

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;

    public NotificationService(Context base) {
        super();
        this.context = base;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationHelper notificationHelper = new NotificationHelper(context);

        //to show the notification
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        notificationHelper.getManager().notify(id, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.enableVibration(true);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

}

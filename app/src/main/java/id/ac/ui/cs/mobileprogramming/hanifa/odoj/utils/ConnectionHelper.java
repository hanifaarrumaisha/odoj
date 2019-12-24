package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;

public class ConnectionHelper extends ContextWrapper {
    ConnectivityManager mManager;

    public ConnectionHelper(Context base){
        super(base);
    }

    public ConnectivityManager getManager(){
        if (mManager == null) {
            mManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        return mManager;
    }
}

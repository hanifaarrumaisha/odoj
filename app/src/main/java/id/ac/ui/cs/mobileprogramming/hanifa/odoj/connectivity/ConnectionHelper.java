package id.ac.ui.cs.mobileprogramming.hanifa.odoj.connectivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.lifecycle.LiveData;

public class ConnectionHelper extends LiveData<Boolean>{
    Context context;
    ConnectivityManager mManager;
    MyNetworkCallback networkCallback;

    public ConnectionHelper(Context context){
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = new MyNetworkCallback();
        }
        onActive();
    }

    public ConnectivityManager getManager(){
        if (mManager == null) {
            mManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        return mManager;
    }

    public boolean isOnline() {
        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    protected void onActive() {
        updateConnection();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getManager().registerDefaultNetworkCallback(networkCallback);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkRequest networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .build();
            getManager().registerNetworkCallback(networkRequest, networkCallback);
        }
    }



    private void updateConnection() {
        if (getManager() != null) {
            NetworkInfo activeNetwork = getManager().getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                postValue(true);
            }else{
                postValue(false);
            }
        }

    }

    class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(Network network) {
            if (network != null) {
                // set a value for live data using when not in main thread
                postValue(true);
            }
        }

        @Override
        public void onLost(Network network) {
            // set a value for live data using when not in main thread
            postValue(false);
//            TODO create IPC to enable the data
        }

    }
}

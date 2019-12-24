package id.ac.ui.cs.mobileprogramming.hanifa.odoj.permission;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.ActivityCompat;

public class PermissionHelper extends ContextWrapper {
    Activity activity;
    String PermissionType;
    private static final int REQUEST_READ_CALENDAR = 200;

    public PermissionHelper(Context base, String PermissionType){
        super(base);
        activity = activity;
    }

    public void requestPermissions(Activity activity, String permission){
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                REQUEST_READ_CALENDAR);
    }



}

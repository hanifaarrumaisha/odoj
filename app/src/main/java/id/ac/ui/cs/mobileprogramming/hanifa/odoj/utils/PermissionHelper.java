package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.R;

public class PermissionHelper {
    Activity activity;
    String PermissionType;
    private static final int REQUEST_READ_CALENDAR = 200;

    public PermissionHelper(Activity activity, String PermissionType){
        activity = activity;
    }

    public void requestPermissions(Activity activity, String permission){
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                REQUEST_READ_CALENDAR);
    }

}

package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.R;

public class PermissionManager {
    Activity activity;
    String PermissionType;
    private static final int REQUEST_READ_CALENDAR = 200;

    public PermissionManager(Activity activity, String PermissionType){
        activity = activity;
    }

    public void requestPermissions(Activity activity, String permission){
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                REQUEST_READ_CALENDAR);
    }

    public Dialog showRationaleDialog(final Activity activity, final String permission) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        switch (permission){
            case Manifest.permission.READ_CALENDAR:
                builder.setMessage(R.string.dialog_rationale_read_calendar)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                requestPermissions(activity, permission);
                            }
                        });
                // Create the AlertDialog object and return it
                return builder.create();
        }
        return null;
    }

}

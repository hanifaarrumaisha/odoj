package id.ac.ui.cs.mobileprogramming.hanifa.odoj.permission;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.lang.Runnable;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.R;

public class PermissionRationale implements Runnable {
    Context context;
    String permissionType;

    public PermissionRationale(Context context, String permissionType){
        this.context = context;
        this.permissionType = permissionType;
    }
    @Override
    public void run() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        switch (permissionType){
            case Manifest.permission.READ_CALENDAR:
                builder.setMessage(R.string.dialog_rationale_read_calendar)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                PermissionHelper permissionManager = new PermissionHelper((Activity) context, Manifest.permission.READ_CALENDAR);
                                permissionManager.requestPermissions((Activity) context, Manifest.permission.READ_CALENDAR);
                                return;
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


        }
    }
}

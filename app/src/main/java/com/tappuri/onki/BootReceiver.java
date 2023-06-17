package com.tappuri.onki;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            // Call your desired function here
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.copyDatabaseFromAssets();
        }
    }
}
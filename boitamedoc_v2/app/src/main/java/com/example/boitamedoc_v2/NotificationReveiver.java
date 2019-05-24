package com.example.boitamedoc_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReveiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toatsMessage");
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}

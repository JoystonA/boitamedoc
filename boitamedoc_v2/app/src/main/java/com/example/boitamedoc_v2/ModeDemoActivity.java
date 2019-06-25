package com.example.boitamedoc_v2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class ModeDemoActivity extends AppCompatActivity implements View.OnClickListener {

    BluetoothSPP bluetooth;
    final String ON = "manuel";
    final String OFF = "Case 0";
    final String NOTIFICATION = "Case 9";

    public static String Title_Notification;
    public static String Message_Notification;
    Button manuel_on_button;
    Button manuel_off_button;
    Button notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        setTitle("Mode Démo");

        bluetooth = App.bluetooth_main;
        manuel_on_button = (Button) findViewById(R.id.manuel_on);
        manuel_off_button = (Button) findViewById(R.id.manuel_off);
        notification = (Button) findViewById(R.id.notification);

        manuel_on_button.setOnClickListener(this);
        manuel_off_button.setOnClickListener(this);
        notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manuel_on:
                bluetooth.send(ON, true);
                break;
            case R.id.manuel_off:
                bluetooth.send(OFF, true);
                break;
            case R.id.notification:
                createNotification();
                bluetooth.send(NOTIFICATION, true);
                break;
        }
    }

    private void createNotification() {
        Title_Notification = "Votre prise de médicament de 9h30";
        Message_Notification = "2 comprimés de DOLIPRANE 500mg | CASE 1";
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            // Perform the operation associated with our pendingIntent
            pending.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
package com.example.boitamedoc_v2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class App extends Application {

    static BluetoothSPP bluetooth_main;
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
        bluetooth_main = new BluetoothSPP(this);
        bluetooth_main.startService(true);
        int state = bluetooth_main.getServiceState();
        System.out.println("state = " + state);

        if (!bluetooth_main.isBluetoothEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth non disponible !", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Bluetooth disponible !", Toast.LENGTH_SHORT).show();
        }
        onStart();
        int state2 = bluetooth_main.getServiceState();
        System.out.println("state = " + state2);
        bluetooth_main.connect("00:06:66:6D:F1:75");
    }
    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel1",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("Notification de Prise de Médicament");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel2",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel2.setDescription("Notification de Prise de Médicament");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel2);

        }
    }

    public void onStart() {
        if (!bluetooth_main.isBluetoothEnabled()) {
            bluetooth_main.enable();
        } else {
            if (!bluetooth_main.isServiceAvailable()) {
                bluetooth_main.setupService();
                bluetooth_main.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }
}

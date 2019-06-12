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

        createNotificationChannel();

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


        bluetooth_main.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext(),"Connecté à " + name,Toast.LENGTH_SHORT).show();
            }
            public void onDeviceDisconnected()
            {
                Toast.makeText(getApplicationContext(),"Connexion perdu",Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext(),"Impossible de se connecter",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel1";
            String description = "Notification de Prise de Médicament";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_1_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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

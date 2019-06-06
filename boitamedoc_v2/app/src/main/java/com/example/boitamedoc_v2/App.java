package com.example.boitamedoc_v2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class App extends Application {
    public static final String URL_BDD = "jdbc:mysql://185.31.40.18:3306/boitamedmxadmin_databases";
    public static final String user = "184284";
    public static final String pawd = "E3esieeboitamx2019";
    public static int id_gestionnaire;
    public static int id_patient;
    public static Connection conn;
    static BluetoothSPP bluetooth_main;
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        //Notification
        createNotificationChannels();

        //Connexion BDD
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("test", "DRIVER OK");

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.d("test", "THREAD OK");

            //conn = DriverManager.getConnection(URL_BDD, user, pawd);
            Log.d("test", "Connexion réussie" + conn);
        } catch (Exception e) {
            Log.d("test", "onCreate: " + e.getMessage() + " || " + e.getCause() + " || " + e.getClass());
        }

        //Bluetooth
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

        /*if(bluetooth_main.getState!=3){
            while(state2!=3){bluetooth_main.connect("00:06:66:6D:F1:75");}
        }*/
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

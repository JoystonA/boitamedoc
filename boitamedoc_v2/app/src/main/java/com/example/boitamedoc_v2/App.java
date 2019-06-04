package com.example.boitamedoc_v2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;

public class App extends Application {
    public static final String URL_BDD = "jdbc:mysql://185.31.40.18:3306/boitamedmxadmin_databases";
    public static final String user = "184284";
    public static final String pawd = "E3esieeboitamx2019";
    public static int id_gestionnaire;
    public static Connection conn;
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
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
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
}

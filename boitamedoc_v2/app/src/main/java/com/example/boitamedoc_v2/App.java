package com.example.boitamedoc_v2;

import android.app.Application;
import java.sql.Connection;
import java.sql.DriverManager;

public class App extends Application {
    public static final String URL_BDD = "https://phpmyadmin.cluster021.hosting.ovh.net/";
    public static final String user = "boitamedmxadmin";
    public static final String pawd = "E3esieeboitamx2019";
    public static Connection  conn;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            conn = DriverManager.getConnection(URL_BDD, user, pawd);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

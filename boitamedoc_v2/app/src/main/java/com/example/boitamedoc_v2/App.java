package com.example.boitamedoc_v2;

import android.app.Application;
import android.util.Log;

import java.sql.*;

public class App extends Application {
    public static final String URL_BDD = "jdbc:mysql://boitamedmxadmin.mysql.db" ;
    public static final String user = "boitamedmxadmin";
    public static final String pawd = "E3esieeboitamx2019";
    public static Connection  conn;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("Théo", "onCreate: DRIVER OK");
            conn = DriverManager.getConnection(URL_BDD, user, pawd);
        }
        catch (Exception e){
            Log.d("Théo test", e.getMessage());
        }
    }
}

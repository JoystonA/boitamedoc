package com.example.boitamedoc_v2;

import android.app.Application;
import android.util.Log;

import java.sql.*;

public class App extends Application {
    public static final String URL_BDD = "jdbc:mysql://185.31.40.43:3306/boitamedmxadmin_databases" ;
    public static final String user = "184284";
    public static final String pawd = "E3esieeboitamx2019";
    public static Connection  conn;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
                Class.forName("com.mysql.jdbc.Driver");
            Log.d("Théo", "onCreate: DRIVER OK");
            conn = DriverManager.getConnection(URL_BDD, user, pawd);
            Log.d("Théo", "onCreate: CONNEXION OK");
        }
        catch (Exception e){
            Log.d("Théo test", e.getMessage() + " "+ e.getClass() + " " + e.getCause());
        }
    }
}

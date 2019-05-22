package com.example.boitamedoc_v2;

import android.app.Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT * FROM medicament WHERE id_medoc='3400921600506'");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            System.out.println("\n**********************************");
            //On affiche le nom des colonnes
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

            System.out.println("\n**********************************");

            while(result.next()){
                for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                    System.out.print("\t" + result.getObject(i).toString() + "\t |");

                System.out.println("\n---------------------------------");

            }

            result.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

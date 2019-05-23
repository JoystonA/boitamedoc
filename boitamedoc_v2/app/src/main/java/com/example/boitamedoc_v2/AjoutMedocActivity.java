package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.boitamedoc_v2.App.conn;

public class AjoutMedocActivity extends AppCompatActivity {
    private TextView testTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutmedoc);

    testTxt = (TextView) findViewById(R.id.testTxt);
        try {
            Statement state = conn.createStatement();

            ResultSet result = state.executeQuery("SELECT COUNT(*) FROM boitamedmxadmin.medicament");

            ResultSetMetaData resultMeta = result.getMetaData();
            while(result.next()){
                testTxt.setText("Test"+ result.getObject(0).toString() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // if(getIntent().getStringExtra("value")!= null) {
       // testTxt.setText("QRCODE = "+ getIntent().getStringExtra("value"));
    }
    //else testTxt.setText("CA MARCHE PAS");
    //}

}

package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class CalibrageActivity extends AppCompatActivity {

    private TextView masseUnComprimé;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrage);
        setTitle("Calibrage Capteur de Masse");
        masseUnComprimé = (TextView) findViewById(R.id.masseUnComprimé);
        messageMasse();
    }

    public void messageMasse() {

        App.bluetooth_main.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                try {
                    float masse = Float.parseFloat(message);
                    float masse_mg = masse / 100;
                    String masse_mg_string = Float.toString(masse_mg);
                    masseUnComprimé.setText("Le comprimé pèse "+masse_mg_string+" mg");
                }
                catch (NumberFormatException exception){}

            }
        });
    }

    /*public void calculNbrComprimé(String message){


    }*/
}

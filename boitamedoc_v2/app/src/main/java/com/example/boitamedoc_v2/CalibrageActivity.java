package com.example.boitamedoc_v2;

import android.content.Intent;
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
                masseUnComprimé.setText(message+" g");
            }
        });
    }
}

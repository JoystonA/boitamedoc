package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class ModeDemoActivity extends AppCompatActivity implements View.OnClickListener {

    BluetoothSPP bluetooth;
    final String AUTO = "1";
    final String ON = "2";
    final String OFF = "3";

    Button auto_button;
    Button manuel_on_button;
    Button manuel_off_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        setTitle("Mode Démo");

        bluetooth = App.bluetooth_main;
        auto_button = (Button) findViewById(R.id.auto);
        manuel_on_button = (Button) findViewById(R.id.manuel_on);
        manuel_off_button = (Button) findViewById(R.id.manuel_off);

        auto_button.setOnClickListener(this);
        manuel_on_button.setOnClickListener(this);
        manuel_off_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto:
                bluetooth.send(AUTO, true);
                break;
            case R.id.manuel_on:
                bluetooth.send(ON, true);
                break;
            case R.id.manuel_off:
                bluetooth.send(OFF, true);
                break;
        }
    }

    /* public void recupMessage(){
        App.bluetooth_main.

    }*/
}
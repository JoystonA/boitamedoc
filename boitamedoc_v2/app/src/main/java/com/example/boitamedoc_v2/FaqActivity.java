package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.boitamedoc_v2.R;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class FaqActivity extends AppCompatActivity implements View.OnClickListener {

    BluetoothSPP bluetooth;
    final String ON = "1";
    final String OFF = "0";

    Button on;
    Button off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faq);
        setTitle("Foire aux Questions");

        //bluetooth=BluetoothConnexionActivity.App.bluetooth_main;
        on = (Button) findViewById(R.id.on2);
        off = (Button) findViewById(R.id.off2);

        on.setOnClickListener(this);
        off.setOnClickListener(this);

    }

    @Override
        public void onClick(View v) {
        switch (v.getId()) {
            case R.id.on2:
                bluetooth.send(ON, true);
                break;

            case R.id.off2:
                bluetooth.send(OFF, true);
                break;

        }



    }
}



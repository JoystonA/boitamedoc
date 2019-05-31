package com.example.boitamedoc_v2;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class BluetoothConnexionActivity extends AppCompatActivity {

    final String ON = "1";
    final String OFF = "0";
    //static BluetoothSPP bluetooth;

    Button connect;
    Button on;
    Button off;
    Button accueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_bluetooth);

        //bluetooth = App.bluetooth_main;

        connect = (Button) findViewById(R.id.connect);
        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        accueil = (Button) findViewById(R.id.accueil);

        if (!App.bluetooth_main.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "Bluetooth non disponible !", Toast.LENGTH_SHORT).show();
            finish();
        }

        App.bluetooth_main.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                connect.setText("Connecté à " + name);
                Toast.makeText(getApplicationContext(),"Connecté à " + name,Toast.LENGTH_SHORT).show();

            }

            public void onDeviceDisconnected() {
                connect.setText("Connexion perdu");
            }

            public void onDeviceConnectionFailed() {
                connect.setText("Impossible de se connecter");
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bluetooth.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }*/
                App.bluetooth_main.autoConnect("BoitaMedoc");
                boolean connection =App.bluetooth_main.isAutoConnecting();
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.bluetooth_main.send(ON, true);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.bluetooth_main.send(OFF, true);
            }
        });

        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bluetooth.connect("00:06:66:6D:F1:75");
                //onDestroy();
                openAccueil();
            }
        });
        App.bluetooth_main.autoConnect("BoitaMedoc");
        boolean connection =App.bluetooth_main.isAutoConnecting();
    }

    public void onStart() {
        super.onStart();
        if (!App.bluetooth_main.isBluetoothEnabled()) {
            App.bluetooth_main.enable();
        } else {
            if (!App.bluetooth_main.isServiceAvailable()) {
                App.bluetooth_main.setupService();
                App.bluetooth_main.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }


    public void onDestroy() {
        super.onDestroy();
        App.bluetooth_main.stopService();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                App.bluetooth_main.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                App.bluetooth_main.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth désactivé !"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void openAccueil() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


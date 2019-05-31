package com.example.boitamedoc_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity{

    public TextView textViewPosologie;
    public static String Title_Notification;
    public static String Message_Notification;
    private SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy" );
    private SimpleDateFormat h = new SimpleDateFormat ("hh:mm");

    //static BluetoothSPP bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        createNotification1();
        setTitle("BOÎTA'MÉDOC");
        //App.bluetooth_main.autoConnect("BoitaMedoc");
        /*bluetooth = new BluetoothSPP(this);
        if (!bluetooth.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "Bluetooth non disponible !", Toast.LENGTH_SHORT).show();
            finish();
        }
        bluetooth.connect("00:06:66:6D:F1:75");
        bluetooth.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext(),"Connecté à " + name,Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext(),"Connexion perdu",Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext(),"Impossible de se connecter",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    //Création d'une barre de tâche en bas de l'application avec la redirection vers chaque page.
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_accueil:
                    selectedFragment = new HomeFragment();
                    setTitle("BOÎTA'MÉDOC");
                    break;
                case R.id.navigation_traitement:
                    selectedFragment = new TraitementFragment();
                    setTitle("Traitement");
                    break;
                case R.id.navigation_boite:
                    selectedFragment = new boiteFragment();
                    setTitle("Boîte de Médicament");
                    break;
                case R.id.navigation_libre_service:
                    selectedFragment = new LibreServiceFragment();
                    setTitle("Libre-Service");
                    break;
                case R.id.navigation_profil:
                    selectedFragment = new ProfilFragment();
                    setTitle("Profil");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };//Fin de BottomNavigationView

    //Création d'un menu sur la bar d'action en haut de l'application
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }//Fin de OnCreateOptionMenu

    //Redirection vers la page de setting
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                openSetting();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSetting() {
        Intent intent;
        intent = new Intent(this, ParametreActivity.class);
        startActivity(intent);
    }

    /*private void recupheure() {
        Date currentTime_1 = new Date();
        String dateString = d.format(currentTime_1);
        String heureString = h.format(currentTime_1);
        System.out.println(heureString);
    }*/

    private void createNotification1(){
        Title_Notification="Votre prise de médicament de 9h30";
        Message_Notification="name_medicament";
        //NotificationReceiver.createNotification(this,Title_Notification,Message_Notification);
        NotificationReceiver.scheduleNotification(this,Title_Notification,Message_Notification,12,55);
        //NotificationReceiver.cancelNotification(this);
    }
/*
    private void createNotification2() {
        Title_Notification = "Votre prise de médicament de 22h30";
        Message_Notification = "name_medicament\nname_medicament";
        NotificationReceiver.createNotification2(this,Title_Notification,Message_Notification);
        //NotificationReceiver.scheduleNotification2(this, Title_Notification, Message_Notification, 15, 10);
       // NotificationReceiver.cancelNotification(this);
    }
*/

/*
    public void onStart() {
        super.onStart();
        if (!bluetooth.isBluetoothEnabled()) {
            bluetooth.enable();
        } else {
            if (!bluetooth.isServiceAvailable()) {
                bluetooth.setupService();
                bluetooth.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }


    public void onDestroy() {
        super.onDestroy();
        bluetooth.stopService();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bluetooth.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth désactivé !"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }*/
}

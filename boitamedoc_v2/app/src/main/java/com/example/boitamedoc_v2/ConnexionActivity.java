package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class ConnexionActivity extends AppCompatActivity {

    private EditText identifiant;
    private EditText motDePasse;
    private Button boutonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setTitle("Connexion");
        identifiant=findViewById(R.id.log_username);
        motDePasse=findViewById(R.id.log_password);
        boutonConnexion=findViewById(R.id.ValidButton_connexion);

        boutonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(identifiant.getText().toString().equals("admin")&&motDePasse.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Chargement en cours...",Toast.LENGTH_SHORT).show();
                    //connexionBluetooth();
                    openApplication();
                    ConnexionActivity.this.finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Identifiant ou mot de passe erroné !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openApplication(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void connexionBluetooth(){
        App.bluetooth_main.connect("00:06:66:6D:F1:75"); //A voir si on laisse ici...
    }
}

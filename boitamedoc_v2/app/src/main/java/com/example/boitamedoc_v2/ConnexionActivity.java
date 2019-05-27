package com.example.boitamedoc_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;


public class ConnexionActivity extends AppCompatActivity {

    private EditText identifiant;
    private EditText motDePasse;
    private Button boutonConnexion;

    private TextInputLayout til_username, til_password;
    private RequestQueue queue;
    private MyRequest request;

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
                    openApplication();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Identifiant ou mot de passe erroné !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        til_username = (TextInputLayout) findViewById(R.id.log_username_text);
        til_password = (TextInputLayout) findViewById(R.id.log_password_text);

    }

    private void openApplication(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

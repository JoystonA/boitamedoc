package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InscriptionActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText lienPatient;
    private EditText date;
    private EditText email;
    private EditText mdp;
    private EditText confirmeMDP;
    private Button   ValidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        setTitle("BOÎTA'MÉDOC");

        nom = findViewById(R.id.edit_nom);
        prenom = findViewById(R.id.edit_prenom);
        lienPatient =findViewById(R.id.edit_lienPatient);
        date =findViewById(R.id.edit_date);
        email =findViewById(R.id.edit_email);
        mdp =findViewById(R.id.edit_mdp);
        confirmeMDP =findViewById(R.id.edit_confirmeMDP);
        ValidButton = findViewById(R.id.ValidButton);
    }

    public void Validation(View v){
        Intent intent;
        String Nom = nom.getText().toString();
        String Prenom = prenom.getText().toString();
        String LienPatient = lienPatient.getText().toString();
        String Date = date.getText().toString();
        String Email = email.getText().toString();
        String MDP = mdp.getText().toString();
        String ConfirmeMDP = confirmeMDP.getText().toString();
        String arg[] = {Nom,Prenom,LienPatient,Date,Email,MDP,ConfirmeMDP};
        intent = new Intent(this, InfoCaseActivity.class).putExtra("inscription1",arg);
        startActivity(intent);
    }

}

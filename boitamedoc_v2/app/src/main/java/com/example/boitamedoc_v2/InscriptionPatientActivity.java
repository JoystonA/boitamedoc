package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class InscriptionPatientActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText date;
    private EditText maladie;
    private TextView numSecu;
    private Switch isApte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscriptionpatient);
        setTitle("Inscription Patient");

        nom = findViewById(R.id.edit_nom);
        prenom = findViewById(R.id.edit_prenom);
        date =findViewById(R.id.edit_date);
        maladie = findViewById(R.id.edit_maladie);
        numSecu = findViewById(R.id.edit_secu);
        numSecu.setText("Numéro de sécurité social : "+ getIntent().getStringExtra("numSecu"));
        isApte = findViewById(R.id.switch_apte);
    }

    public void finish(View v){
        if(textisOk()){
            openMainActivity();
        }
    }

    public boolean textisOk(){
        if (nom.getText().toString().trim().isEmpty() | prenom.getText().toString().trim().isEmpty() |
                date.getText().toString().trim().isEmpty() | maladie.getText().toString().trim().isEmpty() | numSecu.getText().toString().trim().isEmpty()) {
            nom.setError("Rentrez quelque chose");
            prenom.setError("Rentrez quelque chose");
            date.setError("Rentrez quelque chose");
            maladie.setError("Rentrez quelque chose");
            return false;
        }
        nom.setError(null);
        prenom.setError(null);
        date.setError(null);
        maladie.setError(null);
        return true;
    }

    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

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

        nom = findViewById(R.id.inscrip_nom_patient);
        prenom = findViewById(R.id.inscrip_prenom_patient_edit);
        date =findViewById(R.id.inscrip_date_patient_edit);
        maladie = findViewById(R.id.inscrip_maladie_patient_edit);
        numSecu = findViewById(R.id.inscrip_num_secu_patient_text);
        numSecu.setText("Numéro de sécurité social : "+ getIntent().getStringExtra("numSecu"));
        isApte = findViewById(R.id.inscrip_switch_apte_patient);
    }

    public void finish(View v){
        if(nomisOk()&prenomisOk()&dateisOk()&maladieisOk()&numSecuisOk()){
            openMainActivity();
        }
    }

    public boolean nomisOk() {
        String nom_patient = nom.getText().toString().trim();
        if(nom_patient.isEmpty()) {
            nom.setError("Le champs est vide");
            return false;
        }
        nom.setError(null);
        return true;
    }
    public boolean prenomisOk() {
        String prenom_patient = prenom.getText().toString().trim();
        if(prenom_patient.isEmpty()) {
            prenom.setError("Le champs est vide");
            return false;
        }
        prenom.setError(null);
        return true;
    }
    public boolean dateisOk() {
        String date_patient = date.getText().toString().trim();
        if(date_patient.isEmpty()) {
            date.setError("Le champs est vide");
            return false;
        }
        date.setError(null);
        return true;
    }
    public boolean maladieisOk() {
        String maladie_patient = maladie.getText().toString().trim();
        if(maladie_patient.isEmpty()) {
            maladie.setError("Le champs est vide");
            return false;
        }
        maladie.setError(null);
        return true;
    }
    public boolean numSecuisOk() {
        String numSecu_patient = numSecu.getText().toString().trim();
        if(numSecu_patient.isEmpty()) {
            numSecu.setError("Le champs est vide");
            return false;
        }
        numSecu.setError(null);
        return true;
    }

    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


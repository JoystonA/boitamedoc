package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

public class InscriptionPatientActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText date;
    private EditText maladie;
    private TextView numSecu;
    private Switch isApte;
    private RequestQueue queue;
    private MyRequest request;


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
        numSecu.setText(getIntent().getStringExtra("numSecu"));
        isApte = findViewById(R.id.inscrip_switch_apte_patient);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
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
        if(date_patient.length()!=10){
            date.setError("La date est invalide");
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
        String Nom = nom.getText().toString();
        String Prenom = prenom.getText().toString();
        String Date = date.getText().toString();
        String Maladie = maladie.getText().toString();
        String NumSecu = numSecu.getText().toString();
        String IsApte = isApte.getText().toString();
        request.registerPatient(Nom, Prenom, Date, Maladie, NumSecu, IsApte,new MyRequest.InscripPatientCallback(){
            @Override
            public void onSucces(int id_patient) {
                startActivity(intent);
            }

            @Override
            public void onError(boolean[] errors) {
                Log.d("APP", "onError: " + errors[0]+" " +errors[1]);
                if(errors[0]) {
                    Log.d("APP", "onError: nom dedans");
                    nom.setError("Nom Incorrecte");
                }
                if(errors[1]) {
                    Log.d("APP", "onError: prenom dedans");
                    prenom.setError("Prneom Incorrecte");
                }

            }
        });
    }
}


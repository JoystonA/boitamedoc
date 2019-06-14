package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import static com.example.boitamedoc_v2.App.id_patient;


public class ModifProfilActivity extends AppCompatActivity {
    private EditText Nom;
    private EditText Prenom;
    private EditText Date;
    private EditText Maladie;
    private Switch IsApte;
    private Boolean SwitchState;
    private String Apte;

    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifprofil);
        setTitle("Modification du profil");

        Nom = (EditText) findViewById(R.id.modif_patient_nom);
        Prenom = (EditText) findViewById(R.id.modif_patient_prenom);
        Date = (EditText) findViewById(R.id.modif_patient_naissance);
        Maladie = (EditText) findViewById(R.id.modif_patient_maladie);
        IsApte = (Switch) findViewById(R.id.modif_patient_switch_apte);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        IsApte.setChecked(this.getIntent().getBooleanExtra("Apte",false));

        request.recupProfil(id_patient, new MyRequest.recupProfilCallback() {
            @Override
            public void onSucces(String nom, String prenom, String numero_securite_social, String id_boite, String date, String maladie, int apte) {
                Nom.setText(nom);
                Prenom.setText(prenom);
                Date.setText(date);
                Maladie.setText(maladie);
            }

            @Override
            public void onError(boolean error) {
                Log.d("APP", "onError: " + error);
            }
        });
    }

    public void finish(View v){
        if(nomisOk()&prenomisOk()&dateisOk()&maladieisOk()){
            String nom = Nom.getText().toString();
            String prenom = Prenom.getText().toString();
            String date = Date.getText().toString();
            String maladie = Maladie.getText().toString();
            SwitchState = IsApte.isChecked();

            queue = VolleySingleton.getInstance(this).getRequestQueue();
            request = new MyRequest(this, queue);

            if(SwitchState == true){
                Apte = "1";
            }
            else{
                Apte = "0";
            }

            request.modifProfil(id_patient, nom, prenom, date, maladie, Apte, new MyRequest.modifProfilCallback() {
                @Override
                public void onSucces() {
                    Toast.makeText(getApplicationContext(), "Modifications sauvegardées", Toast.LENGTH_SHORT).show();
                    openMainActivity();
                }

                @Override
                public void onError(boolean errors[]) {
                    Log.d("APP", "onError: " + errors[0] + " " + errors[1] + " " + errors[2] + " " + errors[3]);
                    if (errors[0]) {
                        Nom.setError("Nom Incorrecte");
                    }
                    if (errors[1]) {
                        Prenom.setError("Prenom Incorrecte");
                    }
                }
            });

        }
    }

    public boolean nomisOk() {
        String nom_patient = Nom.getText().toString().trim();
        if(nom_patient.isEmpty()) {
            Nom.setError("Le champs est vide");
            return false;
        }
        Nom.setError(null);
        return true;
    }
    public boolean prenomisOk() {
        String prenom_patient = Prenom.getText().toString().trim();
        if(prenom_patient.isEmpty()) {
            Prenom.setError("Le champs est vide");
            return false;
        }
        Prenom.setError(null);
        return true;
    }
    public boolean dateisOk() {
        String date_patient = Date.getText().toString().trim();
        if(date_patient.isEmpty()) {
            Date.setError("Le champs est vide");
            return false;
        }
        if(date_patient.length()!=10){
            Date.setError("La date est invalide");
            return false;
        }
        Date.setError(null);
        return true;
    }
    public boolean maladieisOk() {
        String maladie_patient = Maladie.getText().toString().trim();
        if(maladie_patient.isEmpty()) {
            Maladie.setError("Le champs est vide");
            return false;
        }
        Maladie.setError(null);
        return true;
    }



    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
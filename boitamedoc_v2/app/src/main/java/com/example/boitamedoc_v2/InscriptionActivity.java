package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.santalu.maskedittext.MaskEditText;

public class InscriptionActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText lienPatient;
    private MaskEditText date;
    private EditText email;
    private EditText mdp;
    private EditText confirmeMDP;
    private Button   ValidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        setTitle("Inscription Gestionnnaire");

        nom = findViewById(R.id.inscrip_nom_gestionnaire_edit);
        prenom = findViewById(R.id.inscrip_prenom_gestionnaire_edit);
        lienPatient =findViewById(R.id.inscrip_lienPatient_gestionnaire_edit);
        date =findViewById(R.id.inscrip_date_gestionnaire_edit);
        email =findViewById(R.id.inscrip_email_gestionnaire_edit);
        mdp =findViewById(R.id.inscrip_mdp_gestionnaire_edit);
        confirmeMDP =findViewById(R.id.inscrip_confirm_mdp_gestionnaire_edit);
        ValidButton = findViewById(R.id.ValidButton);
    }

    public void Validation(View v){
        if(nomisOk()&prenomisOk()&lienisOk()&dateisOk()&emailisOk()&mdpisOk()&confirmmdpisOk()&mdpequalsisOk()){
            openInscriptionNumSecuActivity();
        }
    }

    public boolean nomisOk() {
        String nom_gestionnaire = nom.getText().toString().trim();
        if(nom_gestionnaire.isEmpty()) {
            nom.setError("Le champs est vide");
            return false;
        }
        nom.setError(null);
        return true;
    }
    public boolean prenomisOk() {
        String prenom_gestionnaire = prenom.getText().toString().trim();
        if(prenom_gestionnaire.isEmpty()) {
            prenom.setError("Le champs est vide");
            return false;
        }
        prenom.setError(null);
        return true;
    }
    public boolean lienisOk() {
        String lien_patient = lienPatient.getText().toString().trim();
        if(lien_patient.isEmpty()) {
            lienPatient.setError("Le champs est vide");
            return false;
        }
        lienPatient.setError(null);
        return true;
    }
    public boolean dateisOk() {
        String date_gestionnaire = date.getText().toString().trim();
        if(date_gestionnaire.isEmpty()) {
            date.setError("Le champs est vide");
            return false;
        }
        if(date_gestionnaire.length()!=10){
            date.setError("La date est invalide");
            return false;
        }
        date.setError(null);
        return true;
    }

    public boolean emailisOk() {
        String email_gestionnaire = email.getText().toString().trim();
        if(email_gestionnaire.isEmpty()) {
            email.setError("Le champs est vide");
            return false;
        }
        email.setError(null);
        return true;
    }

    public boolean mdpisOk() {
        String mdp_gestionnaire = mdp.getText().toString().trim();
        if(mdp_gestionnaire.isEmpty()) {
            mdp.setError("Le champs est vide");
            return false;
        }
        mdp.setError(null);
        return true;
    }

    public boolean confirmmdpisOk() {
        String confirmmdp_gestionnaire = confirmeMDP.getText().toString().trim();
        if(confirmmdp_gestionnaire.isEmpty()) {
            confirmeMDP.setError("Le champs est vide");
            return false;
        }
        confirmeMDP.setError(null);
        return true;
    }

    public boolean mdpequalsisOk() {
        String mdp_gestionnaire = mdp.getText().toString().trim();
        String confirmmdp_gestionnaire = confirmeMDP.getText().toString().trim();
        if (mdp_gestionnaire.equals(confirmmdp_gestionnaire))
            return true;
        else {
            mdp.setError("Mote de passe différent!");
            confirmeMDP.setError("Mote de passe différent !");
            return false;
        }
    }

    void openInscriptionNumSecuActivity(){
        Intent intent = new Intent(this, InscriptionNumSécuActivity.class);
        startActivity(intent);
    }

}


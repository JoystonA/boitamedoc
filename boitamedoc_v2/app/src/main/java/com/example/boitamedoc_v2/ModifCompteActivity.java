package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.santalu.maskedittext.MaskEditText;


public class ModifCompteActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText lienPatient;
    private MaskEditText date;
    private EditText email;
    private EditText ancienmdp;
    private EditText mdp;
    private EditText confirmeMDP;
    private Button ValidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifcompte);
        setTitle("Modification du compte");

        nom = findViewById(R.id.modif_gestionnaire_nom_edit);
        prenom = findViewById(R.id.modif_gestionnaire_prenom_edit);
        lienPatient =findViewById(R.id.modif_gestionnaire_lien_edit);
        date =findViewById(R.id.modif_gestionnaire_naissance_edit);
        email =findViewById(R.id.modif_gestionnaire_email_edit);
        ancienmdp =findViewById(R.id.modif_gestionnaire_ancien_mdp_edit);
        mdp =findViewById(R.id.modif_gestionnaire_nvx_mdp_edit);
        confirmeMDP =findViewById(R.id.modif_gestionnaire_conf_mdp_edit);
        ValidButton = findViewById(R.id.ValidButton);
    }

    public void Validation_modif(View v){
        if(nomisOk()&prenomisOk()&lienisOk()&dateisOk()&emailisOk()&ancienmdpisOk()&mdpisOk()&confirmmdpisOk()&mdpequalsisOk()){
            openMainActivity();
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

    public boolean ancienmdpisOk() {
        String ancienmdp_gestionnaire = ancienmdp.getText().toString().trim();
        if(ancienmdp_gestionnaire.isEmpty()) {
            ancienmdp.setError("Le champs est vide");
            return false;
        }
        ancienmdp.setError(null);
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

    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
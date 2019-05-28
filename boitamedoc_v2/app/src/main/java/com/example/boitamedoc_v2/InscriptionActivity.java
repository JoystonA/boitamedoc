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
        Intent intent;
        String Nom = nom.getText().toString();
        String Prenom = prenom.getText().toString();
        String LienPatient = lienPatient.getText().toString();
        String Date = date.getText().toString();
        String Email = email.getText().toString();
        String MDP = mdp.getText().toString();
        String ConfirmeMDP = confirmeMDP.getText().toString();
        if (Nom.isEmpty() | Prenom.isEmpty() | LienPatient.isEmpty() | Date.isEmpty() | Email.isEmpty() | MDP.isEmpty() | ConfirmeMDP.isEmpty()) {
            nom.setError("Rentrer quelque chose.");
            prenom.setError("Rentrer quelque chose.");
            lienPatient.setError("Rentrer quelque chose.");
            date.setError("Rentrer quelque chose.");
            email.setError("Rentrer quelque chose.");
            mdp.setError("Rentrer quelque chose.");
            confirmeMDP.setError("Rentrer quelque chose.");
        }
        else{
            nom.setError(null);
            prenom.setError(null);
            lienPatient.setError(null);
            date.setError(null);
            email.setError(null);
            mdp.setError(null);
            confirmeMDP.setError(null);

            if(MDP.equals(ConfirmeMDP)) {
                intent = new Intent(this, InscriptionNumSécuActivity.class);
                startActivity(intent);
            }
            else{
                mdp.setError("Mote de passe différent!");
                confirmeMDP.setError("Mote de passe différent !");
            }
        }

    }
}


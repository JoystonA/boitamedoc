package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;
import com.santalu.maskedittext.MaskEditText;

import static com.example.boitamedoc_v2.App.id_gestionnaire;
import static com.example.boitamedoc_v2.App.id_gestionnaire;

public class ModifCompteActivity extends AppCompatActivity {
    private EditText Nom;
    private EditText Prenom;
    private EditText LienPatient;
    private MaskEditText Date;
    private EditText Email;
    private EditText Ancienmdp;
    private EditText Mdp;
    private EditText ConfirmeMDP;
    private Button ValidButton;

    private RequestQueue queue;
    private MyRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifcompte);
        setTitle("Modification du compte");

        Nom = (EditText) findViewById(R.id.modif_gestionnaire_nom_edit);
        Prenom = (EditText) findViewById(R.id.modif_gestionnaire_prenom_edit);
        LienPatient = (EditText) findViewById(R.id.modif_gestionnaire_lien_edit);
        Date = (MaskEditText) findViewById(R.id.modif_gestionnaire_naissance_edit);
        Email = (EditText) findViewById(R.id.modif_gestionnaire_email_edit);
        Ancienmdp = (EditText) findViewById(R.id.modif_gestionnaire_ancien_mdp_edit);
        Mdp = (EditText) findViewById(R.id.modif_gestionnaire_nvx_mdp_edit);
        ConfirmeMDP = (EditText) findViewById(R.id.modif_gestionnaire_conf_mdp_edit);
        ValidButton = (Button) findViewById(R.id.ValidButton);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        request.recupCompte(id_gestionnaire, new MyRequest.recupCompteCallback() {
            @Override
            public void onSucces(String nom, String prenom, String lienPatient, String date, String email) {
                Nom.setText(nom);
                Prenom.setText(prenom);
                LienPatient.setText(lienPatient);
                Date.setText(date);
                Email.setText(email);
            }

            @Override
            public void onError(boolean error) {
                Log.d("APP", "onError: " + error);
            }
        });
    }

    public void Validation_modif(View v){

        if(!ancienmdpisOk() && mdpisOk() && confirmmdpisOk()){
            Ancienmdp.setError("Le champ est vide");
        }
        else if(ancienmdpisOk() && !mdpisOk() && !confirmmdpisOk()){
            Mdp.setError("Le champ est vide");
        }
        else {
            if (nomisOk() & prenomisOk() & lienisOk() & dateisOk() & emailisOk()) {
                String nom = Nom.getText().toString();
                String prenom = Prenom.getText().toString();
                String lienPatient = LienPatient.getText().toString();
                String date = Date.getText().toString();
                String email = Email.getText().toString();
                String ancienmdp = Ancienmdp.getText().toString();
                String mdp = Mdp.getText().toString();
                String confirmeMDP = ConfirmeMDP.getText().toString();

                queue = VolleySingleton.getInstance(this).getRequestQueue();
                request = new MyRequest(this, queue);

                if (mdp.equals(confirmeMDP)) {
                    request.modifCompte(id_gestionnaire, nom, prenom, lienPatient, date, email, ancienmdp, mdp, confirmeMDP, new MyRequest.modifCompteCallback() {
                        @Override
                        public void onSucces() {
                            Toast.makeText(getApplicationContext(), "Modifications sauvegardées", Toast.LENGTH_SHORT).show();
                            openMainActivity();
                        }

                        @Override
                        public void onError(boolean errors[], String rep_email, String rep_mdp) {
                            Log.d("APP", "onError: " + errors[0] + " " + errors[1] + " " + errors[2] + " " + errors[3]);
                            if (errors[0]) {
                                Nom.setError("Nom Incorrecte");
                            }
                            if (errors[1]) {
                                Prenom.setError("Prenom Incorrecte");
                            }
                            if (errors[2]) {
                                Email.setError(rep_email);
                            }
                            if (errors[3]) {
                                Ancienmdp.setError(rep_mdp);
                            }
                        }
                    });


                } else {
                    Mdp.setError("Mot de passe différent !");
                    ConfirmeMDP.setError("Mot de passe différent !");
                }
            }
        }
    }

    public boolean nomisOk() {
        String nom_gestionnaire = Nom.getText().toString().trim();
        if(nom_gestionnaire.isEmpty()) {
            Nom.setError("Le champs est vide");
            return false;
        }
        Nom.setError(null);
        return true;
    }
    public boolean prenomisOk() {
        String prenom_gestionnaire = Prenom.getText().toString().trim();
        if(prenom_gestionnaire.isEmpty()) {
            Prenom.setError("Le champs est vide");
            return false;
        }
        Prenom.setError(null);
        return true;
    }
    public boolean lienisOk() {
        String lien_patient = LienPatient.getText().toString().trim();
        if(lien_patient.isEmpty()) {
            LienPatient.setError("Le champs est vide");
            return false;
        }
        LienPatient.setError(null);
        return true;
    }
    public boolean dateisOk() {
        String date_gestionnaire = Date.getText().toString().trim();
        if(date_gestionnaire.isEmpty()) {
            Date.setError("Le champs est vide");
            return false;
        }
        if(date_gestionnaire.length()!=10){
            Date.setError("La date est invalide");
            return false;
        }
        Date.setError(null);
        return true;
    }
    public boolean emailisOk() {
        String email_gestionnaire = Email.getText().toString().trim();
        if(email_gestionnaire.isEmpty()) {
            Email.setError("Le champs est vide");
            return false;
        }
        Email.setError(null);
        return true;
    }

    public boolean mdpisOk() {
        String mdp_gestionnaire = Mdp.getText().toString().trim();
        if(mdp_gestionnaire.isEmpty()) {
            //Mdp.setError("Le champs est vide");
            return false;
        }
        //Mdp.setError(null);
        return true;
    }

    public boolean ancienmdpisOk() {
        String ancienmdp_gestionnaire = Ancienmdp.getText().toString().trim();
        if(ancienmdp_gestionnaire.isEmpty()) {
            //Ancienmdp.setError("Le champs est vide");
            return false;
        }
        //Ancienmdp.setError(null);
        return true;
    }

    public boolean confirmmdpisOk() {
        String confirmmdp_gestionnaire = ConfirmeMDP.getText().toString().trim();
        if(confirmmdp_gestionnaire.isEmpty()) {
            //ConfirmeMDP.setError("Le champs est vide");
            return false;
        }
        //ConfirmeMDP.setError(null);
        return true;
    }


    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
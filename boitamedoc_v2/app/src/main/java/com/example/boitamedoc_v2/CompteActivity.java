package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import static com.example.boitamedoc_v2.App.id_gestionnaire;


public class CompteActivity extends AppCompatActivity implements View.OnClickListener{

    private RequestQueue queue;
    private MyRequest request;
    private TextView Prenom;
    private TextView LienPatient;
    private TextView Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_compte);
        Button modifButton = findViewById(R.id.modifier_compte);
        modifButton.setOnClickListener(this);

        Prenom = (TextView) findViewById(R.id.prenom_gestionnaire);
        LienPatient = (TextView) findViewById(R.id.lien_patient);
        Email = (TextView) findViewById(R.id.email_gestionnaire);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        request.recupCompte(id_gestionnaire, new MyRequest.recupCompteCallback() {
            @Override
            public void onSucces(String nom, String prenom, String lienPatient, String date, String email) {
                Prenom.setText(prenom);
                LienPatient.setText("Lien avec le patient : "+lienPatient);
                Email.setText("Email : "+email);
            }

            @Override
            public void onError(boolean error) {
                Prenom.setText("ERREUR");
                LienPatient.setText("ERREUR");
                Email.setText("ERREUR");
            }
        });
    }

    @Override
    public void onClick(View v) {
        openModifCompte();
    }

    private void openModifCompte() {
        Intent intent;
        intent = new Intent(this, ModifCompteActivity.class);
        startActivity(intent);
    }

}
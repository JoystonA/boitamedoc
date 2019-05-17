package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class ParametreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parametre);

        Button Compte = findViewById(R.id.compte);
        Button Mon_patient = findViewById(R.id.mon_patient);
        Button Prise_en_main = findViewById(R.id.prise_en_main);
        Button Faq = findViewById(R.id.faq);
        Button A_propos = findViewById(R.id.a_propos);
        Button Deconnexion = findViewById(R.id.deconnexion);

        Compte.setOnClickListener(this);
        Mon_patient.setOnClickListener(this);
        Prise_en_main.setOnClickListener(this);
        Faq.setOnClickListener(this);
        A_propos.setOnClickListener(this);
        Deconnexion.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compte:
                openSettingCompte();
                break;

            case R.id.mon_patient:
                openSettingMonPatient();
                break;

            case R.id.prise_en_main:
                openSettingPriseEnMain();
                break;

            case R.id.faq:
                openSettingFaq();
                break;

            case R.id.a_propos:
                openSettingAPropos();
                break;
        }
    }

    private void openSettingCompte() {
        Intent intent;
        intent = new Intent(this, CompteActivity.class);
        startActivity(intent);
    }

    private void openSettingMonPatient() {
        Intent intent;
        intent = new Intent(this, MonPatientActivity.class);
        startActivity(intent);
    }

    private void openSettingPriseEnMain() {
        Intent intent;
        intent = new Intent(this, PriseEnMainActivity.class);
        startActivity(intent);
    }

    private void openSettingFaq() {
        Intent intent;
        intent = new Intent(this, FaqActivity.class);
        startActivity(intent);
    }

    private void openSettingAPropos() {
        Intent intent;
        intent = new Intent(this, AProposActivity.class);
        startActivity(intent);
    }

}
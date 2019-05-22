package com.example.boitamedoc_v2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PageOuvertureActivity extends AppCompatActivity {

    private Button InscriptionBouton;
    private Button SeConnecterBouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ouverture);
        setTitle(" ");

        InscriptionBouton = findViewById(R.id.button_inscrire);
        SeConnecterBouton = findViewById(R.id.button_se_connecter);
        SeConnecterBouton.setPaintFlags(SeConnecterBouton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        InscriptionBouton.setOnClickListener(mCorkyListener);
        SeConnecterBouton.setOnClickListener(mCorkyListener);
   }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_inscrire:
                    openInscription();
                    break;
                case R.id.button_se_connecter:
                    openSeConnecter();
                    break;
            }
        }
    };

    public void openInscription() {
        Intent intent;
        intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }
    public void openSeConnecter() {
        Intent intent;
        intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
    }
}

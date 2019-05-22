package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.boitamedoc_v2.R;


public class CompteActivity extends AppCompatActivity{
    private Button modifButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_compte);

        modifButton = (Button) findViewById(R.id.modifier_compte);
    }

    private void openModifCompte() {
        Intent intent;
        intent = new Intent(this, ModifCompteActivity.class);
        startActivity(intent);
    }

}
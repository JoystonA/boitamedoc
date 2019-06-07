package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class AProposActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_apropos);

        Button Conditions = findViewById(R.id.conditions);

        Conditions.setOnClickListener(this);
        setTitle("A Propos");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
             case R.id.conditions:
                openAProposConditions();
                break;

        }
    }

    private void openAProposConditions() {
        Intent intent;
        intent = new Intent(this, ConditionsActivity.class);
        startActivity(intent);
    }

}
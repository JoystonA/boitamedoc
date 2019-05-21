package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.boitamedoc_v2.R;


public class CompteActivity extends AppCompatActivity implements popup.popupListener{
    private Button modifButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_compte);

        modifButton = (Button) findViewById(R.id.modifier_compte);
        modifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUp();
            }
        });

    }

    public void openPopUp(){
        popup popup = new popup();
        popup.show(this.getSupportFragmentManager(),"test popup");
    }

    @Override
    public void applyTexts(String Username, String Password) {

        if(Username.equals("allo")&& Password.equals("quoi")){
            openModifCompte();
            return;
        }
        else{

        }
    }

    private void openModifCompte() {
        Intent intent;
        intent = new Intent(this, ModifCompteActivity.class);
        startActivity(intent);
    }

}
package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InscriptionNumSécuActivity extends AppCompatActivity {
    private TextView textPatient;
    private EditText NumSecu;
    private boolean isKnown=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscriptionnumerosecu);
        setTitle("BOÎTA'MÉDOC");

        NumSecu = findViewById(R.id.textedit_numSecu);
        textPatient = findViewById(R.id.textPatient);
        NumSecu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(NumSecu.getText().toString().trim().equals("123456789")){
                    textPatient.setText("M. DUPONT André");
                    isKnown=true;
                }
                else{
                    textPatient.setText("Le patient n'est pas connue dans notre base de données");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String num = NumSecu.getText().toString();
    }

    public void ValidNum(View v){
        if(textOk()) {
            if (isKnown == false){
                openInscripPatient();
            }
            else {
                openMainActivity();
            }
        }
    }

    public boolean textOk(){
        if(NumSecu.getText().toString().isEmpty()){
            NumSecu.setError("Vous devez taper un numéro");
            return false;
        }
        NumSecu.setError(null);
        return true;
    }

    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void openInscripPatient(){
        Intent intent = new Intent(this, InscriptionPatientActivity.class).putExtra("numSecu",NumSecu.getText().toString().trim());
        startActivity(intent);
    }
}


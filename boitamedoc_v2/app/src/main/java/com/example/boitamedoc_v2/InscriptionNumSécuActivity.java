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

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

public class InscriptionNumSécuActivity extends AppCompatActivity {
    private TextView textPatient;
    public EditText NumSecu;
    private boolean isKnown=false;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscriptionnumerosecu);
        setTitle("Inscription Patient");

        NumSecu = findViewById(R.id.inscrip_num_secu_patient_edit);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        NumSecu = findViewById(R.id.textedit_numSecu);
        Button Button = findViewById(R.id.ValidNumButton);
        textPatient = findViewById(R.id.textPatient);
        NumSecu.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            textPatient.setText("Veuillez rentrer un numéro");
            isKnown = false;
            Button.setEnabled(false);
            if(NumSecu.getText().toString().length()==15) {
                request.checkNumSecu(NumSecu.getText().toString().trim(), new MyRequest.NumSecuCallback() {
                    @Override
                    public void onSucces(String nom, String prenom) {
                        textPatient.setText(nom + " " + prenom);
                        Button.setEnabled(true);
                        isKnown=true;
                    }

                    @Override
                    public void onError(String message) {
                        textPatient.setText("Pas connue dans la base de donnée");
                        Button.setEnabled(true);
                    }
                });
            }

        }
    });
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
        String numSecu = NumSecu.getText().toString();
        if(numSecu.isEmpty()){
            NumSecu.setError("Vous devez taper un numéro");
            return false;
        }
        if(numSecu.length()!=21){
            NumSecu.setError("Le numéro est incorrect");
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


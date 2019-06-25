package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;


public class AgencementMedocActivity extends AppCompatActivity {

    public TextInputEditText nbrmedoc;
    private String numCase;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencement);
        setTitle("Agencement de boîte");

        nbrmedoc = (TextInputEditText) findViewById(R.id.agencement_quantite_edit);
        numCase = this.getIntent().getStringExtra("numCase");
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

    }

    public void ValidationAgencement(View v) {
        String Nbrmedoc = nbrmedoc.getText().toString().trim();

        if (!Nbrmedoc.isEmpty()) {
            request.ajoutComprime(numCase, Nbrmedoc, new MyRequest.ajoutComprimeCallback() {
                @Override
                public void onSucces() {
                    Toast.makeText(getApplicationContext(), "Médicaments ajoutés", Toast.LENGTH_SHORT).show();
                    openMainActivity();
                    App.bluetooth_main.send("Case 0",true);
                }

                @Override
                public void onError(boolean error) {
                    Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            nbrmedoc.setError("Veuillez entrer une valeur");
        }

    }

    public void openMainActivity(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


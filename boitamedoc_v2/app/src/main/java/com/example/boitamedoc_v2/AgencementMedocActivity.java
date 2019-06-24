package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;


public class AgencementMedocActivity extends AppCompatActivity implements View.OnClickListener {

    public TextInputLayout nbrmedoc;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencement);
        setTitle("Agencement de boîte");
        nbrmedoc = (TextInputLayout)findViewById(R.id.agencement_quantite_edit);


       /* numCase = this.getIntent().getStringExtra("numCase");

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        request.recupNbrMedoc(id_medoc, new MyRequest.recupNbrMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    Log.d("APP", "onSucces: " + numCase);
                    request.insetMedoc(numCase,id_medoc,date_ok,num_lot);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject message) {
                name_medoc.setText("Nom du médicament :\n" + "Médicament inconnu");
            }
        });*/

    }


    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.validAgencement:
                openAgencementActivity();
                break;
        }
    }

    public void openAgencementActivity(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*private void quantiteeIsOk() {
        String quantiteeInput = nbrmedoc.getEditText().getText().toString().trim();
        String num_case = getIntent().getStringExtra("case");
        Log.d("APP", "IsOk: "+ num_case);
        if (quantiteeInput.isEmpty() || quantiteeInput.equals("0")) {
            nbrmedoc.setError("Veuillez rentrer un chiffre !");
            return;
        }
        request.quantiteeIsOk(num_case, quantiteeInput, new MyRequest.IsOkCallback() {
            @Override
            public void onSucces(boolean IsOK) {
                Log.d("APP", "onSucces: "+ IsOK);
                if(IsOK){
                    quantitee.setError(null);
                    openPopUpInfoCase();
                }
                else quantitee.setError("Quantité trop grande");
            }

            @Override
            public void onError(String message) {

            }
        });
    }*/
}


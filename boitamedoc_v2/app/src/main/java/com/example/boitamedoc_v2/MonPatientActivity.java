package com.example.boitamedoc_v2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import static com.example.boitamedoc_v2.App.id_patient;


public class MonPatientActivity extends AppCompatActivity {
    private Button add_Button;
    private Button patient[]= new Button[5];
    private String tab_Idpatient[] = new String[5];


    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_monpatient);
        setTitle("Mon Patient");
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        add_Button = (Button) findViewById(R.id.add_Button);
        patient[0] = (Button) findViewById(R.id.but_1);
        patient[1] = (Button) findViewById(R.id.but_2);
        patient[2] = (Button) findViewById(R.id.but_3);
        patient[3] = (Button) findViewById(R.id.but_4);
        patient[4] = (Button) findViewById(R.id.but_5);

        for(int j=0;j<patient.length;j++){
            patient[j].setOnClickListener(this::onClick);
            patient[j].setVisibility(View.GONE);
        }
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });
        request.recupAllPatient(new MyRequest.recupAllPatientCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    int combien = Integer.parseInt(message.getString("combien"));

                    for(int i=0;i<combien;i++){
                        JSONObject json = message.getJSONObject(Integer.toString(i));
                        tab_Idpatient[i] = json.getString("id_patient");
                        patient[i].setText(json.getString("nom")+ " " + json.getString("prenom"));
                        patient[i].setTextColor(Color.WHITE);
                        if(json !=null) {
                            patient[i].setVisibility(View.VISIBLE);
                            if(i==4) add_Button.setVisibility(View.GONE);
                        }

                    }
                }
                catch (Exception e){
                    Log.d("APP", "onSucces: "+e.getMessage());
                }

            }

            @Override
            public void onError(JSONObject message) {
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.but_1 :
                id_patient = tab_Idpatient[0];
                openMainActivity();
                break;
            case R.id.but_2 :
                id_patient = tab_Idpatient[1];
                openMainActivity();
                break;
            case R.id.but_3 :
                id_patient = tab_Idpatient[2];
                openMainActivity();
                break;
            case R.id.but_4 :
                id_patient = tab_Idpatient[3];
                openMainActivity();
                break;
            case R.id.but_5:
                id_patient = tab_Idpatient[4];
                openMainActivity();
                break;
        }
    }

    public void openMainActivity() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addButton(){
        Intent intent;
        intent = new Intent(this,InscriptionNumSécuActivity.class);
        startActivity(intent);
    }
}
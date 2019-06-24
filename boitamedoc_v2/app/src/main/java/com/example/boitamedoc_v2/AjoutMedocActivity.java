package com.example.boitamedoc_v2;

import android.net.ParseException;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AjoutMedocActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView id_medoc_txt;
    private TextView num_lot_txt;
    private TextView name_medoc;
    private TextView date_txt;
    private String qr_code;
    private String id_medoc;
    private String num_lot;
    private String date;
    private String date_ok;
    private String numCase;
    private RequestQueue queue;
    private MyRequest request;
    private Button AjoutMedoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutmedoc);
        setTitle("Boîte de médicament");
        qr_code = getIntent().getStringExtra("value");
        AjoutMedoc = (Button) findViewById(R.id.validMedoc);
        AjoutMedoc.setOnClickListener(this);
        numCase = this.getIntent().getStringExtra("numCase");
        id_medoc_txt = (TextView) findViewById(R.id.id_medoc);
        name_medoc = findViewById(R.id.nom_medoc);
        num_lot_txt = (TextView) findViewById(R.id.num_lot);
        date_txt = (TextView) findViewById(R.id.date_exp);
        qr_code = getIntent().getStringExtra("value");
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        decoupe_qr_code(qr_code);

        request.recupMedoc(id_medoc, new MyRequest.recupMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try{
                    name_medoc.setText("Nom du médicament :\n"+ message.getString("nom"));
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
        });
    }


    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.validMedoc:
                openAgencementMedocActivity();
                int num= Integer.parseInt(numCase)+9;
                String num_str = Integer.toString(num);
                App.bluetooth_main.send("Case "+num_str,true);
                break;
        }
    }

    public void decoupe_qr_code(String qr_code){
        int length = qr_code.length();
        id_medoc=qr_code.substring(4,17);
        num_lot=qr_code.substring(27,length);
        date="20"+qr_code.substring(19,25);


        id_medoc_txt.setText("ID du médicament : "+id_medoc);
        num_lot_txt.setText("Numéro de lot : "+num_lot);

        SimpleDateFormat tempo = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d = tempo.parse(date);
            tempo.applyPattern("dd/MM/yyyy");
            date_ok = tempo.format(d);
        } catch (ParseException e) {
            Log.d("APP", "getParams: Date Non Fonctionnel");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        date_txt.setText("Date d'expiration : "+date_ok);
    }

    public void openAgencementMedocActivity(){
        Intent intent;
        intent = new Intent(this, AgencementMedocActivity.class);
        intent.putExtra("numCase",numCase);
        startActivity(intent);
    }

}


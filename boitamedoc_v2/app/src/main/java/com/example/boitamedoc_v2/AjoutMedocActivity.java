package com.example.boitamedoc_v2;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AjoutMedocActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView id_medoc_txt;
    private TextView num_lot_txt;
    private TextView date_txt;
    private String qr_code;
    private String id_medoc;
    private String num_lot;
    private String date;
    private String date_ok;
    private Button AjoutMedoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutmedoc);
        setTitle("Boîte de médicament");
        id_medoc_txt = (TextView) findViewById(R.id.id_medoc);
        num_lot_txt = (TextView) findViewById(R.id.num_lot);
        date_txt = (TextView) findViewById(R.id.date_exp);
        AjoutMedoc = (Button) findViewById(R.id.validMedoc);

        qr_code = getIntent().getStringExtra("value");
        AjoutMedoc.setOnClickListener(this);

        decoupe_qr_code(qr_code);
    }


    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.validMedoc:
                App.bluetooth_main.send("36",true);
                openCalibrageActivity();
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
            Log.d("APP", "getParams: Date nul chiant JPP");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        date_txt.setText("Date d'expiration : "+date_ok);
    }


    public void openCalibrageActivity(){
        Intent intent;
        intent = new Intent(this, CalibrageActivity.class);
        startActivity(intent);
    }


}


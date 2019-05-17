package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AjoutMedocActivity extends AppCompatActivity {
    private TextView testTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutmedoc);

    testTxt = (TextView) findViewById(R.id.testTxt);
    if(getIntent().getStringExtra("value")!= null) {
        testTxt.setText("QRCODE = "+ getIntent().getStringExtra("value"));
    }
    else testTxt.setText("CA MARCHE PAS");
    }

}

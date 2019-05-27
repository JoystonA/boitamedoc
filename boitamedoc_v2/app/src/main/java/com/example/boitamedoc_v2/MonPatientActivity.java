package com.example.boitamedoc_v2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.boitamedoc_v2.R;


public class MonPatientActivity extends AppCompatActivity {
    private Button add_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_monpatient);
        setTitle("Mon Patient");

        //add_Button = (Button) findViewById(R.id.button7);
        //add_Button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        addButton();
        //    }
        //});
    }

    //public void addButton(){
    //    Button myButton = new Button(this);
    //    myButton.setText("Push Me");

    //    LinearLayout ll = (LinearLayout)findViewById(R.id.ajout_patient);
    //    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    //    ll.addView(myButton, lp);
    //}


}
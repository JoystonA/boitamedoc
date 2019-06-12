package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LibreServiceFragment extends Fragment implements View.OnClickListener {
    private Button Case1;
    private Button Case2;
    private Button Case3;
    private Button Case4;
    private Button Case5;
    private Button Case6;
    private Button Case7;
    private Button Case8;
    public static int CaseLibreService=0;
    public static String CaseNameLibreService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_libreservice, container, false);

        Case1 = (Button) v.findViewById(R.id.case1);
        Case2 = (Button) v.findViewById(R.id.case2);
        Case3 = (Button) v.findViewById(R.id.case3);
        Case4 = (Button) v.findViewById(R.id.case4);
        Case5 = (Button) v.findViewById(R.id.case5);
        Case6 = (Button) v.findViewById(R.id.case6);
        Case7 = (Button) v.findViewById(R.id.case7);
        Case8 = (Button) v.findViewById(R.id.case8);

        Case1.setOnClickListener(this);
        Case2.setOnClickListener(this);
        Case3.setOnClickListener(this);
        Case4.setOnClickListener(this);
        Case5.setOnClickListener(this);
        Case6.setOnClickListener(this);
        Case7.setOnClickListener(this);
        Case8.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.case1:
                CaseLibreService=1;
                CaseNameLibreService= Case1.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case2:
                CaseLibreService=2;
                CaseNameLibreService= Case2.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case3:
                CaseLibreService=3;
                CaseNameLibreService= Case3.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case4:
                CaseLibreService=4;
                CaseNameLibreService= Case4.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case5:
                CaseLibreService=5;
                CaseNameLibreService= Case5.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case6:
                CaseLibreService=6;
                CaseNameLibreService= Case6.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case7:
                CaseLibreService=7;
                CaseNameLibreService= Case7.getText().toString();
                openLibreServiceQuantitee();
                break;
            case R.id.case8:
                CaseLibreService=8;
                CaseNameLibreService= Case8.getText().toString();
                openLibreServiceQuantitee();
                break;
        }

    }

    public void openLibreServiceQuantitee() {
        Intent intent;
        intent = new Intent(getActivity(), LibreServiceQuantiteeActivity.class);
        startActivity(intent);
    }
}

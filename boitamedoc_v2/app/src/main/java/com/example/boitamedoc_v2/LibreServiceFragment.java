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
    private Intent intent;

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
        intent = new Intent(getActivity(), LibreServiceQuantiteeActivity.class);

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
                intent.putExtra("case","1");
                openLibreServiceQuantitee();
                break;
            case R.id.case2:
                intent.putExtra("case","2");
                openLibreServiceQuantitee();
                break;
            case R.id.case3:
                intent.putExtra("case","3");
                openLibreServiceQuantitee();
                break;
            case R.id.case4:
                intent.putExtra("case","4");
                openLibreServiceQuantitee();
                break;
            case R.id.case5:
                intent.putExtra("case","5");
                openLibreServiceQuantitee();
                break;
            case R.id.case6:
                intent.putExtra("case","6");
                openLibreServiceQuantitee();
                break;
            case R.id.case7:
                intent.putExtra("case","7");
                openLibreServiceQuantitee();
                break;
            case R.id.case8:
                intent.putExtra("case","8");
                openLibreServiceQuantitee();
                break;
        }

    }

    public void openLibreServiceQuantitee() {
        startActivity(intent);
    }
}

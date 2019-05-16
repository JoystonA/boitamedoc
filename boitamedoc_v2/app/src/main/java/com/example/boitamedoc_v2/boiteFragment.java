package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class boiteFragment extends Fragment implements View.OnClickListener {
    private Button Case1;
    private Button Case2;
    private Button Case3;
    private Button Case4;
    private Button Case5;
    private Button Case6;
    private Button Case7;
    private Button Case8;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_boite, container, false);

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
                openInfoCase();
                break;
            case R.id.case2:
                openInfoCase();
                break;
            case R.id.case3:
                openInfoCase();
                break;
            case R.id.case4:
                openInfoCase();
                break;
            case R.id.case5:
                openInfoCase();
                break;
            case R.id.case6:
                openInfoCase();
                break;
            case R.id.case7:
                openInfoCase();
                break;
            case R.id.case8:
                openInfoCase();
                break;
        }
    }

    public void openInfoCase() {
        Intent intent;
        intent = new Intent(getActivity(), InfoCaseActivity.class);
        startActivity(intent);
    }
}

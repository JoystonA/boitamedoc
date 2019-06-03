package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;



public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button Case1;
    private Button Case2;
    private Button Case3;
    private Button Case4;
    private Button Case5;
    private Button Case6;
    private Button Case7;
    private Button Case8;
    private TextView Connexion;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Case1 = (Button) v.findViewById(R.id.case1);
        Case2 = (Button) v.findViewById(R.id.case2);
        Case3 = (Button) v.findViewById(R.id.case3);
        Case4 = (Button) v.findViewById(R.id.case4);
        Case5 = (Button) v.findViewById(R.id.case5);
        Case6 = (Button) v.findViewById(R.id.case6);
        Case7 = (Button) v.findViewById(R.id.case7);
        Case8 = (Button) v.findViewById(R.id.case8);
        Connexion = (TextView) v.findViewById(R.id.ConnexionBoiteInfo);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipelayout);

        Case1.setOnClickListener(this);
        Case2.setOnClickListener(this);
        Case3.setOnClickListener(this);
        Case4.setOnClickListener(this);
        Case5.setOnClickListener(this);
        Case6.setOnClickListener(this);
        Case7.setOnClickListener(this);
        Case8.setOnClickListener(this);


        if (App.bluetooth_main.getServiceState() == 3) {
            Connexion.setText("Boîte Connecté");
            swipe_null();
        }
        else {
            Connexion.setText("Impossible de se connecter à la boite !");
            swipe_connexion();
        }

        return v;

    }

    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.ConnexionBoiteInfo:
                se_connecter_bluetooth();
                break;
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

    public void swipe_connexion(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                        int min = 65;
                        int max = 95;


                        se_connecter_bluetooth();

                    }
                }, 5000);
            }
        });
    }

    public void swipe_null(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                        int min = 65;
                        int max = 95;

                        Connexion.setText("Boîte Connecté");
                    }
                }, 100);
            }
        });
    }

    public void se_connecter_bluetooth() {
        Connexion.setText("Recherche en cours ...");
        App.bluetooth_main.connect("00:06:66:6D:F1:75");
        if (App.bluetooth_main.getServiceState() == 3) {
            Connexion.setText("Boîte Connecté");
        }
    }
}
package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import static com.example.boitamedoc_v2.App.id_gestionnaire;
import static com.example.boitamedoc_v2.App.id_patient;


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
    private Intent intent;
    private TextView name_user;
    private TextView name_patient;
    private TextView dernier_prise;
    private RequestQueue queue;
    private MyRequest request;
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
        intent = new Intent(getActivity(), InfoCaseActivity.class);
        name_user = (TextView) v.findViewById(R.id.Name_User);
        dernier_prise = (TextView) v.findViewById(R.id.dernier_prise_de_medoc_accueil);
        name_patient = (TextView) v.findViewById(R.id.Name_Patient);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipelayout);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);
        request.recupPatient(id_patient, new MyRequest.recupPatientCallback() {
             @Override
             public void onSucces(JSONObject message) {
                 String nom_Patient = "ERREUR",prenom_Patient="ERREUR",last_take="ERREUR";
                 try {
                     nom_Patient = message.getString("nom");
                     prenom_Patient = message.getString("prenom");
                     last_take = message.getString("last_take");
                 }
                 catch (Exception e){
                     Log.d("APP", "onSucces: C'esst pas normal "+e.getMessage());
                 }
                  name_patient.setText(nom_Patient + " " + prenom_Patient);
                 if(!last_take.equals("null"))
                     dernier_prise.setText(last_take);
             }
             @Override
             public void onError(boolean error) {
                 name_patient.setText("ERREUR ERREUR");
             }
        });
        ///////////////////////////////////////////////////////////////////////

        request.recupGerant(id_gestionnaire, new MyRequest.recupGerantCallback() {
            @Override
            public void onSucces(String nom, String prenom) {
                name_user.setText("Bienvenue "+prenom);
            }

            @Override
            public void onError(boolean error) {
                name_user.setText("ERREUR ERREUR");
            }
        });

        ///////////////////////////////////////////////////////////////////////
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
            case R.id.case1:
                intent.putExtra("num","1");
                openInfoCase();
                break;
            case R.id.case2:
                intent.putExtra("num","2");
                openInfoCase();
                break;
            case R.id.case3:
                intent.putExtra("num","3");
                openInfoCase();
                break;
            case R.id.case4:
                intent.putExtra("num","4");
                openInfoCase();
                break;
            case R.id.case5:
                intent.putExtra("num","5");
                openInfoCase();
                break;
            case R.id.case6:
                intent.putExtra("num","6");
                openInfoCase();
                break;
            case R.id.case7:
                intent.putExtra("num","7");
                openInfoCase();
                break;
            case R.id.case8:
                intent.putExtra("num","8");
                openInfoCase();

                break;
        }
    }

    public void openInfoCase() {
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
                }, 3000);
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

                        Connexion.setText("Boîte Connectée");
                    }
                }, 100);
            }
        });
    }

    public void se_connecter_bluetooth() {
        Connexion.setText("Recherche en cours ...");
        App.bluetooth_main.connect("00:06:66:6D:F1:75");
        if (App.bluetooth_main.getServiceState() == 3) {
            Connexion.setText("Boîte Connectée");
        }
    }
}
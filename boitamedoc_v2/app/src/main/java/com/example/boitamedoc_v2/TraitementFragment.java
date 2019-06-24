package com.example.boitamedoc_v2;

import android.content.Intent;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TraitementFragment extends Fragment implements View.OnClickListener {
    private Button Case_Modifier;
    private Button Case_Ajouter;
    private Button Case_Modifier2;
    private Button Case_Modifier3;
    private Button Case_Modifier4;
    private Button Case_Modifier5;
    private Button Case_Modifier6;
    private Button Case_Modifier7;
    private Button Case_Modifier8;
    private Button Case_Suppr;
    private Button Case_Suppr2;
    private Button Case_Suppr3;
    private Button Case_Suppr4;
    private Button Case_Suppr5;
    private Button Case_Suppr6;
    private Button Case_Suppr7;
    private Button Case_Suppr8;
    private JSONObject JSONTraitements;
    private JSONObject tab_trait[] = {null,null,null,null,null,null,null,null};
    private  TextView tab_title_traiment[] = new TextView[8];
    private  TextView tab_comprimes[] = new TextView[8];
    private  TextView tab_med_date[] = new TextView[8];
    private String date_debut_ok;
    private String date_fin_ok;

    private RelativeLayout traitement[] = new RelativeLayout[8];
    private boolean trait_connue[] = {false,false,false,false,false,false,false,false};

    private RequestQueue queue;
    private MyRequest request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_traitement, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);

        Case_Modifier = (Button) v.findViewById(R.id.button_modifier);
        Case_Modifier2 = (Button) v.findViewById(R.id.button_modifier2);
        Case_Modifier3 = (Button) v.findViewById(R.id.button_modifier3);
        Case_Modifier4 = (Button) v.findViewById(R.id.button_modifier4);
        Case_Modifier5 = (Button) v.findViewById(R.id.button_modifier5);
        Case_Modifier6 = (Button) v.findViewById(R.id.button_modifier6);
        Case_Modifier7 = (Button) v.findViewById(R.id.button_modifier7);
        Case_Modifier8 = (Button) v.findViewById(R.id.button_modifier8);

        Case_Suppr = (Button) v.findViewById(R.id.button_supprimer);
        Case_Suppr2 = (Button) v.findViewById(R.id.button_supprimer2);
        Case_Suppr3 = (Button) v.findViewById(R.id.button_supprimer3);
        Case_Suppr4 = (Button) v.findViewById(R.id.button_supprimer4);
        Case_Suppr5 = (Button) v.findViewById(R.id.button_supprimer5);
        Case_Suppr6 = (Button) v.findViewById(R.id.button_supprimer6);
        Case_Suppr7 = (Button) v.findViewById(R.id.button_supprimer7);
        Case_Suppr8 = (Button) v.findViewById(R.id.button_supprimer8);

        traitement[0] = (RelativeLayout) v.findViewById(R.id.traitement_layout);
        traitement[1] = (RelativeLayout) v.findViewById(R.id.traitement_layout2);
        traitement[2] = (RelativeLayout) v.findViewById(R.id.traitement_layout3);
        traitement[3] = (RelativeLayout) v.findViewById(R.id.traitement_layout4);
        traitement[4] = (RelativeLayout) v.findViewById(R.id.traitement_layout5);
        traitement[5] = (RelativeLayout) v.findViewById(R.id.traitement_layout6);
        traitement[6] = (RelativeLayout) v.findViewById(R.id.traitement_layout7);
        traitement[7] = (RelativeLayout) v.findViewById(R.id.traitement_layout8);

        tab_title_traiment[0] = v.findViewById(R.id.title_traitement1);
        tab_title_traiment[1] = v.findViewById(R.id.title_traitement2);
        tab_title_traiment[2] = v.findViewById(R.id.title_traitement3);
        tab_title_traiment[3] = v.findViewById(R.id.title_traitement4);
        tab_title_traiment[4] = v.findViewById(R.id.title_traitement5);
        tab_title_traiment[5] = v.findViewById(R.id.title_traitement6);
        tab_title_traiment[6] = v.findViewById(R.id.title_traitement7);
        tab_title_traiment[7] = v.findViewById(R.id.title_traitement8);

        tab_comprimes[0] = v.findViewById(R.id.traitement_patient);
        tab_comprimes[1] = v.findViewById(R.id.traitement_patient2);
        tab_comprimes[2] = v.findViewById(R.id.traitement_patient3);
        tab_comprimes[3] = v.findViewById(R.id.traitement_patient4);
        tab_comprimes[4] = v.findViewById(R.id.traitement_patient);
        tab_comprimes[5] = v.findViewById(R.id.traitement_patient6);
        tab_comprimes[6] = v.findViewById(R.id.traitement_patient7);
        tab_comprimes[7] = v.findViewById(R.id.traitement_patient8);

        tab_med_date[0] = v.findViewById(R.id.name_medecin_heure_date);
        tab_med_date[1] = v.findViewById(R.id.name_medecin_heure_date2);
        tab_med_date[2] = v.findViewById(R.id.name_medecin_heure_date3);
        tab_med_date[3] = v.findViewById(R.id.name_medecin_heure_date4);
        tab_med_date[4] = v.findViewById(R.id.name_medecin_heure_date5);
        tab_med_date[5] = v.findViewById(R.id.name_medecin_heure_date6);
        tab_med_date[6] = v.findViewById(R.id.name_medecin_heure_date7);
        tab_med_date[7] = v.findViewById(R.id.name_medecin_heure_date8);

        Case_Ajouter = (Button) v.findViewById(R.id.button_ajouter);

        Case_Modifier.setOnClickListener(this);
        Case_Modifier2.setOnClickListener(this);
        Case_Modifier3.setOnClickListener(this);
        Case_Modifier4.setOnClickListener(this);
        Case_Modifier5.setOnClickListener(this);
        Case_Modifier6.setOnClickListener(this);
        Case_Modifier7.setOnClickListener(this);
        Case_Modifier8.setOnClickListener(this);

        Case_Suppr.setOnClickListener(this);
        Case_Suppr2.setOnClickListener(this);
        Case_Suppr3.setOnClickListener(this);
        Case_Suppr4.setOnClickListener(this);
        Case_Suppr5.setOnClickListener(this);
        Case_Suppr6.setOnClickListener(this);
        Case_Suppr7.setOnClickListener(this);
        Case_Suppr8.setOnClickListener(this);

        traitement[0].setVisibility(RelativeLayout.GONE);
        traitement[1].setVisibility(RelativeLayout.GONE);
        traitement[2].setVisibility(RelativeLayout.GONE);
        traitement[3].setVisibility(RelativeLayout.GONE);
        traitement[4].setVisibility(RelativeLayout.GONE);
        traitement[5].setVisibility(RelativeLayout.GONE);
        traitement[6].setVisibility(RelativeLayout.GONE);
        traitement[7].setVisibility(RelativeLayout.GONE);

        Case_Ajouter.setOnClickListener(this);
        request.RecupTrait(new MyRequest.traitementTestCallback() {
            @Override
            public void onSucces(JSONObject JSONTraitement) {
                JSONTraitements = JSONTraitement;
                try{
                    for(int i=0;i<8;i++) {
                        String i_string = Integer.toString(i + 1);
                        tab_trait[i] = JSONTraitement.getJSONObject("traitement_" + i_string);
                        String id_medoc = tab_trait[i].getString("id_medoc");
                        if (!id_medoc.equals("0")) {
                            trait_connue[i] = true;
                            // AFFICHAGE INFO
                            JSONObject json_trait = tab_trait[i];
                            request.recupMedoc(json_trait.getString("id_medoc"),i,new MyRequest.recupMedocCallback() {
                                @Override
                                public void onSucces(JSONObject message,int i) {
                                    try {
                                        String title="Traitement: " + message.getString("nom");
                                        tab_title_traiment[i].setText(title);
                                    }
                                    catch (Exception e){

                                    }

                                }

                                @Override
                                public void onError(JSONObject message) {

                                }
                            });


                            String date_debut = json_trait.getString("Date_debut");
                            SimpleDateFormat tempo = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date d = tempo.parse(date_debut);
                                tempo.applyPattern("dd/MM/yyyy");
                                date_debut_ok = tempo.format(d);
                            } catch (ParseException e) {
                                Log.d("APP", "getParams: Date Non Fonctionnel");
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }

                            String date_fin = json_trait.getString("Date_fin");
                            SimpleDateFormat tempo2 = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date d = tempo2.parse(date_fin);
                                tempo2.applyPattern("dd/MM/yyyy");
                                date_fin_ok = tempo.format(d);
                            } catch (ParseException e) {
                                Log.d("APP", "getParams: Date Non Fonctionnel");
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }

                            String comprimes = "Matin: " +json_trait.getString("matin")+", Midi: "+json_trait.getString("midi")+", Soir: "+json_trait.getString("soir");
                            tab_comprimes[i].setText(comprimes);
                            String med_date = "Prescrit par le Dr."+json_trait.getString("nom_medecin")+"\ndu "+date_debut_ok+" au "+date_fin_ok;
                            tab_med_date[i].setText(med_date);
                        }

                    }
                }
                catch (Exception e){
                    Log.d("APP", "onSucces: "+ e.getMessage());
                }
                affichageDynamiqueTraitement();
            }

            @Override
            public void onError() {

            }
        });
        return v;
    }

    @Override
    public void onClick(View V) {
        switch (V.getId()) {
        /////////////////// MODIFIER ////////////////////////
            case R.id.button_modifier :
                openTraitementModifierPage(1);
                break;
            case R.id.button_modifier2 :
                openTraitementModifierPage(2);
                break;
            case R.id.button_modifier3 :
                openTraitementModifierPage(3);
                break;
            case R.id.button_modifier4 :
                openTraitementModifierPage(4);
                break;
            case R.id.button_modifier5 :
                openTraitementModifierPage(5);
                break;
            case R.id.button_modifier6 :
                openTraitementModifierPage(6);
                break;
            case R.id.button_modifier7 :
                openTraitementModifierPage(7);
                break;
            case R.id.button_modifier8 :
                openTraitementModifierPage(8);
                break;

        ///////////////////// SUPPRIMER /////////////////////////////
            case R.id.button_supprimer :
                try {
                    JSONTraitements.put("traitement_1", tab_trait[0].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[0] = false;
                refresh();
                break;

            case R.id.button_supprimer2 :
                try {
                    JSONTraitements.put("traitement_2", tab_trait[1].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[1] = false;
                refresh();
                break;

            case R.id.button_supprimer3 :
                try {
                    JSONTraitements.put("traitement_3", tab_trait[2].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[2] = false;
                refresh();
                break;

            case R.id.button_supprimer4 :
                try {
                    JSONTraitements.put("traitement_4", tab_trait[3].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[3] = false;
                refresh();
                break;

            case R.id.button_supprimer5 :
                try {
                    JSONTraitements.put("traitement_5", tab_trait[4].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[4] = false;
                refresh();
                break;

            case R.id.button_supprimer6 :
                try {
                    JSONTraitements.put("traitement_6", tab_trait[5].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[5] = false;
                refresh();
                break;

            case R.id.button_supprimer7 :
                try {
                    JSONTraitements.put("traitement_7", tab_trait[6].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[6] = false;
                refresh();
                break;

            case R.id.button_supprimer8 :
                try {
                    JSONTraitements.put("traitement_8", tab_trait[7].put("id_medoc", "0"));
                }
                catch (Exception e){
                    Log.d("APP", "onClick: " +e.getMessage());
                }
                request.insertTrait(JSONTraitements);
                trait_connue[7] = false;
                refresh();
                break;

        ///////////////////// AJOUT /////////////////////////
            case R.id.button_ajouter:
                int i=0;
                    while(trait_connue[i]) {
                        i++;
                    }
                    openTraitementAjoutPage(i);

                break;
        }
    }

    public void openTraitementModifierPage(int i) {
        Intent intent;
        intent = new Intent(getActivity(), TraitementAjoutActivity.class).putExtra("num_trait",Integer.toString(i));
        startActivity(intent);
    }

    public void openTraitementAjoutPage(int i) {
        Intent intent;
        intent = new Intent(getActivity(), TraitementAjoutActivity.class).putExtra("num_trait",Integer.toString(i+1));
        startActivity(intent);
    }

    public void affichageDynamiqueTraitement(){
        int compte = 0;
        for(int i=0;i<trait_connue.length;i++){
            if(trait_connue[i]) {
                traitement[i].setVisibility(RelativeLayout.VISIBLE);
                compte++;
            }
            else traitement[i].setVisibility(RelativeLayout.GONE);
        }
        if(compte==8) Case_Ajouter.setVisibility(RelativeLayout.GONE);
    }

    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}
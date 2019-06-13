package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;


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
    private JSONObject tab_trait[] = {null,null,null,null,null,null,null,null};

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

        traitement[0] = (RelativeLayout) v.findViewById(R.id.traitement_layout);
        traitement[1] = (RelativeLayout) v.findViewById(R.id.traitement_layout2);
        traitement[2] = (RelativeLayout) v.findViewById(R.id.traitement_layout3);
        traitement[3] = (RelativeLayout) v.findViewById(R.id.traitement_layout4);
        traitement[4] = (RelativeLayout) v.findViewById(R.id.traitement_layout5);
        traitement[5] = (RelativeLayout) v.findViewById(R.id.traitement_layout6);
        traitement[6] = (RelativeLayout) v.findViewById(R.id.traitement_layout7);
        traitement[7] = (RelativeLayout) v.findViewById(R.id.traitement_layout8);

        Case_Ajouter = (Button) v.findViewById(R.id.button_ajouter);


        Case_Modifier.setOnClickListener(this);
        Case_Modifier2.setOnClickListener(this);
        Case_Modifier3.setOnClickListener(this);
        Case_Modifier4.setOnClickListener(this);
        Case_Modifier5.setOnClickListener(this);
        Case_Modifier6.setOnClickListener(this);
        Case_Modifier7.setOnClickListener(this);
        Case_Modifier8.setOnClickListener(this);

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
                try{
                    for(int i=0;i<8;i++) {
                        String i_string = Integer.toString(i + 1);
                        tab_trait[i] = JSONTraitement.getJSONObject("traitement_" + i_string);
                        String id_medoc = tab_trait[i].getString("id_medoc");
                        if (!id_medoc.equals("0")) {
                            trait_connue[i] = true;
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
            case R.id.button_modifier:
                openTraitementModifierPage();

                break;
            case R.id.button_ajouter:
                int i=0;
                    while(trait_connue[i]) {
                        i++;
                    }
                    openTraitementAjoutPage(i);

                break;
        }
    }

    public void openTraitementModifierPage() {
        Intent intent;
        intent = new Intent(getActivity(), TraitementModificationActivity.class);
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

}
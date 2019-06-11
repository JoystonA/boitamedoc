package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class TraitementFragment extends Fragment implements View.OnClickListener {
    private Button Case_Modifier;
    private Button Case_Ajouter;
    private Button Case_Modifier2;
    private Button Case_Ajouter2;
    private Button Case_Modifier3;
    private Button Case_Ajouter3;
    private Button Case_Modifier4;
    private Button Case_Ajouter4;
    private Button Case_Modifier5;
    private Button Case_Ajouter5;
    private Button Case_Modifier6;
    private Button Case_Ajouter6;
    private Button Case_Modifier7;
    private Button Case_Ajouter7;
    private Button Case_Modifier8;
    private Button Case_Ajouter8;

    private RelativeLayout traitement;
    private RelativeLayout traitement2;
    private RelativeLayout traitement3;
    private RelativeLayout traitement4;
    private RelativeLayout traitement5;
    private RelativeLayout traitement6;
    private RelativeLayout traitement7;
    private RelativeLayout traitement8;

    public static int nbrTraitementAjout=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_traitement, container, false);

        Case_Modifier = (Button) v.findViewById(R.id.button_modifier);
        Case_Modifier2 = (Button) v.findViewById(R.id.button_modifier2);
        Case_Modifier3 = (Button) v.findViewById(R.id.button_modifier3);
        Case_Modifier4 = (Button) v.findViewById(R.id.button_modifier4);
        Case_Modifier5 = (Button) v.findViewById(R.id.button_modifier5);
        Case_Modifier6 = (Button) v.findViewById(R.id.button_modifier6);
        Case_Modifier7 = (Button) v.findViewById(R.id.button_modifier7);
        Case_Modifier8 = (Button) v.findViewById(R.id.button_modifier8);

        traitement = (RelativeLayout) v.findViewById(R.id.traitement_layout);
        traitement2 = (RelativeLayout) v.findViewById(R.id.traitement_layout2);
        traitement3 = (RelativeLayout) v.findViewById(R.id.traitement_layout3);
        traitement4 = (RelativeLayout) v.findViewById(R.id.traitement_layout4);
        traitement5 = (RelativeLayout) v.findViewById(R.id.traitement_layout5);
        traitement6 = (RelativeLayout) v.findViewById(R.id.traitement_layout6);
        traitement7 = (RelativeLayout) v.findViewById(R.id.traitement_layout7);
        traitement8 = (RelativeLayout) v.findViewById(R.id.traitement_layout8);

        Case_Ajouter = (Button) v.findViewById(R.id.button_ajouter);


        Case_Modifier.setOnClickListener(this);
        Case_Modifier2.setOnClickListener(this);
        Case_Modifier3.setOnClickListener(this);
        Case_Modifier4.setOnClickListener(this);
        Case_Modifier5.setOnClickListener(this);
        Case_Modifier6.setOnClickListener(this);
        Case_Modifier7.setOnClickListener(this);
        Case_Modifier8.setOnClickListener(this);

        traitement.setVisibility(RelativeLayout.GONE);
        traitement2.setVisibility(RelativeLayout.GONE);
        traitement3.setVisibility(RelativeLayout.GONE);
        traitement4.setVisibility(RelativeLayout.GONE);
        traitement5.setVisibility(RelativeLayout.GONE);
        traitement6.setVisibility(RelativeLayout.GONE);
        traitement7.setVisibility(RelativeLayout.GONE);
        traitement8.setVisibility(RelativeLayout.GONE);

        Case_Ajouter.setOnClickListener(this);

        affichageDynamiqueTraitement(nbrTraitementAjout);

        return v;
    }

    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.button_modifier:
                openTraitementModifierPage();

                break;
            case R.id.button_ajouter:
                openTraitementAjoutPage();
                break;
        }
    }

    public void openTraitementModifierPage() {
        Intent intent;
        intent = new Intent(getActivity(), TraitementModificationActivity.class);
        startActivity(intent);
    }

    public void openTraitementAjoutPage() {
        Intent intent;
        intent = new Intent(getActivity(), TraitementAjoutActivity.class);
        startActivity(intent);
    }

    public void affichageDynamiqueTraitement(int nbrTraitement){
        switch (nbrTraitement){
            case 0:
                break;
            case 1:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 2:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 3:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 4:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                traitement4.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 5:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                traitement4.setVisibility(RelativeLayout.VISIBLE);
                traitement5.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 6:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                traitement4.setVisibility(RelativeLayout.VISIBLE);
                traitement5.setVisibility(RelativeLayout.VISIBLE);
                traitement6.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 7:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                traitement4.setVisibility(RelativeLayout.VISIBLE);
                traitement5.setVisibility(RelativeLayout.VISIBLE);
                traitement6.setVisibility(RelativeLayout.VISIBLE);
                traitement7.setVisibility(RelativeLayout.VISIBLE);
                break;
            case 8:
                traitement.setVisibility(RelativeLayout.VISIBLE);
                traitement2.setVisibility(RelativeLayout.VISIBLE);
                traitement3.setVisibility(RelativeLayout.VISIBLE);
                traitement4.setVisibility(RelativeLayout.VISIBLE);
                traitement5.setVisibility(RelativeLayout.VISIBLE);
                traitement6.setVisibility(RelativeLayout.VISIBLE);
                traitement7.setVisibility(RelativeLayout.VISIBLE);
                traitement8.setVisibility(RelativeLayout.VISIBLE);
                Case_Ajouter.setVisibility(Button.GONE);
                break;
           default:
               break;
        }
    }

}
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


public class TraitementFragment extends Fragment implements View.OnClickListener {
    private Button Case_Modifier;
    private Button Case_Ajouter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_traitement, container, false);

        Case_Modifier = (Button) v.findViewById(R.id.button_modifier);
        Case_Ajouter = (Button) v.findViewById(R.id.button_ajouter);

        Case_Modifier.setOnClickListener(this);
        Case_Ajouter.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View V) {
        switch (V.getId()) {
            case R.id.button_modifier:
                openTraitementPage();
                break;
            case R.id.button_ajouter:
                openTraitementPage();
                break;
        }
    }

    public void openTraitementPage() {
        Intent intent;
        intent = new Intent(getActivity(),TraitementModificationActivity.class);
        startActivity(intent);
    }
}

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

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;


public class TraitementFragment extends Fragment implements View.OnClickListener {
    private Button Case_Modifier;
    private Button Case_Ajouter;
    private RequestQueue queue;
    private MyRequest request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_traitement, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);

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
}
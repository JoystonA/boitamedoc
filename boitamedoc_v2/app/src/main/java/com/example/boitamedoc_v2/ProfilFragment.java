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

public class ProfilFragment extends Fragment implements View.OnClickListener {
    private Button modifButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        modifButton = (Button) v.findViewById(R.id.modifier_compte);
        modifButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View V) {
        openModifProfil();
    }

    public void openModifProfil() {
        Intent intent;
        intent = new Intent(getActivity(), ModifProfilActivity.class);
        startActivity(intent);
    }
}

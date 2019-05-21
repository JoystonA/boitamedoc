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

public class ProfilFragment extends Fragment {
    private Button modifButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profil, container, false);

        modifButton = (Button) v.findViewById(R.id.modifier_compte);
        modifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUp();
            }
        });
        return v;
    }

    public void openPopUp(){
        popup popup = new popup();
        popup.show(getActivity().getSupportFragmentManager(),"test popup");
    }

}

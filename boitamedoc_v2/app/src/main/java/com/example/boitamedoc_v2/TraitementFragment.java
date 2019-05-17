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


public class TraitementFragment extends Fragment implements View.OnClickListener {
    private Button Case_Modifier;
    private Button Case_Ajouter;
    private String username;
    private String password;


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
                //openPopUp();
                openTraitementModifierPage();
                break;
            case R.id.button_ajouter:
                //openPopUp();
                openTraitementAjoutPage();
                break;
        }
    }

    public void openTraitementModifierPage() {
        Intent intent;
        intent = new Intent(getActivity(),TraitementModificationActivity.class);
        startActivity(intent);
    }

    public void openTraitementAjoutPage() {
        Intent intent;
        intent = new Intent(getActivity(),TraitementAjoutActivity.class);
        startActivity(intent);
    }

    public void openPopUp(){
        popup popup = new popup();
        popup.show(getActivity().getSupportFragmentManager(),"test popup");
    }

    public void setUsername(String p){
        this.username= p;
        Log.d("theo", "ApplyText/ Username "+ username);
    }
    public void setPassword(String p){
        this.password= p;
        Log.d("theo2", "ApplyText/ " + "Password "+password);
    }
}

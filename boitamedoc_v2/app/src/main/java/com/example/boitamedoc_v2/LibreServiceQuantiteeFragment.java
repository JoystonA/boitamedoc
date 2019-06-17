package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LibreServiceQuantiteeFragment extends Fragment {
    private static TextInputLayout quantitee;
    private Button validButton;
    private String username;
    private String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_libreservice_choix, container, false);

        quantitee = v.findViewById(R.id.edit_quantitee_text);
        validButton = v.findViewById(R.id.ModifButton);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantiteeIsOk()){
                    // ICI FAUT FAIRE EN SORTE DE CREER UNE FONCTION QUI ASK LA BDD //
                    String quantiteeInput = quantitee.getEditText().getText().toString().trim();
                    App.bluetooth_main.send("Case "+Integer.toString(LibreServiceFragment.CaseLibreService)+ "|" + quantiteeInput+ " comprime(s) de " + LibreServiceFragment.CaseNameLibreService, true);
                    openPopUpInfoCase();
                }
                return;
            }
        });
        return v;
    }

    private boolean quantiteeIsOk() {
        String quantiteeInput = quantitee.getEditText().getText().toString().trim();

        if (quantiteeInput.isEmpty() || quantiteeInput.equals("0")) {
            quantitee.setError("Rentrer quelque chose!");
            //Limité avec le nombre de médicament des cases.
            return false;
        }
        quantitee.setError(null);
        return true;
    }

    private void openPopUpInfoCase() {
        popup_info_case popup = new popup_info_case();
        popup.show(getActivity().getSupportFragmentManager(),"Information");
    }

    public static String getNbrComprime(){
        return quantitee.getEditText().getText().toString().trim();
    }

}

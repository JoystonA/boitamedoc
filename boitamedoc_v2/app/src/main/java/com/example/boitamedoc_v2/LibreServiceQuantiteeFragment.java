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
    private TextInputLayout quantitee;
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
                    App.bluetooth_main.send(Integer.toString(LibreServiceFragment.CaseLibreService), true);
                    System.out.println(Integer.toString(LibreServiceFragment.CaseLibreService));
                    App.bluetooth_main.send(quantiteeInput+ " comprimé(s) de " + LibreServiceFragment.CaseNameLibreService, true);
                    System.out.println(quantiteeInput+ "comprimé(s) de " + LibreServiceFragment.CaseNameLibreService);
                    openLibreServiceQuantiteeActivity();
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

    private void openLibreServiceQuantiteeActivity() {
        Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}

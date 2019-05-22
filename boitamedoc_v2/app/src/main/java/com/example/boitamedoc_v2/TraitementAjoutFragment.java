package com.example.boitamedoc_v2;

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


public class TraitementAjoutFragment extends Fragment {

    private TextInputLayout nbre_medoc_ajout;
    private TextInputLayout nom_medecin_ajout;
    private TextInputLayout date_debut_ajout;
    private TextInputLayout date_fin_ajout;
    private Button validButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajout_traitement, container, false);

        nbre_medoc_ajout= v.findViewById(R.id.nbr_ajout_medicament);
        nom_medecin_ajout= v.findViewById(R.id.input_ajout_medecin);
        date_debut_ajout= v.findViewById(R.id.input_ajout_date_debut);
        date_fin_ajout= v.findViewById(R.id.input_ajout_date_fin);

        validButton = v.findViewById(R.id.button_valider_ajout_traitement);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nbrMedocIsOk()|nomMedecinIsOk()|dateDebutIsOk()|dateFinIsOk()){
                    // ICI FAUT FAIRE EN SORTE DE CREER UNE FONCTION QUI ASK LA BDD //
                    String nbrMedocInput = nbre_medoc_ajout.getEditText().getText().toString().trim();
                }
                return;
            }
        });
        return v;
    }

    private boolean nbrMedocIsOk() {
        String nbrMedocInput = nbre_medoc_ajout.getEditText().getText().toString().trim();

        if (nbrMedocInput.isEmpty()) {
            nbre_medoc_ajout.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_ajout.setError(null);
        return true;
    }

    private boolean nomMedecinIsOk() {
        String nomMedecinInput = nom_medecin_ajout.getEditText().getText().toString().trim();

        if (nomMedecinInput.isEmpty()) {
            nom_medecin_ajout.setError("Le champs est vide");
            return false;
        }
        nom_medecin_ajout.setError(null);
        return true;
    }

    private boolean dateDebutIsOk() {
        String dateDebutInput = date_debut_ajout.getEditText().getText().toString().trim();

        if (dateDebutInput.isEmpty()) {
            date_debut_ajout.setError("Le champs est vide");
            return false;
        }
        date_debut_ajout.setError(null);
        return true;
    }

    private boolean dateFinIsOk() {
        String dateFinInput = date_fin_ajout.getEditText().getText().toString().trim();

        if (dateFinInput.isEmpty()) {
            date_fin_ajout.setError("Le champs est vide");
            return false;
        }
        date_fin_ajout.setError(null);
        return true;
    }

}

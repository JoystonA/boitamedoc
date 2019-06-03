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
import android.widget.CheckBox;


public class TraitementModificationFragment extends Fragment {

    private TextInputLayout nbre_medoc_modification_matin;
    private CheckBox checkBox_verification_matin;
    private TextInputLayout nbre_medoc_modification_midi;
    private CheckBox checkBox_verification_midi;
    private TextInputLayout nbre_medoc_modification_soir;
    private CheckBox checkBox_verification_soir;

    private TextInputLayout nom_medecin_modification;
    private TextInputLayout date_debut_modification;
    private TextInputLayout date_fin_modification;

    private Button validButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modification_traitement, container, false);

        nbre_medoc_modification_matin= v.findViewById(R.id.nbr_modif_medicament_matin);
        checkBox_verification_matin=(CheckBox)v.findViewById(R.id.checkBox_matin);
        nbre_medoc_modification_midi= v.findViewById(R.id.nbr_modif_medicament_midi);
        checkBox_verification_midi=(CheckBox)v.findViewById(R.id.checkBox_midi);
        nbre_medoc_modification_soir= v.findViewById(R.id.nbr_modif_medicament_soir);
        checkBox_verification_soir=(CheckBox)v.findViewById(R.id.checkBox_soir);

        nom_medecin_modification= v.findViewById(R.id.input_modif_medecin);
        date_debut_modification= v.findViewById(R.id.input_modif_date_debut);
        date_fin_modification= v.findViewById(R.id.input_modif_date_fin);

        validButton = v.findViewById(R.id.button_valider_modif_traitement);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nbrMedocMatinIsOk()|nbrMedocMidiIsOk()|nbrMedocSoirIsOk()|nomMedecinIsOk()|dateDebutIsOk()|dateFinIsOk()){
                    // ICI FAUT FAIRE EN SORTE DE CREER UNE FONCTION QUI ASK LA BDD //
                    String nbrMedocInput_matin = nbre_medoc_modification_matin.getEditText().getText().toString().trim();

                }
                return;
            }
        });
        return v;
    }

    private boolean nbrMedocMatinIsOk() {
        String nbrMedocInput_matin = nbre_medoc_modification_matin.getEditText().getText().toString().trim();
        Boolean checkBox_true_matin = checkBox_verification_matin.isChecked();
        System.out.println(checkBox_true_matin);
        if (nbrMedocInput_matin.isEmpty()&&checkBox_true_matin==true) {
            nbre_medoc_modification_matin.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_modification_matin.setError(null);
        return true;
    }

    private boolean nbrMedocMidiIsOk() {
        String nbrMedocInput_midi = nbre_medoc_modification_midi.getEditText().getText().toString().trim();
        Boolean checkBox_true_midi = checkBox_verification_midi.isChecked();
        System.out.println(checkBox_true_midi);
        if (nbrMedocInput_midi.isEmpty()&&checkBox_true_midi==true) {
            nbre_medoc_modification_midi.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_modification_midi.setError(null);
        return true;
    }

    private boolean nbrMedocSoirIsOk() {
        String nbrMedocInput_soir = nbre_medoc_modification_soir.getEditText().getText().toString().trim();
        Boolean checkBox_true_soir = checkBox_verification_soir.isChecked();
        System.out.println(checkBox_true_soir);
        if (nbrMedocInput_soir.isEmpty()&&checkBox_true_soir==true) {
            nbre_medoc_modification_soir.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_modification_soir.setError(null);
        return true;
    }

    private boolean nomMedecinIsOk() {
        String nomMedecinInput = nom_medecin_modification.getEditText().getText().toString().trim();

        if (nomMedecinInput.isEmpty()) {
            nom_medecin_modification.setError("Le champs est vide");
            return false;
        }
        nom_medecin_modification.setError(null);
        return true;
    }

    private boolean dateDebutIsOk() {
        String dateDebutInput = date_debut_modification.getEditText().getText().toString().trim();

        if (dateDebutInput.isEmpty()) {
            date_debut_modification.setError("Le champs est vide");
            return false;
        }
        date_debut_modification.setError(null);
        return true;
    }

    private boolean dateFinIsOk() {
        String dateFinInput = date_fin_modification.getEditText().getText().toString().trim();

        if (dateFinInput.isEmpty()) {
            date_fin_modification.setError("Le champs est vide");
            return false;
        }
        date_fin_modification.setError(null);
        return true;
    }

}

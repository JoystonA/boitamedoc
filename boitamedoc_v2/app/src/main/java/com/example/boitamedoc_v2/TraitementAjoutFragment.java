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


public class TraitementAjoutFragment extends Fragment {

    private TextInputLayout nbre_medoc_ajout_matin;
    private CheckBox checkBox_verification_matin_ajout;
    private TextInputLayout nbre_medoc_ajout_midi;
    private CheckBox checkBox_verification_midi_ajout;
    private TextInputLayout nbre_medoc_ajout_soir;
    private CheckBox checkBox_verification_soir_ajout;

    private TextInputLayout nom_medecin_ajout;
    private TextInputLayout date_debut_ajout;
    private TextInputLayout date_fin_ajout;
    private Button validButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajout_traitement, container, false);

        nbre_medoc_ajout_matin= v.findViewById(R.id.nbr_ajout_medicament_matin);
        checkBox_verification_matin_ajout=(CheckBox)v.findViewById(R.id.checkBox_matin_ajout);
        nbre_medoc_ajout_midi= v.findViewById(R.id.nbr_ajout_medicament_midi);
        checkBox_verification_midi_ajout=(CheckBox)v.findViewById(R.id.checkBox_midi_ajout);
        nbre_medoc_ajout_soir= v.findViewById(R.id.nbr_ajout_medicament_soir);
        checkBox_verification_soir_ajout=(CheckBox)v.findViewById(R.id.checkBox_soir_ajout);

        nom_medecin_ajout= v.findViewById(R.id.input_ajout_medecin);
        date_debut_ajout= v.findViewById(R.id.input_ajout_date_debut);
        date_fin_ajout= v.findViewById(R.id.input_ajout_date_fin);

        validButton = v.findViewById(R.id.button_valider_ajout_traitement);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nbrMedocMatinIsOk()|nbrMedocMidiIsOk()|nbrMedocSoirIsOk()|nomMedecinIsOk()|dateDebutIsOk()|dateFinIsOk()){
                    // ICI FAUT FAIRE EN SORTE DE CREER UNE FONCTION QUI ASK LA BDD //
                    String nbrMedocInput_matin_ajout = nbre_medoc_ajout_matin.getEditText().getText().toString().trim();
                }
                return;
            }
        });
        return v;
    }

    private boolean nbrMedocMatinIsOk() {
        String nbrMedocInput_matin = nbre_medoc_ajout_matin.getEditText().getText().toString().trim();
        Boolean checkBox_true_matin = checkBox_verification_matin_ajout.isChecked();
        System.out.println(checkBox_true_matin);
        if (nbrMedocInput_matin.isEmpty()&&checkBox_true_matin==true) {
            nbre_medoc_ajout_matin.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_ajout_matin.setError(null);
        return true;
    }

    private boolean nbrMedocMidiIsOk() {
        String nbrMedocInput_midi = nbre_medoc_ajout_midi.getEditText().getText().toString().trim();
        Boolean checkBox_true_midi = checkBox_verification_midi_ajout.isChecked();
        System.out.println(checkBox_true_midi);
        if (nbrMedocInput_midi.isEmpty()&&checkBox_true_midi==true) {
            nbre_medoc_ajout_midi.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_ajout_midi.setError(null);
        return true;
    }

    private boolean nbrMedocSoirIsOk() {
        String nbrMedocInput_soir = nbre_medoc_ajout_soir.getEditText().getText().toString().trim();
        Boolean checkBox_true_soir = checkBox_verification_soir_ajout.isChecked();
        System.out.println(checkBox_true_soir);
        if (nbrMedocInput_soir.isEmpty()&&checkBox_true_soir==true) {
            nbre_medoc_ajout_soir.setError("Le champs est vide");
            return false;
        }
        nbre_medoc_ajout_soir.setError(null);
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

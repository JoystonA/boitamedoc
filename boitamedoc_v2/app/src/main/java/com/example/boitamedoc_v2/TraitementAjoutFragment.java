package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;


public class TraitementAjoutFragment extends Fragment implements OnItemSelectedListener {

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
    private Spinner spinner;
    private ArrayAdapter<String> dataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajout_traitement, container, false);

        nbre_medoc_ajout_matin = v.findViewById(R.id.nbr_ajout_medicament_matin);
        checkBox_verification_matin_ajout = (CheckBox) v.findViewById(R.id.checkBox_matin_ajout);
        nbre_medoc_ajout_midi = v.findViewById(R.id.nbr_ajout_medicament_midi);
        checkBox_verification_midi_ajout = (CheckBox) v.findViewById(R.id.checkBox_midi_ajout);
        nbre_medoc_ajout_soir = v.findViewById(R.id.nbr_ajout_medicament_soir);
        checkBox_verification_soir_ajout = (CheckBox) v.findViewById(R.id.checkBox_soir_ajout);

        nom_medecin_ajout = v.findViewById(R.id.input_ajout_medecin);
        date_debut_ajout = v.findViewById(R.id.input_ajout_date_debut);
        date_fin_ajout = v.findViewById(R.id.input_ajout_date_fin);

        validButton = v.findViewById(R.id.button_valider_ajout_traitement);

        spinner = (Spinner)v.findViewById(R.id.spinner_medicament_ajout_traitement);

        spinner.setOnItemSelectedListener(this);

        List<String> compartiment = new ArrayList<String>();
        compartiment.add("Case 1 | DOLIPRANE 500mg");//Mettre une méthode String qui récupère le nom du médoc dans tels cases.
        compartiment.add("Case 2");
        compartiment.add("Case 3");
        compartiment.add("Case 4");
        compartiment.add("Case 5");
        compartiment.add("Case 6");
        compartiment.add("Case 7");
        compartiment.add("Case 8");

        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, compartiment);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nbrMedocMatinIsOk()|nbrMedocMidiIsOk()|nbrMedocSoirIsOk()|nomMedecinIsOk()|dateDebutIsOk()|dateFinIsOk()){
                    // ICI FAUT FAIRE EN SORTE DE CREER UNE FONCTION QUI ASK LA BDD //
                    String nbrMedocInput_matin_ajout = nbre_medoc_ajout_matin.getEditText().getText().toString().trim();
                    TraitementFragment.nbrTraitementAjout++;
                    //Retourner sur le fragment Traitement
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
        if(dateDebutInput.length()!=10){
            date_debut_ajout.setError("La date est invalide");
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
        if(dateFinInput.length()!=10){
            date_fin_ajout.setError("La date est invalide");
            return false;
        }
        date_fin_ajout.setError(null);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();//récupère l'item choisi
        //Ecrire ce qu'on envoie à la bdd !!
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

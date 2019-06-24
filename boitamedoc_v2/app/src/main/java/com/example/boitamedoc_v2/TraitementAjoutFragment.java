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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private TextView name_legende;
    private String trait_num;
    private RequestQueue queue;
    private MyRequest request;
    private String id_medoc_all[] = new String[8];
    private String case_name_medoc_all[] = new String[8];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ajout_traitement, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);

        name_legende=(TextView) v.findViewById(R.id.name_ajout_text);
        trait_num = getActivity().getIntent().getStringExtra("num_trait");
        name_legende.setText(getActivity().getIntent().getStringExtra("name_legende"));
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
        Log.d("APP", "onCreateView: " +trait_num);

        List<String> compartiment = new ArrayList<String>();
        recupArrayList(compartiment);
      /*compartiment.add("Case 1 | DOLIPRANE 500mg");//Mettre une méthode String qui récupère le nom du médoc dans tels cases.
        compartiment.add("Case 2");
        compartiment.add("Case 3");
        compartiment.add("Case 4");
        compartiment.add("Case 5");
        compartiment.add("Case 6");
        compartiment.add("Case 7");
        compartiment.add("Case 8");*/

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medoc_matin,medoc_midi,medoc_soir;
                if(nbrMedocMatinIsOk()&nbrMedocMidiIsOk()&nbrMedocSoirIsOk()&nomMedecinIsOk()&dateDebutIsOk()&dateFinIsOk()){
                    if (checkBox_verification_matin_ajout.isChecked()) medoc_matin= nbre_medoc_ajout_matin.getEditText().getText().toString().trim();
                    else medoc_matin = "0";
                    if (checkBox_verification_midi_ajout.isChecked()) medoc_midi= nbre_medoc_ajout_midi.getEditText().getText().toString().trim();
                    else medoc_midi ="0";
                    if (checkBox_verification_soir_ajout.isChecked()) medoc_soir= nbre_medoc_ajout_soir.getEditText().getText().toString().trim();
                    else medoc_soir ="0";
                    request.RecupTrait(new MyRequest.traitementTestCallback() {
                        @Override
                        public void onSucces(JSONObject JSONTraitement) {
                            try{
                               String tempo = spinner.getSelectedItem().toString();
                                int i =0;
                                while(!tempo.equals(case_name_medoc_all[i])){
                                    i++;
                                }
                                JSONObject traitement_change = JSONTraitement.getJSONObject("traitement_"+trait_num);
                                traitement_change.put("id_medoc",id_medoc_all[i])
                                        .put("matin",medoc_matin)
                                        .put("midi",medoc_midi)
                                        .put("soir",medoc_soir)
                                        .put("nom_medecin",nom_medecin_ajout.getEditText().getText().toString().trim())
                                        .put("Date_debut",reverseDate(date_debut_ajout.getEditText().getText().toString().trim()))
                                        .put("Date_fin",reverseDate(date_fin_ajout.getEditText().getText().toString().trim()));
                                JSONTraitement.put("traitement_"+trait_num,traitement_change);
                                request.insertTrait(JSONTraitement);
                                openTraitementActivity();
                            }
                            catch (Exception e){

                            }
                        }

                        @Override
                        public void onError(boolean error) {
                        }
                    });
                }
                return;
            }
        });
        return v;
    }

    private boolean nbrMedocMatinIsOk() {
        String nbrMedocInput_matin = nbre_medoc_ajout_matin.getEditText().getText().toString().trim();
        boolean checkBox_true_matin = checkBox_verification_matin_ajout.isChecked();
        System.out.println(checkBox_true_matin);
        if (nbrMedocInput_matin.isEmpty()&&checkBox_true_matin) {
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
        if (nbrMedocInput_midi.isEmpty()&&checkBox_true_midi) {
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

    private String reverseDate(String date){
        String date_ok = date;
        SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = tempo.parse(date);
            tempo.applyPattern("yyyy-MM-dd");
            date_ok = tempo.format(d);
        } catch (ParseException e) {
            Log.d("APP", "getParams: Date nul chiant JPP");
        }
        return date_ok;
    }

    public void recupArrayList(List<String> compartiment){
        for(int i=1;i<9;i++) {
            Log.d("APP", "recupArrayList: " + i);
            request.recupMedocInfo(Integer.toString(i), new MyRequest.recupMedocInfoCallback() {
                @Override
                public void onSucces(JSONObject message) {
                    try {
                        String num_case = message.getString("case");
                        String name_medoc = message.getString("nom");
                        String id_medoc = message.getString("id_medoc");
                        case_name_medoc_all[Integer.parseInt(num_case)-1] = "CASE " +num_case+" | "+name_medoc.substring(0,18)+"...";
                        Log.d("APP", "onSucces: " + num_case+" " + id_medoc);
                        compartiment.add(case_name_medoc_all[Integer.parseInt(num_case)-1]);
                        id_medoc_all[Integer.parseInt(num_case)-1] = id_medoc;
                        if(Integer.parseInt(num_case)==8) {
                            dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, compartiment);

                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(dataAdapter);
                        }
                    } catch (Exception e) {
                        Log.d("APP", "recupArray onSucces: " + e.getMessage());
                    }
                }

                @Override
                public void onError(JSONObject message) {
                    try {
                        String num_case = message.getString("case");
                        String name_medoc = message.getString("nom");
                        String id_medoc = message.getString("id_medoc");
                        case_name_medoc_all[Integer.parseInt(num_case)-1] = "CASE " +num_case+" | "+name_medoc;
                        Log.d("APP", "onSucces: " + num_case+" " + id_medoc);
                        compartiment.add(case_name_medoc_all[Integer.parseInt(num_case)-1]);
                        id_medoc_all[Integer.parseInt(num_case)-1] = id_medoc;
                        if(Integer.parseInt(num_case)==8) {
                            dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, compartiment);

                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(dataAdapter);
                        }
                    } catch (Exception e) {
                        Log.d("APP", "recupArray onError: " + e.getMessage());
                    }
                }
            });
        }
    }

    public void openTraitementActivity() {
        Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

}// END CLASS

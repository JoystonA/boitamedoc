package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import static com.example.boitamedoc_v2.App.id_gestionnaire;
import static com.example.boitamedoc_v2.App.id_patient;

public class ProfilFragment extends Fragment implements View.OnClickListener {

    private Button modifButton;
    private RequestQueue queue;
    private MyRequest request;
    private TextView Prenom;
    private TextView Num_secu;
    private TextView Id_boite;
    private TextView Date;
    private TextView Maladie;
    private TextView Apte;
    private String str_apte;
    private boolean boolean_apte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profil, container, false);

        Prenom = (TextView) v.findViewById(R.id.name_patient_profil);
        Num_secu = (TextView) v.findViewById(R.id.num_secu_patient);
        Id_boite = (TextView) v.findViewById(R.id.num_id_boite_patient);
        Date = (TextView) v.findViewById(R.id.patient_age);
        Maladie = (TextView) v.findViewById(R.id.name_maladie_patient);
        Apte = (TextView) v.findViewById(R.id.apte_patient);

        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);

        modifButton = (Button) v.findViewById(R.id.modifier_compte);
        modifButton.setOnClickListener(this);

        request.recupProfil(id_patient, new MyRequest.recupProfilCallback() {
            @Override
            public void onSucces(String nom, String prenom, String numero_securite_social, String id_boite, String date, String maladie, int apte) {
                Prenom.setText(prenom);
                Num_secu.setText("Numéro de sécurité sociale : "+numero_securite_social);
                Id_boite.setText("Identifiant boîte : "+id_boite);
                Date.setText("Date de naissance : "+date);
                Maladie.setText("Maladie : "+maladie);

                if(apte == 0){
                    str_apte = "Non";
                    boolean_apte = false;
                }
                else if(apte == 1){
                    str_apte = "Oui";
                    boolean_apte = true;
                }
                else{
                    str_apte = "Donnée inconnue";
                }
                Apte.setText("Apte : "+str_apte);

            }

            @Override
            public void onError(boolean error) {
                Prenom.setText("ERREUR");
                Num_secu.setText("ERREUR");
                Id_boite.setText("ERREUR");
                Date.setText("ERREUR");
                Maladie.setText("ERREUR");
                Apte.setText("ERREUR");
            }
        });

        return v;
    }

    @Override
    public void onClick(View V) {
        openModifProfil();
    }

    public void openModifProfil() {
        Intent intent;
        intent = new Intent(getActivity(), ModifProfilActivity.class).putExtra("Apte", boolean_apte);
        startActivity(intent);
    }
}

package com.example.boitamedoc_v2;

import android.content.Intent;
import android.net.ParseException;
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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LibreServiceQuantiteeFragment extends Fragment {
    private TextInputLayout quantitee;
    private Button validButton;
    private TextView nom;
    private TextView date;
    private String date_ok;
    private TextView lot;
    private TextView Case;

    private RequestQueue queue;
    private MyRequest request;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_libreservice_choix, container, false);

        quantitee = v.findViewById(R.id.edit_quantitee_text);
        validButton = v.findViewById(R.id.ModifButton);

        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);
        nom = v.findViewById(R.id.MediTxt);
        lot = v.findViewById(R.id.LotTxt);
        date = v.findViewById(R.id.DateTxt);
        Case = v.findViewById(R.id.CaseTxt);

        Case.setText("Case "+getActivity().getIntent().getStringExtra("case"));
        String num_case = getActivity().getIntent().getStringExtra("case");
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);
        request.recupMedocInfo(num_case,new MyRequest.recupMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    Log.d("APP", "onSucces: " + message);

                    String medoc = message.getString("nom");
                    String date_exp = message.getString("date");
                    String lot_txt = message.getString("num_lot");
                    nom.setText(medoc);
                    lot.setText("Numéro de lot : "+lot_txt);


                    SimpleDateFormat tempo = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date d = tempo.parse(date_exp);
                        tempo.applyPattern("dd/MM/yyyy");
                        date_ok = tempo.format(d);
                    } catch (ParseException e) {
                        Log.d("APP", "getParams: Date Non Fonctionnel");
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    date.setText("Date d'expiration : "+date_ok);
                }
                catch (Exception e){

                }
            }

            @Override
            public void onError(JSONObject message) {
                nom.setText("Il n'y a pas de medicament dans cette case");
            }
        });
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String num_case = getActivity().getIntent().getStringExtra("case");
                String num_case = getActivity().getIntent().getStringExtra("case");
                quantiteeIsOk();
                App.bluetooth_main.send(num_case,true);
                //App.bluetooth_main.send("Case "+num_case + "|" + quantiteeInput+ " comprime(s) de " + LibreServiceFragment.CaseNameLibreService, true);
                //A MODIFIER RAPIDEMENT
                return;
            }
        });
        return v;
    }

    private void quantiteeIsOk() {
        String quantiteeInput = quantitee.getEditText().getText().toString().trim();
        String num_case = getActivity().getIntent().getStringExtra("case");
        Log.d("APP", "IsOk: "+ num_case);
        if (quantiteeInput.isEmpty() || quantiteeInput.equals("0")) {
            quantitee.setError("Veuillez rentrer un chiffre !");
            return;
        }
        request.quantiteeIsOk(num_case, quantiteeInput, new MyRequest.IsOkCallback() {
            @Override
            public void onSucces(boolean IsOK) {
                Log.d("APP", "onSucces: "+ IsOK);
                if(IsOK){
                    quantitee.setError(null);
                    openPopUpInfoCase();
                }
                else quantitee.setError("Quantité trop grande");
            }

            @Override
            public void onError(String message) {

            }
        });

    }

    private void openPopUpInfoCase() {
        popup_info_case popup = new popup_info_case();
        popup.show(getActivity().getSupportFragmentManager(),"Information");
    }

    public String getNbrComprime(){
        String quantiteeInput = quantitee.getEditText().getText().toString().trim();
        return quantiteeInput;
    }
}

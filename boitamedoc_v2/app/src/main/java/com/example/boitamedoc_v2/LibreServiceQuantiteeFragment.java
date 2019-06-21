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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;


public class LibreServiceQuantiteeFragment extends Fragment {
    private TextInputLayout quantitee;
    private Button validButton;
    private TextView nom;
    private TextView date;
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
                  lot.setText(lot_txt);
                  date.setText(date_exp);
              }
              catch (Exception e){

              }
            }

            @Override
            public void onError(JSONObject message) {

            }
        });

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantiteeIsOk();
            }
        });
        return v;
    }

    private void quantiteeIsOk() {
        String quantiteeInput = quantitee.getEditText().getText().toString().trim();
        String num_case = getActivity().getIntent().getStringExtra("case");
        if (quantiteeInput.isEmpty() || quantiteeInput.equals("0")) {
            quantitee.setError("Rentrer quelque chose!");
            return;
        }
        request.quantiteeIsOk(num_case, quantiteeInput, new MyRequest.IsOkCallback() {
            @Override
            public void onSucces(boolean IsOK) {
                Log.d("APP", "onSucces: "+ IsOK);
                if(IsOK){
                    quantitee.setError(null);
                    openMain();
                }
                else quantitee.setError("Quantitee trop grande");
            }

            @Override
            public void onError(String message) {

            }
        });

    }

    private void openMain() {
        Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}

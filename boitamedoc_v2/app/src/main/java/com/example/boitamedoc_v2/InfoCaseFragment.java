package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONObject;

public class InfoCaseFragment extends Fragment {
    private TextView scrolltxt;
    private TextView nameMedic;
    private TextView nb_comprimes;
    private TextView num_lot;
    private TextView date_exp;
    private Button modifButton;
    private RequestQueue queue;
    private MyRequest request;
    private String numCase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_infocase, container, false);

        numCase = getActivity().getIntent().getStringExtra("num");
        scrolltxt = (TextView) v.findViewById(R.id.descrip_medoc);
        nameMedic = v.findViewById(R.id.MediTxt);
        num_lot = v.findViewById(R.id.LotTxt);
        nb_comprimes = v.findViewById(R.id.nb_comprimes);
        date_exp = v.findViewById(R.id.DateTxt);
        modifButton= (Button) v.findViewById(R.id.ModifButton);
        TextView casetxt =  v.findViewById(R.id.CaseTxt);
        casetxt.setText("case " + numCase);
        scrolltxt.setMovementMethod(new ScrollingMovementMethod());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        request = new MyRequest(getActivity(), queue);

        request.recupMedocInfo(numCase, new MyRequest.recupMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    nameMedic.setText(message.getString("nom"));
                    scrolltxt.setText("Description :\n" + message.getString("definition"));
                    date_exp.setText("Date exp :" + message.getString("date"));
                    num_lot.setText("Numéro de lot :" + message.getString("num_lot"));
                    if(message.getString("nbr_comprimes").equals("1")|| message.getString("nbr_comprimes").equals("0"))
                        nb_comprimes.setText(message.getString("nbr_comprimes") + " Comprime");
                    else
                        nb_comprimes.setText(message.getString("nbr_comprimes") + " Comprimes");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {
                scrolltxt.setText("Description :\n" );
                nameMedic.setText("Il n'y a pas de medicalment dans cette case");
            }
        });

        modifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanBarcode();;
            }
        });
        return v;
    }

    private void openScanBarcode(){
        Intent intent;
        intent = new Intent(getActivity(), ScannedBarcodeActivity.class).putExtra("numCase",numCase);
        startActivity(intent);
    }
}

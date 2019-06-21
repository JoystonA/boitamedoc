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

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;


public class LibreServiceQuantiteeFragment extends Fragment {
    private TextInputLayout quantitee;
    private Button validButton;
    private String username;
    private String password;

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

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_case = getActivity().getIntent().getStringExtra("case");
                quantiteeIsOk();
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
                    openPopUpInfoCase();
                }
                else quantitee.setError("Quantitee trop grande");
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

    public static String getNbrComprime(){
        return quantitee.getEditText().getText().toString().trim();
    }
}

package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LibreServiceQuantiteeFragment extends Fragment {
    private TextInputLayout quantitee;
    private Button validButton;
    private TextView test;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_libreservice_choix, container, false);

        quantitee = v.findViewById(R.id.edit_quantitee_text);
        validButton = v.findViewById(R.id.ModifButton);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!quantiteeIsOk()|| 1==1){
                    return;
                }

                String InputQuantitee = quantitee.getEditText().getText().toString().trim();
                Toast.makeText(getActivity(), InputQuantitee, Toast.LENGTH_SHORT).show();
            }
        });
        test = v.findViewById(R.id.txtTest);
        return v;
    }

    private boolean quantiteeIsOk() {
        String quantiteeInput = quantitee.getEditText().getText().toString().trim();

        if (quantiteeInput.isEmpty()) {
            quantitee.setError("Rentrer quelque chose");
            return false;
        }
        quantitee.setError(null);
        return true;
    }
}

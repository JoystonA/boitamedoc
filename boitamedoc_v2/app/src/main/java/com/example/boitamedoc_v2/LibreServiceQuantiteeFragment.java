package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LibreServiceQuantiteeFragment extends Fragment {
    private EditText quantitee;
    private Button validButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        quantitee = getView().findViewById(R.id.EditQuantitee);
        validButton = getView().findViewById(R.id.ValidButton);
        if(validButton ==null) {
            validButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            quantitee.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String quantiteeTxt = quantitee.getText().toString().trim();

                    validButton.setEnabled(!quantiteeTxt.isEmpty());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        return inflater.inflate(R.layout.fragment_libreservice_choix, container,false);
    }
}

package com.example.boitamedoc_v2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class popup_connexion extends AppCompatDialogFragment {
    private EditText edit_username;
    private EditText edit_password;
    private popupListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (popupListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement PopupListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_popup, null);
        builder.setView(view)
                .setTitle("Connexion")
                .setNegativeButton("Annulé", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = edit_username.getText().toString();
                        String password  = edit_password.getText().toString();
                        listener.applyTexts(username,password);

                    }
                });
        edit_username = view.findViewById(R.id.edit_username);
        edit_password = view.findViewById(R.id.edit_password);



        return builder.create();
    }

    public interface popupListener{
        void applyTexts(String Username, String Password);
    }
}

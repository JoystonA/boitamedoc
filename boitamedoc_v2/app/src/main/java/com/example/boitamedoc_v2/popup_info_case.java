package com.example.boitamedoc_v2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;



public class popup_info_case extends AppCompatDialogFragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_popup_info_case, null);
        builder.setView(view)
                .setTitle("Attention!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openMainActivity();
                    }
                });
        return builder.create();
    }

    private void openMainActivity() {
        Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void assezMedoc(){
        //if(nbr_medoc>=nbr_medoc_demande){

    }
}

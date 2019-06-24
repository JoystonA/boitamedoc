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
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class popup_info_case extends AppCompatDialogFragment {

    private TextView text;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_popup_info_case, null);
        text = (TextView) view.findViewById(R.id.text_info_case);
        //(Integer.parseInt(LibreServiceQuantiteeFragment.getNbrComprime())<=100) {
            text.setText("Veuillez prendre vos médicaments !");
            builder.setView(view)
                    .setTitle("Vos médicaments !")
                    .setPositiveButton("Fermer la case", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            App.bluetooth_main.send("Case 0",true);
                            openMainActivity();
                        }
                    });
        //}
        /*else {
            text.setText("ATTENTION ! Il n'y pas assez de médicaments !");
            builder.setView(view)
                    .setTitle("ATTENTION !")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
        }*/
        return builder.create();
    }

    private void openMainActivity() {
        Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


}

package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InfoCaseFragment extends Fragment {
    private TextView scrolltxt;
    private Button modifButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_infocase, container, false);

        scrolltxt = (TextView) v.findViewById(R.id.textView3);
        modifButton= (Button) v.findViewById(R.id.ModifButton);

        scrolltxt.setMovementMethod(new ScrollingMovementMethod());
        modifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUp();
                //openDataMatrix();
            }
        });
        return v;
    }

    public void openPopUp(){
        popup popup = new popup();
        popup.show(getActivity().getSupportFragmentManager(),"test popup");
    }
//    private void openDataMatrix(){
//       Intent intent;
//        intent = new Intent(getActivity(), DataMatrix.class);
//       startActivity(intent);
//    }
}

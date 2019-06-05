package com.example.boitamedoc_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.boitamedoc_v2.myrequest.MyRequest;
import com.mysql.cj.x.protobuf.MysqlxNotice;


public class ConnexionActivity extends AppCompatActivity {

    private TextInputLayout til_email, til_mdp;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setTitle("Connexion");

        til_email = (TextInputLayout) findViewById(R.id.log_username_text);
        til_mdp = (TextInputLayout) findViewById(R.id.log_password_text);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

    }


    public void ValidationConnexion(View v){
        Intent intent;
        String Email = til_email.getEditText().getText().toString();
        String MDP = til_mdp.getEditText().getText().toString();
        if (Email.isEmpty() && !MDP.isEmpty()) {
            til_email.setError("Rentrer quelque chose");
            til_mdp.setError(null);
        }
        else if(MDP.isEmpty() && !Email.isEmpty()){
            til_mdp.setError("Rentrer quelque chose");
            til_email.setError(null);
        }
        else if (Email.isEmpty() && MDP.isEmpty()) {
            til_email.setError("Rentrer quelque chose");
            til_mdp.setError("Rentrer quelque chose");
        }
        else{
            til_email.setError(null);
            til_mdp.setError(null);
            intent = new Intent(this, MainActivity.class);

            request.connexion(Email, MDP, new MyRequest.ConnexionCallback(){
                @Override
                public void onSucces(int message) {
                    startActivity(intent);
                }

                @Override
                public void onError(boolean errors) {
                    Log.d("APP", "onError: " + errors);
                    til_email.setError("Email ou mot de passe incorrect");

                    }
            });
        }

    }
}

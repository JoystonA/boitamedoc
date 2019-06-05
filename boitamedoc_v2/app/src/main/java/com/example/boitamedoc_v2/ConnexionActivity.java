package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;


public class ConnexionActivity extends AppCompatActivity {

    private TextInputLayout til_email, til_mdp;
    private ProgressBar pb_loader;
    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setTitle("Connexion");

        til_email = (TextInputLayout) findViewById(R.id.log_username_text);
        til_mdp = (TextInputLayout) findViewById(R.id.log_password_text);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

    }


    public void ValidationConnexion(View v){
        pb_loader.setVisibility(View.VISIBLE);
        Intent intent;
        String Email = til_email.getEditText().getText().toString();
        String MDP = til_mdp.getEditText().getText().toString();
        if (Email.isEmpty() && !MDP.isEmpty()) {
            til_email.setError("Veuillez rentrer un email");
            til_mdp.setError(null);
            pb_loader.setVisibility(View.GONE);
        }
        else if(MDP.isEmpty() && !Email.isEmpty()){
            til_mdp.setError("Veuillez rentrer un mot de passe");
            til_email.setError(null);
            pb_loader.setVisibility(View.GONE);
        }
        else if (Email.isEmpty() && MDP.isEmpty()) {
            til_email.setError("Veuillez rentrer un email");
            til_mdp.setError("Veuillez rentrer un mot de passe");
            pb_loader.setVisibility(View.GONE);
        }
        else{
            til_email.setError(null);
            til_mdp.setError(null);
            intent = new Intent(this, MainActivity.class);

            request.connexion(Email, MDP, new MyRequest.ConnexionCallback(){
                @Override
                public void onSucces(int message) {
                    pb_loader.setVisibility(View.GONE);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Vous vous êtes bien connecté !", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(boolean error) {
                    pb_loader.setVisibility(View.GONE);
                    Log.d("APP", "onError: " + error);
                    Toast.makeText(getApplicationContext(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }

            });
        }

    }
}

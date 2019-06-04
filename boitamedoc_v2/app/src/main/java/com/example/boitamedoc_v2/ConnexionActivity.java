package com.example.boitamedoc_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.boitamedoc_v2.myrequest.MyRequest;
import com.mysql.cj.x.protobuf.MysqlxNotice;


public class ConnexionActivity extends AppCompatActivity {

    private Button boutonConnexion;
    private TextInputLayout til_email, til_mdp;
    private RequestQueue queue;
    private MyRequest request;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setTitle("Connexion");

        til_email = (TextInputLayout) findViewById(R.id.log_username_text);
        til_mdp = (TextInputLayout) findViewById(R.id.log_password_text);
        boutonConnexion = (Button) findViewById(R.id.ValidButton_connexion);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        sessionManager = new SessionManager(this);

        boutonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = til_email.getEditText().getText().toString().trim();
                String mdp = til_mdp.getEditText().getText().toString().trim();
                if(email.length() > 0 && mdp.length() > 0) {
                    request.connexion(email, mdp);
                }

            }
        });


    }

    private void openApplication(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

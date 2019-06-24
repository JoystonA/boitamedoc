package com.example.boitamedoc_v2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.boitamedoc_v2.myrequest.MyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.boitamedoc_v2.App.id_gestionnaire;

public class NotificationActivity extends AppCompatActivity {

    private RequestQueue queue;
    private MyRequest request;
    private TextView Case3;
    private TextView Case7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Case3 = (TextView) findViewById(R.id.text_posologie3);
        Case7 = (TextView) findViewById(R.id.text_posologie7);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        request.recupMedocInfo("3", new MyRequest.recupMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    Case3.setText("Case 3 : " + "2 comprimé(s) de " + message.getString("nom").substring(0,10) + "...");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject message) {
                Case3.setText("Il n'y a pas de medicament dans cette case");
            }
        });

        request.recupMedocInfo("7", new MyRequest.recupMedocInfoCallback() {
            @Override
            public void onSucces(JSONObject message) {
                try {
                    Case7.setText("Case 7 : " + "2 comprimé(s) de " + message.getString("nom").substring(0,10) + "...");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject message) {
                Case7.setText("Il n'y a pas de medicament dans cette case");
            }
        });

    }
}

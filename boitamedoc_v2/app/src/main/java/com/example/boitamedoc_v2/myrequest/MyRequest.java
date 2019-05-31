package com.example.boitamedoc_v2.myrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ipsaous on 25/09/2015.
 */
public class MyRequest {

    private Context context;
    private RequestQueue queue;

    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String str_nom, final String str_prenom, final String str_lienPatient, final String str_date, final String str_email, final String str_MDP, final String str_confirmMdp){

        String url = "https://www.boitamedoc.com/connexion/inscription_gestionnaire.php";
        //String url = "http://127.0.0.1/test/test.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("APP", "ERROR = " + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                map.put("lienPatient", str_lienPatient);
                map.put("date", str_date);
                map.put("email", str_email);
                map.put("mdp", str_MDP);
                map.put("confirmeMDP", str_confirmMdp);

                return map;
            }
        };

        queue.add(request);

    }


    public void register(final String str_nom, final String str_prenom, final String str_date, final String str_maladie, final String str_numSecu, final String str_apte){

        String url = "https://www.boitamedoc.com/connexion/inscription_patient.php";
        //String url = "http://127.0.0.1/test/test.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("APP", "ERROR = " + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                map.put("date", str_date);
                map.put("maladie", str_maladie);
                map.put("numSecu", str_numSecu);
                map.put("apte", str_apte);

                return map;
            }
        };

        queue.add(request);

    }

}
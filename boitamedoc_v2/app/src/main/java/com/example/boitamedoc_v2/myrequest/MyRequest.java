package com.example.boitamedoc_v2.myrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ipsaous on 25/09/2015.
 */
public class MyRequest {

    private Context context;
    private RequestQueue queue;
    private String url_debut ="https://www.boitamedoc.com/test/";
    private String[] returnReponse={null,null,null};
    private boolean attRep;


    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String str_nom, final String str_prenom, final String str_lienPatient, final String str_date, final String str_email, final String str_MDP, final String str_confirmMdp, InscripGerantCallback callback){

        String url = url_debut + "inscription_gestionnaire.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean errors[]={false,false,false};
                if(response!=null){
                    Log.d("APP", "onResponse: "+response) ;
                }

                try {
                    JSONObject reponse = new JSONObject(response);
                    boolean error = reponse.getBoolean("error");
                    if(error){
                        try {
                            JSONObject message = reponse.getJSONObject("message");
                            try {
                                if (!message.getString("nom").equals(null)){
                                    errors[0] = true;
                                }
                            }
                            catch (Exception e){
                            }
                            try {
                                if (!message.getString("prenom").equals(null)){
                                    errors[1] = true;
                                }
                            }
                            catch (Exception e){
                            }
                            try {
                                if (!message.getString("email").equals(null)){
                                    errors[2] = true;
                                }
                            }
                            catch (Exception e){
                            }
                        }
                        catch (Exception e){

                        }// Try get Message
                        callback.onError(errors);
                    }
                    else{
                        callback.onSucces("Ca a marché");
                    }
                }
                catch(Exception e){

                }// TRY get JSON RESPONSE


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APP", "ERROR = " + error);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                String date_ok =str_date;
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                map.put("lien_patient", str_lienPatient);
                map.put("email", str_email);
                map.put("mdp", str_MDP);
                map.put("confirmeMDP", str_confirmMdp);
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(str_date);
                    tempo.applyPattern("yyyy/MM/dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("APP", "getParams: " + date_ok);
                map.put("date", date_ok);
                return map;
            }
        };
        queue.add(request);
    }

    public String[] checkNumSecu(final String numSecu, NumSecuCallback Callback) {
        String url = url_debut + "CheckNumSecu.php";
        attRep = false;
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.d("APP", "on Response :" + response);
                    try {
                        Log.d("APP", "onResponse:  " + response);
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message.getString("nom"),message.getString("prenom"));
                    }
                    else{
                        Callback.onError("Unknonw");
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.d("APP", response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("APP", "ERROR = " + error);

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("numSecu", numSecu);
                    return map;
                }
            };
            queue.add(request);
            return returnReponse;
        }


        public interface NumSecuCallback{
        void onSucces(String nom, String prenom);
        void onError(String message);
        }

    public interface InscripGerantCallback{
        void onSucces(String message);
        void onError(boolean errors[]);
    }
}
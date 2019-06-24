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

import static com.example.boitamedoc_v2.App.id_boite;
import static com.example.boitamedoc_v2.App.id_gestionnaire;
import static com.example.boitamedoc_v2.App.id_patient;

public class MyRequest {

    private Context context;
    private RequestQueue queue;
    private String url_debut ="https://www.boitamedoc.com/test/";

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
                String rep="";
                if(response!=null){
                    //Log.d("APP", "onResponse: "+response) ;
                }
                try {
                    JSONObject reponse = new JSONObject(response);
                    JSONObject message = reponse.getJSONObject("message");
                    boolean error = reponse.getBoolean("error");
                    if(error){
                        try {
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
                                    rep = message.getString("email");
                                }
                            }
                            catch (Exception e){
                            }
                        }
                        catch (Exception e){
                            Log.d("APP", "onResponse: "+ e.getMessage());
                        }// Try get Message
                        callback.onError(errors,rep);
                    }
                    else{
                        id_gestionnaire =  message.getString("ID");
                        //Log.d("APP", "onResponse all pass: " + id_gestionnaire);
                        if(id_gestionnaire != null)callback.onSucces(id_gestionnaire);
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
                map.put("email", str_email);
                map.put("mdp", str_MDP);
                map.put("confirmeMDP", str_confirmMdp);
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(str_date);
                    tempo.applyPattern("yyyy/MM/dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    Log.d("APP", "getParams: Date nul chiant JPP");
                }
                map.put("date", date_ok);

                return map;
            }
        };
        queue.add(request);
    }

    public void registerPatient(final String str_nom, final String str_prenom, final String str_date, final String str_maladie, final String str_num_secu, final String str_apte, final InscripPatientCallback callback){

        String url = url_debut + "inscription_patient.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean errors[]={false,false};
                if(response!=null){
                    //Log.d("APP", "onResponse: "+response) ;
                }
                try {
                    JSONObject reponse = new JSONObject(response);
                    JSONObject message = reponse.getJSONObject("message");
                    boolean error = reponse.getBoolean("error");
                    if(error){
                        try {
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
                        }
                        catch (Exception e){
                            Log.d("APP", "onResponse: "+ e.getMessage());
                        }// Try get Message
                        //Log.d("APP", "onResponse: "+errors[0] + " " + errors[1] );
                        callback.onError(errors);
                    }
                    else{
                        id_patient =  message.getString("ID");
                        callback.onSucces(message.getString("prenom"),message.getString("nom"));

                    }
                }
                catch(Exception e){

                }
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
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                String date_ok =str_date;
                map.put("maladie", str_maladie);
                map.put("numSecu", str_num_secu);
                map.put("apte", str_apte);
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(str_date);
                    tempo.applyPattern("yyyy/MM/dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    Log.d("APP", "getParams: Date nul chiant JPP");
                }
                map.put("date", date_ok);
                JSONObject json = new JSONObject();
                try {
                    json.put("traitement_1", new JSONObject()
                            .put("id_medoc", "0")
                            .put("matin", "0")
                            .put("midi", "0")
                            .put("soir", "0")
                            .put("nom_medecin", "")
                            .put("Date_debut", 0000 - 00 - 00)
                            .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_2", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_3", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_4", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_5", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_6", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_7", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00))
                            .put("traitement_8", new JSONObject()
                                    .put("id_medoc", "0")
                                    .put("matin", "0")
                                    .put("midi", "0")
                                    .put("soir", "0")
                                    .put("nom_medecin", "")
                                    .put("Date_debut", 0000 - 00 - 00)
                                    .put("Date_fin", 0000 - 00 - 00));
                }
                catch (Exception e){
                    Log.d("APP", "getParams: C'est la merde ");
                }
                map.put("traitement",json.toString());
                return map;
            }
        };
        queue.add(request);
    }

    public void connexion(final String str_email, final String str_mdp, final ConnexionCallback callback){

        String url = url_debut + "connexion.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("APP", "onResponse: " + response) ;

                if(response!=null){
                 //   Log.d("APP", "onResponse: " + response) ;
                }
                try {
                    JSONObject reponse = new JSONObject(response);
                    boolean error = reponse.getBoolean("error");
                    if(error){
                        callback.onError(error);
                    }
                    else{
                        JSONObject message = reponse.getJSONObject("message");
                        id_gestionnaire =  message.getString("id_gestionnaire");
                        id_patient = message.getString("id_patient");
                        callback.onSucces(id_gestionnaire);
                    }
                }
                catch(Exception e){
                    Log.d("APP", "onResponse: " + e.getMessage());
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
                map.put("email", str_email);
                map.put("mdp", str_mdp);

                return map;
            }
        };
        queue.add(request);
    }

    public void checkNumSecu(final String numSecu, NumSecuCallback Callback) {
        String url = url_debut + "CheckNumSecu.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    //Log.d("APP", "on Response :" + response);
                    try {
                        //Log.d("APP", "onResponse:  " + response);
                        JSONObject json = new JSONObject(response);
                        boolean connue = json.getBoolean("connue");
                        if (connue) {
                            JSONObject message = json.getJSONObject("message");
                            id_patient = message.getString("id_patient");
                            Callback.onSucces(message.getString("nom"),message.getString("prenom"));
                        }
                        else{
                            Callback.onError("Unknonw");
                        }
                    } catch (Exception e) {
                        Log.d("APP", "onResponse: ERREUR " + e.getMessage());
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
    }

    public void recupPatient(String id_patient, recupPatientCallback Callback) {
        String url = url_debut + "RecupPatient.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message);
                    }
                    else{
                        Callback.onError(true);
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
                map.put("id_patient", id_patient);
                return map;
            }
        };
        queue.add(request);
    }

    public void recupGerant(String id_gerant, recupGerantCallback Callback) {
        String url = url_debut + "RecupGerant.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message.getString("nom"),message.getString("prenom"));
                    }
                    else{
                        Callback.onError(true);
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
                map.put("id_gerant", id_gestionnaire);
                return map;
            }
        };
        queue.add(request);
    }

    public void AjoutPatient(String id_patient,String id_gerant) {
        String url = url_debut + "AjoutPatient.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d("APP", "on Response AjourPatient:" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if (!error) {
                    //    Log.d("APP", "onResponse: INSERTION OKOKOKOK");
                    }
                    else{
                    //    Log.d("APP", "onResponse: ERREUR DANS LINSERTION");
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
                map.put("id_gerant", id_gerant);
                Log.d("APP", "getParams: " + id_gerant + " "+ id_patient);
                map.put("id_patient", id_patient);
                return map;
            }
        };
        queue.add(request);
    }

    public void recupMedocInfo(String id_case, recupMedocInfoCallback Callback) {
        String url = url_debut + "RecupMedocInfo.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject message = json.getJSONObject("message");
                    boolean error = json.getBoolean("erreur");
                    if (!error) {
                        if(!message.getString("id_medoc").equals("0"))
                            Callback.onSucces(message);
                        else
                            Callback.onError(message);
                    }
                    else{
                        Callback.onError(message);
                    }
                } catch (Exception e) {
                    Log.d("APP", "onResponse: " +e.getMessage());
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
                map.put("id_boite", id_boite);
                map.put("id_case1", id_case);
                return map;
            }
        };
        queue.add(request);
    }

    public void recupMedoc(String id_medoc, recupMedocInfoCallback Callback) {
        String url = url_debut + "RecupMedicament.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message);
                    }
                    else{
                        Callback.onError(json);
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
                map.put("id_medoc", id_medoc);
                return map;
            }
        };
        queue.add(request);
    }

    public void recupMedoc(String id_medoc,int i, recupMedocCallback Callback) {
        String url = url_debut + "RecupMedicament.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message,i);
                    }
                    else{
                        Callback.onError(json);
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
                map.put("id_medoc", id_medoc);
                return map;
            }
        };
        queue.add(request);
    }

    public void insetMedoc(String id_case, String id_medoc,String date,String lot) {
        String url = url_debut + "insertMedoc.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    String message = json.getString("message");
                    boolean error = json.getBoolean("error");
                    if (!error) {
                    }
                    else{
                        Log.d("APP", "onResponse: " + message);
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
                map.put("id_boite", id_boite);
                map.put("id_medoc", id_medoc);
                map.put("id_case1", id_case);
                String date_ok = date;
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(date);
                    tempo.applyPattern("yyyy-MM-dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    Log.d("APP", "getParams: Date nul chiant JPP");
                }
                Log.d("APP", "getParams: " + date_ok);
                map.put("date", date_ok);
                map.put("num_lot", lot);
                return map;
            }
        };
        queue.add(request);
    }

    public void RecupTrait(traitementTestCallback Callback) {
        String url = url_debut + "recupTrait.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject JSONtraitement;
                String tempo;
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if (!error) {
                        tempo = json.getString("traitement");
                        JSONtraitement = new JSONObject(tempo);
                    //    Log.d("APP", "onResponse: if " + JSONtraitement);
                        Callback.onSucces(JSONtraitement);
                    }
                    else{
                        Callback.onError();
                        Log.d("APP", "onResponse: else " + json.getString("message"));
                    }
                }
                catch (Exception e) {
                    Log.d("APP", "onResponse: catch Myrequest " + e.getMessage() + " "+e.getClass());
                }
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
                map.put("id_patient", id_patient);
                return map;
            }
        };
        queue.add(request);
    }

    public void insertTrait(JSONObject traitement) {
        String url = url_debut + "insertTrait.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if(error)
                    {
                        String message = json.getString("message");
                        Log.d("APP", "onResponse: "+ message);
                    }
                }
                catch(Exception e){

                }
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
                map.put("id_patient",id_patient);
                map.put("traitement",traitement.toString());
                return map;
            }
        };
        queue.add(request);
    }

    public void recupCompte(String id_gerant, recupCompteCallback Callback) {
        String url = url_debut + "RecupCompte.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message.getString("nom"), message.getString("prenom"), message.getString("lienPatient"), message.getString("date"), message.getString("email"));
                    }
                    else{
                        Callback.onError(true);
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
                map.put("id_gerant", id_gerant);
                return map;
            }
        };
        queue.add(request);
    }

    public void modifCompte(String id_gerant, final String str_nom, final String str_prenom, final String str_lienPatient, final String str_date, final String str_email, final String str_old_MDP, final String str_MDP, final String str_confirmMdp, modifCompteCallback callback){

        String url = url_debut + "ModifCompte.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean errors[]={false,false,false,false};
                String rep_email="";
                String rep_mdp="";
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
                                    rep_email = message.getString("email");
                                }
                            }
                            catch (Exception e){
                            }

                            try {
                                if (!message.getString("mdp").equals(null)){
                                    errors[3] = true;
                                    rep_mdp = message.getString("mdp");
                                }
                            }
                            catch (Exception e){
                            }
                        }
                        catch (Exception e){
                            Log.d("APP", "onResponse: "+ e.getMessage());
                        }// Try get Message
                        callback.onError(errors, rep_email, rep_mdp);
                    }
                    else{
                        Log.d("APP", "onResponse: callback");
                        callback.onSucces();
                    }
                }
                catch(Exception e){
                    Log.d("APP", "onResponse: "+e.getMessage());
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
                map.put("id_gerant", id_gerant);
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                map.put("lien_patient", str_lienPatient);
                map.put("old_mdp", str_old_MDP);
                map.put("email", str_email);
                map.put("mdp", str_MDP);
                map.put("confirmeMDP", str_confirmMdp);
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(str_date);
                    tempo.applyPattern("yyyy/MM/dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    Log.d("APP", "getParams: Date nul chiant JPP");
                }
                map.put("date", date_ok);

                return map;
            }
        };
        queue.add(request);
    }

    public void recupProfil(String id_patient, recupProfilCallback Callback) {
        String url = url_debut + "RecupProfil.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("APP", "on Response :" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    boolean connue = json.getBoolean("connue");
                    if (connue) {
                        JSONObject message = json.getJSONObject("message");
                        Callback.onSucces(message.getString("nom"), message.getString("prenom"), message.getString("numero_securite_social"), message.getString("id_boite"), message.getString("date"), message.getString("maladie"), message.getInt("apte"));
                    }
                    else{
                        Callback.onError(true);
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
                map.put("id_patient", id_patient);
                return map;
            }
        };
        queue.add(request);
    }

    public void modifProfil(String id_patient, final String str_nom, final String str_prenom, final String str_date, final String str_maladie, final String str_apte, modifProfilCallback callback){

        String url = url_debut + "ModifProfil.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean errors[]={false,false};
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

                        }
                        catch (Exception e){
                            Log.d("APP", "onResponse: "+ e.getMessage());
                        }// Try get Message
                        callback.onError(errors);
                    }
                    else{
                        Log.d("APP", "onResponse: appel de la fonction callback");
                        callback.onSucces();
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
                map.put("id_patient", id_patient);
                map.put("nom", str_nom);
                map.put("prenom", str_prenom);
                map.put("maladie", str_maladie);
                map.put("apte", str_apte);
                SimpleDateFormat tempo = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d = tempo.parse(str_date);
                    tempo.applyPattern("yyyy/MM/dd");
                    date_ok = tempo.format(d);
                } catch (ParseException e) {
                    Log.d("APP", "getParams: Date nul chiant JPP");
                }
                map.put("date", date_ok);

                return map;
            }
        };
        queue.add(request);
    }

    public void modifDernierePrise(String id_patient, final String str_dernierePrise, modifDernierePriseCallback callback){

        String url = url_debut + "ModifDernierePrise.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response!=null){
                    //   Log.d("APP", "onResponse: " + response) ;
                }
                try {
                    JSONObject reponse = new JSONObject(response);
                    boolean error = reponse.getBoolean("error");
                    if(error){
                        callback.onError(error);
                    }
                    else{
                        callback.onSucces();
                    }
                }
                catch(Exception e){
                    Log.d("APP", "onResponse: " + e.getMessage());
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
                map.put("id_patient", id_patient);
                map.put("dernierePrise", str_dernierePrise);

                return map;
            }
        };
        queue.add(request);
    }

    public void quantiteeIsOk(String num_case,String quantitee,IsOkCallback callback){

        String url = url_debut + "IsOk.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject json = new JSONObject(response);
                    Log.d("APP", "onResponse: "+ json);
                    boolean error = json.getBoolean("error");
                    boolean isOk = json.getBoolean("isok");
                    if(!error)
                        callback.onSucces(isOk);
                    else {
                        String message = json.getString("message");
                        callback.onError(message);
                    }
                }
                catch (Exception e){

                }

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
                map.put("id_boite",id_boite);
                map.put("id_case",num_case);
                map.put("quantitee",quantitee);
                return map;
            }
        };
        queue.add(request);
    }

    public void recupAllPatient(recupAllPatientCallback callback){

        String url = url_debut + "recupAllPatient.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject json = new JSONObject(response);
                    if(json!= null) callback.onSucces(json);
                    else callback.onError(json);
                }
                catch (Exception e){

                }

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
                map.put("id_gerant",id_gestionnaire);
                return map;
            }
        };
        queue.add(request);
    }

    public interface recupPatientCallback{
        void onSucces(JSONObject message);
        void onError(boolean error);
    }

    public interface recupGerantCallback{
        void onSucces(String nom, String prenom);
        void onError(boolean error);
    }

    public interface NumSecuCallback{
        void onSucces(String nom, String prenom);
        void onError(String message);
        }

    public interface InscripGerantCallback{
        void onSucces(String id_gestion);
        void onError(boolean errors[],String rep);
    }

    public interface InscripPatientCallback{
        void onSucces(String nom,String prenom);
        void onError(boolean errors[]);
    }

    public interface ConnexionCallback{
        void onSucces(String id_gestion);
        void onError(boolean error);
    }

    public interface recupMedocInfoCallback{
        void onSucces(JSONObject message);
        void onError(JSONObject message);
    }

    public interface traitementTestCallback{
        void onSucces(JSONObject JSONTraitement);
        void onError();
    }

    public interface recupCompteCallback{
        void onSucces(String nom, String prenom, String lienPatient, String date, String email);
        void onError(boolean error);
    }

    public interface modifCompteCallback{
        void onSucces();
        void onError(boolean errors[], String rep_email, String rep_mdp);
    }

    public interface recupProfilCallback{
        void onSucces(String nom, String prenom, String numero_securite_social, String id_boite, String date, String maladie, int apte);
        void onError(boolean error);
    }

    public interface modifProfilCallback{
        void onSucces();
        void onError(boolean errors[]);
    }

    public interface modifDernierePriseCallback{
        void onSucces();
        void onError(boolean errors);
    }

    public interface recupMedocCallback{
        void onSucces(JSONObject message,int i);
        void onError(JSONObject message);
    }

    public interface recupAllPatientCallback{
        void onSucces(JSONObject message);
        void onError(JSONObject message);
    }

    public interface IsOkCallback{
        void onSucces(boolean IsOK);
        void onError(String message);
    }

}
package com.example.fcd.coach.modele;

import android.util.Log;

import com.example.fcd.coach.controleur.Controle;
import com.example.fcd.coach.outils.AccesHTTP;
import com.example.fcd.coach.outils.AsyncResponse;
import com.example.fcd.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;


/**
 * Created by fcd on 01/02/2018.
 */

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR = "http://192.168.56.1/CoachENS/serveurcoach.php";
    private Controle controle;
    public AccesDistant(){
        controle = Controle.getInstance(null);
    }

    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");
        if (message.length > 1) {
            if (message[0].equals("enreg")) {
                Log.d("enreg", "*********" + message[1]);
            } else if (message[0].equals("dernier")) {
                //Log.d("dernier", "*********" + message[1]);
                try {
                    JSONObject info = new JSONObject(message[1]);
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    Date datemesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                    Profil profil = new Profil(poids, taille, age, sexe, datemesure);
                    controle.setProfil(profil);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else if (message[0].equals("Erreur")) {
                Log.d("Erreur", "*********" + message[1]);
            }
        }
    }

    /***
     * Envoi d'infos vers le serveur distant
     * @param operation
     * @param lesDonneesJSON
     */

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);

    }


}

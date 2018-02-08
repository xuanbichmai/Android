package com.example.fcd.coach.controleur;

import android.content.Context;

import com.example.fcd.coach.modele.AccesDistant;
import com.example.fcd.coach.modele.AccessLocal;
import com.example.fcd.coach.modele.Profil;
import java.util.Date;
import com.example.fcd.coach.outils.Serializer;
import com.example.fcd.coach.vue.MainActivity;

import org.json.JSONArray;

/**
 * Created by Violette on 11/30/2017.
 */


public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    //private static AccessLocal accessLocal;
    private static AccesDistant accesDistant;
    private static Context context;

    private Controle() {
        super();
    }


    public  static final Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.context = context;
            Controle.instance = new Controle();
            //recupSerialize(context);
            //accessLocal = new AccessLocal(context);
            //profil = accessLocal.recupDernier();
            accesDistant = new AccesDistant();;
            accesDistant.envoi("dernier", new JSONArray());

        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context, Date dateMesure) {
        dateMesure = new Date();
        profil = new Profil(poids, taille, age, sexe, dateMesure);
            //Serializer.serialize(nomFic, profil, context);
        //accessLocal.ajout(profil);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
    }

    public void setProfil(Profil profil) {
        Controle.profil = profil;
        ((MainActivity)context).recupProfil();
    }



    /**
     * récupérer du calcul de IMG
     * @return
     */
    public float getImg(){
        if(profil != null)
            return profil.getImg();
        else
                return 0;
    }

    /**
     * récupérer du message
     * @return
     */
    public String getMessage(){
        if(profil != null)
            return profil.getMessage();
        else
            return null;
    }

    public Integer getTaille() {
        if(profil != null)
            return profil.getTaille();
        else
            return 0;
    }

    public Integer getPoids() {
        if(profil != null)
            return profil.getPoids();
        else
            return 0;
    }

    public Integer getAge() {
        if(profil != null)
            return profil.getAge();
        else
            return 0;
    }

    public Integer getSexe() {
        if(profil != null)
            return profil.getSexe();
        else
            return 0;
    }

    /*private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
    } */




}

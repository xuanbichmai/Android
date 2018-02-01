package com.example.fcd.coach.modele;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.fcd.coach.outils.MesOutils.convertDateToString;
import static com.example.fcd.coach.outils.MesOutils.convertStringToDate;

/**
 * Created by fcd on 30/11/2017.
 */

public class Profil implements Serializable {
    // Constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;
    private Date datemesure;


    public Profil(int poids, int taille, int age, int sexe, Date datemesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.datemesure = datemesure;

       calculIMG();
       resultIMG();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDatemesure() {
        return datemesure;
    }

    /**
     * IMG = (1,2 * Poids/Taille²) + (0,23 * age) - (10,83 * S) - 5,4
     * avec S=0 pour une femme, =1 pour un homme
     */
    private void calculIMG() {
        img = (float) ((float)(1.2*(poids/(((float)taille/100)*((float)taille/100)))) + (0.23*age) - (10.63*sexe) - 5.4);
    }


    /**
     * return message pour chaque img correspondant
     */
    private void resultIMG() {
        // mémorisation des bornes
        Integer min;
        Integer max;
        if (sexe == 0) {  // femme
            min = minFemme;
            max = maxFemme;
        } else { // homme
            min = minHomme;
            max = maxHomme;
        }
        // message en fonction de l'img
        message = "normal";
        if (img < min) { // maigre
            message = "trop maigre";
        } else {
            if (img > max) { // graisse
                message = "trop de graisse";
            }
        }
    }

    public JSONArray convertToJSONArray(){
        ArrayList uneListe = new ArrayList();
        uneListe.add(convertDateToString(datemesure));
        uneListe.add(poids);
        uneListe.add(taille);
        uneListe.add(age);
        uneListe.add(sexe);

        return new JSONArray(uneListe);

    }
}



package com.example.fcd.coach.modele;

import java.io.Serializable;
import java.util.Date;

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
    String message;
    Date dateMesure;

    public Profil(int poids, int taille, int age, int sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.dateMesure = dateMesure;

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

    public Date getDateMesure() {
        return dateMesure;
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
}



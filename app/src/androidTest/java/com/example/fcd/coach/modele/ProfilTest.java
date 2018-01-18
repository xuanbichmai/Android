package com.example.fcd.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fcd on 30/11/2017.
 */
public class ProfilTest {
    // création d'un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(67, 165, 35, 0);
    // résultat de l'img correspondant
    private float img = (float)32.2 ; //32.4 test will generate erreur
    // message correspondant
    private String message = "trop de graisse" ;

    @Test
    public void getImg() throws Exception {
        assertEquals(message, profil.getMessage());
    }

    @Test
    public void getMessage() throws Exception {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

}
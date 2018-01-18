package com.example.fcd.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fcd.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

import static com.example.fcd.coach.outils.MesOutils.convertStringToDate;

/**
 * Created by fcd on 11/01/2018.
 */

public class AccessLocal {
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;
    private String req;

    public AccessLocal(Context context) {
        this.accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    public void ajout(Profil profil) {
        bd = accesBD.getWritableDatabase();
        req = "insert into profil values (\"" + profil.getDateMesure() + "\"," + profil.getPoids() +
                "," + profil.getTaille() + "," + profil.getAge() + "," +
                profil.getSexe() + ")";
        bd.execSQL(req);

    }

    public Profil recupDernier() {
        Profil profil = null;
        Integer poids;
        Integer taille;
        Integer age;
        Integer sexe;

        bd = accesBD.getReadableDatabase();
        req = "select * from profil";
        Cursor curseur = bd.rawQuery(req, null); ;
        curseur.moveToLast();

        if(!curseur.isAfterLast()) {
            Date dateMesure =  convertStringToDate(curseur.getString(0));
            // Using Log.d to print in console the result like System.out.print in java
            Log.d("date=", "date après convertir: "+dateMesure);

            //Date dateMesure = new Date();
            poids = curseur.getInt(1);
            Log.d("poids=", "poids:"+poids);
            taille = curseur.getInt(2);
            Log.d("taille=", "taille:"+taille);
            age = curseur.getInt(3);
            Log.d("age=", "age:"+age);
            sexe = curseur.getInt(4);
            Log.d("sexe=", "sexe:"+sexe);
            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }
        curseur.close();
        return profil;

    }
}


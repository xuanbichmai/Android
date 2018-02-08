package com.example.fcd.coach.vue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fcd.coach.R;
import com.example.fcd.coach.controleur.Controle;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
        private EditText txtPoids;
        private EditText txtTaille;
        private EditText txtAge;
        private RadioButton rdHomme;
        private RadioButton rdFemme;
        private ImageView imgSmiley;
        private TextView lblIMG;
        private Controle controle;
        private Date dateMesure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        controle = Controle.getInstance(this);
        ecouteCalcul();
        //recupProfil();
    }


    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test only", Toast.LENGTH_SHORT).show();
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}

                int sexe = 0;
                if(rdHomme.isChecked()) {
                    sexe = 1;
                }
                if(poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids,taille,age,sexe, dateMesure);
                }
                }
        });
    }

    /**
     * Affiche le résultat des mesures(image et img)
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe, Date dateMesure) {
        controle.creerProfil(poids, taille, age, sexe, this, dateMesure);
        String message = controle.getMessage();
        float img = controle.getImg();
        imgSmiley.setImageResource(R.drawable.normal);
        lblIMG.setTextColor(Color.GREEN);
        if (message.equals("trop maigre")) {
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setTextColor(Color.RED);
        } else {
            if (message.equals("trop de graisse")) {
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
            }
        }
        lblIMG.setText(String.format("%.01f", img) + " : IMG " + message);
    }

    public void recupProfil(){
        if(controle.getPoids() != null)
            txtPoids.setText(""+controle.getPoids());
        if(controle.getTaille() != null)
            txtTaille.setText(""+controle.getTaille());
        if (controle.getAge() != null)
            txtAge.setText(""+controle.getAge());
        if (controle.getSexe() == 1)
            rdHomme.setChecked(true);
        if (controle.getSexe() == 0)
            rdFemme.setChecked(true);
        findViewById(R.id.btnCalc).performClick();
    }

}

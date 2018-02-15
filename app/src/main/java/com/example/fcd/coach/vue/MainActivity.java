package com.example.fcd.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fcd.coach.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }

    protected void creerMenu(){
        ImageButton btnMonIMG = (ImageButton)findViewById(R.id.btnMonIMG);
        ImageButton btnMonHistorique = (ImageButton)findViewById(R.id.btnMonHistorique);
        ecouteMenu(btnMonIMG, CalculActivity.class);
        ecouteMenu(btnMonHistorique, CalculActivity.class);
    }

    private void ecouteMenu(ImageButton btn, final Class classe){
        btn.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });



    }
}

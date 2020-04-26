package com.example.lac_temperature_tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnFenetreSaisir = (Button) findViewById(R.id.buttonSaisirTemp);
        Button btnFenetreSaisirJours = (Button) findViewById(R.id.buttonAfficherTempJours);
        Button btnFenetreAfficherMois = (Button) findViewById(R.id.buttonAfficherTempMois);
        Button btnMoyenneTemp = (Button)findViewById(R.id.buttonMoyenne);
        Button btnQuitter = (Button) findViewById(R.id.buttonQuitter);
        View.OnClickListener ecouteurAccueil = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.buttonSaisirTemp:
                        Intent intent = new Intent ( MainActivity.this , SaisirTemperature.class);
                        startActivity(intent);
                        break;
                    case R.id.buttonAfficherTempJours:
                        Intent intent2 = new Intent(MainActivity.this, SaisirTempJours.class);
                        startActivity(intent2);
                        break;
                    case R.id.buttonAfficherTempMois:
                        Intent intent3 = new Intent(MainActivity.this, SaisirTemperatureMois.class);
                        startActivity(intent3);
                        break;
                    case R.id.buttonMoyenne:
                        Intent intent4 = new Intent(MainActivity.this,MoyenneTemp.class);
                        startActivity(intent4);
                }
            }
        };
        View.OnClickListener ecouteurQuitter = new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                switch (v2.getId()) {
                    case R.id.buttonQuitter:
                        System.exit(0);
                        break;
                }

            }
        };

        btnFenetreSaisir.setOnClickListener(ecouteurAccueil);
        btnQuitter.setOnClickListener(ecouteurQuitter);
        btnFenetreSaisirJours.setOnClickListener(ecouteurAccueil);
        btnFenetreAfficherMois.setOnClickListener(ecouteurAccueil);
        btnMoyenneTemp.setOnClickListener(ecouteurAccueil);
    }
}

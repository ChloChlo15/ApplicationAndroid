package com.example.lac_temperature_tp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;



public class SaisirTemperatureMois extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_temp_mois);
        final Spinner spinnerMois = (Spinner)findViewById(R.id.spinnerMois);
        String[] Mois={"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Mois);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMois.setAdapter(dataAdapterR);
        Button btnEnregistrer = (Button)findViewById(R.id.buttonEnregistrer);
        Button btnRetour  = (Button)findViewById(R.id.buttonRetourAccueil);
        View.OnClickListener ecouteurTempMois = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonRetourAccueil:
                        Intent intent2 = new Intent(SaisirTemperatureMois.this , MainActivity.class);
                        startActivity(intent2);
                        System.exit(0);
                        break;

                }
            }
        };

        btnRetour.setOnClickListener(ecouteurTempMois);
        final Spinner spinnerHeure = (Spinner)findViewById(R.id.spinnerChoixHeure);
        String[] Heure={"4h","8h","12h","16h","20h","24h"};
        ArrayAdapter<String> dataAdapterR1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Heure);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerHeure.setAdapter(dataAdapterR1);


    }


}


package com.example.lac_temperature_tp5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class SaisirTemperature extends AppCompatActivity {
    int jour;
    int mois;
    String heure;
    float temperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisietemp);

        final BdAdapter releveBdd = new BdAdapter(this);

        //Pour afficher la date du jour et l'heure actuelle.
        Date now = new Date();
        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeformatter = DateFormat.getTimeInstance(DateFormat.SHORT);
        TextView date = (TextView) findViewById(R.id.textViewDate);
        date.setText(dateformatter.format(now)+" "+timeformatter.format(now));

        Button btnRetour = (Button)findViewById(R.id.buttonRetourAccueil);

        Button btnEnregistrer = (Button)findViewById(R.id.buttonSaisirEnregistrer);

        Button btnAffichageBdd = (Button)findViewById(R.id.buttonBdd);

        final Spinner spinnerJours = (Spinner) findViewById(R.id.spinnerJours);
        String[] Jours = {"1","2","3","4","5","6","7"};
        ArrayAdapter<String> dataAdapterJours = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Jours);
        spinnerJours.setAdapter(dataAdapterJours);

        final Spinner spinnerMois = (Spinner) findViewById(R.id.spinnerMois);
        String[] Mois = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> dataAdapterMois = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Mois);
        spinnerMois.setAdapter(dataAdapterMois);

        final Spinner spinnerHeure = (Spinner)findViewById(R.id.spinnerChoixHeure);
        String[] Heure={"4h","8h","12h","16h","20h","24h"};
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Heure);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerHeure.setAdapter(dataAdapterR);



        spinnerMois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mois = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SaisirTemperature.this, "Vous devez sélectionner un mois", Toast.LENGTH_SHORT).show();
            }
        });
        spinnerHeure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                heure = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SaisirTemperature.this, "Vous devez sélectionner une Heure", Toast.LENGTH_SHORT).show();
            }
        });
        spinnerJours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jour = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SaisirTemperature.this, "Vous devez sélectionner un jour", Toast.LENGTH_SHORT).show();
            }
        });
       


        View.OnClickListener ecouteurRetour = new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                switch (v2.getId()){
                    case R.id.buttonRetourAccueil:
                        Intent intent2 = new Intent(SaisirTemperature.this , MainActivity.class);
                        startActivity(intent2);
                        System.exit(0);
                        break;
                    case R.id.buttonBdd:
                        Intent intent3 = new Intent (SaisirTemperature.this, BddTempJour.class);
                        startActivity(intent3);
                        break;
                    //Quand le bouton valider est cliqué on ajoute le relevé dans la BDD.
                    case R.id.buttonSaisirEnregistrer:
                        EditText temp = (EditText) findViewById(R.id.editTextDegré);
                        temperature = Float.parseFloat(temp.getText().toString());
                        releveBdd.open();
                        Releve releve = new Releve(jour,mois,temperature,heure);
                        releveBdd.insererTempérature(releve);
                        releveBdd.close();
                        Toast.makeText(SaisirTemperature.this, "Un Releve a été ajouter à la BDD", Toast.LENGTH_SHORT).show();
                        break;
                }



                }

        };
        btnRetour.setOnClickListener(ecouteurRetour);
        btnAffichageBdd.setOnClickListener(ecouteurRetour);
        btnEnregistrer.setOnClickListener(ecouteurRetour);

    }

}

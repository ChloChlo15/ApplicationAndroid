package com.example.lac_temperature_tp5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

public class BddTempJour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdd_temp_jour);



        //Création d'une instance de la classe unReleveBDD
        BdAdapter releveBdd = new BdAdapter(this);

        //Création de plusieurs Releve
        //Releve Releve1 = new Releve(8,"24H");
        //Releve Releve2 = new Releve(2,"8h");

        //On ouvre la base de données pour écrire dedans
        //releveBdd.open();

        //On insère les articles que l'on vient de créer
        //releveBdd.insererReleve(Releve1);
        //releveBdd.insererReleve(Releve2);
        //System.out.println("insertion de 2 articles");
        //releveBdd.close();

        ListView listeTempJour = (ListView) findViewById(R.id.ListeTempJour);
        releveBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        releveBdd.open();
        Cursor c = releveBdd.getData();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " relevés dans la BD", Toast.LENGTH_LONG).show();
        // colonnes à afficher
        String[] columns = new String[]{BdAdapter.COL_ID, BdAdapter.COL_JOUR, BdAdapter.COL_MOIS, BdAdapter.COL_HEURE, BdAdapter.COL_TEMPERATURE};

        // champs dans lesquelles afficher les colonnes
        int[] to = new int[]{R.id.textViewId, R.id.textViewJour, R.id.textViewMois, R.id.textViewHeure, R.id.textViewTemp};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_bdd_temp_jour, c, columns, to, 0);
        //Assign adapter to ListView
        listeTempJour.setAdapter(dataAdapter);
        releveBdd.close();


    }


}

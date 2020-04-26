package com.example.lac_temperature_tp5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AffichageTempJour extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_temp_jour);
        Button btnRechercher = (Button) findViewById(R.id.buttonRechercherJour);
        Button btnRetour = (Button)findViewById(R.id.buttonRetourAccueil);

        View.OnClickListener ecouteurRechercher = new View.OnClickListener() {
            @Override
            public void onClick(View j) {
                switch (j.getId()) {
                    case R.id.buttonRechercherJour:
                        EditText EditTextJour = (EditText) findViewById(R.id.editTextJour);
                        int jour = Integer.parseInt(EditTextJour.getText().toString());
                        EditText EditTextMois = (EditText) findViewById(R.id.editTextJourMois);
                        int mois = Integer.parseInt(EditTextMois.getText().toString());
                        AffichageJour(jour,mois);
                        break;
                    case R.id.buttonRetourAccueil:
                        Intent intent2 = new Intent(AffichageTempJour.this , SaisirTempJours.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
            }
        };
        btnRechercher.setOnClickListener(ecouteurRechercher);
        btnRetour.setOnClickListener(ecouteurRechercher);
    }

    public void AffichageJour(int jour, int mois) {
        ListView listTemperatureJour = (ListView)findViewById(R.id.ListeJourTemp) ;
        BdAdapter releveBdd = new BdAdapter(this);
        releveBdd.open();
        Cursor c = releveBdd.TempByJours(jour,mois);
        Toast.makeText(getApplicationContext(),"il y a "+ String.valueOf(c.getCount())+ "releves dans la bdd", Toast.LENGTH_LONG).show();
        //On récupère les colonnes à afficher
        String[] columns = new String[]{BdAdapter.COL_ID,BdAdapter.COL_JOUR, BdAdapter.COL_MOIS, BdAdapter.COL_HEURE,BdAdapter.COL_TEMPERATURE};
        // On affiche les colonnes
        int[] to = new int[]{R.id.textViewId, R.id.textViewJour, R.id.textViewMois, R.id.textViewHeure, R.id.textViewTemp};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_bdd_temp_jour, c, columns, to, 0);
        //On met les valeurs dans la ListView
        listTemperatureJour.setAdapter((dataAdapter));
        releveBdd.close();
    }
}

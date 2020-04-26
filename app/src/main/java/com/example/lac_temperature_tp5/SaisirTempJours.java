package com.example.lac_temperature_tp5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import static java.lang.Double.parseDouble;

public class SaisirTempJours extends AppCompatActivity {
    CalendarView calender;
    TextView date_affiche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_temperature_jours);
        Button btnRetour  = (Button)findViewById(R.id.buttonRetourAccueil);
        final RadioButton CelToFahr = (RadioButton)findViewById(R.id.radioButtonCelFahr);
        final RadioButton FahrToCel = (RadioButton)findViewById(R.id.radioButtonFahrCel);
        Button btnConversion = (Button)findViewById(R.id.buttonConvertir);
        Button btnAffichageBdd = (Button)findViewById(R.id.buttonBdd);
        final EditText SaisirTemp = (EditText)findViewById(R.id.editTextTemp);
        calender = (CalendarView)findViewById(R.id.calendrier);
        date_affiche = (TextView)findViewById(R.id.textViewMessageDate);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int
                    dayOfMonth)
            {

                 String Date= dayOfMonth + "-"+ (month + 1) + "-" + year;

                date_affiche.setText(Date);
            }
        });
        View.OnClickListener ecouteurTempJours = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonRetourAccueil:
                        Intent intent2 = new Intent(SaisirTempJours.this , MainActivity.class);
                        startActivity(intent2);
                        System.exit(0);
                        break;
                    case R.id.buttonBdd:
                        Intent intent3 = new Intent (SaisirTempJours.this, AffichageTempJour.class);
                        startActivity(intent3);
                        break;

                }
            }
        };
        btnRetour.setOnClickListener(ecouteurTempJours);
        btnAffichageBdd.setOnClickListener(ecouteurTempJours);
        View.OnClickListener ecouteurConversion = new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                switch (v3.getId()) {
                    case R.id.buttonConvertir:
                        double dblFahrenheit = 0;
                        double dblCelsius = (5.0 / 9) * (dblFahrenheit - 32);
                        double dblConverTemp = 0;
                        double dblFahConversion;

                        //Format
                        DecimalFormat dfTenth = new DecimalFormat("#.#");
                        if (FahrToCel.isChecked()) {
                            String strFah = SaisirTemp.getText().toString();
                            if (!strFah.isEmpty()) {

                                dblFahrenheit = parseDouble(strFah);
                                if (dblFahrenheit <= 212) {
                                    dblConverTemp = (5.0 / 9) * (dblFahrenheit - 32);
                                    SaisirTemp.setText(dfTenth.format(dblConverTemp));

                                }
                            }
                        }

                        double dblCelsius1 = 0;
                        double dblConverTemp1 = 0;

                        if (CelToFahr.isChecked()){

                            String strcel = SaisirTemp.getText().toString();
                            if (!strcel.isEmpty()) {
                                dblCelsius1 = parseDouble(strcel);
                                if (dblCelsius <= 90){
                                    dblConverTemp1 = dblCelsius1 * 9/5 + 32;
                                    SaisirTemp.setText(dfTenth.format(dblConverTemp1));
                                }
                            }
                        }
                }
            }
        };

        btnConversion.setOnClickListener(ecouteurConversion);

        //Pour afficher la date du jour et l'heure actuelle.
        Date now = new Date();
        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeformatter = DateFormat.getTimeInstance(DateFormat.SHORT);
        TextView date = (TextView) findViewById((R.id.textViewDate));
        date.setText(dateformatter.format(now) + " " + timeformatter.format(now));






    }

}

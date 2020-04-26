package com.example.lac_temperature_tp5;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoyenneTemp extends AppCompatActivity {
    boolean fahrenheit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyenne_temp);
        RadioButton btnFahrenheit = (RadioButton) findViewById(R.id.radioButtonFahr);
        RadioButton btnCelsius = (RadioButton) findViewById(R.id.radioButtonCel);


        Button btnRechercher = (Button) findViewById(R.id.buttonRechercher);




        View.OnClickListener ecouteurSaisieDesTemp = new View.OnClickListener() {
            @Override
            public void onClick(View y) {
                switch (y.getId()) {
                    case R.id.buttonRechercher:
                        EditText EditTextMois = (EditText) findViewById(R.id.editTextMois);
                        int mois = Integer.parseInt(EditTextMois.getText().toString());
                        calculMoyenneMois(mois);
                        break;
                    case R.id.radioButtonCel:
                        fahrenheit = false;
                        break;
                    case R.id.radioButtonFahr:
                        fahrenheit = true;
                        break;

                }
            }



        };
        btnRechercher.setOnClickListener(ecouteurSaisieDesTemp);
        btnFahrenheit.setOnClickListener(ecouteurSaisieDesTemp);
        btnCelsius.setOnClickListener(ecouteurSaisieDesTemp);


    }
    public void calculMoyenneMois ( int mois ){
        float temperature = 0;


        BdAdapter releveBdd = new BdAdapter(this);
        releveBdd.open();
        Cursor c = releveBdd.TempByMois(mois);
        c.moveToFirst();
        if (c != null && c.moveToFirst()) {
            do {
                temperature = temperature + c.getFloat(0);
            } while (c.moveToNext());
        }
        temperature = temperature / c.getCount();
        if(fahrenheit == true){
            temperature = (temperature * (180/100)) + 32;
        }
        TextView temp = (TextView) findViewById(R.id.textViewTemp);
        temp.setText(String.valueOf(temperature));
        return;
    }

}
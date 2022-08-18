package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompat {

    EditText username, password;
    Button izbiriHriba;
    Button izbiraMape;
    DBHelper DB;
    Button vremeneskiPodatki;
    Button osvojeniVrh;
    Button spletnekamere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView btn = findViewById(R.id.btnLogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        username = (EditText) findViewById(R.id.inputUsername1);
        password = (EditText) findViewById(R.id.inputPassword1);
        izbiriHriba = (Button) findViewById(R.id.btnRazlizcnePoti);
        izbiraMape = (Button) findViewById(R.id.btnZemljevid);
        vremeneskiPodatki=(Button) findViewById(R.id.btnVremenskiPodatki);
        osvojeniVrh=(Button) findViewById(R.id.btnOsvojeniVrhovi);
        spletnekamere=(Button) findViewById(R.id.btnSpletneKamere);

        DB = new DBHelper(this);

        izbiriHriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Hribi_Izbira.class);
                startActivity(i);
            }
        });

        izbiraMape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });

        vremeneskiPodatki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),WeatherActivity.class);
                startActivity(i);
            }
        });

        osvojeniVrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Osvojeni_vrh.class);
                startActivity(i);
            }
        });

        spletnekamere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),SpletneKamere.class);
                startActivity(i);
            }
        });




    }
}
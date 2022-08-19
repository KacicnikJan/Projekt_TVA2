package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.first:
            Intent i2 = new Intent(getApplicationContext(),Hribi_Izbira.class);
            startActivity(i2);
            return true;
        case R.id.second:
           finish();
            return true;
        case R.id.third:
            Intent i3 = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i3);
            return true;
        case R.id.stiri:
            Intent i7 = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i7);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }

}
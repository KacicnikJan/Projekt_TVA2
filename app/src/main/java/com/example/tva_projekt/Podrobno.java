package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;

public class Podrobno extends AppCompatActivity {
    ActivityPodrobnoBinding binding;
    DBHelper DB;
    Integer idPot;
    TextView lok, opisPoti, opisIzhodisce;
    Button prikaz_poti;
    Integer idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPodrobnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB=new DBHelper(this);
        Intent intent = this.getIntent();

        if(intent!= null) {
            idPot = intent.getIntExtra("idPoti",0);

        }
        Pot pot = DB.izpisPodrobnoPot(idPot);
        lok = findViewById(R.id.latitude);
        opisIzhodisce=findViewById(R.id.opisIzhodisce);
        opisPoti=findViewById(R.id.opisPot);
        lok.setText(pot.izhodisceLat + " °N, " + pot.izhodisceLong + " °E");
        opisIzhodisce.setText("Navodila do izhodišča: " + pot.izhodisceDostop);
        opisPoti.setText("Opis poti: " + pot.opisPoti);
        prikaz_poti=findViewById(R.id.btnPrikazPoti);

        prikaz_poti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Podrobno.this,mapa_prikaz.class);
                i.putExtra("idPoti", idPot);
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

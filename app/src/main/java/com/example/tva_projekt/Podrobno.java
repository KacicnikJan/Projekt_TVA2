package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;

public class Podrobno extends AppCompatActivity {
    ActivityPodrobnoBinding binding;
    DBHelper DB;
    Integer idPot;
    TextView lok, opisPoti, opisIzhodisce;

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





    }
}

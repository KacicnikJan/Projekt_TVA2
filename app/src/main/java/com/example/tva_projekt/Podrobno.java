package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;

public class Podrobno extends AppCompatActivity {
    ActivityPodrobnoBinding binding;
    DBHelper DB;
    Integer idPot;

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
        Toast.makeText(Podrobno.this, pot.imePoti, Toast.LENGTH_SHORT).show();


    }
}

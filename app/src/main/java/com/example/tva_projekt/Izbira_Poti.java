package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPosamezniVrhBinding;

public class Izbira_Poti extends AppCompatActivity {

    ActivityIzbiraPotiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIzbiraPotiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if(intent!= null){
            String imeVrha = intent.getStringExtra("imeVrha");
            String ndmv = intent.getStringExtra("ndmv");

            binding.imeVrha.setText(imeVrha);
            binding.ndmv.setText(ndmv);
        }
    }
}
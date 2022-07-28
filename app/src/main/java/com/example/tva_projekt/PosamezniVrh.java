package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.example.tva_projekt.databinding.ActivityPosamezniVrhBinding;

public class PosamezniVrh extends AppCompatActivity {

    ActivityPosamezniVrhBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPosamezniVrhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if(intent!= null){
            String ime = intent.getStringExtra("ime");
            int StVrhov = intent.getIntExtra("stVrhov", 0);

            binding.posamezno.setText(ime);
            binding.stVrhov.setText(Integer.toString(StVrhov));
        }
    }
}
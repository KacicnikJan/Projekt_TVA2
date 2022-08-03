package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;

public class Podrobno extends AppCompatActivity {
    ActivityPodrobnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPodrobnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if(intent!= null) {
            String imePoti = intent.getStringExtra("imePoti");
            binding.potIme.setText(imePoti);
        }


    }
}

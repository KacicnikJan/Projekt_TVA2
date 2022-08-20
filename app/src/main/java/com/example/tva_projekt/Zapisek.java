package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tva_projekt.databinding.ActivityOsvojeniVrhBinding;
import com.example.tva_projekt.databinding.ActivityZapisekBinding;

public class Zapisek extends AppCompatActivity {

    ActivityZapisekBinding binding;
    Integer idUser, idObisk;
    TextView textViewDate;
    ImageView oslika;
    String datum;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapisek);
        db=new DBHelper(this);
        binding= ActivityZapisekBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();

        if(intent!= null) {
            idUser = intent.getIntExtra("idUser",0);
            idObisk=intent.getIntExtra("idObisk",0);


        }
        Obisk tale = db.pridobiObisk(idObisk);

        textViewDate=findViewById(R.id.textViewDatum);
        textViewDate.setText(tale.datum);
        oslika=findViewById(R.id.slika);


        oslika.setImageBitmap(tale.slika);


    }
}
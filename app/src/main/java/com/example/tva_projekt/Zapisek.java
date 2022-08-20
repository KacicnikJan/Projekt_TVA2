package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tva_projekt.databinding.ActivityOsvojeniVrhBinding;
import com.example.tva_projekt.databinding.ActivityZapisekBinding;

public class Zapisek extends AppCompatActivity {

    ActivityZapisekBinding binding;
    Integer idUser, idObisk;
    TextView textViewDate;
    TextView opis;
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

        opis=findViewById(R.id.txtOpisek);
        opis.setText(tale.komentar);

        textViewDate=findViewById(R.id.textViewDatum);
        textViewDate.setText(tale.datum);
        oslika=findViewById(R.id.slika);


        oslika.setImageBitmap(tale.slika);


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
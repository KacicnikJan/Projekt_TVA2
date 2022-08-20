package com.example.tva_projekt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tva_projekt.databinding.ActivityHribiIzbiraBinding;
import com.facebook.CallbackManager;


import java.util.ArrayList;

public class Osvojeni_vrh extends AppCompatActivity {

    ActivityHribiIzbiraBinding binding;
    DBHelper DB;
    ArrayList<Vrh> noviArrayList=new ArrayList<>();
    Integer idUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osvojeni_vrh);

        binding=ActivityHribiIzbiraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();

        if(intent!= null) {
            idUser = intent.getIntExtra("idUser",0);

        }
        DB=new DBHelper(this);
        noviArrayList=DB.izpisiVrhove(1);

        ListAdapterVrh listAdapterHribi = new ListAdapterVrh(Osvojeni_vrh.this, noviArrayList);

        binding.listview.setAdapter(listAdapterHribi);
        binding.listview.setClickable(true);
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
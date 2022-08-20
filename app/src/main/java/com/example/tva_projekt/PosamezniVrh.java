package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import com.example.tva_projekt.databinding.ActivityPosamezniVrhBinding;

import java.util.ArrayList;
import java.util.Random;

public class PosamezniVrh extends AppCompatActivity {

    ActivityPosamezniVrhBinding binding;
    DBHelper DB;
    int idHribovja;
    ArrayList<Vrh> vrhArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posamezni_vrh);
        binding = ActivityPosamezniVrhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB=new DBHelper(this);


        Intent intent = this.getIntent();

        if(intent!= null){
            String ime = intent.getStringExtra("ime");
            int StVrhov = intent.getIntExtra("stVrhov", 0);
            idHribovja=intent.getIntExtra("id",0);

            binding.posamezno.setText(ime);
            binding.stVrhov.setText(Integer.toString(StVrhov));
        }


        vrhArrayList = DB.izpisiVrhove(idHribovja);


        ListAdapterVrh listAdapterVrh = new ListAdapterVrh(PosamezniVrh.this, vrhArrayList);

        binding.listviewvrh.setAdapter(listAdapterVrh);
        binding.listviewvrh.setClickable(true);
        binding.listviewvrh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PosamezniVrh.this, Izbira_Poti.class);

                Vrh izbran = listAdapterVrh.getItem(position);
                i.putExtra("imeVrha", izbran.imeVrha);
                i.putExtra("idVrha", izbran.idVrha);
                i.putExtra("ndmv",izbran.ndmv);

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
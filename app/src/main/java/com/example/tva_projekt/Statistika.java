package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.tva_projekt.databinding.ActivityStatistikaBinding;

import java.util.ArrayList;

public class Statistika extends AppCompatActivity {

    DBHelper DB;
    ArrayList<Stats> noviArrayList;
    Integer idUser;
    ActivityStatistikaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistika);
        binding=ActivityStatistikaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();



        if(intent!= null) {
            idUser = intent.getIntExtra("idUser",0);

        }

        DB=new DBHelper(this);
        noviArrayList=DB.pridobiStatistiko(idUser);

        ListAdapterStats listAdapterStats = new ListAdapterStats(Statistika.this, noviArrayList);

        binding.listviewstats.setAdapter(listAdapterStats);
        binding.listviewstats.setClickable(true);
        binding.listviewstats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Statistika.this, Izbira_Poti.class);

                Stats izbran = listAdapterStats.getItem(position);


                i.putExtra("idVrha", izbran.idVrha);

                i.putExtra("idUser", idUser);

                startActivity(i);
            }
        });

    }
}
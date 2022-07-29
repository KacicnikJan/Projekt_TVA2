package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tva_projekt.databinding.ActivityHribiIzbiraBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tva_projekt.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class Hribi_Izbira extends AppCompatActivity {

    ActivityHribiIzbiraBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hribi_izbira);
        binding=ActivityHribiIzbiraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] ime={"Goriško, Notranjsko in Snežniško hribovje", "Julijske Alpe", "Kamniško Savinjske Alpe", "Karavanke", "Pohorje, Dravinjske gorice in Haloze", "Polhograjsko hribovje in Ljubljana"};
        int[] stVrhov={5, 2, 3, 6, 4, 5};

        ArrayList<Hribovje> hribovjeArrayList = new ArrayList<>();

        for(int i=0;i<ime.length;i++){
            Hribovje hribovje = new Hribovje(ime[i], stVrhov[i]);
            hribovjeArrayList.add(hribovje);
        }

        ListAdapterHribi listAdapterHribi = new ListAdapterHribi(Hribi_Izbira.this, hribovjeArrayList);

        binding.listview.setAdapter(listAdapterHribi);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hribi_Izbira.this, PosamezniVrh.class);
                i.putExtra("ime", ime[position]);
                i.putExtra("stVrhov",stVrhov[position]);

                startActivity(i);
            }
        });
    }
}
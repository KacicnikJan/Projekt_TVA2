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
    DBHelper DB;
    ArrayList<Hribovje> noviArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hribi_izbira);
        binding=ActivityHribiIzbiraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB=new DBHelper(this);
        noviArrayList=DB.izpisiHribovja();

        ListAdapterHribi listAdapterHribi = new ListAdapterHribi(Hribi_Izbira.this, noviArrayList);

        binding.listview.setAdapter(listAdapterHribi);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hribi_Izbira.this, PosamezniVrh.class);

                Hribovje izbrano = listAdapterHribi.getItem(position);

                int stVrhov = DB.pridobiStVrhov(izbrano.idHribovja);
                i.putExtra("stVrhov", stVrhov);
                i.putExtra("ime", izbrano.ime);
                i.putExtra("id", izbrano.idHribovja);

                startActivity(i);
            }
        });
    }
}
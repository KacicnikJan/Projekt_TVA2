package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import com.example.tva_projekt.databinding.ActivityPosamezniVrhBinding;

import java.util.ArrayList;
import java.util.Random;

public class PosamezniVrh extends AppCompatActivity {

    ActivityPosamezniVrhBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPosamezniVrhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] imeVrha = {"Triglav", "Mali Golak", "Snežnik", "Montaž", "Grintovec"};
        String[] ndmv = {"2864m", "1920m", "989m","2687m","2560m"};


        Intent intent = this.getIntent();

        if(intent!= null){
            String ime = intent.getStringExtra("ime");
            int StVrhov = intent.getIntExtra("stVrhov", 0);

            binding.posamezno.setText(ime);
            binding.stVrhov.setText(Integer.toString(StVrhov));
        }

        ArrayList<Vrh> vrhArrayList = new ArrayList<>();

        for(int i=0;i<imeVrha.length;i++){
            Random rnd = new Random();
            int x = rnd.nextInt(100);
            Vrh vrh = new Vrh(Integer.toString(x),imeVrha[i], "lorem ipsum neki neki", "koordinate pridejo sem", ndmv[i]);
            vrhArrayList.add(vrh);
        }

        ListAdapterVrh listAdapterVrh = new ListAdapterVrh(PosamezniVrh.this, vrhArrayList);

        binding.listviewvrh.setAdapter(listAdapterVrh);
        binding.listviewvrh.setClickable(true);
        binding.listviewvrh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PosamezniVrh.this, Izbira_Poti.class);
                i.putExtra("imeVrha", imeVrha[position]);
                i.putExtra("ndmv",ndmv[position]);

                startActivity(i);
            }
        });
    }
}
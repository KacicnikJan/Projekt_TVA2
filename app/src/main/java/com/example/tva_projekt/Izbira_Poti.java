package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPosamezniVrhBinding;

import java.util.ArrayList;
import java.util.Random;

public class Izbira_Poti extends AppCompatActivity {

    ActivityIzbiraPotiBinding binding;
    DBHelper DB;
    Integer idVrha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIzbiraPotiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB=new DBHelper(this);
        Intent intent = this.getIntent();




        if(intent!= null){
            String imeVrha = intent.getStringExtra("imeVrha");
            String ndmv = intent.getStringExtra("ndmv");
            idVrha = intent.getIntExtra("idVrha",0);
            binding.vrhIme.setText(imeVrha);
            binding.nadmorska.setText(ndmv);
        }
        ArrayList<Pot> potArrayList = DB.izpisiPoti(idVrha);
        /*
        for(int i=0;i<imePoti.length;i++){
            Random rnd = new Random();
            int x = rnd.nextInt(100);
            Pot pot = new Pot(imePoti[i], zahtevnost[i], casHoje[i], "random izhodisce", "gres tam pa tam pa prides tja in se obrneš ker si smotan in pol greš desno in si gor", "random link do galerije" );
            potArrayList.add(pot);
        }

         */

        ListAdapterPot listAdapterPot = new ListAdapterPot(Izbira_Poti.this, potArrayList);

        binding.listviewpot.setAdapter(listAdapterPot);
        binding.listviewpot.setClickable(true);
        binding.listviewpot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Izbira_Poti.this, Podrobno.class);
                Pot izbrana = listAdapterPot.getItem(position);
                i.putExtra("idPoti", izbrana.idPot);

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
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            return true;
        case R.id.third:
            Intent i3 = new Intent(getApplicationContext(),WeatherActivity.class);
            startActivity(i3);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }
}
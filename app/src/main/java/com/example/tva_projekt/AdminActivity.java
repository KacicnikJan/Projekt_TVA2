package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    List<Hribovje> dropList;
    List<Vrh> dropListVrh;
    EditText iGorovje, iVrh, iPot, iVisina, iLokacijaVrhLong, iLokacijaVrhLat, iOpisGore, iImePoti, iOpisIzhodisca, iOpisPoti, iLokacijaIzhodisceLong, iLokacijaIzhodisceLat, iCasHoje, iLinkGalerija;
    Spinner dropGorovje, dropVrh, dropZahtevnost;
    DBHelper DB;
    Integer checked=0;
    public Integer idHribovja, idVrha;
    AutoCompleteTextView autoCompleteTextView;

    private BaseAdapter gorovjeAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return dropList.size();
        }

        @Override
        public Object getItem(int position) {
            return dropList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HribovjeHolder holder;
            View hribovjeView = convertView;

            if(hribovjeView == null){
                hribovjeView = getLayoutInflater().inflate(R.layout.row_hribovje_spinner, parent, false);
                holder = new HribovjeHolder();
                holder.hribovjeIzbor = hribovjeView.findViewById(R.id.izbor_hribovje_ime);
                hribovjeView.setTag(holder);
            }
            else{
                holder = (HribovjeHolder) hribovjeView.getTag();
            }
            Hribovje hribovje = dropList.get(position);
            holder.hribovjeIzbor.setText(hribovje.getIme());


            return hribovjeView;
        }

        class HribovjeHolder{
            private TextView hribovjeIzbor;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView btnDodaj=findViewById(R.id.btnDodaj);
        TextView btn=findViewById(R.id.btnLogoutAdmin);
        iGorovje = findViewById(R.id.inputGorovje);
        iVrh=findViewById(R.id.inputVrh);
        iPot=findViewById(R.id.inputPot);
        iVisina=findViewById(R.id.inputVisina);
        iLokacijaVrhLong=findViewById(R.id.inputLokacijaVrhLong);
        iLokacijaVrhLat=findViewById(R.id.inputLokacijaVrhLat);
        iOpisGore=findViewById(R.id.inputOpisVrh);
        radioGroup = findViewById(R.id.radioGroup);
        dropGorovje=findViewById(R.id.dropGorovje);
        dropVrh=findViewById(R.id.dropVrh);
        idHribovja=0;
        autoCompleteTextView=findViewById(R.id.autoVrh);
        iImePoti=findViewById(R.id.imePoti);
        iOpisIzhodisca =findViewById(R.id.opisIzhodisce);
        iOpisPoti = findViewById(R.id.opisPot);
        iLokacijaIzhodisceLong =findViewById(R.id.inputLokacijaIzhodisceLong);
        iLokacijaIzhodisceLat = findViewById(R.id.inputLokacijaIzhodisceLat);
        iCasHoje = findViewById(R.id.casHoje);
        iLinkGalerija = findViewById(R.id.linkGalerija);
        dropZahtevnost=findViewById(R.id.spinnerZahtevnost);

        DB=new DBHelper(this);

        prikaziVnesenaHribovja();
        prikaziVrheHribovje(idHribovja);

        dropGorovje.setAdapter(gorovjeAdapter);
        dropGorovje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idHribovja = dropList.get(position).getIdHribovja();
                prikaziVrheHribovje(idHribovja);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zahtevnosti_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        dropZahtevnost.setAdapter(adapter);
        dropZahtevnost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String zahtevnost = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


                dropVrh.setAdapter(vrhAdapter);
        dropVrh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idVrha = dropListVrh.get(position).getIdVrha();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedButtonId) {
                switch(checkedButtonId){
                    case R.id.radio_gorovje:
                        iGorovje.setVisibility(View.VISIBLE);
                        iPot.setVisibility(View.GONE);
                        iVrh.setVisibility(View.GONE);
                        iOpisGore.setVisibility(View.GONE);
                        iLokacijaVrhLong.setVisibility(View.GONE);
                        iLokacijaVrhLat.setVisibility(View.GONE);
                        iVisina.setVisibility(View.GONE);
                        dropGorovje.setVisibility(View.GONE);
                        dropVrh.setVisibility(View.GONE);
                        autoCompleteTextView.setVisibility(View.GONE);
                        checked=0;
                        iImePoti.setVisibility(View.GONE);
                        iOpisIzhodisca.setVisibility(View.GONE);
                        iOpisPoti.setVisibility(View.GONE);
                        iLokacijaIzhodisceLong.setVisibility(View.GONE);
                        iLokacijaIzhodisceLat.setVisibility(View.GONE);
                        iCasHoje.setVisibility(View.GONE);
                        iLinkGalerija.setVisibility(View.GONE);
                        dropZahtevnost.setVisibility(View.GONE);


                        break;
                    case R.id.radio_vrh:
                        iGorovje.setVisibility(View.GONE);
                        iPot.setVisibility(View.GONE);
                        iVrh.setVisibility(View.VISIBLE);
                        iOpisGore.setVisibility(View.VISIBLE);
                        iLokacijaVrhLong.setVisibility(View.VISIBLE);
                        iLokacijaVrhLat.setVisibility(View.VISIBLE);
                        iVisina.setVisibility(View.VISIBLE);
                        dropGorovje.setVisibility(View.VISIBLE);
                        dropVrh.setVisibility(View.GONE);
                        autoCompleteTextView.setVisibility(View.GONE);
                        checked=1;
                        iImePoti.setVisibility(View.GONE);
                        iOpisIzhodisca.setVisibility(View.GONE);
                        iOpisPoti.setVisibility(View.GONE);
                        iLokacijaIzhodisceLong.setVisibility(View.GONE);
                        iLokacijaIzhodisceLat.setVisibility(View.GONE);
                        iCasHoje.setVisibility(View.GONE);
                        iLinkGalerija.setVisibility(View.GONE);
                        dropZahtevnost.setVisibility(View.GONE);

                        break;
                    case R.id.radio_pot:
                        iGorovje.setVisibility(View.GONE);
                        iPot.setVisibility(View.GONE);
                        iVrh.setVisibility(View.GONE);
                        iOpisGore.setVisibility(View.GONE);
                        iLokacijaVrhLong.setVisibility(View.GONE);
                        iLokacijaVrhLat.setVisibility(View.GONE);
                        iVisina.setVisibility(View.GONE);
                        dropGorovje.setVisibility(View.GONE);
                        dropVrh.setVisibility(View.GONE);
                        autoCompleteTextView.setVisibility(View.VISIBLE);
                        iImePoti.setVisibility(View.VISIBLE);
                        iOpisIzhodisca.setVisibility(View.VISIBLE);
                        iOpisPoti.setVisibility(View.VISIBLE);
                        iLokacijaIzhodisceLong.setVisibility(View.VISIBLE);
                        iLokacijaIzhodisceLat.setVisibility(View.VISIBLE);
                        iCasHoje.setVisibility(View.VISIBLE);
                        iLinkGalerija.setVisibility(View.VISIBLE);
                        dropZahtevnost.setVisibility(View.VISIBLE);

                        checked=2;

                        break;

                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });

        loadData();
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (checked){
                    case 0: //dodajanje gorovja
                        String imeGorovja=iGorovje.getText().toString();
                        if(imeGorovja.equals("")){
                            Toast.makeText(AdminActivity.this, "Please enter all fields",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Hribovje hribovje = new Hribovje(0,imeGorovja);
                            Boolean insert=DB.dodajHribovje(hribovje.idHribovja,hribovje.ime);
                            if (insert==true) {
                                Toast.makeText(AdminActivity.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                //startActivity(intent);
                            }else {
                                Toast.makeText(AdminActivity.this,"Input failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    case 1: // dodajanje vrha
                        String imeVrha = iVrh.getText().toString();
                        String lat = iLokacijaVrhLat.getText().toString();
                        String lon = iLokacijaVrhLong.getText().toString();
                        Integer visina = Integer.parseInt(iVisina.getText().toString());
                        String opis = iOpisGore.getText().toString();
                        if(imeVrha.equals("") || lat.equals("") || lon.equals("") || visina.equals(null) || opis.equals("")){
                            Toast.makeText(AdminActivity.this, "Please enter all fields",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Vrh vrh = new Vrh(imeVrha,opis,lon,lat,0,idHribovja,visina);
                            Boolean insert=DB.dodajVrh(vrh.imeVrha, vrh.opis, vrh.lokacijaVrhLong,vrh.lokacijaVrhLat,vrh.idVrha, vrh.idHribovja, vrh.ndmv);
                            if (insert==true) {
                                Toast.makeText(AdminActivity.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                //startActivity(intent);
                            }else {
                                Toast.makeText(AdminActivity.this,"Input failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    case 2: //dodajanje poti

                        String imeGore = autoCompleteTextView.getText().toString();
                        lat = iLokacijaIzhodisceLat.getText().toString();
                        lon = iLokacijaIzhodisceLong.getText().toString();
                        String imePoti = iImePoti.getText().toString();
                        String casHoje = iCasHoje.getText().toString();
                        String opisPoti = iOpisPoti.getText().toString();
                        String zahtevnost = dropZahtevnost.getSelectedItem().toString();
                        String link = iLinkGalerija.getText().toString();
                        String izhodisceDostop = iOpisIzhodisca.getText().toString();
                        Integer idVrha=DB.pridobiIdVrha(imeGore);

                        if(izhodisceDostop.equals("") || link.equals("") || zahtevnost.equals("") || opisPoti.equals("") || imeGore.equals("") || imePoti.equals("") || lat.equals("") || lon.equals("")){
                            Toast.makeText(AdminActivity.this, "Please enter all fields",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Pot pot= new Pot(0, idVrha, imePoti, zahtevnost, casHoje, lat, lon, opisPoti, link, izhodisceDostop);
                            Boolean insert=DB.dodajPot(pot.idPot, pot.idVrha, pot.imePoti, pot.zahtevnost, pot.casHoje, pot.izhodisceLat, pot.izhodisceLong, pot.opisPoti, pot.link, pot.izhodisceDostop);
                            if (insert==true) {
                                Toast.makeText(AdminActivity.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                //startActivity(intent);
                            }else {
                                Toast.makeText(AdminActivity.this,"Input failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                }

                startActivity(new Intent(AdminActivity.this, AdminActivity.class));
            }
        });
    }
    private void loadData(){
        List<Vrh> vrhs = new ArrayList<Vrh>();
        VrhSearchAdapter vrhSearchAdapter=new VrhSearchAdapter(getApplicationContext(),vrhs);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(vrhSearchAdapter);
    }
    private void prikaziVnesenaHribovja() {
        dropList = new ArrayList<>();
        dropList = DB.izpisiHribovja();
    }
    public void prikaziVrheHribovje(Integer idHribovja){
        dropListVrh=new ArrayList<>();
        dropListVrh=DB.izpisiVrhove(idHribovja);
    }

    private BaseAdapter vrhAdapter = new BaseAdapter() {
        @Override
        public int getCount() {

            return dropListVrh.size();

        }

        @Override
        public Object getItem(int position) {
            return dropListVrh.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            VrhHolder holderVrh;
            View vrhView = convertView;

            if(vrhView == null){
                vrhView = getLayoutInflater().inflate(R.layout.row_vrh_spinner, parent, false);
                holderVrh = new VrhHolder();
                holderVrh.vrhIzbor = vrhView.findViewById(R.id.izbor_vrh_ime);
                vrhView.setTag(holderVrh);
            }
            else{
                holderVrh = (VrhHolder) vrhView.getTag();
            }
            Vrh vrh = dropListVrh.get(position);
            holderVrh.vrhIzbor.setText(vrh.getImeVrha());


            return vrhView;
        }

        class VrhHolder{
            private TextView vrhIzbor;
        }

    };
}
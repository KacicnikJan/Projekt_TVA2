package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    EditText iGorovje, iVrh, iPot, iVisina, iLokacijaVrh, iOpisGore;
    DBHelper DB;

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
        iLokacijaVrh=findViewById(R.id.inputLokacijaVrh);
        iOpisGore=findViewById(R.id.inputOpisVrh);
        radioGroup = findViewById(R.id.radioGroup);
        DB=new DBHelper(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedButtonId) {
                switch(checkedButtonId){
                    case R.id.radio_gorovje:
                        iGorovje.setVisibility(View.VISIBLE);
                        iPot.setVisibility(View.GONE);
                        iVrh.setVisibility(View.GONE);
                        iOpisGore.setVisibility(View.GONE);
                        iLokacijaVrh.setVisibility(View.GONE);
                        iVisina.setVisibility(View.GONE);
                        break;
                    case R.id.radio_vrh:
                        iGorovje.setVisibility(View.GONE);
                        iPot.setVisibility(View.GONE);
                        iVrh.setVisibility(View.VISIBLE);
                        iOpisGore.setVisibility(View.VISIBLE);
                        iLokacijaVrh.setVisibility(View.VISIBLE);
                        iVisina.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radio_pot:
                        iGorovje.setVisibility(View.GONE);
                        iPot.setVisibility(View.VISIBLE);
                        iVrh.setVisibility(View.GONE);
                        iOpisGore.setVisibility(View.GONE);
                        iLokacijaVrh.setVisibility(View.GONE);
                        iVisina.setVisibility(View.GONE);
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
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                startActivity(new Intent(AdminActivity.this, AdminActivity.class));
            }
        });
    }



}
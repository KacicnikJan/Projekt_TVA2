package com.example.tva_projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityDodajObiskBinding;

import java.util.ArrayList;
import java.util.List;

public class DodajObisk extends AppCompatActivity {
    DBHelper DB;
    Integer idUser;
    AutoCompleteTextView autoCompleteTextView;
    EditText ikomentar, idatum;
    Button btnDodajZapis;
    public ActivityDodajObiskBinding binding;
    Bitmap bitmap;
    byte [] bitimg;
    private ImageView dodajSliko;
    private Uri imageFilePath;
    private Bitmap imageTostore;
    private static final int PICK_IMAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_obisk);
        binding=ActivityDodajObiskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB=new DBHelper(this);
        Intent intent = this.getIntent();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        if(intent!= null) {
            idUser = intent.getIntExtra("idUser",0);

        }
        autoCompleteTextView = findViewById(R.id.autoPot);
        ikomentar=findViewById(R.id.komentar);
        idatum=findViewById(R.id.datum);
        //btnDodajZapis=(Button) View.findViewById(R.id.btn_dodaj_zapis);
        loadData();

        binding.btnDodajZapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String komentar = ikomentar.getText().toString();
                String datum = idatum.getText().toString();
                String imePoti= autoCompleteTextView.getText().toString();



                if(datum.equals("")|| imePoti.equals("")){

                Toast.makeText(DodajObisk.this, "Vnesi vsa potrebna polja", Toast.LENGTH_SHORT).show();}
                else if (imageTostore.equals(null) && !(komentar.equals("")) && !(datum.equals("")|| imePoti.equals("")) ){
                    Integer idPoti = DB.pridobiIdPoti(imePoti);
                    Obisk obisk= new Obisk(0,idPoti, idUser, komentar, datum,null);
                    //Obisk(Integer idObisk, Integer idPot, Integer idUser, String komentar, String datum, Bitmap slika)
                    Boolean insert=DB.vnesiObisk(obisk.idUser, obisk.idPot, null, obisk.datum, obisk.komentar);
                    if (insert==true) {
                        Toast.makeText(DodajObisk.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        //startActivity(intent);
                    }else {
                        Toast.makeText(DodajObisk.this,"Input failed", Toast.LENGTH_SHORT).show();
                    }

                }else if (komentar.equals("") && !(imageTostore.equals(null)) && !(datum.equals("")|| imePoti.equals(""))){
                    komentar="Brez komentarja";
                    Integer idPoti = DB.pridobiIdPoti(imePoti);
                    Obisk obisk= new Obisk(0,idPoti, idUser, komentar, datum,imageTostore);
                    //Obisk(Integer idObisk, Integer idPot, Integer idUser, String komentar, String datum, Bitmap slika)
                    Boolean insert=DB.vnesiObisk(obisk.idUser, obisk.idPot, obisk.slika, obisk.datum, obisk.komentar);
                    if (insert==true) {
                        Toast.makeText(DodajObisk.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        //startActivity(intent);
                    }else {
                        Toast.makeText(DodajObisk.this,"Input failed", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Integer idPoti = DB.pridobiIdPoti(imePoti);
                    Obisk obisk= new Obisk(0,idPoti, idUser, komentar, datum,imageTostore);
                    //Obisk(Integer idObisk, Integer idPot, Integer idUser, String komentar, String datum, Bitmap slika)
                    Boolean insert=DB.vnesiObisk(obisk.idUser, obisk.idPot, obisk.slika, obisk.datum, obisk.komentar);
                    if (insert==true) {
                        Toast.makeText(DodajObisk.this, "Entered successfully", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        //startActivity(intent);
                    }else {
                        Toast.makeText(DodajObisk.this,"Input failed", Toast.LENGTH_SHORT).show();
                    }


                }


                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);


            }
        });
        binding.dodajSliko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Izberi sliko"),PICK_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        try{

            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData()!=null){
                imageFilePath=data.getData();
                imageTostore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                binding.dodajSliko.setImageBitmap(imageTostore);
            }

        }catch (Exception e){
            Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
  

    private void loadData(){
        List<Pot> pots = new ArrayList<Pot>();
        PotSearchAdapter potSearchAdapter=new PotSearchAdapter(getApplicationContext(),pots);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(potSearchAdapter);
    }

}
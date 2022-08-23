package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityIzbiraPotiBinding;
import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;

public class Podrobno extends AppCompatActivity {
    ActivityPodrobnoBinding binding;
    DBHelper DB;
    Integer idPot;
    TextView lok, opisPoti, opisIzhodisce;
    Button prikaz_poti;
    Integer idUser;
    private WebView webView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPodrobnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB=new DBHelper(this);
        Intent intent = this.getIntent();

        if(intent!= null) {
            idPot = intent.getIntExtra("idPoti",0);

        }
        Pot pot = DB.izpisPodrobnoPot(idPot);
        lok = findViewById(R.id.latitude);
        opisIzhodisce=findViewById(R.id.opisIzhodisce);
        opisPoti=findViewById(R.id.opisPot);
        lok.setText(pot.izhodisceLat + " °N, " + pot.izhodisceLong + " °E");
        opisIzhodisce.setText("Navodila do izhodišča: " + pot.izhodisceDostop);
        opisPoti.setText("Opis poti: " + pot.opisPoti);
        prikaz_poti=findViewById(R.id.btnPrikazPoti);
        prikaz_poti.setVisibility(View.VISIBLE);

        textView=(TextView) findViewById(R.id.idInterne);
        webView = (WebView) findViewById(R.id.wv);
        textView.setVisibility(View.INVISIBLE);

        if(CheckNetwork.isInternetAvailable(Podrobno.this))
        {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            // webView = (WebView) findViewById(R.id.wv);
            // webView.setWebViewClient(new MyWebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    Podrobno.this.setProgress(progress * 1000);
                }
            });
            webView.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(Podrobno.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            //no connection
            Toast toast = Toast.makeText(Podrobno.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
            prikaz_poti.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }

        prikaz_poti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Podrobno.this,mapa_prikaz.class);
                i.putExtra("idPoti", idPot);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        else
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}

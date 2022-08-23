package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tva_projekt.databinding.ActivityPodrobnoBinding;
import com.google.android.material.navigation.NavigationView;

import java.net.InetAddress;

public class HomeActivity extends AppCompat {

    EditText username, password;
    Button izbiriHriba;
    Button izbiraMape;
    DBHelper DB;
    Button vremeneskiPodatki;
    Button dodajZapis;
    Button osvojeniVrh;
    Button spletnekamere;
    Integer idUser;
    Button izpis;
    Context context;
    private WebView webView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
            DB=new DBHelper(this);
            Intent intent = this.getIntent();

        izpis = findViewById(R.id.btnLogout);
        username = (EditText) findViewById(R.id.inputUsername1);
        password = (EditText) findViewById(R.id.inputPassword1);
        izbiriHriba = (Button) findViewById(R.id.btnRazlizcnePoti);
        izbiraMape = (Button) findViewById(R.id.btnZemljevid);
        dodajZapis = (Button) findViewById(R.id.btn_dodaj_zapis);
        vremeneskiPodatki=(Button) findViewById(R.id.btnVremenskiPodatki);
        osvojeniVrh=(Button) findViewById(R.id.btnOsvojeniVrhovi);
        spletnekamere=(Button) findViewById(R.id.btnSpletneKamere);

        DB = new DBHelper(this);

        if(intent!= null) {
                idUser = intent.getIntExtra("idUser",0);

            }
        
        
        textView=(TextView) findViewById(R.id.idInterne); 
        webView = (WebView) findViewById(R.id.wv);
        textView.setVisibility(View.INVISIBLE);

        if(CheckNetwork.isInternetAvailable(HomeActivity.this))
        {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            // webView = (WebView) findViewById(R.id.wv);
            // webView.setWebViewClient(new MyWebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    HomeActivity.this.setProgress(progress * 1000);
                }
            });
            webView.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(HomeActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            //no connection
            Toast toast = Toast.makeText(HomeActivity.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
            vremeneskiPodatki.setVisibility(View.INVISIBLE);
            izbiraMape.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
        

        izbiriHriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Hribi_Izbira.class);
                startActivity(i);
            }
        });

        vremeneskiPodatki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),WeatherActivity.class);
                startActivity(i);
            }
        });
        dodajZapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),DodajObisk.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        osvojeniVrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Osvojeni_vrh.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        izbiraMape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });
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
        case R.id.pet:
            Intent i8 = new Intent(getApplicationContext(),SpletneKamere.class);
            startActivity(i8);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }
}
package com.example.tva_projekt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.tva_projekt.directionhelpers.FetchURL;
import com.example.tva_projekt.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import android.os.Handler;

import java.util.HashMap;
import java.util.Map;


public class mapa_prikaz extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    boolean doubleBackToExitPressedOnce = false;
    DBHelper DB;
    Integer idPot;
    TextView lok, opisPoti, opisIzhodisce, lok2;
    Button prikaz_poti;
    Integer idUser;

    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection;
    private Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_prikaz);

        DB=new DBHelper(this);
        Intent intent = this.getIntent();

        if(intent!= null) {
            idPot = intent.getIntExtra("idPoti",0);

        }
        Pot pot = DB.izpisPodrobnoPot(idPot);

        double izhodisce_LAT = Double.parseDouble(pot.izhodisceLat);
        double izhodisce_LON = Double.parseDouble(pot.izhodisceLong);


        double trenutno_LAT = Double.parseDouble("46.5547");
        double trenutno_LON = Double.parseDouble("15.6459");

        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(mapa_prikaz.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            }
        });
        place1 = new MarkerOptions().position(new LatLng(trenutno_LAT, trenutno_LON)).title("Maribor");
        place2 = new MarkerOptions().position(new LatLng(izhodisce_LAT, izhodisce_LON)).title(pot.imePoti);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        mMap.addMarker(place1);
        mMap.addMarker(place2);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(mapa_prikaz.this,Podrobno.class);
                intent.putExtra("idPoti", idPot);
                startActivity(intent);
            }
        });
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
}


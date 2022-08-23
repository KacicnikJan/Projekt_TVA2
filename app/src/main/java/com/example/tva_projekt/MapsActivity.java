package com.example.tva_projekt;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tva_projekt.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng triglav = new LatLng(46.3342, 13.8287);
        mMap.addMarker(new MarkerOptions().position(triglav).title("Triglav"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(triglav));

        LatLng jezerska_kocna = new LatLng(46.3587, 14.52206);
        mMap.addMarker(new MarkerOptions().position(jezerska_kocna).title("Jezerska kocna"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jezerska_kocna));

        LatLng grintavec = new LatLng(46.35718, 14.53548);
        mMap.addMarker(new MarkerOptions().position(grintavec).title("Grintavec"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(grintavec));

        LatLng stol = new LatLng(46.433924290276614, 14.166731073012334);
        mMap.addMarker(new MarkerOptions().position(stol).title("Stol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stol));

        LatLng montaz = new LatLng(46.43577, 13.43405);
        mMap.addMarker(new MarkerOptions().position(montaz).title("Montaz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(montaz));

        LatLng vrtaca = new LatLng(46.43992, 14.21226);
        mMap.addMarker(new MarkerOptions().position(vrtaca).title("Vrtaca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vrtaca));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this,Hribi_Izbira.class);
                startActivity(intent);
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
        case R.id.pet:
            Intent i8 = new Intent(getApplicationContext(),SpletneKamere.class);
            startActivity(i8);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }
}
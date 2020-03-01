package com.example.mytracker;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MediumActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button myMap;
    private Button myProfile;
    private Button mySteps;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        myMap = (Button) findViewById(R.id.view_map);
        myProfile = (Button) findViewById(R.id.user);
        mySteps = (Button) findViewById(R.id.step);
        home = (Button) findViewById(R.id.tours);
        myMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchMap();
            }
        });
        myProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchProfile();
            }
        });
        mySteps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchSteps();
            }
        });
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchHome();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cameraObscura = new LatLng( 55.949208,   -3.195562);
        mMap.addMarker(new MarkerOptions().position(cameraObscura).title("Camera Obscura"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraObscura, 15));

        LatLng edinburghCastle = new LatLng(55.948595, -3.199913);
        mMap.addMarker(new MarkerOptions().position(edinburghCastle).title("Edinburgh Castle"));

        LatLng scottMonument  = new LatLng( 55.952415, -3.193278);
        mMap.addMarker(new MarkerOptions().position(scottMonument).title("Scott Monument"));

        LatLng caltonHill  = new LatLng( 55.9532, -3.1760);
        mMap.addMarker(new MarkerOptions().position(caltonHill).title("Calton Hill"));

        LatLng scottishGallery  = new LatLng( 55.955418, -3.193583);
        mMap.addMarker(new MarkerOptions().position(scottishGallery).title("Scottish National Gallery of Modern arts"));
    }
    private void launchMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchProfile(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    private void launchSteps(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void launchHome(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}

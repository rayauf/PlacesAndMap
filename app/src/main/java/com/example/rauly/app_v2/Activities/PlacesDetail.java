package com.example.rauly.app_v2.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rauly.app_v2.Fragments.PlacesDisplayFrag;
import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacesDetail extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Places place;
    private TextView placeName;
    private TextView avCost;
    private TextView placeOpenH;
    private TextView placeCloseH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        placeName = (TextView) findViewById(R.id.detailName);
        avCost = (TextView) findViewById(R.id.avCostDetail);
        placeOpenH = (TextView) findViewById(R.id.OpHourDetail);
        placeCloseH = (TextView) findViewById(R.id.ClHourDetail);

        place = getIntent().getParcelableExtra(PlacesDisplayFrag.EXTRA_ITEM_PLACE);
        updateUI();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng placeLocation = new LatLng(place.getLatitude(), place.getLongitude());
        mMap.addMarker(new MarkerOptions().position(placeLocation).title(place.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLocation, 10));
        setUpMap();
    }

    private void updateUI(){
        placeName.setText(place.getName());
        avCost.setText( "$" + Double.toString(place.getAvCost()));
        placeOpenH.setText(place.getOpenH());
        placeCloseH.setText(place.getCloseH());
    }

    private void setUpMap(){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}

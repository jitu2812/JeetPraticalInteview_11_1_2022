package com.example.jeetpraticalinteview_11_1_2022.Activity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.jeetpraticalinteview_11_1_2022.Model.LocationListModel;
import com.example.jeetpraticalinteview_11_1_2022.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lats = "", longs = "", location_name = "";
    Double lat_value, long_value;
    SearchView searchView;
    Button btn_asd, btn_dec;
    ArrayList<LocationListModel> locationListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchView = findViewById(R.id.idSearchView);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lats = getIntent().getStringExtra("lats");
        longs = getIntent().getStringExtra("longs");
        location_name = getIntent().getStringExtra("location_name");
        lat_value = Double.valueOf(lats);
        long_value = Double.valueOf(longs);
        btn_asd = findViewById(R.id.btn_asd);
        btn_dec = findViewById(R.id.btn_dec);
        if (locationListModels == null) {
            locationListModels = new ArrayList<>();
        }
        btn_asd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(locationListModels, LocationListModel.a_to_z);

            }
        });
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(locationListModels, LocationListModel.z_to_a);

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // at last we calling our map fragment to update.
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng datas = new LatLng(lat_value, long_value);
        mMap.addMarker(new MarkerOptions().position(datas).title(location_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(datas));

    }
}
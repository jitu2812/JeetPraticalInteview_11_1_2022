package com.example.jeetpraticalinteview_11_1_2022.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.example.jeetpraticalinteview_11_1_2022.Adapter.LocationListAdapter;
import com.example.jeetpraticalinteview_11_1_2022.Model.LocationListModel;
import com.example.jeetpraticalinteview_11_1_2022.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Activity activity = MainActivity.this;
    PlacesAutocompleteTextView mAutocomplete;
    PlacesClient placesClient;
    LatLng latLng = null;
    String location = "";
    String latitude = "";
    String longitude = "", lat_longs = "";
    AutocompleteSupportFragment autocompleteFragment;
    LocationListAdapter locationListAdapter;
    ArrayList<LocationListModel> locationListModels;
    RecyclerView rec_location;
    Button btn_add;
    ProgressDialog dialog;
    Button btn_asd, btn_dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutocomplete = findViewById(R.id.autocomplete);
        rec_location = findViewById(R.id.rec_location);
        btn_add = findViewById(R.id.btn_add);
        btn_asd = findViewById(R.id.btn_asd);
        btn_dec = findViewById(R.id.btn_dec);
        dialog = new ProgressDialog(activity);
        autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Search Loaction");
        autocompleteFragment.setPlaceFields(Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME, com.google.android.libraries.places.api.model.Place.Field.LAT_LNG, com.google.android.libraries.places.api.model.Place.Field.ADDRESS));
        //S_code=edt_search_code.getText().toString();
        Places.initialize(getApplicationContext(), "AIzaSyAavypuxfrzJal2XRtvTb4j9xy_Y4r0lfs");

        placesClient = Places.createClient(this);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull com.google.android.libraries.places.api.model.Place place) {
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());


                if (place.getAddress() != null) {
                    System.out.println(" my address is " + place.getAddress());

                    location = place.getAddress();

                    // System.out.println(" my addressess is " + location);
                    //autocompleteFragment.setText(s_location);
                }


                if (place.getLatLng() != null) {
                    latitude = String.valueOf(place.getLatLng().latitude);
                    longitude = String.valueOf(place.getLatLng().longitude);
                    lat_longs = latitude + longitude;
                }

            }


            @Override
            public void onError(Status status) {
                Log.i("TAG", "An error occurred: " + status);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (!location.isEmpty()) {
                    dialog.dismiss();
                    locationListModels.add(new LocationListModel(location, latitude, longitude, lat_longs));
                    locationListAdapter.notifyItemInserted(locationListModels.size());
                    saveData(location);
                } else {
                    Toast.makeText(activity, "Please Enter Location", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_asd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(locationListModels, LocationListModel.a_to_z);
                locationListAdapter.notifyDataSetChanged();
            }
        });
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(locationListModels, LocationListModel.z_to_a);
                locationListAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        setAdapterData();
    }

    private void setAdapterData() {

        locationListAdapter = new LocationListAdapter(activity, locationListModels);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rec_location.setHasFixedSize(true);
        rec_location.setLayoutManager(manager);
        rec_location.setAdapter(locationListAdapter);

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("locationss", MODE_PRIVATE);

        String imgSett = sharedPreferences.getString("locations", location);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("location", null);

        Type type = new TypeToken<ArrayList<LocationListModel>>() {
        }.getType();

        locationListModels = gson.fromJson(json, type);

        if (locationListModels == null) {
            locationListModels = new ArrayList<>();
        }
    }

    private void saveData(String locations) {

        SharedPreferences sharedPreferences = getSharedPreferences("locationss", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(locationListModels);

        editor.putString("location", json);
        editor.putString("locations", locations);
        editor.putString("latitudes", latitude);
        editor.putString("longitudes", longitude);
        editor.apply();

    }
}
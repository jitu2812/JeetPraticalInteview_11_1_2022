package com.example.jeetpraticalinteview_11_1_2022;

import android.content.Context;

public class SharedPreferences {
    private Context context;
    private String ISMAILICALENDER = "Ismaili Calender";
    private String Latitude = "Latitude";
    private String Longitude = "Longitude";

    public SharedPreferences(Context context) {
        this.context = context;
    }

    public void setLatitude(String latitude) {
        android.content.SharedPreferences sharedpreferences = context.getSharedPreferences(
                ISMAILICALENDER, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Latitude, latitude);
        editor.apply();
    }

    public void setLongitude(String longitude) {
        android.content.SharedPreferences sharedpreferences = context.getSharedPreferences(
                ISMAILICALENDER, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Longitude, longitude);
        editor.apply();
    }


    public String getLatitude() {
        android.content.SharedPreferences sharedpreferences = context.getSharedPreferences(
                ISMAILICALENDER, Context.MODE_PRIVATE);
        return sharedpreferences.getString(Latitude, "");
    }

    public String getLongitude() {
        android.content.SharedPreferences sharedpreferences = context.getSharedPreferences(
                ISMAILICALENDER, Context.MODE_PRIVATE);
        return sharedpreferences.getString(Longitude, "");
    }

}

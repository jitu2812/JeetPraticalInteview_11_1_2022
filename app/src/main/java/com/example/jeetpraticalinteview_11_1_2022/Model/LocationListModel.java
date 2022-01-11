package com.example.jeetpraticalinteview_11_1_2022.Model;

import java.util.Collections;
import java.util.Comparator;

public class LocationListModel {
    String location_name, lat, longs, lat_longs;

    public LocationListModel() {
    }

    public LocationListModel(String location_name, String lat, String longs, String lat_longs) {
        this.location_name = location_name;
        this.lat = lat;
        this.longs = longs;
        this.lat_longs = lat_longs;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public String getLat_longs() {
        return lat_longs;
    }

    public void setLat_longs(String lat_longs) {
        this.lat_longs = lat_longs;
    }

    public static Comparator<LocationListModel> getA_to_z() {
        return a_to_z;
    }

    public static void setA_to_z(Comparator<LocationListModel> a_to_z) {
        LocationListModel.a_to_z = a_to_z;
    }

    public static Comparator<LocationListModel> getZ_to_a() {
        return z_to_a;
    }

    public static void setZ_to_a(Comparator<LocationListModel> z_to_a) {
        LocationListModel.z_to_a = z_to_a;
    }

    public static Comparator<LocationListModel> a_to_z = new Comparator<LocationListModel>() {

        @Override
        public int compare(LocationListModel l1, LocationListModel l2) {
            return l1.getLocation_name().compareTo(l2.getLocation_name());
        }
    };
    public static Comparator<LocationListModel> z_to_a = new Comparator<LocationListModel>() {

        @Override
        public int compare(LocationListModel l1, LocationListModel l2) {
            return l2.getLocation_name().compareTo(l1.getLocation_name());
        }
    };
    public static Comparator<LocationListModel> lat_longs_1 = new Comparator<LocationListModel>() {

        @Override
        public int compare(LocationListModel l1, LocationListModel l2) {
            return l1.getLat_longs().compareTo(l2.getLat_longs());
        }
    };
    public static Comparator<LocationListModel> lat_longs_2 = new Comparator<LocationListModel>() {

        @Override
        public int compare(LocationListModel l1, LocationListModel l2) {
            return l2.getLat_longs().compareTo(l1.getLat_longs());
        }
    };


}

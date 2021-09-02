package com.example.safeway.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.safeway.FunctionalitiesActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MaxLegalSpeed implements LocationListener {

    private static Context mContext;
    TextView txt_speed, txt_street_name, txt_street_limit, txt_city_name;

    Geocoder geocoder;
    List<Address> addresses;

    double latitude, longitude;

    /*
        Singleton Support (Thread Safe)
     */
    private static volatile MaxLegalSpeed instance;
    private static Object mutex = new Object();

    private MaxLegalSpeed() {
    }

    public static MaxLegalSpeed getInstance() {
        MaxLegalSpeed result = instance;
        if (instance == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new MaxLegalSpeed();
            }
        }
        return instance;
    }
    /////////////////////////////////////////////////////////////

    public void initialization(TextView txt_speed2, TextView txt_street_name2, TextView txt_street_limit2, TextView txt_city_name2, Context context2) {
        /*
            Initializes all the variables we need for displaying this functionality.

            `txt_speed` -> TextView responsible with the current speed of the user.
            `txt_street_name` -> TextView responsible with displaying the street name the user is on.
            `txt_street_limit` -> TextView responsible with displaying the maximum legal speed allowed on the street, the user is located on.
            `txt_city_name` -> TextView responsible with displaying the city name the user is in.
         */

        txt_speed = txt_speed2;
        txt_street_name = txt_street_name2;
        txt_street_limit = txt_street_limit2;
        txt_city_name = txt_city_name2;

        mContext = context2;

        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Looper.prepare();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);
        Looper.loop();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        /*
            Every time the location changes, this function is called.

            `location` -> The current location of the user
         */

        if (location == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    txt_speed.setText("-.- km/h");
                }
            };
            handler.post(runnable);
        } else {
            float nCurrentSpeed = location.getSpeed();
            nCurrentSpeed *= 3.6f;

            Handler handler = new Handler(Looper.getMainLooper());
            float finalNCurrentSpeed = nCurrentSpeed;
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    txt_speed.setText(finalNCurrentSpeed + "km/h");
                }
            };
            handler.post(runnable);

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            String city_name = null;
            String street_name = null;
            String street_speed_limit = "50";

            try {
                city_name = get_city_name(latitude, longitude);
                street_name = get_street_name(latitude, longitude);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] street_name_arr = street_name.split(" ", 2);

            if (street_name_arr[0].toLowerCase().equals("autostrada")) {
                street_speed_limit = "130";
            }
            else if (street_name.substring(0, 2).equals("DN") || street_name.substring(0, 2).equals("DJ")) {
                street_speed_limit = "90";
            }

            Handler handler2 = new Handler(Looper.getMainLooper());
            String finalCity_name = city_name;
            String finalStreet_speed_limit = street_speed_limit;
            String finalStreet_name = street_name;
            Runnable runnable2 = new Runnable(){
                @Override
                public void run() {
                    txt_street_limit.setText(finalStreet_speed_limit);
                    txt_city_name.setText(finalCity_name);
                    txt_street_name.setText(finalStreet_name);
                }
            };
            handler2.post(runnable2);
        }

    }

    private String get_city_name(double lat, double lon) throws  IOException {
        /*
            Function responsible with retrieving the city name, the user is located in, based on coordinates.

            `lat` -> Latitude.
            `lon` -> Longitude.
         */

        geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }

    private String get_street_name(double lat, double lon) throws  IOException {
        /*
            Function responsible with retrieving the street name, the user is located on, based on coordinates.

            `lat` -> Latitude.
            `lon` -> Longitude.
         */

        geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getThoroughfare();
        }
        return null;
    }
}

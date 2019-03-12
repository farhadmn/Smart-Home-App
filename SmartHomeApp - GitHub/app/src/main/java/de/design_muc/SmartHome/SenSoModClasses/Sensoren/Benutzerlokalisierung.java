package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.content.Context.LOCATION_SERVICE;

public class Benutzerlokalisierung extends ComputedSensor  implements LocationListener {

    private Context mContext;
    private GPSSensor mygps;
    private double lat, lng;



    //GPS Sensor
    boolean isGPSOn=false;
    //Network locaion
    boolean isNetworkOn=false;

    //minimum distance to request for location update
    private static final long min_dist=1;

    // minimum time to request location updates
    private static final long min_time=1000*1; // 1 sec

    //flag to getlocation
    boolean isLocationEnabled=false;

    LocationManager locationManager;
    Location location;
    String ort;

    public Benutzerlokalisierung(Context mContext, String ort) {
        this.mContext = mContext;
        this.ort=ort;
        checkLoc();
        createGPSSensor();
        decisionLogic();

    }


    public Location  checkLoc(){

        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);




        try {



            locationManager=(LocationManager)mContext.getSystemService(LOCATION_SERVICE);

            //check for gps
            isGPSOn=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //check for network
            isNetworkOn=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if(!isGPSOn && !isNetworkOn)
            {
                isLocationEnabled=false;
                // no location provider
                Toast.makeText(mContext,"No Location Provider is Available",Toast.LENGTH_SHORT).show();
            }
            else {
                isLocationEnabled=true;

                //  network location ist an,  request location update
                if(isNetworkOn)
                {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,min_time,min_dist,this);
                    if(locationManager!=null)
                    {
                        location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location!=null)
                        {
                            lat=location.getLatitude();
                            lng=location.getLongitude();
                        }
                    }
                }
                if(isGPSOn)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,min_time,min_dist,this);
                    if(locationManager!=null)
                    {
                        location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location!=null)
                        {
                            lat=location.getLatitude();
                            lng=location.getLongitude();
                        }
                    }
                }
            }





        }
        catch(SecurityException e) {
            e.printStackTrace();
        }

        return location;

    }


    public String getOrt() {
        return ort;
    }


    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void decisionLogic(){


        double lat, lng;
        String t,m;
        lat=mygps.getLat();
        lng= mygps.getLng();
        // String s = Double.toString(lat);

        // Toast.makeText(this,"Du bist zuhause:" + s,Toast.LENGTH_SHORT).show();


        DecimalFormat conv = new DecimalFormat("##.##");
        t= conv.format(lat);
        m= conv.format(lng);




        if(t.equals("48,21") && (m.equals("11,58"))){

            setOrt("Athome");

        }

        if(t.equals("48,55") && (m.equals("12,19"))){


            setOrt("AtWork");

        }

        else {


            setOrt("Unkown");

        }




    }



    public void createGPSSensor(){

        mygps= new GPSSensor(lat, lng);
    }

    public double getLat() {

        double latgps;
        latgps=mygps.getLat();
        return latgps;
    }

    public double getLng() {
        double lnggps;
        lnggps=mygps.getLng();
        return lnggps;
    }

    @Override
    public void onLocationChanged(Location location) {

        this.location=location;
        test();
        // lat = location.getLatitude();
        // lng = location.getLongitude();

    }

    public void test(){

        mygps.setLat(lat);
        mygps.setLng(lng);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        popupAskLocation();

    }


    //call to open settings and ask to enable Location
    public void popupAskLocation() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        //set title
        dialog.setTitle("Settings");
        //set Message
        dialog.setMessage("Standort ist nicht aktiviert. Bitte schalten das GPS an.");
        // on pressing this will be called
        dialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        //on Pressing cancel
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // show Dialog box
        dialog.show();
    }
}

// Use IDE to generate toString and equals methods


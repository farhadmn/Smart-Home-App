package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import static android.content.Context.LOCATION_SERVICE;

public class GPSSensor extends PhysicalSensor implements LocationListener {

    private double lat, lng;
    private static GPSSensor gpsSensorIntance;
    //minimum distance to request for location update
    private static final long min_dist=1;

    // minimum time to request location updates
    private static final long min_time=1000; // 1 sec

    private GPSSensor(Context mContext) {
        this.name = "GPSSensor";
        getCurrentLocation(mContext);

    }

    private void getCurrentLocation(Context mContext) {
        LocationManager locationManager=(LocationManager)mContext.getSystemService(LOCATION_SERVICE);
        try {

            //check for gps
            boolean isGPSOn=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //check for network
            boolean isNetworkOn=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSOn && !isNetworkOn) {
                // no location provider
                Toast.makeText(mContext,"No Location Provider is Available",Toast.LENGTH_SHORT).show();
            } else {
                //  network location ist an,  request location update
                if(isNetworkOn) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, min_time, min_dist,this);
                    if(locationManager!=null) {
                        Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location!=null) {
                            lat=location.getLatitude();
                            lng=location.getLongitude();
                        }
                    }
                }
                if(isGPSOn) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,min_time,min_dist,this);
                    if(locationManager!=null) {
                        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location!=null) {
                            lat=location.getLatitude();
                            lng=location.getLongitude();
                        }
                    }
                }
            }
        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    public static GPSSensor getIntance(Context context){
        if(gpsSensorIntance == null) {
            gpsSensorIntance = new GPSSensor(context);
        }
        return gpsSensorIntance;
    }

    /*TODO: create logic to return the Koordinaten*/
    public double getLat() {
        return lat;
    }

    public double getLong() {
        return lng;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.lat = location.getLatitude();
        this.lng = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

// Use IDE to generate toString and equals methods


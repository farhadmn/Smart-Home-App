package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Raumerkennung extends ComputedSensor implements BeaconConsumer {

    private List <IBeaconSensor> beaconList = new ArrayList <IBeaconSensor> ();

    private BeaconManager beaconManager;
    private Context mContext;

    public Raumerkennung(Context context) {
     name="Raumerkennung";
        mContext = context;
    }



    /*TODO: create logic to return the Raum*/
    private String output(String raum) {
        return raum;
    }

    public   void decisionLogic () {

        for (int i = 0;i< beaconList.size() ;i++){

            if(beaconList.get(i).getName().equals("6")) {    // 6 = Minor of Ibeacon
                output("Wohnzimmer");

            }

            if(beaconList.get(i).getName().equals("8")) {
                output("Schlafzimmer");

            }

            if(beaconList.get(i).getName().equals("9")) {
                output("Office");

            }

            else {
                output("UnknowRoom");

            }



        }
    }



    //AltBeacon




    @Override
    public void onBeaconServiceConnect() {

     //  beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));


        //location access nicht vergessen

        this.beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    beaconList.clear();
                    for(Iterator<Beacon> iterator = beacons.iterator(); iterator.hasNext();) {

                        beaconList.add(new IBeaconSensor (iterator.next().getId3().toString()));
                    }

                }
            }
        });
        try {
            this.beaconManager.startRangingBeaconsInRegion(new Region("MyRegionId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getApplicationContext() {
        return mContext.getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        mContext.unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return mContext.bindService(intent, serviceConnection, i);
    }


}




















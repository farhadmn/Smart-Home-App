package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.util.Log;

import de.design_muc.SmartHome.SenSoModClasses.JsonHelper.FetchAPIData;

public class JalousienSteuerung extends ComputedSensor {


    JalousienSensor myJalousienSensor;
    WettervorhersageSensor myWettervorhersageSensor;


    public JalousienSteuerung() {
        multiple = false;
        this.name = "JalousienSteuerung";
        Onreate();
        getAPIValue(	48.14,11.58);
        decisionLogic();
        test();
    }


    public void Onreate() {

        myJalousienSensor = new JalousienSensor(0);
        myWettervorhersageSensor = WettervorhersageSensor.getInstance();

    }


    public void decisionLogic() {
        if (myWettervorhersageSensor.getWetterValueAPI().equals("Snow") || myWettervorhersageSensor.getWetterValueAPI().equals("Rain") || myWettervorhersageSensor.getWind().equals("10")&& myJalousienSensor.getJalousienStatus() > 0) {
            myJalousienSensor.setJalousienStatus(0);
        }

    }

    public void getAPIValue ( double lat, double lng)  {


        FetchAPIData task = new FetchAPIData();
//        task.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&APPID=");
        task.execute("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&APPID=e64f6007f0b760cc45977e7638309536");

    }





        public synchronized void setStatus(int s){

            myJalousienSensor.setJalousienStatus(s);

        }


        public synchronized int getStatus(){

        return myJalousienSensor.getJalousienStatus();

        }


    public void test(){

        String w,t;

        w=myWettervorhersageSensor.getWind();


        Log.i("Wetter", w);

    }


    }









    /*TODO: create logic to return the Steuerungsmethode*/





// Use IDE to generate toString and equals methods


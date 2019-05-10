package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.Activity;
import android.util.Log;


public class JalousienSteuerung extends ComputedSensor {


    private JalousienSensor myJalousienSensor;
    private WeatherSensor myWeatherSensor;


    public JalousienSteuerung(Activity activity) {
        multiple = false;
        this.name = "JalousienSteuerung";
        onCreate(activity);
        getAPIValue(48.14,11.58);
        decisionLogic();
//        test();
    }


    public void onCreate(Activity activity) {
        myJalousienSensor = new JalousienSensor(0);
        myWeatherSensor = WeatherSensor.getInstance(activity);

    }


    public void decisionLogic() {
//        if (myWeatherSensor.getWetterValueAPI(this).equals("Snow") || myWeatherSensor.getWetterValueAPI().equals("Rain") || myWeatherSensor.getWind().equals("10")&& myJalousienSensor.getJalousienStatus() > 0) {
//            myJalousienSensor.setJalousienStatus(0);
//        }

    }

    public void getAPIValue ( double lat, double lng)  {

//        FetchAPIData task = new FetchAPIData(this.con);
////        task.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&APPID=");
//        task.execute("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&APPID=e64f6007f0b760cc45977e7638309536");

    }

    public synchronized void setStatus(int s) {
        myJalousienSensor.setJalousienStatus(s);
    }


    public synchronized int getStatus(){
        return myJalousienSensor.getJalousienStatus();
    }


    public void test(){
        String w,t;
        w= myWeatherSensor.getWind();
        Log.i("Wetter", w);
    }
}









    /*TODO: create logic to return the Steuerungsmethode*/





// Use IDE to generate toString and equals methods


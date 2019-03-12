package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class WettervorhersageSensor extends VirtualSensor {


    private static WettervorhersageSensor instance = null;
    private String WetterValueAPI,wind,temp;

    public WettervorhersageSensor(String WetterValueAPI, String wind, String temp) {
        multiple = false;
        this.name = "WettervorhersageSensor";
        this.WetterValueAPI = WetterValueAPI;
        this.wind=wind;
        this.temp=temp;
    }



    /*TODO: create logic to return the WetterValueAPI*/


    public String getWetterValueAPI() {
        return WetterValueAPI;
    }

    public void setWetterValueAPI(String WetterValueAPI) {
        this.WetterValueAPI = WetterValueAPI;
    }


    public static WettervorhersageSensor getInstance() {
        if(instance == null)
            instance = new WettervorhersageSensor("","", "");
        return instance;
    }

    public String getWind() {
        return wind;
    }

    public String getTemp() {
        return temp;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
// Use IDE to generate toString and equals methods


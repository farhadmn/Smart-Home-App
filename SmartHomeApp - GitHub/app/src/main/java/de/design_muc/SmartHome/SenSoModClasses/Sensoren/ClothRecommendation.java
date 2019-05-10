package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.Activity;

public class ClothRecommendation extends ComputedSensor {


    private WeatherSensor myWeatherSensor;
    private String recommendation;
    private boolean status;

    private static ClothRecommendation instance = null;

    private ClothRecommendation(String recommendation, boolean status, Activity activity) {
        multiple = false;
        this.name = "ClothRecommendation";
        this.recommendation = recommendation;
        this.status=status;
        this.myWeatherSensor = WeatherSensor.getInstance(activity);
    }

    public void decisionLogic() {
        if ("Rain".equals(myWeatherSensor.getWeather())) {
            recommendation = "Regenbekleidung";
        } else if ("Snow".equals(myWeatherSensor.getWeather())) {
            recommendation = "Winterbekleidung";
        } else if ("Clear".equals(myWeatherSensor.getWeather()) && (Double.valueOf(myWeatherSensor.getTemp()) <= 5.0 )) {
            recommendation = "Herbstlook";
        }else if ("Clear".equals(myWeatherSensor.getWeather()) && (Double.valueOf(myWeatherSensor.getTemp()) > 20.0  )) {
            recommendation = "Sommerbekleidung";
        } else {
            recommendation = "Leider ist gerade kein Vorschlag möglicht. Bitte schau aus dem Fenster";
        }
    }

    public static ClothRecommendation getInstance(Activity activity) {
        if(instance == null)
            instance = new ClothRecommendation("", true, activity);
        return instance;
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
    }

    public String getRecommendation() {
        decisionLogic();
        return recommendation;
    }
}


package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.Activity;

import java.text.DecimalFormat;

public class ClothRecommendation extends ComputedSensor {

    private WeatherSensor myWeatherSensor;
    private String recommendation;
    private boolean status;

    private static ClothRecommendation instance = null;

    private ClothRecommendation(String recommendation, boolean status, Activity activity) {
        multiple = false;
        this.name = "ClothRecommendation";
        this.recommendation = recommendation;
        this.status = status;
        this.myWeatherSensor = WeatherSensor.getInstance(activity);
    }

    public void decisionLogic() {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String temperature = decimalFormat.format(myWeatherSensor.getTemp());
        if ("Rain".equals(myWeatherSensor.getWeather()) || "Mist".equals(myWeatherSensor.getWeather()) || "Drizzel".equals(myWeatherSensor.getWeather())) {
            recommendation = "Es regnet oder nieselt und es hat " + temperature + " °C.\nRegenbekleidung wird empfholen";
        } else if ("Fog".equals(myWeatherSensor.getWeather())) {
            recommendation = "Es ist neblig und es hat " + temperature + " °C.\nRegenbekleidung wird empfholen";
        } else if ("Clouds".equals(myWeatherSensor.getWeather())) {
            recommendation = "Es ist bewölkt und es hat " + temperature + " °C.\nÜbergangskleidung wird empfohlen.";
        } else if ("Snow".equals(myWeatherSensor.getWeather()) || myWeatherSensor.getTemp() <= 5.0) {
            recommendation = "Es schneit und es hat " + temperature + " °C.\nWinterbekleidung wird empfholen.";
        } else if (myWeatherSensor.getTemp() <= 20.0) {
            recommendation = "Es ist klarer Himmel und es hat " + temperature + " °C.\nÜbergangskleidung wird empfohlen.";
        } else if (myWeatherSensor.getTemp() > 20.0) {
            recommendation = "Es ist klarer Himmel und es hat " + temperature + " °C.\nSommerbekleidung wird empfohlen";
        } else {
            recommendation = "Leider ist gerade kein Vorschlag möglicht. Bitte schau aus dem Fenster.";
        }
    }

    public static ClothRecommendation getInstance(Activity activity) {
        if (instance == null)
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


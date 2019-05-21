package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.Activity;

import java.text.DecimalFormat;

import de.design_muc.SmartHome.R;

public class ClothRecommendation extends ComputedSensor {

    private WeatherSensor myWeatherSensor;
    private String recommendation;
    private boolean status;
    private Activity activity;

    private static ClothRecommendation instance = null;

    private ClothRecommendation(String recommendation, boolean status, Activity activity) {
        multiple = false;
        this.name = "ClothRecommendation";
        this.recommendation = recommendation;
        this.status = status;
        this.myWeatherSensor = WeatherSensor.getInstance();
        this.activity = activity;
    }

    public void decisionLogic() {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String temperature = decimalFormat.format(myWeatherSensor.getTemp());
        if ("Rain".equals(myWeatherSensor.getWeather()) || "Mist".equals(myWeatherSensor.getWeather()) || "Drizzel".equals(myWeatherSensor.getWeather())) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextRain);
        } else if ("Fog".equals(myWeatherSensor.getWeather())) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextFog);
        } else if ("Clouds".equals(myWeatherSensor.getWeather())) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextClouds);
        } else if ("Snow".equals(myWeatherSensor.getWeather()) || myWeatherSensor.getTemp() <= 5.0) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextSnow);
        } else if (myWeatherSensor.getTemp() <= 20.0) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextBelow20Degree);
        } else if (myWeatherSensor.getTemp() > 20.0) {
            recommendation = this.activity.getString(R.string.clothRecommendationTextOver20Degree);
        } else {
            recommendation = this.activity.getString(R.string.clothRecommendationTextDefault);
        }
        recommendation = recommendation.replace("X", temperature);
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


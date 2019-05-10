package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.design_muc.SmartHome.SchlafzimmerActivity;

public class WeatherSensor extends VirtualSensor {


    private static WeatherSensor instance = null;
    private String weather, wind, temp = "";
    private static String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?lat=";
    private static String WEATHER_API_URL_KEY = "&APPID=e64f6007f0b760cc45977e7638309536";
    private SchlafzimmerActivity activity;

    private WeatherSensor(Activity activity) {
        this.multiple = false;
        this.name = "WeatherSensor";
        this.activity = (SchlafzimmerActivity) activity;
        getWetterValueAPI(activity);
    }

    public void getWetterValueAPI(Activity activity) {
        FetchWeatherData fetchWeatherData = new FetchWeatherData();
        fetchWeatherData.execute(WEATHER_API_URL + GPSSensor.getIntance(activity).getLat()
                + "&lon="+ GPSSensor.getIntance(activity).getLong() + WEATHER_API_URL_KEY);
    }

    public static WeatherSensor getInstance(Activity activity) {
        if(instance == null)
            instance = new WeatherSensor(activity);
        return instance;
    }

    public String getWind() {
        return wind;
    }

    public String getTemp() {
        return temp;
    }
    public String getWeather(){
        return weather;
    }

    private class FetchWeatherData extends AsyncTask<String, Void, String> {

        private String result = "";
        private URL url;
        private HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... urls) {
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject json = new JSONObject(result);

                //Wetter
                JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                weather = details.getString("main");
                Log.i("Wetter", weather);

                //Temperatur
                JSONObject main = json.getJSONObject("main");
                temp = main.getString("temp");
                Log.i("Temp", temp);

                //Wind
                wind = json.getJSONObject("wind").getString("speed");
                Log.i("Wind", wind);
                activity.getRecommendation();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
// Use IDE to generate toString and equals methods


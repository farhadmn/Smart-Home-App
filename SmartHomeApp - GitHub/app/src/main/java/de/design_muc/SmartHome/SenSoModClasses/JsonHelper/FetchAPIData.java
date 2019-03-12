package de.design_muc.SmartHome.SenSoModClasses.JsonHelper;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.WettervorhersageSensor;


public class FetchAPIData extends AsyncTask<String, Void, String> {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        WettervorhersageSensor myWetter;



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
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            myWetter = WettervorhersageSensor.getInstance();


            try {

                JSONObject json = new JSONObject(result);
                String wetter;
                String temp;
                String windValue;




                //Wetter
                JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                wetter = details.getString("main");

               myWetter.setWetterValueAPI(wetter);


                //Log.i("Wetter",wetter);

                //Temperatur
                JSONObject main = json.getJSONObject("main");
                temp = main.getString("temp");


                myWetter.setTemp(temp);

                //Log.i("Temp",temp);

                //Wind
                JSONObject wind = json.getJSONObject("wind");
                windValue = wind.getString("speed");

              //  Log.i("Wind",windValue);

                myWetter.setWind(windValue);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


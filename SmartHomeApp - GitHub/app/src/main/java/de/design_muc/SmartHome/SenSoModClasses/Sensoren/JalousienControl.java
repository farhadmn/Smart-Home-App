package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.widget.SeekBar;

public class JalousienControl extends ComputedSensor {

    private JalousienSensor myJalousienSensor;
    private WeatherSensor myWeatherSensor;

    public JalousienControl() {
        this.multiple = false;
        this.name = "JalousienControl";
        this.myJalousienSensor = new JalousienSensor(0);
        this.myWeatherSensor = WeatherSensor.getInstance();
    }

    public void decisionLogic(SeekBar seekBar) {
        if ("Snow".equals(myWeatherSensor.getWeather()) || "Rain".equals(myWeatherSensor.getWeather()) || 10 > myWeatherSensor.getWind() && myJalousienSensor.getJalousienStatus() > 0) {
            this.myJalousienSensor.setJalousienStatus(1);
        } else if("Clouds".equals(myWeatherSensor.getWeather())) {
            this.myJalousienSensor.setJalousienStatus(2);
        } else {
            this.myJalousienSensor.setJalousienStatus(3);
        }
        seekBar.setProgress(this.myJalousienSensor.getJalousienStatus());
    }

    public synchronized void setStatus(int s) {
        this.myJalousienSensor.setJalousienStatus(s);
    }

    public synchronized int getStatus() {
        return this.myJalousienSensor.getJalousienStatus();
    }

}
// Use IDE to generate toString and equals methods


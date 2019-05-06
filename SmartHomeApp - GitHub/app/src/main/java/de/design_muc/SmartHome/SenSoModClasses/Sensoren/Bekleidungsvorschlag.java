package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.util.Log;
import android.widget.Toast;

public class Bekleidungsvorschlag extends ComputedSensor {


     WettervorhersageSensor myWettervorhersageSensor;

    String vorschlag;
    String wetter;
    double temp;

    boolean status;
    private static Bekleidungsvorschlag instance = null;

    public Bekleidungsvorschlag( String vorschlag, boolean status) {
        multiple = false;
        this.name = "Bekleidungsvorschlag";
        this.vorschlag=vorschlag;
        this.status=status;

        myWettervorhersageSensor = WettervorhersageSensor.getInstance();
        decisionLogic();

    }






    public void decisionLogic() {
        if (myWettervorhersageSensor.getWetterValueAPI().equals("Rain")) {
            setVorschlag("Regenbekleidung");
        } else if (myWettervorhersageSensor.getWetterValueAPI().equals("Snow")) {
            setVorschlag("Winterbekleidung");
        } else if (myWettervorhersageSensor.getWetterValueAPI().equals("Clear") && (Double.valueOf(myWettervorhersageSensor.getTemp()) <= 5.0 )) {
            setVorschlag("Herbstlook");
        }else if (myWettervorhersageSensor.getWetterValueAPI().equals("Clear") && (Double.valueOf(myWettervorhersageSensor.getTemp()) >20.0  )) {
            setVorschlag("Sommerbekleidung");
        }


    }



    public static Bekleidungsvorschlag getInstance() {

        if(instance == null)
            instance = new Bekleidungsvorschlag("", true);

        return instance;
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
    }

    public String getVorschlag() {
        return vorschlag;
    }


    public void setVorschlag(String vorschlag) {
        this.vorschlag = vorschlag;
    }
}
// Use IDE to generate toString and equals methods


package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import java.util.List;

public class Kalender extends VirtualSensor {


    List<String> termine;

    public Kalender(List<String> termine) {
        multiple = false;
        this.name = "Kalender";
        this.termine=termine;
    }


    public List<String> getTermine() {
        return termine;
    }

    public void setTermine(List<String> termine) {
        this.termine = termine;
    }
}
// Use IDE to generate toString and equals methods


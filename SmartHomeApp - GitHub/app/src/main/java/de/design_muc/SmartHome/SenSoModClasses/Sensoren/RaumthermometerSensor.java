package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class RaumthermometerSensor extends PhysicalSensor {

    private double raumtemperaturvalue;


    public RaumthermometerSensor(double raumtemperaturvalue) {
        multiple = false;
        this.name = "RaumthermometerSensor";
        this.raumtemperaturvalue = raumtemperaturvalue;
    }



    /*TODO: create logic to return the RaumtemperaturValue*/


    public double getRaumtemperaturValue() {
        return raumtemperaturvalue;
    }

    public void setRaumtemperaturValue(double RaumtemperaturValue) {
        this.raumtemperaturvalue = RaumtemperaturValue;
    }
}
// Use IDE to generate toString and equals methods


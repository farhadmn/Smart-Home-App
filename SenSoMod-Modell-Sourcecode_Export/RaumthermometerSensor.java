package sensomod.generated;

public class RaumthermometerSensor extends PhysicalSensor {

    public RaumthermometerSensor(double raumtemperaturvalue) {
        multiple = false;
        this.name = "RaumthermometerSensor";
        this.raumtemperaturvalue = raumtemperaturvalue;
    }

    private double RaumtemperaturValue;

    /*TODO: create logic to return the RaumtemperaturValue*/
    public double output() {
    }

    public double getRaumtemperaturValue() {
        return RaumtemperaturValue;
    }

    public void setRaumtemperaturValue(double RaumtemperaturValue) {
        this.RaumtemperaturValue = RaumtemperaturValue;
    }
}
// Use IDE to generate toString and equals methods


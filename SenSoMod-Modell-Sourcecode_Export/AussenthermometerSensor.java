package sensomod.generated;

public class AussenthermometerSensor extends PhysicalSensor {

    public AussenthermometerSensor(double temperaturvalue) {
        multiple = false;
        this.name = "AussenthermometerSensor";
        this.temperaturvalue = temperaturvalue;
    }

    private double TemperaturValue;

    /*TODO: create logic to return the TemperaturValue*/
    public double output() {
    }

    public double getTemperaturValue() {
        return TemperaturValue;
    }

    public void setTemperaturValue(double TemperaturValue) {
        this.TemperaturValue = TemperaturValue;
    }
}
// Use IDE to generate toString and equals methods


package sensomod.generated;

public class RouterSensor extends PhysicalSensor {

    public RouterSensor(String ssidvalue) {
        multiple = false;
        this.name = "RouterSensor";
        this.ssidvalue = ssidvalue;
    }

    private String SSIDValue;

    /*TODO: create logic to return the SSIDValue*/
    public String output() {
    }

    public String getSSIDValue() {
        return SSIDValue;
    }

    public void setSSIDValue(String SSIDValue) {
        this.SSIDValue = SSIDValue;
    }
}
// Use IDE to generate toString and equals methods


package sensomod.generated;

public class IBeaconSensor extends PhysicalSensor {

    public IBeaconSensor(String majorvalue) {
        multiple = false;
        this.name = "IBeaconSensor";
        this.majorvalue = majorvalue;
    }

    private String MajorValue;

    /*TODO: create logic to return the MajorValue*/
    public String output() {
    }

    public String getMajorValue() {
        return MajorValue;
    }

    public void setMajorValue(String MajorValue) {
        this.MajorValue = MajorValue;
    }
}
// Use IDE to generate toString and equals methods


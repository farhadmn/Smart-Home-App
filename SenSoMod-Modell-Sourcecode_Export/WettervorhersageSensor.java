package sensomod.generated;

public class WettervorhersageSensor extends VirtualSensor {

    public WettervorhersageSensor(String wettervalueapi) {
        multiple = false;
        this.name = "WettervorhersageSensor";
        this.wettervalueapi = wettervalueapi;
    }

    private String WetterValueAPI;

    /*TODO: create logic to return the WetterValueAPI*/
    public String output() {
    }

    public String getWetterValueAPI() {
        return WetterValueAPI;
    }

    public void setWetterValueAPI(String WetterValueAPI) {
        this.WetterValueAPI = WetterValueAPI;
    }
}
// Use IDE to generate toString and equals methods


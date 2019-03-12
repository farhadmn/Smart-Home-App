package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class DruckerSensor extends PhysicalSensor {

    private String StatusDrucker;
    private static DruckerSensor instance = null;

    public DruckerSensor(String StatusDrucker) {
        multiple = false;
        this.name = "DruckerSensor";
        this.StatusDrucker = StatusDrucker;
    }


    public String getStatusDrucker() {
        return StatusDrucker;
    }

    public void setStatusDrucker(String statusDrucker) {
        StatusDrucker = statusDrucker;
    }


    public static DruckerSensor getInstance() {
        if(instance == null)
            instance = new DruckerSensor("");
        return instance;
    }
}
// Use IDE to generate toString and equals methods


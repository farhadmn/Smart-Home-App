package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class LichtStatus extends PhysicalSensor {

    public LichtStatus(boolean status) {
        multiple = false;
        this.name = "LichtStatus";
        this.status = status;
    }




    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
    }
}
// Use IDE to generate toString and equals methods


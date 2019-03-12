package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class JalousienSensor extends PhysicalSensor {


    private int JalousienStatus;
    public JalousienSensor(int JalousienStatus) {
        multiple = false;
        this.name = "JalousienSensor";
        this.JalousienStatus=JalousienStatus;
    }



    /*TODO: create logic to return the JalousienStatus*/


    public synchronized int getJalousienStatus() {
        return JalousienStatus;
    }

    public synchronized void setJalousienStatus(int JalousienStatus) {
        this.JalousienStatus = JalousienStatus;
    }
}
// Use IDE to generate toString and equals methods


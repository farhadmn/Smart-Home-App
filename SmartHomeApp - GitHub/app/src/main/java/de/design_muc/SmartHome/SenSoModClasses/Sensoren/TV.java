package de.design_muc.SmartHome.SenSoModClasses.Sensoren;



public class TV extends PhysicalSensor {



    public TV(boolean status) {
        multiple = false;
        this.name = "TV";
        this.status = status;
    }



    public synchronized  void setStatus(boolean status) {
        this.status = status;
    }

    // getStatus

    public synchronized  boolean getStatus() {
        return status;
    }


}


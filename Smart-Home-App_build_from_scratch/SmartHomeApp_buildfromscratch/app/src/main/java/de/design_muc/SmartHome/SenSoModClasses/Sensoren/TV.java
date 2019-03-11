package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.PhysicalSensor;

public class TV extends PhysicalSensor {

    boolean status;
    String name;

    public TV() {

        name = "SmartTV";
    }



    //setStatus
    public void setStatus(boolean status) {
        this.status = status;
    }

    // getStatus

    public boolean getStatus (){

        return status;
    }


}


package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class Alarmanlage extends PhysicalSensor {

    String name;
    private static Alarmanlage instance = null;

    boolean status;

    // Constructor
    public Alarmanlage(boolean status) {

        name = "Alarmanlage";
        this.status=status;
    }


    //setStatus
    public void setStatus(boolean status) {
        this.status = status;
    }


    //getStatus
    public boolean getStatus (){

        return status;
    }

    public static Alarmanlage getInstance() {
        if(instance == null)
            instance = new Alarmanlage(true);
        return instance;
    }



}



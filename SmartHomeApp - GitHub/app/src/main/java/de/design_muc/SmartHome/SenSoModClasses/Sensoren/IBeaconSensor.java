package de.design_muc.SmartHome.SenSoModClasses.Sensoren;


import java.util.ArrayList;
import java.util.List;

public class IBeaconSensor extends PhysicalSensor {

    public IBeaconSensor(String name) {
        multiple = false;
        this.name = name;

    }



    /*TODO: create logic to return the MajorValue*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<IBeaconSensor> myList = new ArrayList<IBeaconSensor>();


    public void add(String beacon) {

            myList.add(new IBeaconSensor(beacon));
        }

}

// Use IDE to generate toString and equals methods


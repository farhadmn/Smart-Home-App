package sensomod.generated;

public class Raumerkennung extends ComputedSensor {

    public Raumerkennung(LocationDBSensor locationdbsensor, IBeaconSensor ibeaconsensor) {
        multiple = false;
        this.name = "Raumerkennung";
        this.locationdbsensor = locationdbsensor;
        this.ibeaconsensor = ibeaconsensor;
    }

    private LocationDBSensor locationdbsensor;

    private IBeaconSensor ibeaconsensor;

    /*TODO: create logic to return the Raum*/
    public Raum output() {
    }

    public void decisionLogic() {
        if(IBeacon=="lffffffff-1234-aaaa-1a2b-a1b2c3d4e546" ) {
	Raum= "Schlafzimmer"		}
if(IBeacon.Major="lffffffff-1234-aaaa-1a2df-a1b2c3d4e546" ) {
	Raum= "Wohnzimmer"		}
if(IBeacon="lffffffff-as34asdaaa-1a2df-a1b2c3d4e546" ) {
	Raum= "Office"		}
else {
Raum="unknown" };
    }

    public LocationDBSensor getLocationdbsensor() {
        return locationdbsensor;
    }

    public void setLocationdbsensor(LocationDBSensor locationdbsensor) {
        this.locationdbsensor = locationdbsensor;
    }

    public IBeaconSensor getIbeaconsensor() {
        return ibeaconsensor;
    }

    public void setIbeaconsensor(IBeaconSensor ibeaconsensor) {
        this.ibeaconsensor = ibeaconsensor;
    }
}
// Use IDE to generate toString and equals methods


package sensomod.generated;

public class Benutzerlokalisierung extends ComputedSensor {

    public Benutzerlokalisierung(LocationDBSensor locationdbsensor, RouterSensor routersensor, Raumerkennung raumerkennung, GPSSensor gpssensor) {
        multiple = false;
        this.name = "Benutzerlokalisierung";
        this.locationdbsensor = locationdbsensor;
        this.routersensor = routersensor;
        this.raumerkennung = raumerkennung;
        this.gpssensor = gpssensor;
    }

    private LocationDBSensor locationdbsensor;

    private RouterSensor routersensor;

    private Raumerkennung raumerkennung;

    private GPSSensor gpssensor;

    /*TODO: create logic to return the UserLocation*/
    public UserLocation output() {
    }

    public void decisionLogic() {
        	
if (longitude=12.10 && latitude= 91) || (Wifi.SSID="TPLink") {

	UserLocationlocation= "at home"; 

	if(Raum= "Schlafzimmer"){
		detectedRaum="Schlafzimmer";}
	if(Raum= "Wohnzimmer"){
		detectedRaum="Wohnzimmer";}
	if(Raum= "unknown"){
	detectedRaum="unknown";}

}
if (longitude=11.10 && latitude= 81) || (Wifi.SSID="BayernWland") ) {

UserLocationlocation= "at work"; 
if(Raum= "Office"){
detectedRaum="Office";}
if(Raum= "unknown"){
detectedRaum="unknown";}

}
else {
UserLocationlocation= "unknown"
};
    }

    public LocationDBSensor getLocationdbsensor() {
        return locationdbsensor;
    }

    public void setLocationdbsensor(LocationDBSensor locationdbsensor) {
        this.locationdbsensor = locationdbsensor;
    }

    public RouterSensor getRoutersensor() {
        return routersensor;
    }

    public void setRoutersensor(RouterSensor routersensor) {
        this.routersensor = routersensor;
    }

    public Raumerkennung getRaumerkennung() {
        return raumerkennung;
    }

    public void setRaumerkennung(Raumerkennung raumerkennung) {
        this.raumerkennung = raumerkennung;
    }

    public GPSSensor getGpssensor() {
        return gpssensor;
    }

    public void setGpssensor(GPSSensor gpssensor) {
        this.gpssensor = gpssensor;
    }
}
// Use IDE to generate toString and equals methods


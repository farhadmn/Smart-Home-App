package sensomod.generated;

public class Heizungssteuerung extends ComputedSensor {

    public Heizungssteuerung(AussenthermometerSensor aussenthermometersensor, RaumthermometerSensor raumthermometersensor, Benutzerlokalisierung benutzerlokalisierung) {
        multiple = false;
        this.name = "Heizungssteuerung";
        this.aussenthermometersensor = aussenthermometersensor;
        this.raumthermometersensor = raumthermometersensor;
        this.benutzerlokalisierung = benutzerlokalisierung;
    }

    private AussenthermometerSensor aussenthermometersensor;

    private RaumthermometerSensor raumthermometersensor;

    private Benutzerlokalisierung benutzerlokalisierung;

    /*TODO: create logic to return the Stufe*/
    public Stufe output() {
    }

    public void decisionLogic() {
        if( UserLocation == "unkown" || UserLocation== "at work" && TemperaturValue=> 15){

		Stufe= 0; }

if( UserLocation == "unkown" || UserLocation== "at work" && TemperaturValue=< 5 ){

	Stufe= 1; }
	
	if( UserLocation == "at home" ){
	
	while (RaumtemperaturValue  = requestedTemperatur)
	Stufe +
	};
    }

    public AussenthermometerSensor getAussenthermometersensor() {
        return aussenthermometersensor;
    }

    public void setAussenthermometersensor(AussenthermometerSensor aussenthermometersensor) {
        this.aussenthermometersensor = aussenthermometersensor;
    }

    public RaumthermometerSensor getRaumthermometersensor() {
        return raumthermometersensor;
    }

    public void setRaumthermometersensor(RaumthermometerSensor raumthermometersensor) {
        this.raumthermometersensor = raumthermometersensor;
    }

    public Benutzerlokalisierung getBenutzerlokalisierung() {
        return benutzerlokalisierung;
    }

    public void setBenutzerlokalisierung(Benutzerlokalisierung benutzerlokalisierung) {
        this.benutzerlokalisierung = benutzerlokalisierung;
    }
}
// Use IDE to generate toString and equals methods


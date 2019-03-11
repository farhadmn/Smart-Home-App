package sensomod.generated;

public class Bekleidungsvorschlag extends ComputedSensor {

    public Bekleidungsvorschlag(WettervorhersageSensor wettervorhersagesensor, AussenthermometerSensor aussenthermometersensor, Appointment appointment) {
        multiple = false;
        this.name = "Bekleidungsvorschlag";
        this.wettervorhersagesensor = wettervorhersagesensor;
        this.aussenthermometersensor = aussenthermometersensor;
        this.appointment = appointment;
    }

    private WettervorhersageSensor wettervorhersagesensor;

    private AussenthermometerSensor aussenthermometersensor;

    private Appointment appointment;

    /*TODO: create logic to return the Vorschlag*/
    public Vorschlag output() {
    }

    public void decisionLogic() {
         if ( WetterValueAPI== "rain") {

	Vorschlag= "Regenbekleidung";
}
if (WetterValueAPI =="snow") {

	Vorschlag= "Winterbekleidung"; }
	
if (WetterValueAPI= "clear"  && Temperatur <= 5.0) {
	
	Vorschlag=" Herbstlook"; }
	
if (WetterValueAPI= "clear"  && TemperaturValue  >= 25.0) {

	Vorschlag=" Sommerbekleidung";
	};
    }

    public WettervorhersageSensor getWettervorhersagesensor() {
        return wettervorhersagesensor;
    }

    public void setWettervorhersagesensor(WettervorhersageSensor wettervorhersagesensor) {
        this.wettervorhersagesensor = wettervorhersagesensor;
    }

    public AussenthermometerSensor getAussenthermometersensor() {
        return aussenthermometersensor;
    }

    public void setAussenthermometersensor(AussenthermometerSensor aussenthermometersensor) {
        this.aussenthermometersensor = aussenthermometersensor;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
// Use IDE to generate toString and equals methods


package sensomod.generated;

public class AlarmanlageSteuerung extends ComputedSensor {

    public AlarmanlageSteuerung(Benutzerlokalisierung benutzerlokalisierung) {
        multiple = false;
        this.name = "AlarmanlageSteuerung";
        this.benutzerlokalisierung = benutzerlokalisierung;
    }

    private Benutzerlokalisierung benutzerlokalisierung;

    /*TODO: create logic to return the status*/
    public status output() {
    }

    public void decisionLogic() {
        if( UserLocation == "unkown" || UserLocation== "at work" ){

		status= 1; }


	
	if( UserLocation == "at home" ){
	
status= 0; };
    }

    public Benutzerlokalisierung getBenutzerlokalisierung() {
        return benutzerlokalisierung;
    }

    public void setBenutzerlokalisierung(Benutzerlokalisierung benutzerlokalisierung) {
        this.benutzerlokalisierung = benutzerlokalisierung;
    }
}
// Use IDE to generate toString and equals methods


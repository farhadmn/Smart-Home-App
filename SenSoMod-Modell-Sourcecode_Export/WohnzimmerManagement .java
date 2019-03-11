package sensomod.generated;

public class WohnzimmerManagement  extends Context {

    public WohnzimmerManagement (TV tv, Heizungssteuerung heizungssteuerung, AlarmanlageSteuerung alarmanlagesteuerung, Benutzerlokalisierung benutzerlokalisierung) {
        this.name = "WohnzimmerManagement ";
        this.tv = tv;
        this.heizungssteuerung = heizungssteuerung;
        this.alarmanlagesteuerung = alarmanlagesteuerung;
        this.benutzerlokalisierung = benutzerlokalisierung;
    }

    private TV tv;

    private Heizungssteuerung heizungssteuerung;

    private AlarmanlageSteuerung alarmanlagesteuerung;

    private Benutzerlokalisierung benutzerlokalisierung;

    public void decisionLogic() {
        if( UserLocation == "unkown" || UserLocation== "at work" ){

if(AlarmanlageSteuerung.staus==0){
	
	WohnzimmerState="AlarmanlageAus"
	
	} else {
	
	WohnzimmerState="AlarmanlageAn"
	}
	
if(TV.staus==0){
	
	WohnzimmerState="TVAus"
	
	} else {
	
	WohnzimmerState="TVAn"
	}

	if (Heizungssteuerung.status=="0") {
	
 	WohnzimmerState="HeizungAus" }
	
	else {
	
	
WohnzimmerState="HeizungAn" }

}

if ( UserLocation=="at home" && detectedRaum="unkwon" ) {
 
 SchlafzimmerManagementOut="unkwonZimmer; 
 }
 else { SchlafzimmerManagementOut= unkwonLocation };
    }

    /*TODO: create logic to return the WohnzimmerState*/
    public WohnzimmerState output() {
    }

    public TV getTv() {
        return tv;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }

    public Heizungssteuerung getHeizungssteuerung() {
        return heizungssteuerung;
    }

    public void setHeizungssteuerung(Heizungssteuerung heizungssteuerung) {
        this.heizungssteuerung = heizungssteuerung;
    }

    public AlarmanlageSteuerung getAlarmanlagesteuerung() {
        return alarmanlagesteuerung;
    }

    public void setAlarmanlagesteuerung(AlarmanlageSteuerung alarmanlagesteuerung) {
        this.alarmanlagesteuerung = alarmanlagesteuerung;
    }

    public Benutzerlokalisierung getBenutzerlokalisierung() {
        return benutzerlokalisierung;
    }

    public void setBenutzerlokalisierung(Benutzerlokalisierung benutzerlokalisierung) {
        this.benutzerlokalisierung = benutzerlokalisierung;
    }
}
// Use IDE to generate toString and equals methods


package sensomod.generated;

public class SchlafzimmerManagement  extends Context {

    public SchlafzimmerManagement (LichtStatus lichtstatus, AlarmanlageSteuerung alarmanlagesteuerung, JalousienSteuerung jalousiensteuerung, Benutzerlokalisierung benutzerlokalisierung, Bekleidungsvorschlag bekleidungsvorschlag) {
        this.name = "SchlafzimmerManagement ";
        this.lichtstatus = lichtstatus;
        this.alarmanlagesteuerung = alarmanlagesteuerung;
        this.jalousiensteuerung = jalousiensteuerung;
        this.benutzerlokalisierung = benutzerlokalisierung;
        this.bekleidungsvorschlag = bekleidungsvorschlag;
    }

    private LichtStatus lichtstatus;

    private AlarmanlageSteuerung alarmanlagesteuerung;

    private JalousienSteuerung jalousiensteuerung;

    private Benutzerlokalisierung benutzerlokalisierung;

    private Bekleidungsvorschlag bekleidungsvorschlag;

    public void decisionLogic() {
         if ( UserLocation=="at home" && detectedRaum="Schlafzimmer" ) {
 
	if(Licht.status==0){
	SchlafzimmerManagementOut="LichtAus"
	
	} else {
		SchlafzimmerManagementOut="LichtAn"
		}
	
	if(AlarmanlageSteuerung.staus==0){
	
	SchlafzimmerManagementOut="AlarmanlageAus"
	
	} else {
	
	SchlafzimmerManagementOut="AlarmanlageAn"
	}
	
	if (Bekleidungsvorschlag=="Regenbekleidung") {
	
 	SchlafzimmerManagementOut="Regenbekleidung" }

}
 if ( UserLocation=="at home" && detectedRaum="unkwon" ) {
 
 SchlafzimmerManagementOut="unkwonZimmer; 
 }
 else { SchlafzimmerManagementOut= unkwonLocation }
 ;
    }

    /*TODO: create logic to return the SchlafzimmerManagementState*/
    public SchlafzimmerManagementState output() {
    }

    public LichtStatus getLichtstatus() {
        return lichtstatus;
    }

    public void setLichtstatus(LichtStatus lichtstatus) {
        this.lichtstatus = lichtstatus;
    }

    public AlarmanlageSteuerung getAlarmanlagesteuerung() {
        return alarmanlagesteuerung;
    }

    public void setAlarmanlagesteuerung(AlarmanlageSteuerung alarmanlagesteuerung) {
        this.alarmanlagesteuerung = alarmanlagesteuerung;
    }

    public JalousienSteuerung getJalousiensteuerung() {
        return jalousiensteuerung;
    }

    public void setJalousiensteuerung(JalousienSteuerung jalousiensteuerung) {
        this.jalousiensteuerung = jalousiensteuerung;
    }

    public Benutzerlokalisierung getBenutzerlokalisierung() {
        return benutzerlokalisierung;
    }

    public void setBenutzerlokalisierung(Benutzerlokalisierung benutzerlokalisierung) {
        this.benutzerlokalisierung = benutzerlokalisierung;
    }

    public Bekleidungsvorschlag getBekleidungsvorschlag() {
        return bekleidungsvorschlag;
    }

    public void setBekleidungsvorschlag(Bekleidungsvorschlag bekleidungsvorschlag) {
        this.bekleidungsvorschlag = bekleidungsvorschlag;
    }
}
// Use IDE to generate toString and equals methods


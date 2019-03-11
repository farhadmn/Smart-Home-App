package sensomod.generated;

public class Arbeitszeiterfassung extends ComputedSensor {

    public Arbeitszeiterfassung(Benutzerlokalisierung benutzerlokalisierung) {
        multiple = false;
        this.name = "Arbeitszeiterfassung";
        this.benutzerlokalisierung = benutzerlokalisierung;
    }

    private Benutzerlokalisierung benutzerlokalisierung;

    /*TODO: create logic to return the DBEintrag*/
    public DBEintrag output() {
    }

    public void decisionLogic() {
        if(detectedRaum==Office) {DBEintrag.Beginn= new Date();}
else {

DBEintrag.Ende= new Date();}
;
    }

    public Benutzerlokalisierung getBenutzerlokalisierung() {
        return benutzerlokalisierung;
    }

    public void setBenutzerlokalisierung(Benutzerlokalisierung benutzerlokalisierung) {
        this.benutzerlokalisierung = benutzerlokalisierung;
    }
}
// Use IDE to generate toString and equals methods


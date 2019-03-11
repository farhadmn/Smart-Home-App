package sensomod.generated;

public class AlarmanlageStatusAnzeigen extends ContextDescription {

    public AlarmanlageStatusAnzeigen(SchlafzimmerManagement schlafzimmermanagement, WohnzimmerManagement wohnzimmermanagement) {
        this.name = "AlarmanlageStatusAnzeigen";
        this.schlafzimmermanagement = schlafzimmermanagement;
        this.wohnzimmermanagement = wohnzimmermanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    private WohnzimmerManagement wohnzimmermanagement;

    public void contextExpression() {
        SchlafzimmerState=AlarmanlageStatus || WohnzimmerState=AlarmanlageStatus;
    }

    public SchlafzimmerManagement getSchlafzimmermanagement() {
        return schlafzimmermanagement;
    }

    public void setSchlafzimmermanagement(SchlafzimmerManagement schlafzimmermanagement) {
        this.schlafzimmermanagement = schlafzimmermanagement;
    }

    public WohnzimmerManagement getWohnzimmermanagement() {
        return wohnzimmermanagement;
    }

    public void setWohnzimmermanagement(WohnzimmerManagement wohnzimmermanagement) {
        this.wohnzimmermanagement = wohnzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


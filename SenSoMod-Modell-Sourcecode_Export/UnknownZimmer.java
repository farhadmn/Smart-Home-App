package sensomod.generated;

public class UnknownZimmer extends ContextDescription {

    public UnknownZimmer(SchlafzimmerManagement schlafzimmermanagement, WohnzimmerManagement wohnzimmermanagement, OfficeManagement officemanagement) {
        this.name = "UnknownZimmer";
        this.schlafzimmermanagement = schlafzimmermanagement;
        this.wohnzimmermanagement = wohnzimmermanagement;
        this.officemanagement = officemanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    private WohnzimmerManagement wohnzimmermanagement;

    private OfficeManagement officemanagement;

    public void contextExpression() {
        detectedRaum= "unkown";
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

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


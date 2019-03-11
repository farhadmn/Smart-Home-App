package sensomod.generated;

public class DruckerStatusAnzeigen extends ContextDescription {

    public DruckerStatusAnzeigen(OfficeManagement officemanagement) {
        this.name = "DruckerStatusAnzeigen";
        this.officemanagement = officemanagement;
    }

    private OfficeManagement officemanagement;

    public void contextExpression() {
        OfficeState= DruckerStatus;
    }

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


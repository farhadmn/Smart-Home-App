package sensomod.generated;

public class AtWork extends ContextDescription {

    public AtWork(OfficeManagement officemanagement) {
        this.name = "AtWork";
        this.officemanagement = officemanagement;
    }

    private OfficeManagement officemanagement;

    public void contextExpression() {
        UserLocation="at work";
    }

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


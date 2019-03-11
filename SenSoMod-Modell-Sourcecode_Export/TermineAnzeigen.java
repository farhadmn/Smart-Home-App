package sensomod.generated;

public class TermineAnzeigen extends ContextDescription {

    public TermineAnzeigen(OfficeManagement officemanagement) {
        this.name = "TermineAnzeigen";
        this.officemanagement = officemanagement;
    }

    private OfficeManagement officemanagement;

    public void contextExpression() {
        OfficeState=appointmentState;
    }

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


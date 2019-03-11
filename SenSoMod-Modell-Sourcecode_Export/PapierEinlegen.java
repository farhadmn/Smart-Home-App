package sensomod.generated;

public class PapierEinlegen extends ContextDescription {

    public PapierEinlegen(OfficeManagement officemanagement) {
        this.name = "PapierEinlegen";
        this.officemanagement = officemanagement;
    }

    private OfficeManagement officemanagement;

    public void contextExpression() {
        OfficeState= PapierEinlegen;
    }

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


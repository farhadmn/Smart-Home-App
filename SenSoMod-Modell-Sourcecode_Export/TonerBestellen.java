package sensomod.generated;

public class TonerBestellen extends ContextDescription {

    public TonerBestellen(OfficeManagement officemanagement) {
        this.name = "TonerBestellen";
        this.officemanagement = officemanagement;
    }

    private OfficeManagement officemanagement;

    public void contextExpression() {
        OfficeState= TonerBestellen;
    }

    public OfficeManagement getOfficemanagement() {
        return officemanagement;
    }

    public void setOfficemanagement(OfficeManagement officemanagement) {
        this.officemanagement = officemanagement;
    }
}
// Use IDE to generate toString and equals methods


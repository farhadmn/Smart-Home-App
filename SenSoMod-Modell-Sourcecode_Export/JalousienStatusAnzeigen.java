package sensomod.generated;

public class JalousienStatusAnzeigen extends ContextDescription {

    public JalousienStatusAnzeigen(SchlafzimmerManagement schlafzimmermanagement) {
        this.name = "JalousienStatusAnzeigen";
        this.schlafzimmermanagement = schlafzimmermanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    public void contextExpression() {
        SchlafzimmerState=JalousienStatus;
    }

    public SchlafzimmerManagement getSchlafzimmermanagement() {
        return schlafzimmermanagement;
    }

    public void setSchlafzimmermanagement(SchlafzimmerManagement schlafzimmermanagement) {
        this.schlafzimmermanagement = schlafzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


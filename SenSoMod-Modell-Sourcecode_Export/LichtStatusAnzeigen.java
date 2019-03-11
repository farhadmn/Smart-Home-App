package sensomod.generated;

public class LichtStatusAnzeigen extends ContextDescription {

    public LichtStatusAnzeigen(SchlafzimmerManagement schlafzimmermanagement) {
        this.name = "LichtStatusAnzeigen";
        this.schlafzimmermanagement = schlafzimmermanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    public void contextExpression() {
        SchlafzimmerState=LichtStatus;
    }

    public SchlafzimmerManagement getSchlafzimmermanagement() {
        return schlafzimmermanagement;
    }

    public void setSchlafzimmermanagement(SchlafzimmerManagement schlafzimmermanagement) {
        this.schlafzimmermanagement = schlafzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


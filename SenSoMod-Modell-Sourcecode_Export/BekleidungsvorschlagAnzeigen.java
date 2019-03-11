package sensomod.generated;

public class BekleidungsvorschlagAnzeigen extends ContextDescription {

    public BekleidungsvorschlagAnzeigen(SchlafzimmerManagement schlafzimmermanagement) {
        this.name = "BekleidungsvorschlagAnzeigen";
        this.schlafzimmermanagement = schlafzimmermanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    public void contextExpression() {
        SchlafzimmerState=Vorschlag;
    }

    public SchlafzimmerManagement getSchlafzimmermanagement() {
        return schlafzimmermanagement;
    }

    public void setSchlafzimmermanagement(SchlafzimmerManagement schlafzimmermanagement) {
        this.schlafzimmermanagement = schlafzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


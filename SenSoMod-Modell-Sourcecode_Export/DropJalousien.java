package sensomod.generated;

public class DropJalousien extends ContextDescription {

    public DropJalousien(SchlafzimmerManagement schlafzimmermanagement) {
        this.name = "DropJalousien";
        this.schlafzimmermanagement = schlafzimmermanagement;
    }

    private SchlafzimmerManagement schlafzimmermanagement;

    public void contextExpression() {
        SchlafzimmerState=DropJalousien;
    }

    public SchlafzimmerManagement getSchlafzimmermanagement() {
        return schlafzimmermanagement;
    }

    public void setSchlafzimmermanagement(SchlafzimmerManagement schlafzimmermanagement) {
        this.schlafzimmermanagement = schlafzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


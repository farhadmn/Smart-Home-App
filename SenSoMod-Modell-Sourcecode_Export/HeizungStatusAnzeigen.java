package sensomod.generated;

public class HeizungStatusAnzeigen extends ContextDescription {

    public HeizungStatusAnzeigen(WohnzimmerManagement wohnzimmermanagement) {
        this.name = "HeizungStatusAnzeigen";
        this.wohnzimmermanagement = wohnzimmermanagement;
    }

    private WohnzimmerManagement wohnzimmermanagement;

    public void contextExpression() {
        WohnzimmerState= HeizungStatus;
    }

    public WohnzimmerManagement getWohnzimmermanagement() {
        return wohnzimmermanagement;
    }

    public void setWohnzimmermanagement(WohnzimmerManagement wohnzimmermanagement) {
        this.wohnzimmermanagement = wohnzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


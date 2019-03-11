package sensomod.generated;

public class TVStatusAnzeigen extends ContextDescription {

    public TVStatusAnzeigen(WohnzimmerManagement wohnzimmermanagement) {
        this.name = "TVStatusAnzeigen";
        this.wohnzimmermanagement = wohnzimmermanagement;
    }

    private WohnzimmerManagement wohnzimmermanagement;

    public void contextExpression() {
        WohnzimmerState=TVStatus;
    }

    public WohnzimmerManagement getWohnzimmermanagement() {
        return wohnzimmermanagement;
    }

    public void setWohnzimmermanagement(WohnzimmerManagement wohnzimmermanagement) {
        this.wohnzimmermanagement = wohnzimmermanagement;
    }
}
// Use IDE to generate toString and equals methods


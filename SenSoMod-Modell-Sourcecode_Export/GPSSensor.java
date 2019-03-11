package sensomod.generated;

public class GPSSensor extends PhysicalSensor {

    public GPSSensor(double koordinaten) {
        multiple = false;
        this.name = "GPSSensor";
        this.koordinaten = koordinaten;
    }

    private double Koordinaten;

    /*TODO: create logic to return the Koordinaten*/
    public double output() {
    // TODO
    }

    public double getKoordinaten() {
        return Koordinaten;
    }

    public void setKoordinaten(double Koordinaten) {
        this.Koordinaten = Koordinaten;
    }
}
// Use IDE to generate toString and equals methods


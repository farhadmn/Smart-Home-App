package sensomod.generated;

public class JalousienSteuerung extends ComputedSensor {

    public JalousienSteuerung(WettervorhersageSensor wettervorhersagesensor, JalousienSensor jalousiensensor) {
        multiple = false;
        this.name = "JalousienSteuerung";
        this.wettervorhersagesensor = wettervorhersagesensor;
        this.jalousiensensor = jalousiensensor;
    }

    private WettervorhersageSensor wettervorhersagesensor;

    private JalousienSensor jalousiensensor;

    /*TODO: create logic to return the Steuerungsmethode*/
    public Steuerungsmethode output() {
    }

    public void decisionLogic() {
        if (Wetter="warnung" || Wetter="windig" && Jalousien.Status==3 ||  JalousienStatus==2)
Steuerungsmethode=DropJalousien;
};
    }

    public WettervorhersageSensor getWettervorhersagesensor() {
        return wettervorhersagesensor;
    }

    public void setWettervorhersagesensor(WettervorhersageSensor wettervorhersagesensor) {
        this.wettervorhersagesensor = wettervorhersagesensor;
    }

    public JalousienSensor getJalousiensensor() {
        return jalousiensensor;
    }

    public void setJalousiensensor(JalousienSensor jalousiensensor) {
        this.jalousiensensor = jalousiensensor;
    }
}
// Use IDE to generate toString and equals methods


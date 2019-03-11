package sensomod.generated;

public class JalousienSensor extends PhysicalSensor {

    public JalousienSensor(int jalousienstatus) {
        multiple = false;
        this.name = "JalousienSensor";
        this.jalousienstatus = jalousienstatus;
    }

    private int JalousienStatus;

    /*TODO: create logic to return the JalousienStatus*/
    public int output() {
    }

    public int getJalousienStatus() {
        return JalousienStatus;
    }

    public void setJalousienStatus(int JalousienStatus) {
        this.JalousienStatus = JalousienStatus;
    }
}
// Use IDE to generate toString and equals methods


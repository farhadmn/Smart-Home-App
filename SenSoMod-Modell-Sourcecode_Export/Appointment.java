package sensomod.generated;

public class Appointment extends ComputedSensor {

    public Appointment(Kalender kalender) {
        multiple = false;
        this.name = "Appointment";
        this.kalender = kalender;
    }

    private Kalender kalender;

    /*TODO: create logic to return the appointmentState*/
    public appointmentState output() {
    }

    public void decisionLogic() {
        if(KalenderEntry.Date== today) {
appointment= "Metting" }
else  {
appointment="none"};
    }

    public Kalender getKalender() {
        return kalender;
    }

    public void setKalender(Kalender kalender) {
        this.kalender = kalender;
    }
}
// Use IDE to generate toString and equals methods


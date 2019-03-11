package sensomod.generated;

public class OfficeManagement  extends Context {

    public OfficeManagement (DruckerSensor druckersensor, Appointment appointment, Arbeitszeiterfassung arbeitszeiterfassung) {
        this.name = "OfficeManagement ";
        this.druckersensor = druckersensor;
        this.appointment = appointment;
        this.arbeitszeiterfassung = arbeitszeiterfassung;
    }

    private DruckerSensor druckersensor;

    private Appointment appointment;

    private Arbeitszeiterfassung arbeitszeiterfassung;

    public void decisionLogic() {
        if( UserLocation == "at work" || detectedRaum== "Office" ){
if(Drucker.Status == "papierleer"){

OfficeState= "PapierEinlegen";
}

if(Drucker.Status == "tonerleer"){

OfficeState= TonerBestellen;
}
else {

OfficeState= "DruckerOK";

}

if(Appointment=="meeting"){

OfficeState= "Meeting"

}
else {

OfficeState= "Keine Termine"

}

if(Arbeitszeiterfassung=="Beginn"){

OfficeState="BeginnErfasst";

}
else {

OfficeState="EndeErfasst";
}
}

if( UserLocation == "at work" || detectedRaum== "unkown" ){

OfficeState="unkownZimmer";
}

if(UserLocation == "unkown" ){

OfficeState="unkownLocation";
}
;
    }

    /*TODO: create logic to return the OfficeState*/
    public OfficeState output() {
    }

    public DruckerSensor getDruckersensor() {
        return druckersensor;
    }

    public void setDruckersensor(DruckerSensor druckersensor) {
        this.druckersensor = druckersensor;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Arbeitszeiterfassung getArbeitszeiterfassung() {
        return arbeitszeiterfassung;
    }

    public void setArbeitszeiterfassung(Arbeitszeiterfassung arbeitszeiterfassung) {
        this.arbeitszeiterfassung = arbeitszeiterfassung;
    }
}
// Use IDE to generate toString and equals methods


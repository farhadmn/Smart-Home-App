package sensomod.generated;

public class LocationDBSensor extends VirtualSensor {

    public LocationDBSensor(String dbdata) {
        multiple = false;
        this.name = "LocationDBSensor";
        this.dbdata = dbdata;
    }

    private String DBData;

    /*TODO: create logic to return the DBData*/
    public String output() {
    // TODO
    }

    public String getDBData() {
        return DBData;
    }

    public void setDBData(String DBData) {
        this.DBData = DBData;
    }
}
// Use IDE to generate toString and equals methods


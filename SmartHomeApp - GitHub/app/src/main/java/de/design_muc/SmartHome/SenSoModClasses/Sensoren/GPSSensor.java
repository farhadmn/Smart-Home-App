package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

public class GPSSensor extends PhysicalSensor {

    private double lat, lng;

    public GPSSensor(double lat, double lng) {

        this.name = "GPSSensor";
        this.lat= lat;
        this.lng=lng;

    }



    /*TODO: create logic to return the Koordinaten*/



    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

// Use IDE to generate toString and equals methods


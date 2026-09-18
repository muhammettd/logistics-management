package com.muhammettd.application.dto;

public class LocationEvent {

    private String driverId;

    // Location data
    private double latitude;
    private double longitude;

    private double speed;
    private double heading;

    private long timestamp;

    public String getDriverId() {
        return driverId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHeading() {
        return heading;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

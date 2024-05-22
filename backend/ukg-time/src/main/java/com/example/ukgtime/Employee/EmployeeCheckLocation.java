package com.example.ukgtime.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeCheckLocation {

    @JsonProperty("employeeId")
    private long employeeId;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    public EmployeeCheckLocation(long employeeId, double latitude, double longitude) {
        this.employeeId = employeeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EmployeeCheckLocation() {}

    public long getEmployeeId() {
        return employeeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
            return "Employee Check Location [employeeId: " + employeeId + ", " +
                    "latitude: " + latitude +
                    "longitude: "+ longitude + "]";
        }

}

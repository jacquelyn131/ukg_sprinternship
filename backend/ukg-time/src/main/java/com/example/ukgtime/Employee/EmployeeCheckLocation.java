package com.example.ukgtime.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeCheckLocation {

    @JsonProperty("employeeId")
    private long employeeId;

    @JsonProperty("latitude")
    private long latitude;

    @JsonProperty("longitude")
    private long longitude;

    public EmployeeCheckLocation(long employeeId, long latitude, long longitude) {
        this.employeeId = employeeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EmployeeCheckLocation() {}

    public long getEmployeeId() {
        return employeeId;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
            return "Employee Check Location [employeeId: " + employeeId + ", " +
                    "latitude: " + latitude +
                    "longitude: "+ longitude + "]";
        }

}

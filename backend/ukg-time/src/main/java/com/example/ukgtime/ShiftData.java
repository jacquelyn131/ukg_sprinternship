package com.example.ukgtime;

// a class for holding data about an employee's shift
public class ShiftData {
    private String startTime; // string military format: 14:22, no seconds
    private String endTime;
    private String date;
    private float shiftDuration; // decimal number: 2 decimal places
    private long employeeId;

    public ShiftData(String startTime, String endTime, String date, float shiftDuration, long employeeId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.shiftDuration = shiftDuration;
        this.employeeId = employeeId;
    }

    public ShiftData() {

    }

    @Override
    public String toString() {
        return String.format("ShiftData[startTime: '%s', endTime: '%s', date: '%s', shiftDuration: %f, " +
                "employeeId: %d]", startTime, endTime, date, shiftDuration, employeeId);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(float shiftDuration) {
        this.shiftDuration = shiftDuration;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}

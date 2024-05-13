package com.example.ukgtime;

public class ClockPunch {
    private String dateTime; // "YYYY-MM-DD hh:mm:ss"
    private long punchId;
    private long employeeId;
    private long officeId;
    private String type;
    private String valid; // either true or false or null
    private String comments;

    public ClockPunch(String dateTime, long punchId, long employeeId, long officeId, String type, String valid, String comments) {
        this.dateTime = dateTime;
        this.punchId = punchId;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.type = type;
        this.valid = valid;
        this.comments = comments;
    }

    public ClockPunch(String dateTime, long employeeId, long officeId, String type, String valid, String comments) {
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.type = type;
        this.valid = valid;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return String.format("ClockPunch[dateTime: '%s', employeeId: %d, officeId: %d, " +
                "type: %s, valid: %s, comments: %s]", dateTime, employeeId, officeId,
                type, valid, comments);
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getPunchId() {
        return punchId;
    }

    public void setPunchId(long punchId) {
        this.punchId = punchId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

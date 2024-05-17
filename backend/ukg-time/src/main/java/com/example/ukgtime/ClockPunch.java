package com.example.ukgtime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClockPunch {
    @JsonProperty("dateTime")
    private String dateTime; // "YYYY-MM-DD hh:mm:ss"

    @JsonProperty("punchId")
    private long punchId;

    @JsonProperty("employeeId")
    private long employeeId;

    @JsonProperty("officeId")
    private long officeId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("valid")
    private Boolean valid; // either true or false or null

    @JsonProperty("comments")
    private String comments;

    public ClockPunch(String dateTime, long punchId, long employeeId, long officeId, String type, Boolean valid, String comments) {
        this.dateTime = dateTime;
        this.punchId = punchId;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.type = type;
        this.valid = valid;
        this.comments = comments;
    }

    public ClockPunch(String dateTime, long employeeId, long officeId, String type, Boolean valid, String comments) {
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.officeId = officeId;
        this.type = type;
        this.valid = valid;
        this.comments = comments;
    }

    public ClockPunch() {

    }

    @Override
    public String toString() {
        return String.format("ClockPunch[dateTime: '%s', employeeId: %d, officeId: %d, " +
                "type: %s, valid: %s, comments: %s, punchId: %s]", dateTime, employeeId, officeId,
                type, valid, comments, punchId);
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

package com.example.ukgtime;

import java.util.Arrays;

public class Employee {
    private long employeeId;
    private String ssn;
    private String firstName, lastName;
    private int company_id;
    private String dob;
    private byte[] profileImage;

    public Employee(long employeeId, String ssn, String firstName, String lastName, int company_id, String dob, byte[] profileImage) {
        this.employeeId = employeeId;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company_id = company_id;
        this.dob = dob;
        this.profileImage = profileImage;
    }

    public Employee(long employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format();
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}

package com.example.ukgtime;

import java.util.Arrays;

public class Employee {
    private long employeeId;
    private String ssn;
    private String firstName, lastName;
    private int companyId;
    private String dob;
    private byte[] profileImage;

    public Employee(long employeeId, String ssn, String firstName, String lastName, int companyId, String dob, byte[] profileImage) {
        this.employeeId = employeeId;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
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
        return String.format("Employee[id: %d, ssn: '%s', firstName: '%s', lastName: '%s'" +
            "companyId: %d, dob: '%s', profileImage: '%s']", employeeId, ssn, firstName, lastName,
                companyId, dob, profileImage);
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

    public int getcompanyId() {
        return companyId;
    }

    public void setcompanyId(int companyId) {
        this.companyId = companyId;
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

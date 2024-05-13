package com.example.ukgtime.Employee;

public class Employee {
    private long employeeId;
    private String ssn;
    private String firstName, lastName;
    private String dob;
    private String email;
    private long managerId;

    public Employee(long employeeId, String ssn, String firstName, String lastName, String dob, String email, long managerId) {
        this.employeeId = employeeId;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.managerId = managerId;
    }

    public Employee(long employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Employee[id: %d, ssn: '%s', firstName: '%s', lastName: '%s', " +
            "dob: '%s', email: '%s', managerId: %d]", employeeId, ssn, firstName, lastName,
                dob, email, managerId);
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}

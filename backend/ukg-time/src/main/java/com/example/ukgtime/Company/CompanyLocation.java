package com.example.ukgtime.Company;

import java.util.Arrays;

public class CompanyLocation {
    private long companyOfficeId;
    private double[] location; // longitude/latitude stored in a double array
    private double radius; // raduis in degrees of longitude


    public CompanyLocation(long companyOfficeId, double[] location, double radius) {
        this.companyOfficeId = companyOfficeId;
        this.location = location;
        this.radius = radius;
    }

    public CompanyLocation(double[] location, double radius) {
        this.location = location;
        this.radius = radius;
    }

    public CompanyLocation() {

    }

    @Override
    public String toString() {
        return String.format("CompanyLocation[companyOfficeId: %d, location: '%s', radius: %f]",
                companyOfficeId, location== null ? "":location.toString(), radius);
    }

    public long getCompanyOfficeId() {
        return companyOfficeId;
    }

    public void setCompanyOfficeId(long companyOfficeId) {
        this.companyOfficeId = companyOfficeId;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

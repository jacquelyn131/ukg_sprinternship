package com.example.ukgtime.Company;

public class Company {
    private long companyId;
    private String companyName;
    private long headquartersId;

    @Override
    public String toString() {
        return String.format("Company[companyId: %d, companyName: '%s', " +
                "headquartersId: %d]", companyId, companyName, headquartersId);
    }

    public Company() {
    }

    public Company(long companyId, String companyName, long headquartersId) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.headquartersId = headquartersId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getHeadquartersId() {
        return headquartersId;
    }

    public void setHeadquartersId(long headquartersId) {
        this.headquartersId = headquartersId;
    }
}

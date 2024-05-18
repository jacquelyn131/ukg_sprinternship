package com.example.ukgtime.Company;

public class CompanyAddress {
    private long companyId;
    private long companyOfficeId;
    private String street;
    private String zip; // five digit zip
    private String country; // three character country code

    public CompanyAddress(long companyId, long companyOfficeId, String street, String zip, String country) {
        this.companyId = companyId;
        this.companyOfficeId = companyOfficeId;
        this.street = street;
        this.zip = zip;
        this.country = country;
    }

    public CompanyAddress(long companyOfficeId, String street, String zip, String country) {
        this.companyOfficeId = companyOfficeId;
        this.street = street;
        this.zip = zip;
        this.country = country;
    }

    public CompanyAddress() {

    }

    @Override
    public String toString() {
        return String.format("CompanyAddress[companyId: $d, companyOfficeId: %d, " +
                "street: '%s', zip: '%s', country: '%s']");
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCompanyOfficeId() {
        return companyOfficeId;
    }

    public void setCompanyOfficeId(long companyOfficeId) {
        this.companyOfficeId = companyOfficeId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

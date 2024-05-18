package com.example.ukgtime;

public class Zip {
    private String city;
    private String state; // two character state code: NY, FL, ...
    private String zip; // five digit zip code

    public Zip(String city, String state, String zip) {
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Zip() {

    }

    @Override
    public String toString() {
        return String.format("Zip[city: '%s', state: '%s', zip: '%s']");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

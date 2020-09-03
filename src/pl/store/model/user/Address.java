package pl.store.model.user;

import java.io.Serializable;

public class Address implements Serializable {

    private String country;
    private String postalCode;
    private String streetName;
    private int houseNumber;
    private int flatNumber;

    public Address(String country, String postalCode, String streetName, int houseNumber, int flatNumber) {
        this.country = country;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return country + " " + postalCode + " " + streetName + " " + houseNumber + "/" + flatNumber;
    }

    public String toCsv() {
        return country + " ; " + postalCode + " ; " + streetName + " ; " + houseNumber + " ; " + flatNumber;
    }
}

package com.ncr.foodyou.models;

/**
 * Created by Samarth on 8/21/15
 */
public class Address {

    private String Address1;
    private String Address2;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private Coordinates Coordinates;

    public Address(String address1, String address2, String city, String state, String zip, String phone, Coordinates coordinates) {
        Address1 = address1;
        Address2 = address2;
        City = city;
        State = state;
        Zip = zip;
        Phone = phone;
        Coordinates = coordinates;
    }

    @Override
    public String toString() {
        String retVal = "";

        retVal += Address1 + "\n";
        retVal += Address2 + "\n";
        retVal += City + ", " + State + Zip + "\n";
        retVal += Phone + "\n";

        return retVal;
    }
}

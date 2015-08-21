package com.ncr.foodyou.models;

/**
 * Created by Samarth on 8/7/15
 */
public class Customer {

    private String Name;
    private Address Address;

    public Customer(String name, Address address) {

        this.Name = name;
        this.Address = address;
    }

    public String getName() {
        return Name;
    }

    public Address getAddress() {
        return Address;
    }
}

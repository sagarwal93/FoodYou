package com.ncr.foodyou.models;

import android.location.Location;

/**
 * Created by Samarth on 9/4/15
 */
public class Driver {

    private String Name;
    private Coordinates Coordinates;

    public Driver(String name) {
        Name = name;
    }

    public void setLocation(Coordinates coordinates) {
        Coordinates = coordinates;
    }

    public String getName() {
        return Name;
    }

}

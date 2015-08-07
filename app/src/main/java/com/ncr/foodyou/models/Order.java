package com.ncr.foodyou.models;

import java.util.List;

/**
 * Created by Samarth on 7/24/15
 */
public class Order {

    private String _id;
    private double distance;
    private Site site;
    private List<MenuItem> items;


    public Order(String _id, double distance, Site site, List<MenuItem> items) {
        this._id = _id;
        this.distance = distance;
        this.site = site;
        this.items = items;
    }

    public String getSiteName() {
        return site.getSiteName();
    }

}

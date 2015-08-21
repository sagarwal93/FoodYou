package com.ncr.foodyou.models;

/**
 * Created by Samarth on 7/24/15
 */
public class MenuItem {

    private String _id;
    private String name;
    private double price;

    public MenuItem(String _id, String name, double price) {
        this._id = _id;
        this.name = name;
        this.price = price;
    }

//    public String getMenuItemJson() {
//        String retJson = "{";
//
//        retJson += ("_id:" + _id + ",");
//        retJson += ("name:" + name + ",");
//        retJson += ("price:" + Double.toString(price));
//
//        retJson += "}";
//        return retJson;
//    }
}

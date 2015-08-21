package com.ncr.foodyou.models;

/**
 * Created by Samarth on 7/24/15
 */
public class Site {

    private String _id;
    private String menu_id;
    private String name;
    private String address;
    private double lat;
    private double lng;

    public Site(String _id, String menu_id, String name, String address, double lat, double lng) {
        this._id = _id;
        this.menu_id = menu_id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getSiteName() {
        return name;
    }
    public String getSiteAddress() { return address; }

//    public String getSiteJson() {
//        String retJson = "{";
//
//        retJson += "_id:" + _id + ",";
//        retJson += "menu_id:" + menu_id + ",";
//        retJson += "name:" + name;
//
//        retJson += "}";
//        return retJson;
//    }
}

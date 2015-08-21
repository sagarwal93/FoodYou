package com.ncr.foodyou.models;

/**
 * Created by Samarth on 7/24/15
 */
public class Site {

    private String id;
    private String Menu_id;
    private String Name;
    private Address Address;

    public Site(String _id, String menu_id, String name, Address address) {
        this.id = _id;
        this.Menu_id = menu_id;
        this.Name = name;
        this.Address = address;
    }

    public String getSiteName() {
        return Name;
    }
    public String getSiteAddress() { return Address.toString(); }

//    public static Site getSiteFromJson(String json) {
//
//
//    }

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

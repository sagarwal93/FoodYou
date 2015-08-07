package com.ncr.foodyou.models;

/**
 * Created by Samarth on 7/24/15
 */
public class Site {

    private String _id;
    private String menu_id;
    private String name;

    public Site(String _id, String menu_id, String name) {
        this._id = _id;
        this.menu_id = menu_id;
        this.name = name;
    }

    public String getSiteName() {
        return name;
    }
}

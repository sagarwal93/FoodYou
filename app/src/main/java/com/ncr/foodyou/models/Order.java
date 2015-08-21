package com.ncr.foodyou.models;

import java.util.List;

/**
 * Created by Samarth on 7/24/15
 */
public class Order {

    public enum OrderState {active, pickedup, delivered, closed}

    private String _id;
    private double distance;
    private Site site;
    private List<MenuItem> items;
    private Customer customer;
    private OrderState state;

    public Order(String _id, double distance, Site site, List<MenuItem> items, Customer customer) {
        this._id = _id;
        this.distance = distance;
        this.site = site;
        this.items = items;
        this.customer = customer;
        this.state = OrderState.active;
    }

    public String getSiteName() {
        return site.getSiteName();
    }

    public String getSiteAddress() {
        return site.getSiteAddress();
    }

    public OrderState getOrderState() {
        return state;
    }

    public void setOrderState(OrderState newState) {
        state = newState;
    }

    public String getStringDistance() {
        return Double.toString(distance);
    }

//    public String getOrderJson() {
//        String retJson = "{";
//
//        retJson += ("_id:" + _id + ",");
//        retJson += ("distance:" + Double.toString(distance) + ",");
//        retJson += ("site:" + site.getSiteJson() + ",");
//
//        for(int i = 0; i < items.size(); i++) {
//            retJson += items.get(i).getMenuItemJson();
//            retJson += (i + 1 == items.size() ? "" : ",");
//        }
//
//        retJson += "}";
//        return retJson;
//    }

}

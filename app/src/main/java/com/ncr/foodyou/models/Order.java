package com.ncr.foodyou.models;

import java.util.List;

/**
 * Created by Samarth on 7/24/15
 */
public class Order {

    public enum OrderState {Pending, Assigned, Pickedup, Delivered}

    private String id;
    private double distance;
    private Site Site;
    private List<MenuItem> MenuItems;
    private Customer Customer;
    private Driver driver;
    private OrderState Status;
    private Reward Reward;

    public Order(String id, double distance, Site site, List<MenuItem> items,
                 Customer customer, OrderState status, Reward reward) {
        this.id = id;
        this.distance = distance;
        Site = site;
        MenuItems = items;
        Customer = customer;
        Reward = reward;
        Status = status;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getOrderId() {
        return id;
    }

    public String getSiteName() {
        return Site.getSiteName();
    }

    public String getSiteAddress() {
        return Site.getSiteAddress();
    }

    public OrderState getOrderState() {
        return Status;
    }

    public void setOrderState(OrderState newState) {
        Status = newState;
    }

    public String getCustomerDetails() {
        String retString = "";

        retString += Customer.getName() + "\n";
        retString += Customer.getAddress() + "\n";

        return retString;
    }

    public void setDistance(String distance) {
        if (distance == null) return;
        this.distance = Double.parseDouble(distance);
    }

    public String getStringDistance() {
        return Double.toString(distance);
    }

    public Reward getReward() {
        return Reward;
    }

//    public String getOrderJson() {
//        String retJson = "{";
//
//        retJson += ("_id:" + _id + ",");
//        retJson += ("distance:" + Double.toString(distance) + ",");
//        retJson += ("Site:" + Site.getSiteJson() + ",");
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

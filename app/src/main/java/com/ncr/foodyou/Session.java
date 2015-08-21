package com.ncr.foodyou;

import com.ncr.foodyou.models.Order;

/**
 * Created by Samarth on 8/7/15.
 */
public class Session {

    public static Order activeOrder;

    public static void setActiveOrder(Order order) {
        activeOrder = order;
    }

    public static String jsonify() {
//        String retJson = "{";
//        retJson += activeOrder.getOrderJson();
//        retJson += "}";
//        return retJson;
        return "";
    }
}

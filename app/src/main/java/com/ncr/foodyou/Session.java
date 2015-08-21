package com.ncr.foodyou;

import com.ncr.foodyou.models.Order;
import com.ncr.foodyou.models.Site;

import org.json.JSONObject;

/**
 * Created by Samarth on 8/7/15.
 */
public class Session {

    public static Order activeOrder;

    public static void setActiveOrder(Order order) {
        activeOrder = order;
    }

    public static void setActiveOrderFromJson (Order order) {
        activeOrder = order;
    }
}

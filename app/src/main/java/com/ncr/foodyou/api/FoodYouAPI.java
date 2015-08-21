package com.ncr.foodyou.api;

import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;
import com.ncr.foodyou.BuildConfig;
import com.ncr.foodyou.Session;
import com.ncr.foodyou.http.AsyncHttpClient;
import com.ncr.foodyou.http.AsyncHttpResponseHandler;
import com.ncr.foodyou.http.AsyncResultHandler;
import com.ncr.foodyou.models.Order;

import org.json.JSONObject;

/**
 * Created by Samarth on 7/24/15.
 */
public class FoodYouAPI {

    private static final String BASE_URL = BuildConfig.api_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getNextOrder(final Location location, final AsyncResultHandler handler) {
        client.get(BASE_URL + "/order/NextPickUp?lat=" + location.getLatitude() + "&lng=" + location.getLongitude(),
                new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Order order = new Gson().fromJson(result.toString(), Order.class);
                    Session.setActiveOrderFromJson(order);
                    handler.onSuccess();
                }
                catch (Exception e) {
                    Log.e("GSON exception", e.getMessage());
                }
            }
        });
    }

}

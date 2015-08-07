package com.ncr.foodyou.api;

import android.location.Location;
import com.ncr.foodyou.BuildConfig;
import com.ncr.foodyou.http.AsyncHttpClient;
import com.ncr.foodyou.http.AsyncHttpResponseHandler;
import com.ncr.foodyou.http.AsyncResultHandler;
import org.json.JSONObject;

/**
 * Created by Samarth on 7/24/15.
 */
public class FoodYouAPI {

    private static final String BASE_URL = BuildConfig.api_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getOrders(final Location location, final AsyncResultHandler handler) {
        client.get(BASE_URL + "/order/" + location.getLatitude() + "/" + location.getLongitude(),
                new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject result) {
                handler.onSuccess(result);
            }
        });
    }

}

package com.ncr.foodyou.http;
import org.json.JSONObject;

/**
 * Created by Samarth on 7/2/15
 */
public interface AsyncHttpResponseHandler {

    void onSuccess(JSONObject obj);
    void onSuccess();

}

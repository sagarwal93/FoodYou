package com.ncr.foodyou.http;

import org.json.JSONObject;

/**
 * Created by Samarth on 7/24/15.
 */
public interface AsyncResultHandler {

    public void onSuccess(Object a);
    public void onFailure();

}

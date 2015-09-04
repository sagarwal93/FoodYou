package com.ncr.foodyou.http;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Samarth on 7/1/15.
 */
public class AsyncHttpClient {

    private DefaultHttpClient client;
    private int timeout;

    public AsyncHttpClient() {

        client = new DefaultHttpClient();
        timeout = 10000;

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, this.timeout);
    }

    public void post(final String url, final JSONObject data, final AsyncHttpResponseHandler handler) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpPost post = new HttpPost(url);
                    //Log.d("String Entity Data", data.toString());
                    StringEntity se = new StringEntity(data.toString(), HTTP.UTF_8);
                    post.setEntity(se);

                    HttpResponse responseHttp = client.execute(post);

                    String responseData = EntityUtils.toString(responseHttp.getEntity());
//                    Log.d("ResponseData", responseData.toString());
//                    Log.d("ResponseHTTP", Integer.toString(responseHttp.getStatusLine().getStatusCode()));

                    if (responseHttp.getStatusLine().getStatusCode() == 200) {
                        JSONObject responseJSON = new JSONObject(responseData);
                        //Log.d("Executing Post", "Success");
                        handler.onSuccess(responseJSON);
                    }
                }
                catch (UnsupportedEncodingException e) {
                    Log.e("Crap", e.getMessage());
                }
                catch (ClientProtocolException e) {
                    Log.e("Crap", e.getMessage());
                }
                catch (IOException e) {
                    Log.e("Crap", e.getMessage());
                }
                catch (JSONException e) {
                    Log.e("Crap", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    public void put(final String url, final AsyncHttpResponseHandler handler) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpPut put = new HttpPut(url);
                    HttpResponse responseHttp = client.execute(put);

                    if (responseHttp.getStatusLine().getStatusCode() == 204) {
                        return null;
                    }
                }
                catch (Exception e) {
                    Log.e("Put Exception", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                handler.onSuccess();
            }
        }.execute();
    }

    public void put(final String url, final String data, final AsyncHttpResponseHandler handler) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    HttpPut put = new HttpPut(url);
                    StringEntity se = new StringEntity(data.toString(), HTTP.UTF_8);
                    put.setEntity(se);

                    HttpResponse responseHttp = client.execute(put);
                    if (responseHttp.getStatusLine().getStatusCode() == 204) {
                        return true;
                    }
                }
                catch (Exception e) {
                    Log.e("Put Exception", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                handler.onSuccess();
            }
        }.execute();
    }

    public void get(final String url, final AsyncHttpResponseHandler handler) {

        new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected JSONObject doInBackground(Void... params) {
                try {
                    HttpGet get = new HttpGet(url);
                    HttpResponse responseHttp = client.execute(get);
                    String responseData = EntityUtils.toString(responseHttp.getEntity());
                    JSONObject responseJSON = new JSONObject(responseData);

                    if (responseHttp.getStatusLine().getStatusCode() == 200)
                        return responseJSON;
                }
                catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject result) {
                super.onPostExecute(result);
                if(result != null){
                    handler.onSuccess(result);
                }
            }
        }.execute();
    }

    public void setConnectTimeout(int value) {
        this.timeout = value < 1000?10000:value;
        HttpParams httpParams = this.client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, this.timeout);
    }

}

package com.ncr.foodyou;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Samarth on 9/4/15
 */
public class LocationButler {

    private GoogleApiClient client;

    public LocationButler() {
    }

    public void connect(Context context, GoogleApiClient.ConnectionCallbacks callbacks, GoogleApiClient.OnConnectionFailedListener failedListener) {
        client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(failedListener)
                .build();
        client.connect();
    }

    public void disconnect() {
        if(client != null) {
            client.disconnect();
        }
    }

    public Location getLastLocation() {
        Location location = LocationServices.FusedLocationApi.getLastLocation(client);
        if (location != null) {
            return location;
        }
        return new Location("Default");
    }

}

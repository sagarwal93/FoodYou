package com.ncr.foodyou.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ncr.foodyou.LocationButler;
import com.ncr.foodyou.R;
import com.ncr.foodyou.Session;
import com.ncr.foodyou.api.FoodYouAPI;
import com.ncr.foodyou.http.AsyncResultHandler;
import com.ncr.foodyou.models.Coordinates;
import com.ncr.foodyou.models.Driver;
import com.ncr.foodyou.models.Order;

import org.w3c.dom.Text;

public class OpenOrdersActivity extends Activity {

    private SharedPreferences pref;
    private TextView orderDisplay;
    private LocationButler locationButler;
    private TextView siteInfo;
    private TextView distanceDisplay;
    private TextView rewardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        pref = getApplicationContext().getSharedPreferences(getString(R.string.foodyousession), Context.MODE_PRIVATE);

        siteInfo = (TextView) findViewById(R.id.siteinfodisplay);
        distanceDisplay = (TextView) findViewById(R.id.sitedistancedisplay);
        rewardInfo = (TextView) findViewById(R.id.rewardinfodisplay);

        Log.d("Created", "OpenOrdersActivity");

        locationButler = new LocationButler();
        locationButler.connect(this, connectionCallbacks, failedListener);

        Log.d("Got location", "OpenOrdersActivity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private GoogleApiClient.OnConnectionFailedListener failedListener = new GoogleApiClient.OnConnectionFailedListener() {

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {

        }
    };

    private GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {
            if(locationButler != null) {
                Location location = locationButler.getLastLocation();
                //locationButler.disconnect();

                FoodYouAPI.getNextOrder(location, new AsyncResultHandler() {

                    @Override
                    public void onSuccess() {
                        populateOrderTextView();
                        populateDistanceTextView();
                        populateRewardTextView();
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    public void populateOrderTextView() {
        String displayText = "";
        displayText += Session.activeOrder.getSiteName() + "\n";
        displayText += Session.activeOrder.getSiteAddress().toString() + "\n";

        siteInfo.setText(displayText);
    }

    public void populateDistanceTextView() {
        // Not the right distance yet, have to calculate driver -> store
        distanceDisplay.setText("10.0 miles");
    }

    public void populateRewardTextView() {
        String text = "";
        text += Session.activeOrder.getReward().getDescription();
        //rewardInfo.setText(text); // set reward info here
    }

    public void acceptOrder(View view) {

        if (Session.activeOrder.getOrderState() != Order.OrderState.Pending) {
            return;
        }

        addDriverToOrder();

        Session.activeOrder.setOrderState(Order.OrderState.Assigned);

        FoodYouAPI.claimOrderForDriver(Session.activeOrder, new AsyncResultHandler() {
            @Override
            public void onSuccess() {
                startDeliverActivity();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void addDriverToOrder() {
        Driver driver = new Driver("Samarth Agarwal");
        Location location = locationButler.getLastLocation();
        driver.setLocation(new Coordinates(location.getLatitude(), location.getLongitude()));
        Session.activeOrder.setDriver(driver);

        locationButler.disconnect();
    }

    public void startDeliverActivity() {
        Intent intent = new Intent(this, DeliverActivity.class);
        startActivity(intent);
    }
}

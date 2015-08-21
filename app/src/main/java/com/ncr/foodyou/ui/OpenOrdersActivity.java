package com.ncr.foodyou.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ncr.foodyou.R;
import com.ncr.foodyou.Session;
import com.ncr.foodyou.api.FoodYouAPI;
import com.ncr.foodyou.http.AsyncResultHandler;
import com.ncr.foodyou.models.Customer;
import com.ncr.foodyou.models.Order;
import com.ncr.foodyou.models.Site;
import com.ncr.foodyou.ui.adapters.OrdersAdapter;
import java.util.ArrayList;

public class OpenOrdersActivity extends Activity {

    private SharedPreferences pref;
    private TextView orderDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        pref = getApplicationContext().getSharedPreferences(getString(R.string.foodyousession), Context.MODE_PRIVATE);
        orderDisplay = (TextView) findViewById(R.id.orderdisplay);

        getOrder();
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

    public void getOrder() {
        Location location = new Location("Test");
        location.setLatitude(525586.54);
        location.setLongitude(25452.545);
        FoodYouAPI.getNextOrder(location, new AsyncResultHandler() {

            @Override
            public void onSuccess() {
                populateOrderTextView();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void populateOrderTextView() {
        String displayText = "";
        displayText += "Order Information: \n";
        displayText += Session.activeOrder.getSiteName() + "\n";
        displayText += Session.activeOrder.getSiteAddress() + "\n";
        orderDisplay.setText(displayText);
    }

    public void acceptOrder(View view) {
        if (Session.activeOrder.getOrderState() == Order.OrderState.Pending) {
            Session.activeOrder.setOrderState(Order.OrderState.Assigned);
        }
        Intent intent = new Intent(this, DeliverActivity.class);
        startActivity(intent);
    }

    public void denyOrder(View view) {
        getOrder();
    }

//    public void selectOrder(View view) {
//        int itemPosition = ordersRecyclerView.getChildAdapterPosition(view);
//        Session.setActiveOrder(orders.get(itemPosition));
//        Session.activeOrder.setOrderState(Order.OrderState.active);
//
//        Intent intent = new Intent(this, DeliverActivity.class);
//        startActivity(intent);
//    }
}

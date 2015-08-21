package com.ncr.foodyou.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ncr.foodyou.R;
import com.ncr.foodyou.Session;
import com.ncr.foodyou.models.Customer;
import com.ncr.foodyou.models.Order;
import com.ncr.foodyou.models.Site;
import com.ncr.foodyou.ui.adapters.OrdersAdapter;
import java.util.ArrayList;

public class OpenOrdersActivity extends Activity {

    private RecyclerView ordersRecyclerView;
    private OrdersAdapter ordersAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private ArrayList<Order> orders;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        ordersRecyclerView = (RecyclerView) findViewById(R.id.ordersRecyclerView);
        ordersLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);


        pref = getApplicationContext().getSharedPreferences(getString(R.string.foodyousession), Context.MODE_PRIVATE);
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

    public void getOrders(View view) {
        Location location = new Location("Test");
        location.setLatitude(50);
        location.setLongitude(50);
//        FoodYouAPI.getOrders(location, new AsyncResultHandler() {
//            @Override
//            public void onSuccess(Object result) {
//                orders = (ArrayList<Order>) result;
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//        });
        com.ncr.foodyou.models.MenuItem menuItem = new com.ncr.foodyou.models.MenuItem("", "Burger", 3.50);
        ArrayList<com.ncr.foodyou.models.MenuItem> items = new ArrayList<>();
        items.add(0, menuItem);
        Site site = new Site("", "", "Chipotle", "Ponce De Leon", 33.7739824,-84.3634878);
        Order testOrder1 = new Order("", 2.3, site, items, new Customer("sam", "atl"));
        Order testOrder2 = new Order("", 4, site, items, new Customer("matt", "atl"));

        orders = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(orders);
        ordersRecyclerView.setAdapter(ordersAdapter);
        orders.add(0, testOrder1);
        orders.add(1, testOrder2);

        ordersAdapter.updateList(orders);
    }

    public void selectOrder(View view) {
        int itemPosition = ordersRecyclerView.getChildAdapterPosition(view);
        Session.setActiveOrder(orders.get(itemPosition));

        Intent intent = new Intent(this, DeliverActivity.class);
        startActivity(intent);
    }
}

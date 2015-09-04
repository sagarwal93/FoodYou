package com.ncr.foodyou.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ncr.foodyou.R;
import com.ncr.foodyou.Session;
import com.ncr.foodyou.models.Order;

import org.w3c.dom.Text;

public class DeliverActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    TextView statusInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        statusInfoTextView = (TextView) findViewById(R.id.status_information);
        statusInfoTextView.setText(getOrderInformation());
    }

    private String getOrderInformation() {
        String retString = "";

        retString += "Order Information: \n";
        retString += Session.activeOrder.getSiteName() + "\n";
        retString += Session.activeOrder.getSiteAddress() + "\n";

        return retString;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deliver, menu);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latlng = new LatLng(33.7739824,-84.3634878);
        MarkerOptions marker = new MarkerOptions().position(latlng).title("Marker");
        map.addMarker(marker);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17));
    }

    public void changeOrderState(View view) {
        if (Session.activeOrder.getOrderState() == Order.OrderState.Assigned) {
            Session.activeOrder.setOrderState(Order.OrderState.Pickedup);
            Button orderStateButton = (Button) findViewById(R.id.order_state_button);
            orderStateButton.setText("Delivered");

            statusInfoTextView.setText("");
            statusInfoTextView.setText(getRouteStateText());
            changeMapMarkers();
        }
        else if (Session.activeOrder.getOrderState() == Order.OrderState.Pickedup) {
            Session.activeOrder.setOrderState(Order.OrderState.Delivered);

            Intent intent = new Intent(this, ConfirmationActivity.class);
            startActivity(intent);
        }
    }

    public String getRouteStateText() {
        String retString = "";

        retString += "Customer Information: \n";
        retString += Session.activeOrder.getCustomerDetails();

        return retString;
    }

    public void changeMapMarkers() {
        map.clear();

        LatLng latlng = new LatLng(33.740445,-84.381299);
        MarkerOptions marker = new MarkerOptions().position(latlng).title("Marker");
        map.addMarker(marker);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17));
    }
}

package com.ncr.foodyou.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ncr.foodyou.R;
import com.ncr.foodyou.models.Order;
import java.util.ArrayList;

/**
 * Created by Samarth on 7/24/15
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private ArrayList<Order> ordersDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.order_list_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OrdersAdapter(ArrayList<Order> ordersDataset) {
        this.ordersDataset = ordersDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void updateList(ArrayList<Order> ordersDataset) {
        this.ordersDataset = ordersDataset;
        notifyDataSetChanged();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(ordersDataset.get(position).getSiteName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ordersDataset.size();
    }
}

package com.urch.chatwin.ibuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Jayden on 9/11/2016.
 */

public class ListAdapter extends ArrayAdapter<Item> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_layout, null);
        }

        Item p = getItem(position);

        if (p != null) {
            TextView title = (TextView) v.findViewById(R.id.list_item_name);
            TextView description = (TextView) v.findViewById(R.id.list_item_information);

            if (title != null) {
                title.setText(p.getName());
            }

            if (description != null) {
                double total = (p.getCost())*p.getQuantity();
                DecimalFormat df = new DecimalFormat("#.00");
                description.setText("Quantity: "+p.getQuantity()+ " Total: $"+df.format(total));
            }

        }

        return v;
    }

}
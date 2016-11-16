package com.urch.chatwin.ibuy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Jayden on 9/11/2016.
 */

public class CommonAdapter extends ArrayAdapter<Item> {

    private ArrayList<ImageButton> buttons;
    private DB_Handler db;

    public CommonAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CommonAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource, items);
        buttons = new ArrayList<>();
        db = new DB_Handler(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.common_layout, null);
        }

        final Item p = getItem(position);
        ImageButton c = (ImageButton) v.findViewById(R.id.common_delete);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.nonCommon(p);
                remove(p);
                Toast toast = Toast.makeText(CommonAdapter.super.getContext(), "Item no longer common!", Toast.LENGTH_SHORT);
                toast.show();
                notifyDataSetChanged();
            }
        });
        buttons.add(c);



        if (p != null) {
            TextView title = (TextView) v.findViewById(R.id.common_item_name);
            TextView description = (TextView) v.findViewById(R.id.common_item_information);

            if (title != null) {
                title.setText(p.getName());
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AddList.class);
                        intent.putExtra("id", p.getId());
                        getContext().startActivity(intent);
                    }
                });
            }

            if (description != null) {
                double total = (p.getCost())*p.getQuantity();
                DecimalFormat df = new DecimalFormat("#.00");
                description.setText("Quantity: "+p.getQuantity()+ " Total: $"+df.format(total));
                description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //USE TAGES AND START INTENTS
                        Intent intent = new Intent(getContext(), AddList.class);
                        intent.putExtra("id", p.getId());
                        intent.putExtra("common", true);
                        getContext().startActivity(intent);
                    }
                });
            }

        }

        return v;
    }

}
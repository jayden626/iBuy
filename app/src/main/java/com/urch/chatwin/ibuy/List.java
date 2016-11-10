package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<Item> items;
    ArrayAdapter<Item> itemArrayAdapter;
    DB_Handler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        db = new DB_Handler(this.getApplicationContext());
        items = db.getAllItems();


        //for testing only
        //db.addUser("User#" + db.getUsersCount());
        //end

        itemArrayAdapter = new ListAdapter(this, android.R.layout.simple_list_item_1, items);
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(itemArrayAdapter);

        CheckBox c = (CheckBox) findViewById(R.id.list_item_checked);
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    //db.deleteItem();
                }
            }
        });
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddList.class);
        startActivity(intent);
    }
}

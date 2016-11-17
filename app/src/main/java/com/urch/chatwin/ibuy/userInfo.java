package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by Charles Chatwin on 11/7/2016.
 */
public class userInfo extends Activity {

    User u;
    DB_Handler db;
    int uID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DB_Handler(this.getApplicationContext());

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            u.setId(id);
            uID = id;
        }

        setContentView(R.layout.activity_user_info);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

    }

    public void showList(View view) {
        Intent intent = new Intent(this, List.class);
        intent.putExtra("id", uID);
        startActivity(intent);
    }

    public void viewCommon(View view) {
        Intent intent = new Intent(this, CommonList.class);
        intent.putExtra("id", uID);
        startActivity(intent);
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddList.class);
        intent.putExtra("id", uID);
        startActivity(intent);
    }

    public void logOut(View view) {
        uID = -1;
        Intent intent = new Intent(this, MainMenu.class);//LoginActivity.class);
        intent.putExtra("id", uID);
        startActivity(intent);
    }
    /*
    public void addRoute(View view) {
        Intent intent = new Intent(this, AddRoute.class);
        startActivity(intent);
    }
    */
}




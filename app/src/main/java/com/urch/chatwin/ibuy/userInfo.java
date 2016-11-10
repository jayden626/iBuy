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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_info);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

    }

    public void showList(View view) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddList.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        Intent intent = new Intent(this, MainMenu.class);//LoginActivity.class);
        startActivity(intent);
    }
    /*
    public void addRoute(View view) {
        Intent intent = new Intent(this, AddRoute.class);
        startActivity(intent);
    }
    */
}




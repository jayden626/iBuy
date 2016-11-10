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
public class UserCreate extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_user_create);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

    }

    public void createUser() {
        //TODO Create the user
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(this, MainMenu.class);//LoginActivity.class);
        startActivity(intent);
    }
}

package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainMenu extends AppCompatActivity {
    //User u;
    int uID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_menu);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        uID = -1;

        /*
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            u.setId(id);
            uID = id;
        }*/

    }

    public void showList(View view) {
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }

    public void createNewUser(View view) {
        Intent intent = new Intent(this, UserCreate.class);
        startActivity(intent);
    }

    public void userLoginP(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

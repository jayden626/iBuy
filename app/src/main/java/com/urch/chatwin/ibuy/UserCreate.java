package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Charles Chatwin on 11/7/2016.
 */
public class UserCreate extends AppCompatActivity {

    DB_Handler db;
    TextView password;
    TextView username;
    ArrayList<User> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_user_create);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        //creating/assigning DB and textview's
        db = new DB_Handler(this.getApplicationContext());
        username = (TextView) findViewById(R.id.new_user_name);
        password = (TextView) findViewById(R.id.new_password);
        userList = db.getAllUsers();
    }

    public void createUser() {

        if (userList.contains(username.getText())) {
            username.setText("Username Already Exists; try again!");
        }
        else {
            db.addUser((String)username.getText()); //TODO if addUser is made to include password: add '(String)password.getText()'

            //clearing the input to let user know it was created
            username.setText("");
            password.setText("");
        }

    }

    public void backToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);//LoginActivity.class);
        startActivity(intent);
    }
}

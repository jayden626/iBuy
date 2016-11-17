package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Charles Chatwin on 11/7/2016.
 */
public class UserCreate extends AppCompatActivity {

    User u;
    int uID;
    int count = 0;
    DB_Handler db;
    TextView password;
    TextView username;
    ArrayList<User> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_create);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        //creating/assigning DB and textview's
        db = new DB_Handler(this.getApplicationContext());
        username = (EditText) findViewById(R.id.new_user_name);
        password = (EditText) findViewById(R.id.new_password);
        uID = -1;

    }

    public void createUser(View view) {

        userList = db.getAllUsers();
        View focusView;
        uID = -1;

        for(User u : userList){
            if(u.getName().equalsIgnoreCase(username.getText().toString())){
                uID = u.getId();
                break;
            }
        }

        if (uID != -1) {
            username.setError(getString(R.string.error_username_exists));
            focusView = username;
            focusView.requestFocus();
        }
        else {
            uID = count++;
            u = new User(uID, username.getText().toString());
            db.addUser(u.getName()); //TODO if addUser is made to include password: add 'password.getText().toString()'

            //changing screen to log in to let user know it was created
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }

    public void backToLogin(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}

package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.activity_main_menu);

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
}
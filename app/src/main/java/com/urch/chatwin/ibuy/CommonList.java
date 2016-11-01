package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CommonList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.activity_list);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
    }
}

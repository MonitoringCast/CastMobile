package com.mega.robert.chromecastmoneymaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

/**
 * Created by robert on 05/08/16.
 */
public class MainActivity extends FragmentActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume(){
        super.onResume();
//        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean first_use = prefs.getBoolean("first_use", true);
        String connect_url = prefs.getString("connect_url", "");
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");

        if (first_use || connect_url.isEmpty() || username.isEmpty() || password.isEmpty()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_use", false);
            editor.apply();
            Intent intent = new Intent(this, SetupActivity.class);
            startActivity(intent);
        } else {
            System.out.println(connect_url);
            Intent intent = new Intent(this, CastActivity.class);
            startActivity(intent);
        }
        // TODO: on error from connect_url, change to SetupActivity again and flash a message.
        //       or get the result from the activity and set as preference here after the check passes.
    }
}

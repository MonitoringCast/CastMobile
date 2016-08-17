package com.mega.robert.chromecastmoneymaker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetupActivity extends Activity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        final EditText editTextPassword = (EditText)findViewById(R.id.password);
        final EditText editTextUsername = (EditText)findViewById(R.id.username);
        final EditText editTextUrl = (EditText)findViewById(R.id.connect_url);
        final SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        final String connect_url = preferences.getString("connect_url", "");
        final String username = preferences.getString("username", "");
        final String password = preferences.getString("password", "");

        editTextPassword.setText(password);
        editTextUsername.setText(username);
        editTextUrl.setText(connect_url);

        final Button button = (Button) findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor prefs = preferences.edit();

                String password_input = editTextPassword.getEditableText().toString();
                String username_input = editTextUsername.getEditableText().toString();
                String url_input = editTextUrl.getEditableText().toString();

                prefs.putString("password", password_input);
                prefs.putString("username", username_input);
                prefs.putString("connect_url", url_input);

                prefs.apply();
                finish();
            }
        });


        editTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                button.performClick();
                return false;
            }
        });
    }
}

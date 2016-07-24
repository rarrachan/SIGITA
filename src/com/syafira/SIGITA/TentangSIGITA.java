package com.syafira.SIGITA;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by syafira rarra on 06/01/2016.
 */

public class TentangSIGITA extends Home {

    // Declare
    private TextView button_tentangsigita;
    private TextView tentang_sigita;
    private TextView text_footer;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tentang_sigita);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        button_tentangsigita = (TextView) findViewById(R.id.button_tentangsigita);
        tentang_sigita = (TextView) findViewById(R.id.tentang_sigita);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_tentangsigita.setTypeface(typeface);
        tentang_sigita.setTypeface(typeface);
        text_footer.setTypeface(typeface);
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Home Activity
        Intent index = new Intent(TentangSIGITA.this, Home.class);
        lastActivity = System.currentTimeMillis();
        index.putExtra("lastActivity", lastActivity);
        index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(index);

        // Close This Activity
        finish();
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(TentangSIGITA.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

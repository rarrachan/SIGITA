package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/18/2016.
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;

public class ImageZoom extends Activity {

    private SessionManager session;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        String foto_path = fetchID.getStringExtra("foto_path");
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Declare ImageZoomer Class
        ImageZoomer img = new ImageZoomer(this);

        // Load Image
        img.setImageDrawable(Drawable.createFromPath(foto_path));
        img.setMaxZoom(4f);
        setContentView(img);
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(ImageZoom.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}
package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    // Splash Screen Timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Load Database
        DBHelper db = new DBHelper(this);
        db.open();

        SessionManager session = new SessionManager();
        session.clearSession(this);


        new Handler().postDelayed(new Runnable() {

         /*
          * Showing splash screen with a timer. This will be useful when you
          * want to show case your app logo / company
          */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                long lastActivity = System.currentTimeMillis();
                Intent i = new Intent(Splash.this, Home.class);
                i.putExtra("lastActivity", lastActivity);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
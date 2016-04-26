package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/03/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class TumbuhKembang extends Activity implements OnClickListener {

    // Declare
    private Button button_profil;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private TextView text_button_galeritumbang;
    private ImageView button_galeritumbang;
    private LinearLayout GaleriTumbangLinearLayout;
    private TextView text_button_tahapantumbang;
    private ImageView button_tahapantumbang;
    private LinearLayout TahapanTumbangLinearLayout;
    private SessionManager session;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tumbuh_kembang);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        TahapanTumbangLinearLayout = (LinearLayout) findViewById(R.id.TahapanTumbangLinearLayout);
        text_button_tahapantumbang= (TextView) findViewById(R.id.text_button_tahapantumbang);
        GaleriTumbangLinearLayout = (LinearLayout) findViewById(R.id.GaleriTumbangLinearLayout);
        text_button_galeritumbang= (TextView) findViewById(R.id.text_button_galeritumbang);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_button_tahapantumbang.setTypeface(typeface);
        text_button_galeritumbang.setTypeface(typeface);
        text_footer.setTypeface(typeface);

    }

    // OnClick Activity
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            // Profil
            case R.id.ProfilLinearLayout :
                Intent profil = new Intent(this, Profil.class);
                startActivity(profil);
                break;
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Close This Activity
        finish();
    }

    // Redirect Back to This Activity
    @Override
    public void onRestart() {
        // Restart Activity
        super.onRestart();
        recreate();
    }
}

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

public class Gizi extends Activity implements OnClickListener {

    // Declare
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private TextView text_button_kalkulatorgizi;
    private LinearLayout KalkulatorGiziLinearLayout;
    private TextView text_button_dokumentasigizi;
    private LinearLayout DokumentasiGiziLinearLayout;
    private SessionManager session;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.gizi);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        KalkulatorGiziLinearLayout = (LinearLayout) findViewById(R.id.KalkulatorGiziLinearLayout);
        text_button_kalkulatorgizi = (TextView) findViewById(R.id.text_button_kalkulatorgizi);
        DokumentasiGiziLinearLayout = (LinearLayout) findViewById(R.id.DokumentasiGiziLinearLayout);
        text_button_dokumentasigizi = (TextView) findViewById(R.id.text_button_dokumentasigizi);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(this);
        KalkulatorGiziLinearLayout.setOnClickListener(this);

        // Set Custtom Layout
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_button_kalkulatorgizi.setTypeface(typeface);
        text_button_dokumentasigizi.setTypeface(typeface);
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

            // Kalkulator Gizi
            case R.id.KalkulatorGiziLinearLayout :
                Intent kalkulatorgizi = new Intent(this, KalkulatorGizi.class);
                startActivity(kalkulatorgizi);
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

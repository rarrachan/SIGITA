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

public class Imunisasi extends Activity implements OnClickListener {

    // Declare
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private TextView text_button_jadwalimunisasi;
    private LinearLayout JadwalImunisasiLinearLayout;
    private TextView text_button_riwayatimunisasi;
    private LinearLayout RiwayatImunisasiLinearLayout;
    private TextView text_button_alarmimunisasi;
    private LinearLayout AlarmImunisasiLinearLayout;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.imunisasi);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        JadwalImunisasiLinearLayout = (LinearLayout) findViewById(R.id.JadwalImunisasiLinearLayout);
        text_button_jadwalimunisasi = (TextView) findViewById(R.id.text_button_jadwalimunisasi);
        RiwayatImunisasiLinearLayout = (LinearLayout) findViewById(R.id.RiwayatImunisasiLinearLayout);
        text_button_riwayatimunisasi = (TextView) findViewById(R.id.text_button_riwayatimunisasi);
        AlarmImunisasiLinearLayout = (LinearLayout) findViewById(R.id.AlarmImunisasiLinearLayout);
        text_button_alarmimunisasi = (TextView) findViewById(R.id.text_button_alarmimunisasi);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(this);
        JadwalImunisasiLinearLayout.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_button_jadwalimunisasi.setTypeface(typeface);
        text_button_riwayatimunisasi.setTypeface(typeface);
        text_button_alarmimunisasi.setTypeface(typeface);
        text_footer.setTypeface(typeface);

    }

    //OnClick Activity
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            // Profil
            case R.id.ProfilLinearLayout :
                Intent profil = new Intent(this, Profil.class);
                startActivity(profil);
                break;
            // Jadwal Imunisasi
            case R.id.JadwalImunisasiLinearLayout :
                Intent jadwal = new Intent(this, JadwalImunisasi.class);
                startActivity(jadwal);
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

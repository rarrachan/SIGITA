package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/26/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Typeface;

public class Index extends Activity implements OnClickListener {

    // Declare
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_button_gizi;
    private LinearLayout GiziLinearLayout;
    private TextView text_button_imunisasi;
    private LinearLayout ImunisasiLinearLayout;
    private TextView text_button_tumbuhkembang;
    private LinearLayout TumbuhKembangLinearLayout;
    private TextView text_button_rekammedis;
    private TextView text_footer;
    private SessionManager session;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.index);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_gizi = (TextView) findViewById(R.id.text_button_gizi);
        GiziLinearLayout = (LinearLayout) findViewById(R.id.GiziLinearLayout);
        text_button_imunisasi = (TextView) findViewById(R.id.text_button_imunisasi);
        ImunisasiLinearLayout = (LinearLayout) findViewById(R.id.ImunisasiLinearLayout);
        text_button_tumbuhkembang = (TextView) findViewById(R.id.text_button_tumbuhkembang);
        TumbuhKembangLinearLayout = (LinearLayout) findViewById(R.id.TumbuhKembangLinearLayout);
        text_button_rekammedis = (TextView) findViewById(R.id.text_button_rekammedis);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(this);
        GiziLinearLayout.setOnClickListener(this);
        ImunisasiLinearLayout.setOnClickListener(this);
        TumbuhKembangLinearLayout.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_button_gizi.setTypeface(typeface);
        text_button_imunisasi.setTypeface(typeface);
        text_button_tumbuhkembang.setTypeface(typeface);
        text_button_rekammedis.setTypeface(typeface);
        text_footer.setTypeface(typeface);
    }

    // OnClick Activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Profil
            case R.id.ProfilLinearLayout:
                Intent profil = new Intent(this, Profil.class);
                startActivity(profil);
                break;

            // Gizi
            case R.id.GiziLinearLayout:
                Intent gizi = new Intent(this, Gizi.class);
                startActivity(gizi);
                break;

            // Imunisasi
            case R.id.ImunisasiLinearLayout:
                Intent imunisasi = new Intent(this, Imunisasi.class);
                startActivity(imunisasi);
                break;

            // Tumbuh Kembang
            case R.id.TumbuhKembangLinearLayout:
                Intent tumbang = new Intent(this, TumbuhKembang.class);
                startActivity(tumbang);
                break;
        }
    }

    // Pressed Button Back
    @Override
    public void onBackPressed() {
        // Create Dialog
        new AlertDialog.Builder(this)
                .setTitle("Keluar SIGITA")
                .setMessage("Apa Anda yakin ingin keluar?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Close Application
                        Intent finish = new Intent(Intent.ACTION_MAIN);
                        finish.addCategory(Intent.CATEGORY_HOME);
                        finish.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(finish);

                        // Clear Session
                        session.clearSession(Index.this);
                        Index.super.onBackPressed();
                    }
                }).create().show();
    }

    // Redirect Back to This Activity
    @Override
    public void onRestart() {
        // Restart Activity
        super.onRestart();
        recreate();
    }
}

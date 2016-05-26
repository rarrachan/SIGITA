package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/26/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Typeface;

import java.util.Date;

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
    private LinearLayout RekamMedisLinearLayout;
    private TextView text_footer;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.index);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

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
        RekamMedisLinearLayout = (LinearLayout) findViewById(R.id.RekamMedisLinearLayout);
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
        RekamMedisLinearLayout.setOnClickListener(this);

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
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                startActivity(profil);
                break;

            // Gizi
            case R.id.GiziLinearLayout:
                Intent gizi = new Intent(this, Gizi.class);
                lastActivity = System.currentTimeMillis();
                gizi.putExtra("lastActivity", lastActivity);
                startActivity(gizi);

                finish();
                break;

            // Imunisasi
            case R.id.ImunisasiLinearLayout:
                Intent imunisasi = new Intent(this, Imunisasi.class);
                lastActivity = System.currentTimeMillis();
                imunisasi.putExtra("lastActivity", lastActivity);
                startActivity(imunisasi);
                finish();
                break;

            // Tumbuh Kembang
            case R.id.TumbuhKembangLinearLayout:
                Intent tumbang = new Intent(this, TumbuhKembang.class);
                lastActivity = System.currentTimeMillis();
                tumbang.putExtra("lastActivity", lastActivity);
                startActivity(tumbang);
                finish();
                break;

            // Rekam Medis
            case R.id.RekamMedisLinearLayout :
                // Check Session
                if (session.checkSession(this)) {
                    Intent medis = new Intent(this, RekamMedis.class);
                    lastActivity = System.currentTimeMillis();
                    medis.putExtra("lastActivity", lastActivity);
                    startActivity(medis);
                    finish();
                } else {
                    final Dialog dialog = new Dialog(Index.this);
                    dialog.setContentView(R.layout.alert_akses);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();

                    // Load Dialog Widget
                    TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                    TextView alert_akses = (TextView) dialog.findViewById(R.id.alert_akses);
                    ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                    // Set Custom Font Dialog
                    Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
                    alert_akses.setTypeface(typeface);
                    alert_warning.setTypeface(typeface);

                    // Set OnClickListener Dialog
                    button_ok.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close Dialog
                            dialog.dismiss();

                            // Start Profil Activity
                            Intent profil = new Intent(Index.this, Profil.class);
                            lastActivity = System.currentTimeMillis();
                            profil.putExtra("lastActivity", lastActivity);
                            startActivity(profil);
                        }
                    });
                }
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


    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();

        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(Index.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        } else {
            // Check Session
            if (session.checkSession(this)) {
                // Set Profil Name
                text_button_profil.setText(session.loadSession(this, "nama"));
            } else {
                text_button_profil.setText("Profil");
            }
        }
    }
}

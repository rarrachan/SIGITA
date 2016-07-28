package com.syafira.SIGITA;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by syafira rarra on 05/31/2016.
 */

public class ProfilPasscode extends Profil {

    // Declare
    private TextView button_passcode;
    private TextView text_passcode_nama;
    private TextView passcode_nama;
    private TextView text_passcode_switch;
    private ToggleButton passcode_switch;
    private LinearLayout UbahPasscodeLinearLayout;
    private TextView text_passcode_ubah;
    private TextView text_footer;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.passcode);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        final int id = fetchID.getIntExtra("id", 0);
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneProfil(id);
        cursor.moveToFirst();
        final String nama = cursor.getString(cursor.getColumnIndex("profil_nama"));
        final String pass = cursor.getString(cursor.getColumnIndex("profil_passcode"));

        // Session Manager
        session = new SessionManager();
        
        // Load Widget
        button_passcode = (TextView) findViewById(R.id.button_passcode);
        text_passcode_nama = (TextView) findViewById(R.id.text_passcode_nama);
        passcode_nama = (TextView) findViewById(R.id.passcode_nama);
        text_passcode_switch = (TextView) findViewById(R.id.text_passcode_switch);
        passcode_switch = (ToggleButton) findViewById(R.id.passcode_switch);
        UbahPasscodeLinearLayout = (LinearLayout) findViewById(R.id.UbahPasscodeLinearLayout);
        text_passcode_ubah = (TextView) findViewById(R.id.text_passcode_ubah);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custtom Layout
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_passcode.setTypeface(typeface);
        text_passcode_nama.setTypeface(typeface);
        passcode_nama.setTypeface(typeface);
        text_passcode_switch.setTypeface(typeface);
        text_passcode_ubah.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Set Text
        passcode_nama.setText(nama);

        // CheckPasscode
        if(pass.equals("")){
           passcode_switch.setChecked(false);
            UbahPasscodeLinearLayout.setVisibility(View.GONE);
        }else{
            passcode_switch.setChecked(true);
            UbahPasscodeLinearLayout.setVisibility(View.VISIBLE);
        }

        // Switch Passcode
        passcode_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch ON
                if (passcode_switch.isChecked()) {
                    Intent passcode = new Intent(ProfilPasscode.this, ProfilPasscodeTambah.class);
                    lastActivity = System.currentTimeMillis();
                    passcode.putExtra("lastActivity", lastActivity);
                    passcode.putExtra("passcode", pass);
                    passcode.putExtra("id", id);
                    passcode.putExtra("action", "tambahpasscode");
                    passcode.putExtra("pathbefore", pathbefore);
                    passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                    passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(passcode);
                    finish();

                // Switch OFF
                } else {
                    Intent passcode = new Intent(ProfilPasscode.this, ProfilPasscodeCek.class);
                    lastActivity = System.currentTimeMillis();
                    passcode.putExtra("lastActivity", lastActivity);
                    passcode.putExtra("passcode", pass);
                    passcode.putExtra("id", id);
                    passcode.putExtra("action", "hapuspasscode");
                    passcode.putExtra("pathbefore", pathbefore);
                    passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                    passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(passcode);
                    finish();
                }
            }
        });

        // Set OnClickListener
        UbahPasscodeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Image Zoom Activity
                Intent passcode = new Intent(ProfilPasscode.this, ProfilPasscodeCek.class);
                lastActivity = System.currentTimeMillis();
                passcode.putExtra("lastActivity", lastActivity);
                passcode.putExtra("passcode", pass);
                passcode.putExtra("id", id);
                passcode.putExtra("action", "ubahpasscode");
                passcode.putExtra("pathbefore", pathbefore);
                passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(passcode);
                finish();
            }
        });
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Start Profil Activity
        Intent detail_profil = new Intent(this, ProfilDetail.class);
        lastActivity = System.currentTimeMillis();
        detail_profil.putExtra("lastActivity", lastActivity);
        detail_profil.putExtra("id", id);
        detail_profil.putExtra("pathbefore", pathbefore);
        detail_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
        detail_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(detail_profil);

        // Close This Activity
        finish();
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();

        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneProfil(id);
        cursor.moveToFirst();
        final String nama = cursor.getString(cursor.getColumnIndex("profil_nama"));
        final String pass = cursor.getString(cursor.getColumnIndex("profil_passcode"));

        passcode_nama.setText(nama);
        if(pass.equals("")){
            passcode_switch.setChecked(false);
            UbahPasscodeLinearLayout.setVisibility(View.GONE);
        }else{
            passcode_switch.setChecked(true);
            UbahPasscodeLinearLayout.setVisibility(View.VISIBLE);
        }

        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(ProfilPasscode.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

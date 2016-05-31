package com.syafira.SIGITA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by syafira rarra on 05/31/2016.
 */
public class Passcode extends Activity {

    // Declare
    private TextView button_passcode;
    private TextView text_passcode_nama;
    private TextView passcode_nama;
    private TextView text_passcode_switch;
    private ToggleButton passcode_switch;
    private LinearLayout UbahPasscodeLinearLayout;
    private TextView text_passcode_ubah;
    private ImageView button_passcode_ubah;
    private TextView text_footer;
    private TextView titikdua;
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

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneProfil(id);
        cursor.moveToFirst();
        final String nama = cursor.getString(cursor.getColumnIndex("profil_nama"));
        final String pass = cursor.getString(cursor.getColumnIndex("profil_passcode"));

        // Load Session Manager
        session = new SessionManager();

        button_passcode = (TextView) findViewById(R.id.button_passcode);
        text_passcode_nama = (TextView) findViewById(R.id.text_passcode_nama);
        passcode_nama = (TextView) findViewById(R.id.passcode_nama);
        text_passcode_switch = (TextView) findViewById(R.id.text_passcode_switch);
        passcode_switch = (ToggleButton) findViewById(R.id.passcode_switch);
        UbahPasscodeLinearLayout = (LinearLayout) findViewById(R.id.UbahPasscodeLinearLayout);
        text_passcode_ubah = (TextView) findViewById(R.id.text_passcode_ubah);
        button_passcode_ubah = (ImageView) findViewById(R.id.button_passcode_ubah);
        text_footer = (TextView) findViewById(R.id.text_footer);
        titikdua = (TextView) findViewById(R.id.titikdua);

        // Set Custtom Layout
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_passcode.setTypeface(typeface);
        text_passcode_nama.setTypeface(typeface);
        passcode_nama.setTypeface(typeface);
        text_passcode_switch.setTypeface(typeface);
        text_passcode_ubah.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        titikdua.setTypeface(typeface);

        passcode_nama.setText(nama);

        if(pass.equals("")){
           passcode_switch.setChecked(false);
            UbahPasscodeLinearLayout.setVisibility(View.GONE);
        }else{
            passcode_switch.setChecked(true);
            UbahPasscodeLinearLayout.setVisibility(View.VISIBLE);
        }

        passcode_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passcode_switch.isChecked()) {
                    Intent passcode = new Intent(Passcode.this, ProfilPasscode.class);
                    lastActivity = System.currentTimeMillis();
                    passcode.putExtra("lastActivity", lastActivity);
                    passcode.putExtra("passcode", pass);
                    passcode.putExtra("id", id);
                    passcode.putExtra("action", "tambahpasscode");
                    startActivity(passcode);
                } else {
                    Intent passcode = new Intent(Passcode.this, ProfilCekPasscode.class);
                    lastActivity = System.currentTimeMillis();
                    passcode.putExtra("lastActivity", lastActivity);
                    passcode.putExtra("passcode", pass);
                    passcode.putExtra("id", id);
                    passcode.putExtra("action", "hapuspasscode");
                    startActivity(passcode);
                }
            }
        });

        // Set OnClickListener
        UbahPasscodeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Image Zoom Activity
                Intent passcode = new Intent(Passcode.this, ProfilCekPasscode.class);
                lastActivity = System.currentTimeMillis();
                passcode.putExtra("lastActivity", lastActivity);
                passcode.putExtra("passcode", pass);
                passcode.putExtra("id", id);
                passcode.putExtra("action", "ubahpasscode");
                startActivity(passcode);
            }
        });
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);

        // Start Profil Activity
        Intent detail_profil = new Intent(this, DetailProfil.class);
        lastActivity = System.currentTimeMillis();
        detail_profil.putExtra("lastActivity", lastActivity);
        detail_profil.putExtra("id", id);
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
            session.clearSession(Passcode.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}

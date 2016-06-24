package com.syafira.SIGITA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

/**
 * Created by syafira rarra on 05/04/2016.
 */

public class TahapanTumbang extends Activity {

    // Declare
    private TextView button_tahapantumbang;
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_usia_tumbang;
    private TextView text_gerakan_kasar;
    private TextView text_gerakan_halus;
    private TextView text_komunikasi;
    private TextView text_sosial_kemandirian;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tahapan_tumbang);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_tahapantumbang = (TextView) findViewById(R.id.button_tahapantumbang);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_usia_tumbang = (TextView) findViewById(R.id.text_usia_tumbang);
        text_gerakan_kasar = (TextView) findViewById(R.id.text_gerakan_kasar);
        text_gerakan_halus = (TextView) findViewById(R.id.text_gerakan_halus);
        text_komunikasi = (TextView) findViewById(R.id.text_komunikasi);
        text_sosial_kemandirian = (TextView) findViewById(R.id.text_sosial_kemandirian);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        TableLayout tahap_tumbang = (TableLayout) findViewById(R.id.tahap_tumbang);

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(TahapanTumbang.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", "tahapantumbang");
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);
                finish();
            }
        });

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_tahapantumbang.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_usia_tumbang.setTypeface(typeface);
        text_gerakan_kasar.setTypeface(typeface);
        text_gerakan_halus.setTypeface(typeface);
        text_komunikasi.setTypeface(typeface);
        text_sosial_kemandirian.setTypeface(typeface);

        // Get Data from Database
        Cursor cursor = db.getTahapan();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams tumbuh_kembang = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(tumbuh_kembang);

                TextView usia_tumbang = new TextView(this);
                TextView gerakan_kasar = new TextView(this);
                TextView gerakan_halus = new TextView(this);
                TextView komunikasi = new TextView(this);
                TextView sosial_kemandirian = new TextView(this);

                TableRow.LayoutParams usia_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams gerakan_kasar_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams gerakan_halus_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams komunikasi_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams sosial_kemandirian_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);

                usia_tumbang.setGravity(Gravity.CENTER_VERTICAL);
                gerakan_kasar.setGravity(Gravity.CENTER_VERTICAL);
                gerakan_halus.setGravity(Gravity.CENTER_VERTICAL);
                komunikasi.setGravity(Gravity.CENTER_VERTICAL);
                sosial_kemandirian.setGravity(Gravity.CENTER_VERTICAL);

                int margin_in_dp_1 = 1 ;//value in dp
                int margin_in_dp_3 = 3 ;//value in dp
                int margin_in_pixel_1 = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_1, getResources()
                                .getDisplayMetrics());
                int margin_in_pixel_3 = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_3, getResources()
                                .getDisplayMetrics());
                usia_text_view.setMargins(margin_in_pixel_3, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                gerakan_kasar_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                gerakan_halus_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                komunikasi_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                sosial_kemandirian_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_3, margin_in_pixel_1);

                int padding_in_dp = 5 ;//value in dp
                int padding_in_pixel = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, padding_in_dp, getResources()
                                .getDisplayMetrics());
                usia_tumbang.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                gerakan_kasar.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                gerakan_halus.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                komunikasi.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                sosial_kemandirian.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);

                usia_tumbang.setLayoutParams(usia_text_view);
                gerakan_kasar.setLayoutParams(gerakan_kasar_text_view);
                gerakan_halus.setLayoutParams(gerakan_halus_text_view);
                komunikasi.setLayoutParams(komunikasi_text_view);
                sosial_kemandirian.setLayoutParams(sosial_kemandirian_text_view);

                usia_tumbang.setBackgroundResource(R.drawable.border_row);
                gerakan_kasar.setBackgroundResource(R.drawable.border_row);
                gerakan_halus.setBackgroundResource(R.drawable.border_row);
                komunikasi.setBackgroundResource(R.drawable.border_row);
                sosial_kemandirian.setBackgroundResource(R.drawable.border_row);

                usia_tumbang.setText(cursor.getString(cursor.getColumnIndex("tahap_usia")));
                gerakan_kasar.setText(cursor.getString(cursor.getColumnIndex("tahap_gerakan_kasar")));
                gerakan_halus.setText(cursor.getString(cursor.getColumnIndex("tahap_gerakan_halus")));
                komunikasi.setText(cursor.getString(cursor.getColumnIndex("tahap_komunikasi")));
                sosial_kemandirian.setText(cursor.getString(cursor.getColumnIndex("tahap_sosial_kemandirian")));

                usia_tumbang.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                gerakan_kasar.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                gerakan_halus.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                komunikasi.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                sosial_kemandirian.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);

                usia_tumbang.setTextColor(Color.parseColor("#D046F2"));
                gerakan_kasar.setTextColor(Color.parseColor("#D046F2"));
                gerakan_halus.setTextColor(Color.parseColor("#D046F2"));
                komunikasi.setTextColor(Color.parseColor("#D046F2"));
                sosial_kemandirian.setTextColor(Color.parseColor("#D046F2"));

                usia_tumbang.setTypeface(typeface);
                gerakan_kasar.setTypeface(typeface);
                gerakan_halus.setTypeface(typeface);
                komunikasi.setTypeface(typeface);
                sosial_kemandirian.setTypeface(typeface);

                row.addView(usia_tumbang);
                row.addView(gerakan_kasar);
                row.addView(gerakan_halus);
                row.addView(komunikasi);
                row.addView(sosial_kemandirian);

                tahap_tumbang.addView(row);
            } while (cursor.moveToNext());

            // Close Databsae
            db.close();
            cursor.close();
        }

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent tumbang = new Intent(this, TumbuhKembang.class);
        lastActivity = System.currentTimeMillis();
        tumbang.putExtra("lastActivity", lastActivity);
        tumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(tumbang);

        // Close This Activity
        finish();
    }

    // Pressed Back Button
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(TahapanTumbang.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
            
        } else {
            // Check Session
            if (session.checkSession(this)) {
                // Set Profil Name
                text_button_profil.setText(session.loadSession(this, "nama"));
            }
        }
    }
}
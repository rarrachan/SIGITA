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
 * Created by syafira rarra on 04/20/2016.
 */
public class JadwalImunisasi extends Activity {

    // Declare
    private TextView button_jadwalimunisasi;
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_jenis_vaksin;
    private TextView text_usia_vaksin;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.jadwal_imunisasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_jadwalimunisasi = (TextView) findViewById(R.id.button_jadwalimunisasi);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_jenis_vaksin = (TextView) findViewById(R.id.text_jenis_vaksin);
        text_usia_vaksin = (TextView) findViewById(R.id.text_usia_vaksin);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        TableLayout jadwal_imunisasi = (TableLayout) findViewById(R.id.jadwal_imunisasi);

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(JadwalImunisasi.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                startActivity(profil);
            }
        });

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_jadwalimunisasi.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_jenis_vaksin.setTypeface(typeface);
        text_usia_vaksin.setTypeface(typeface);

        // Get Data from Database
        Cursor cursor = db.getList();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams list_imunisasi = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(list_imunisasi);

                TextView vaksin = new TextView(this);
                TextView umur = new TextView(this);
                ImageView detail = new ImageView(this);

                TableRow.LayoutParams vaksin_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams umur_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                TableRow.LayoutParams detail_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, .5f);

                detail.setImageResource(R.drawable.tombol_detail);
                vaksin.setGravity(Gravity.CENTER_VERTICAL);
                umur.setGravity(Gravity.CENTER_VERTICAL);

                int margin_in_dp_1 = 1 ;//value in dp
                int margin_in_dp_3 = 3 ;//value in dp
                int margin_in_pixel_1 = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_1, getResources()
                                .getDisplayMetrics());
                int margin_in_pixel_3 = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_3, getResources()
                                .getDisplayMetrics());
                vaksin_text_view.setMargins(margin_in_pixel_3, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                umur_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                detail_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_3, margin_in_pixel_1);

                int padding_in_dp = 5 ;//value in dp
                int padding_in_pixel = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, padding_in_dp, getResources()
                                .getDisplayMetrics());
                vaksin.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                umur.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                detail.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);

                vaksin.setLayoutParams(vaksin_text_view);
                umur.setLayoutParams(umur_text_view);
                detail.setLayoutParams(detail_text_view);

                vaksin.setBackgroundResource(R.drawable.border_row);
                umur.setBackgroundResource(R.drawable.border_row);
                detail.setBackgroundResource(R.drawable.border_row);

                vaksin.setText(cursor.getString(cursor.getColumnIndex("list_vaksin")));
                umur.setText(cursor.getString(cursor.getColumnIndex("list_umur")));
                final int id = cursor.getInt(cursor.getColumnIndex("listID"));

                vaksin.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                umur.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);

                vaksin.setTextColor(Color.parseColor("#D046F2"));
                umur.setTextColor(Color.parseColor("#D046F2"));

                vaksin.setTypeface(typeface);
                umur.setTypeface(typeface);

                row.addView(vaksin);
                row.addView(umur);
                row.addView(detail);

                detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show Detail Profil Activity
                        Intent detail_imunisasi = new Intent(JadwalImunisasi.this, DetailJadwalImunisasi.class);
                        detail_imunisasi.putExtra("id", id);
                        lastActivity = System.currentTimeMillis();
                        detail_imunisasi.putExtra("lastActivity", lastActivity);
                        startActivity(detail_imunisasi);

                        // Close This Activity
                        finish();
                    }
                });

                jadwal_imunisasi.addView(row);
            } while (cursor.moveToNext());

            // Close Databsae
            db.close();
            cursor.close();
        }

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent imunisasi = new Intent(this, Imunisasi.class);
        lastActivity = System.currentTimeMillis();
        imunisasi.putExtra("lastActivity", lastActivity);
        startActivity(imunisasi);

        // Close This Activity
        finish();
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(JadwalImunisasi.this);

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

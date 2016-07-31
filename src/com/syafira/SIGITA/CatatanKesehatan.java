package com.syafira.SIGITA;

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
 * Created by syafira rarra on 05/01/2016.
 */

public class CatatanKesehatan extends Home{

    // Declare
    private TextView button_catatankesehatan;
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_kesehatan_tanggalberobat;
    private TextView text_kesehatan_keluhan;
    private TextView text_kesehatan_obat;
    private ImageView button_tambah;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.catatankesehatan);

        // Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_catatankesehatan = (TextView) findViewById(R.id.button_catatankesehatan);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_kesehatan_tanggalberobat = (TextView) findViewById(R.id.text_kesehatan_tanggalberobat);
        text_kesehatan_keluhan = (TextView) findViewById(R.id.text_kesehatan_keluhan);
        text_kesehatan_obat = (TextView) findViewById(R.id.text_kesehatan_obat);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_tambah = (ImageView) findViewById(R.id.button_tambah);

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_catatankesehatan.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_kesehatan_tanggalberobat.setTypeface(typeface);
        text_kesehatan_keluhan.setTypeface(typeface);
        text_kesehatan_obat.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));

            TableLayout catatan_kesehatan = (TableLayout) findViewById(R.id.catatan_kesehatan);

            // Get Data from Database
            Cursor cursor = db.getKesehatan(Integer.parseInt(session.loadSession(this, "id")));
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    TextView text_kesehatan_detail = (TextView) findViewById(R.id.text_kesehatan_detail);
                    text_kesehatan_detail.setVisibility(View.VISIBLE);

                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams kesehatan = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(kesehatan);

                    TextView tanggal = new TextView(this);
                    TextView keluhan = new TextView(this);
                    TextView obat = new TextView(this);
                    final ImageView detail = new ImageView(this);

                    TableRow.LayoutParams tanggal_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams keluhan_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams obat_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams detail_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, .8f);

                    detail.setImageResource(R.drawable.tombol_detail);
                    tanggal.setGravity(Gravity.CENTER_VERTICAL);
                    keluhan.setGravity(Gravity.CENTER_VERTICAL);
                    obat.setGravity(Gravity.CENTER_VERTICAL);

                    int margin_in_dp_1 = 1 ;//value in dp
                    int margin_in_dp_3 = 3 ;//value in dp
                    int margin_in_pixel_1 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_1, getResources()
                                    .getDisplayMetrics());
                    int margin_in_pixel_3 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_3, getResources()
                                    .getDisplayMetrics());
                    tanggal_text_view.setMargins(margin_in_pixel_3, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    keluhan_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    obat_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    detail_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_3, margin_in_pixel_1);

                    int padding_in_dp = 5 ;//value in dp
                    int padding_in_pixel = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, padding_in_dp, getResources()
                                    .getDisplayMetrics());
                    tanggal.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    keluhan.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    obat.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    detail.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);

                    tanggal.setLayoutParams(tanggal_text_view);
                    keluhan.setLayoutParams(keluhan_text_view);
                    obat.setLayoutParams(obat_text_view);
                    detail.setLayoutParams(detail_text_view);

                    tanggal.setBackgroundResource(R.drawable.border_row);
                    keluhan.setBackgroundResource(R.drawable.border_row);
                    obat.setBackgroundResource(R.drawable.border_row);
                    detail.setBackgroundResource(R.drawable.border_row);

                    tanggal.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tanggal")));
                    keluhan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_keluhan")));
                    obat.setText(cursor.getString(cursor.getColumnIndex("kesehatan_obat")));
                    final int id = cursor.getInt(cursor.getColumnIndex("kesehatanID"));

                    tanggal.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    keluhan.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    obat.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);

                    tanggal.setTextColor(Color.parseColor("#D046F2"));
                    keluhan.setTextColor(Color.parseColor("#D046F2"));
                    obat.setTextColor(Color.parseColor("#D046F2"));

                    tanggal.setTypeface(typeface);
                    keluhan.setTypeface(typeface);
                    obat.setTypeface(typeface);

                    row.addView(tanggal);
                    row.addView(keluhan);
                    row.addView(obat);
                    row.addView(detail);

                    detail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Show Detail Kesehatan Activity
                            Intent detail_kesehatan = new Intent(CatatanKesehatan.this, CatatanKesehatanDetail.class);
                            lastActivity = System.currentTimeMillis();
                            detail_kesehatan.putExtra("lastActivity", lastActivity);
                            detail_kesehatan.putExtra("id", id);
                            detail_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(detail_kesehatan);

                            // Close This Activity
                            finish();
                        }
                    });

                    catatan_kesehatan.addView(row);
                } while (cursor.moveToNext());

                // Close Databsae
                db.close();
                cursor.close();

            }

        // No session
        } else {
            // Start Home Activity
            Intent index = new Intent(CatatanKesehatan.this, Home.class);
            lastActivity = System.currentTimeMillis();
            index.putExtra("lastActivity", lastActivity);
            index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(index);
            finish();
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(CatatanKesehatan.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", "catatankesehatan");
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);
                finish();
            }
        });

        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent tambah_kesehatan = new Intent(CatatanKesehatan.this, CatatanKesehatanTambah.class);
                lastActivity = System.currentTimeMillis();
                tambah_kesehatan.putExtra("lastActivity", lastActivity);
                tambah_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tambah_kesehatan);

                finish();
            }
        });

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {

        // Start Home Activity
        Intent index = new Intent(this, Home.class);
        lastActivity = System.currentTimeMillis();
        index.putExtra("lastActivity", lastActivity);
        index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(index);

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
            session.clearSession(CatatanKesehatan.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        } else {
            
            // Check Session
            if (session.checkSession(this)) {
                // Set Profil Name
                text_button_profil.setText(session.loadSession(this, "nama"));
            } else {
                // Start Home Activity
                Intent index = new Intent(CatatanKesehatan.this, Home.class);
                lastActivity = System.currentTimeMillis();
                index.putExtra("lastActivity", lastActivity);
                index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(index);
                finish();
            }
        }
    }
}

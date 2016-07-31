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
 * Created by syafira rarra on 05/25/2016.
 */

public class GiziDokumentasi extends Gizi {

    // Declare
    private TextView button_dokumentasigizi;
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_dokumentasigizi_tanggal;
    private TextView text_dokumentasigizi_usia;
    private TextView text_dokumentasigizi_tinggibadan;
    private TextView text_dokumentasigizi_beratbadan;
    private ImageView button_tambah;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.gizi_dokumentasigizi);

        // Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_dokumentasigizi = (TextView) findViewById(R.id.button_dokumentasigizi);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_dokumentasigizi_tanggal = (TextView) findViewById(R.id.text_dokumentasigizi_tanggal);
        text_dokumentasigizi_usia = (TextView) findViewById(R.id.text_dokumentasigizi_usia);
        text_dokumentasigizi_tinggibadan = (TextView) findViewById(R.id.text_dokumentasigizi_tinggibadan);
        text_dokumentasigizi_beratbadan = (TextView) findViewById(R.id.text_dokumentasigizi_beratbadan);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_tambah = (ImageView) findViewById(R.id.button_tambah);

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_dokumentasigizi.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_dokumentasigizi_tanggal.setTypeface(typeface);
        text_dokumentasigizi_usia.setTypeface(typeface);
        text_dokumentasigizi_tinggibadan.setTypeface(typeface);
        text_dokumentasigizi_beratbadan.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));


            TableLayout dokumentasigizi = (TableLayout) findViewById(R.id.dokumentasi_gizi);

            // Get Data from Database
            Cursor cursor = db.getDokumentasi(Integer.parseInt(session.loadSession(this, "id")));
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    TextView text_dokumentasigizi_detail = (TextView) findViewById(R.id.text_dokumentasigizi_detail);
                    text_dokumentasigizi_detail.setVisibility(View.VISIBLE);

                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams gizi = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(gizi);

                    TextView tanggal = new TextView(this);
                    TextView usia = new TextView(this);
                    TextView tinggi = new TextView(this);
                    TextView berat = new TextView(this);
                    final ImageView detail = new ImageView(this);

                    TableRow.LayoutParams tanggal_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams usia_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams tinggi_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams berat_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams detail_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, .8f);

                    detail.setImageResource(R.drawable.tombol_detail);
                    tanggal.setGravity(Gravity.CENTER_VERTICAL);
                    usia.setGravity(Gravity.CENTER_VERTICAL);
                    tinggi.setGravity(Gravity.CENTER_VERTICAL);
                    berat.setGravity(Gravity.CENTER_VERTICAL);

                    int margin_in_dp_1 = 1 ;//value in dp
                    int margin_in_dp_3 = 3 ;//value in dp
                    int margin_in_pixel_1 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_1, getResources()
                                    .getDisplayMetrics());
                    int margin_in_pixel_3 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_3, getResources()
                                    .getDisplayMetrics());
                    tanggal_text_view.setMargins(margin_in_pixel_3, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    usia_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    tinggi_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    berat_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    detail_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_3, margin_in_pixel_1);

                    int padding_in_dp = 5;//value in dp
                    int padding_in_pixel = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, padding_in_dp, getResources()
                                    .getDisplayMetrics());
                    tanggal.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    usia.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    tinggi.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    berat.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    detail.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);

                    tanggal.setLayoutParams(tanggal_text_view);
                    usia.setLayoutParams(usia_text_view);
                    tinggi.setLayoutParams(tinggi_text_view);
                    berat.setLayoutParams(berat_text_view);
                    detail.setLayoutParams(detail_text_view);

                    tanggal.setBackgroundResource(R.drawable.border_row);
                    usia.setBackgroundResource(R.drawable.border_row);
                    berat.setBackgroundResource(R.drawable.border_row);
                    tinggi.setBackgroundResource(R.drawable.border_row);
                    detail.setBackgroundResource(R.drawable.border_row);

                    tanggal.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_tanggal")));
                    usia.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_usia")));
                    tinggi.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi")));
                    tinggi.append(" CM");
                    berat.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_berat")));
                    berat.append(" KG");
                    final int dokumentasiID = cursor.getInt(cursor.getColumnIndex("dokumentasiID"));

                    tanggal.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    usia.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    tinggi.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    berat.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);

                    tanggal.setTextColor(Color.parseColor("#D046F2"));
                    usia.setTextColor(Color.parseColor("#D046F2"));
                    tinggi.setTextColor(Color.parseColor("#D046F2"));
                    berat.setTextColor(Color.parseColor("#D046F2"));

                    tanggal.setTypeface(typeface);
                    usia.setTypeface(typeface);
                    tinggi.setTypeface(typeface);
                    berat.setTypeface(typeface);

                    row.addView(tanggal);
                    row.addView(usia);
                    row.addView(tinggi);
                    row.addView(berat);
                    row.addView(detail);

                    detail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Show Detail Profil Activity
                            Intent detail_dokumentasi = new Intent(GiziDokumentasi.this, GiziDokumentasiDetail.class);
                            lastActivity = System.currentTimeMillis();
                            detail_dokumentasi.putExtra("lastActivity", lastActivity);
                            detail_dokumentasi.putExtra("dokumentasiID", dokumentasiID);
                            detail_dokumentasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(detail_dokumentasi);

                            // Close This Activity
                            finish();
                        }
                    });

                    dokumentasigizi.addView(row);
                } while (cursor.moveToNext());

                // Close Databsae
                db.close();
                cursor.close();

            }
        } else {
            Intent gizi = new Intent(this, Gizi.class);
            lastActivity = System.currentTimeMillis();
            gizi.putExtra("lastActivity", lastActivity);
            gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gizi);
            finish();
        }

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(GiziDokumentasi.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", "dokumentasigizi");
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);
                finish();
            }
        });

        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent tambah_dokumentasi = new Intent(GiziDokumentasi.this, GiziDokumentasiTambah.class);
                lastActivity = System.currentTimeMillis();
                tambah_dokumentasi.putExtra("lastActivity", lastActivity);
                tambah_dokumentasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tambah_dokumentasi);

                finish();
            }
        });

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Home Activity
        Intent gizi = new Intent(this, Gizi.class);
        lastActivity = System.currentTimeMillis();
        gizi.putExtra("lastActivity", lastActivity);
        gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(gizi);

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
            session.clearSession(GiziDokumentasi.this);

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
                Intent index = new Intent(GiziDokumentasi.this, Home.class);
                lastActivity = System.currentTimeMillis();
                index.putExtra("lastActivity", lastActivity);
                index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(index);
                finish();
            }
        }
    }
}

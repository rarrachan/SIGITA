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

public class ImunisasiRiwayat extends Imunisasi {

    // Declare
    private TextView button_riwayatImunisasi;
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_tanggal_vaksin;
    private TextView text_jenis_vaksin;
    private TextView text_usia_vaksin;
    private ImageView button_tambah;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.riwayat_imunisasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_riwayatImunisasi = (TextView) findViewById(R.id.button_riwayatimunisasi);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_tanggal_vaksin = (TextView) findViewById(R.id.text_riwayat_tanggal);
        text_jenis_vaksin = (TextView) findViewById(R.id.text_riwayat_jenisvaksin);
        text_usia_vaksin = (TextView) findViewById(R.id.text_riwayat_usia);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_tambah = (ImageView) findViewById(R.id.button_tambah);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));

            TableLayout riwayat_imunisasi = (TableLayout) findViewById(R.id.riwayat_imunisasi);

            // Ser OnClickListener
            ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show Detail Profil Activity
                    Intent profil = new Intent(ImunisasiRiwayat.this, Profil.class);
                    lastActivity = System.currentTimeMillis();
                    profil.putExtra("lastActivity", lastActivity);
                    profil.putExtra("pathbefore", "riwayatimunisasi");
                    profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(profil);
                    finish();
                }
            });

            button_tambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show Detail Profil Activity
                    Intent tambah_riwayat = new Intent(ImunisasiRiwayat.this, ImunisasiRiwayatTambah.class);
                    lastActivity = System.currentTimeMillis();
                    tambah_riwayat.putExtra("lastActivity", lastActivity);
                    tambah_riwayat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(tambah_riwayat);
                    finish();
                }
            });

            // Set Custom Font
            final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
            button_riwayatImunisasi.setTypeface(typeface);
            text_button_profil.setTypeface(typeface);
            text_footer.setTypeface(typeface);
            text_tanggal_vaksin.setTypeface(typeface);
            text_jenis_vaksin.setTypeface(typeface);
            text_usia_vaksin.setTypeface(typeface);

            // Get Data from Database
            Cursor cursor = db.getRiwayat(Integer.parseInt(session.loadSession(this, "id")));
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    TextView text_riwayat_detail = (TextView) findViewById(R.id.text_riwayat_detail);
                    text_riwayat_detail.setVisibility(View.VISIBLE);

                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams riwayat = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(riwayat);

                    TextView tanggal = new TextView(this);
                    TextView jenisvaksin = new TextView(this);
                    TextView usia = new TextView(this);
                    final ImageView detail = new ImageView(this);

                    TableRow.LayoutParams tanggal_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams jenisvaksin_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams usia_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams detail_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, .8f);

                    detail.setImageResource(R.drawable.tombol_detail);
                    tanggal.setGravity(Gravity.CENTER_VERTICAL);
                    jenisvaksin.setGravity(Gravity.CENTER_VERTICAL);
                    usia.setGravity(Gravity.CENTER_VERTICAL);

                    int margin_in_dp_1 = 1;//value in dp
                    int margin_in_dp_3 = 3;//value in dp
                    int margin_in_pixel_1 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_1, getResources()
                                    .getDisplayMetrics());
                    int margin_in_pixel_3 = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, margin_in_dp_3, getResources()
                                    .getDisplayMetrics());
                    tanggal_text_view.setMargins(margin_in_pixel_3, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    jenisvaksin_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    usia_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_1);
                    detail_text_view.setMargins(margin_in_pixel_1, margin_in_pixel_1, margin_in_pixel_3, margin_in_pixel_1);

                    int padding_in_dp = 5;//value in dp
                    int padding_in_pixel = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, padding_in_dp, getResources()
                                    .getDisplayMetrics());
                    tanggal.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    jenisvaksin.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    usia.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);
                    detail.setPadding(padding_in_pixel, padding_in_pixel, padding_in_pixel, padding_in_pixel);

                    tanggal.setLayoutParams(tanggal_text_view);
                    jenisvaksin.setLayoutParams(jenisvaksin_text_view);
                    usia.setLayoutParams(usia_text_view);
                    detail.setLayoutParams(detail_text_view);

                    tanggal.setBackgroundResource(R.drawable.border_row);
                    jenisvaksin.setBackgroundResource(R.drawable.border_row);
                    usia.setBackgroundResource(R.drawable.border_row);
                    detail.setBackgroundResource(R.drawable.border_row);

                    tanggal.setText(cursor.getString(cursor.getColumnIndex("riwayat_tanggal")));
                    jenisvaksin.setText(cursor.getString(cursor.getColumnIndex("riwayat_vaksin")));
                    usia.setText(cursor.getString(cursor.getColumnIndex("riwayat_usia")));
                    final int id = cursor.getInt(cursor.getColumnIndex("riwayatID"));

                    tanggal.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    jenisvaksin.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                    usia.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);

                    tanggal.setTextColor(Color.parseColor("#D046F2"));
                    jenisvaksin.setTextColor(Color.parseColor("#D046F2"));
                    usia.setTextColor(Color.parseColor("#D046F2"));

                    tanggal.setTypeface(typeface);
                    jenisvaksin.setTypeface(typeface);
                    usia.setTypeface(typeface);

                    row.addView(tanggal);
                    row.addView(jenisvaksin);
                    row.addView(usia);
                    row.addView(detail);

                    detail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Show Detail Profil Activity
                            Intent detail_riwayat = new Intent(ImunisasiRiwayat.this, ImunisasiRiwayatDetail.class);
                            lastActivity = System.currentTimeMillis();
                            detail_riwayat.putExtra("lastActivity", lastActivity);
                            detail_riwayat.putExtra("id", id);
                            detail_riwayat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(detail_riwayat);

                            // Close This Activity
                            finish();
                        }
                    });

                    riwayat_imunisasi.addView(row);
                } while (cursor.moveToNext());

                // Close Databsae
                db.close();
                cursor.close();
            }

        // No session
        } else {
            // Start Imunisasi Activity
            Intent imunisasi = new Intent(ImunisasiRiwayat.this, Imunisasi.class);
            lastActivity = System.currentTimeMillis();
            imunisasi.putExtra("lastActivity", lastActivity);
            imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(imunisasi);
            finish();
        }

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {

        // Start Imunisasi Activity
        Intent imunisasi = new Intent(ImunisasiRiwayat.this, Imunisasi.class);
        lastActivity = System.currentTimeMillis();
        imunisasi.putExtra("lastActivity", lastActivity);
        imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            session.clearSession(ImunisasiRiwayat.this);

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
                // Start Imunisasi Activity
                Intent imunisasi = new Intent(ImunisasiRiwayat.this, Imunisasi.class);
                lastActivity = System.currentTimeMillis();
                imunisasi.putExtra("lastActivity", lastActivity);
                imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(imunisasi);
                finish();
            }
        }
    }

}

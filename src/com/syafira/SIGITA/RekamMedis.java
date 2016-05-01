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
 * Created by syafira rarra on 05/01/2016.
 */
public class RekamMedis extends Activity {

    // Declare
    private TextView text_footer;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_tanggal_medis;
    private TextView text_keluhan_medis;
    private TextView text_obat_medis;
    private ImageView button_tambah;
    private SessionManager session;
    private DBHelper db;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.rekam_medis);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_tanggal_medis = (TextView) findViewById(R.id.text_tanggal_medis);
        text_keluhan_medis = (TextView) findViewById(R.id.text_medis_keluhan);
        text_obat_medis = (TextView) findViewById(R.id.text_medis_obat);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_tambah = (ImageView) findViewById(R.id.button_tambah);

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_tanggal_medis.setTypeface(typeface);
        text_keluhan_medis.setTypeface(typeface);
        text_obat_medis.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));


            TableLayout rekam_medis = (TableLayout) findViewById(R.id.rekam_medis);

            // Get Data from Database
            Cursor cursor = db.getMedis(Integer.parseInt(session.loadSession(this, "id")));
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams medis = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(medis);

                    TextView tanggal = new TextView(this);
                    TextView keluhan = new TextView(this);
                    TextView obat = new TextView(this);
                    ImageView detail = new ImageView(this);

                    TableRow.LayoutParams tanggal_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams keluhan_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams obat_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                    TableRow.LayoutParams detail_text_view = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, .5f);

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

                    tanggal.setText(cursor.getString(cursor.getColumnIndex("medis_tanggal")));
                    keluhan.setText(cursor.getString(cursor.getColumnIndex("medis_keluhan")));
                    obat.setText(cursor.getString(cursor.getColumnIndex("medis_obat")));
                    final int id = cursor.getInt(cursor.getColumnIndex("medisID"));

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
                            // Show Detail Profil Activity
                            Intent detail_medis = new Intent(RekamMedis.this, DetailRekamMedis.class);
                            detail_medis.putExtra("id", id);
                            startActivity(detail_medis);

                            // Close This Activity
                            finish();
                        }
                    });

                    rekam_medis.addView(row);
                } while (cursor.moveToNext());

                // Close Databsae
                db.close();
                cursor.close();

            }
        } else {
            finish();
        }

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(RekamMedis.this, Profil.class);
                startActivity(profil);
            }
        });

        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent tambah_medis = new Intent(RekamMedis.this, TambahMedis.class);
                startActivity(tambah_medis);
            }
        });

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Index Activity
        Intent index = new Intent(this, Index.class);
        startActivity(index);

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

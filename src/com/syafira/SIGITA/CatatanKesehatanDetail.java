package com.syafira.SIGITA;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by syafira rarra on 05/01/2016.
 */

public class CatatanKesehatanDetail extends CatatanKesehatan {

    // Declare
    private TextView detail_catatan_kesehatan;
    private TextView text_footer;
    private TextView text_kesehatan_nama;
    private TextView kesehatan_nama;
    private TextView text_kesehatan_tanggalberobat;
    private TextView kesehatan_tanggalberobat;
    private TextView text_kesehatan_dokter;
    private TextView kesehatan_dokter;
    private TextView text_kesehatan_rumahsakit;
    private TextView kesehatan_rumahsakit;
    private TextView text_kesehatan_tinggibadan;
    private TextView kesehatan_tinggibadan;
    private TextView kesehatan_centimeter;
    private TextView text_kesehatan_beratbadan;
    private TextView kesehatan_beratbadan;
    private TextView kesehatan_kilogram;
    private TextView text_kesehatan_keluhan;
    private TextView kesehatan_keluhan;
    private TextView text_kesehatan_tindakan;
    private TextView kesehatan_tindakan;
    private TextView text_kesehatan_obat;
    private TextView kesehatan_obat;
    private ImageView button_hapus;
    private ImageView button_ubah;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.catatankesehatan_detail);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneKesehatan(id);
        cursor.moveToFirst();

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        detail_catatan_kesehatan = (TextView) findViewById(R.id.detail_catatan_kesehatan);
        text_kesehatan_nama = (TextView) findViewById(R.id.text_kesehatan_nama);
        kesehatan_nama = (TextView) findViewById(R.id.kesehatan_nama);
        text_kesehatan_tanggalberobat = (TextView) findViewById(R.id.text_kesehatan_tanggalberobat);
        kesehatan_tanggalberobat = (TextView) findViewById(R.id.kesehatan_tanggalberobat);
        text_kesehatan_dokter = (TextView) findViewById(R.id.text_kesehatan_dokter);
        kesehatan_dokter = (TextView) findViewById(R.id.kesehatan_dokter);
        text_kesehatan_rumahsakit = (TextView) findViewById(R.id.text_kesehatan_rumahsakit);
        kesehatan_rumahsakit = (TextView) findViewById(R.id.kesehatan_rumahsakit);
        text_kesehatan_tinggibadan = (TextView) findViewById(R.id.text_kesehatan_tinggibadan);
        kesehatan_tinggibadan = (TextView) findViewById(R.id.kesehatan_tinggibadan);
        kesehatan_centimeter = (TextView) findViewById(R.id.kesehatan_centimeter);
        text_kesehatan_beratbadan = (TextView) findViewById(R.id.text_kesehatan_beratbadan);
        kesehatan_beratbadan = (TextView) findViewById(R.id.kesehatan_beratbadan);
        kesehatan_tinggibadan = (TextView) findViewById(R.id.kesehatan_tinggibadan);
        kesehatan_kilogram = (TextView) findViewById(R.id.kesehatan_kilogram);
        text_kesehatan_keluhan = (TextView) findViewById(R.id.text_kesehatan_keluhan);
        kesehatan_keluhan = (TextView) findViewById(R.id.kesehatan_keluhan);
        text_kesehatan_tindakan = (TextView) findViewById(R.id.text_kesehatan_tindakan);
        kesehatan_tindakan = (TextView) findViewById(R.id.kesehatan_tindakan);
        text_kesehatan_obat = (TextView) findViewById(R.id.text_kesehatan_obat);
        kesehatan_obat = (TextView) findViewById(R.id.kesehatan_obat);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_catatan_kesehatan.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_kesehatan_nama.setTypeface(typeface);
        kesehatan_nama.setTypeface(typeface);
        text_kesehatan_tanggalberobat.setTypeface(typeface);
        kesehatan_tanggalberobat.setTypeface(typeface);
        text_kesehatan_dokter.setTypeface(typeface);
        kesehatan_dokter.setTypeface(typeface);
        text_kesehatan_rumahsakit.setTypeface(typeface);
        kesehatan_rumahsakit.setTypeface(typeface);
        text_kesehatan_tinggibadan.setTypeface(typeface);
        kesehatan_tinggibadan.setTypeface(typeface);
        kesehatan_centimeter.setTypeface(typeface);
        text_kesehatan_beratbadan.setTypeface(typeface);
        kesehatan_beratbadan.setTypeface(typeface);
        kesehatan_kilogram.setTypeface(typeface);
        text_kesehatan_keluhan.setTypeface(typeface);
        kesehatan_keluhan.setTypeface(typeface);
        text_kesehatan_tindakan.setTypeface(typeface);
        kesehatan_tindakan.setTypeface(typeface);
        text_kesehatan_obat.setTypeface(typeface);
        kesehatan_obat.setTypeface(typeface);

        // Show Data From Database
        kesehatan_nama.setText(session.loadSession(this, "nama"));
        final int kesehatanID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("kesehatanID")));
        kesehatan_tanggalberobat.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tanggal")));
        kesehatan_keluhan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_keluhan")));
        kesehatan_tindakan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tindakan")));
        kesehatan_obat.setText(cursor.getString(cursor.getColumnIndex("kesehatan_obat")));
        if(!cursor.getString(cursor.getColumnIndex("kesehatan_dokter")).equals("") ) {
            kesehatan_dokter.setText(cursor.getString(cursor.getColumnIndex("kesehatan_dokter")));
        }
        if(!cursor.getString(cursor.getColumnIndex("kesehatan_rumahsakit")).equals("") ) {
            kesehatan_rumahsakit.setText(cursor.getString(cursor.getColumnIndex("kesehatan_rumahsakit")));
        }
        if(!cursor.getString(cursor.getColumnIndex("kesehatan_berat")).equals("") ) {
            kesehatan_beratbadan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_berat")));
        }
        if(!cursor.getString(cursor.getColumnIndex("kesehatan_tinggi")).equals("") ) {
            kesehatan_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tinggi")));
        }

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Kesehatan Activity
                Intent ubah_kesehatan = new Intent(CatatanKesehatanDetail.this, CatatanKesehatanUbah.class);

                // Put Intent Extra
                lastActivity = System.currentTimeMillis();
                ubah_kesehatan.putExtra("lastActivity", lastActivity);
                ubah_kesehatan.putExtra("id", kesehatanID);
                ubah_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ubah_kesehatan);

                finish();
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(CatatanKesehatanDetail.this);
                dialog.setContentView(R.layout.catatankesehatan_hapus);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                // Load Dialog Widget
                TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                TextView alert_hapus_kesehatan = (TextView) dialog.findViewById(R.id.alert_hapus);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_hapus_kesehatan.setTypeface(typeface);
                alert_warning.setTypeface(typeface);

                // Set OnClickListener Dialog
                button_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close Dialog
                        dialog.dismiss();
                    }
                });
                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show Hapus Kesehatan Activity
                        Intent hapus_kesehatan = new Intent(CatatanKesehatanDetail.this, CatatanKesehatanHapus.class);
                        hapus_kesehatan.putExtra("id", kesehatanID);
                        hapus_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(hapus_kesehatan);

                        // Close This Activity
                        finish();

                        // Close Dialog
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    // Pressed Back Button
    @Override
    public void onBackPressed() {
        
        // Show Catatan Kesehatan Activity
        Intent kesehatan = new Intent(CatatanKesehatanDetail.this, CatatanKesehatan.class);
        lastActivity = System.currentTimeMillis();
        kesehatan.putExtra("lastActivity", lastActivity);
        kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(kesehatan);
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
            session.clearSession(CatatanKesehatanDetail.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

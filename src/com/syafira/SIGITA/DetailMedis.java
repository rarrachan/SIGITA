package com.syafira.SIGITA;

import android.app.Activity;
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

public class DetailMedis extends Activity {

    // Declare
    private TextView detail_rekam_medis;
    private TextView text_footer;
    private TextView text_medis_nama;
    private TextView medis_nama;
    private TextView text_medis_tanggalberobat;
    private TextView medis_tanggalberobat;
    private TextView text_medis_dokter;
    private TextView medis_dokter;
    private TextView text_medis_rumahsakit;
    private TextView medis_rumahsakit;
    private TextView text_medis_tinggibadan;
    private TextView medis_tinggibadan;
    private TextView medis_centimeter;
    private TextView text_medis_beratbadan;
    private TextView medis_beratbadan;
    private TextView medis_kilogram;
    private TextView text_medis_keluhan;
    private TextView medis_keluhan;
    private TextView text_medis_tindakan;
    private TextView medis_tindakan;
    private TextView text_medis_obat;
    private TextView medis_obat;
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
        setContentView(R.layout.detail_medis);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneMedis(id);
        cursor.moveToFirst();

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        detail_rekam_medis = (TextView) findViewById(R.id.detail_rekam_medis);
        text_medis_nama = (TextView) findViewById(R.id.text_medis_nama);
        medis_nama = (TextView) findViewById(R.id.medis_nama);
        text_medis_tanggalberobat = (TextView) findViewById(R.id.text_medis_tanggalberobat);
        medis_tanggalberobat = (TextView) findViewById(R.id.medis_tanggalberobat);
        text_medis_dokter = (TextView) findViewById(R.id.text_medis_dokter);
        medis_dokter = (TextView) findViewById(R.id.medis_dokter);
        text_medis_rumahsakit = (TextView) findViewById(R.id.text_medis_rumahsakit);
        medis_rumahsakit = (TextView) findViewById(R.id.medis_rumahsakit);
        text_medis_tinggibadan = (TextView) findViewById(R.id.text_medis_tinggibadan);
        medis_tinggibadan = (TextView) findViewById(R.id.medis_tinggibadan);
        medis_centimeter = (TextView) findViewById(R.id.medis_centimeter);
        text_medis_beratbadan = (TextView) findViewById(R.id.text_medis_beratbadan);
        medis_beratbadan = (TextView) findViewById(R.id.medis_beratbadan);
        medis_tinggibadan = (TextView) findViewById(R.id.medis_tinggibadan);
        medis_kilogram = (TextView) findViewById(R.id.medis_kilogram);
        text_medis_keluhan = (TextView) findViewById(R.id.text_medis_keluhan);
        medis_keluhan = (TextView) findViewById(R.id.medis_keluhan);
        text_medis_tindakan = (TextView) findViewById(R.id.text_medis_tindakan);
        medis_tindakan = (TextView) findViewById(R.id.medis_tindakan);
        text_medis_obat = (TextView) findViewById(R.id.text_medis_obat);
        medis_obat = (TextView) findViewById(R.id.medis_obat);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_rekam_medis.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_medis_nama.setTypeface(typeface);
        medis_nama.setTypeface(typeface);
        text_medis_tanggalberobat.setTypeface(typeface);
        medis_tanggalberobat.setTypeface(typeface);
        text_medis_dokter.setTypeface(typeface);
        medis_dokter.setTypeface(typeface);
        text_medis_rumahsakit.setTypeface(typeface);
        medis_rumahsakit.setTypeface(typeface);
        text_medis_tinggibadan.setTypeface(typeface);
        medis_tinggibadan.setTypeface(typeface);
        medis_centimeter.setTypeface(typeface);
        text_medis_beratbadan.setTypeface(typeface);
        medis_beratbadan.setTypeface(typeface);
        medis_kilogram.setTypeface(typeface);
        text_medis_keluhan.setTypeface(typeface);
        medis_keluhan.setTypeface(typeface);
        text_medis_tindakan.setTypeface(typeface);
        medis_tindakan.setTypeface(typeface);
        text_medis_obat.setTypeface(typeface);
        medis_obat.setTypeface(typeface);

        // Show Data From Database
        medis_nama.setText(session.loadSession(this, "nama"));
        final int medisID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("medisID")));
        medis_tanggalberobat.setText(cursor.getString(cursor.getColumnIndex("medis_tanggal")));
        medis_keluhan.setText(cursor.getString(cursor.getColumnIndex("medis_keluhan")));
        medis_tindakan.setText(cursor.getString(cursor.getColumnIndex("medis_tindakan")));
        medis_obat.setText(cursor.getString(cursor.getColumnIndex("medis_obat")));
        if(!cursor.getString(cursor.getColumnIndex("medis_dokter")).equals("") ) {
            medis_dokter.setText(cursor.getString(cursor.getColumnIndex("medis_dokter")));
        }
        if(!cursor.getString(cursor.getColumnIndex("medis_rumahsakit")).equals("") ) {
            medis_rumahsakit.setText(cursor.getString(cursor.getColumnIndex("medis_rumahsakit")));
        }
        if(!cursor.getString(cursor.getColumnIndex("medis_berat")).equals("") ) {
            medis_beratbadan.setText(cursor.getString(cursor.getColumnIndex("medis_berat")));
        }
        if(!cursor.getString(cursor.getColumnIndex("medis_tinggi")).equals("") ) {
            medis_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("medis_tinggi")));
        }

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Medis Activity
                Intent ubah_medis = new Intent(DetailMedis.this, UbahMedis.class);

                // Put Intent Extra
                lastActivity = System.currentTimeMillis();
                ubah_medis.putExtra("lastActivity", lastActivity);
                ubah_medis.putExtra("id", medisID);
                ubah_medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ubah_medis);

                finish();
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(DetailMedis.this);
                dialog.setContentView(R.layout.hapus_medis);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                // Load Dialog Widget
                TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                TextView alert_hapus_medis = (TextView) dialog.findViewById(R.id.alert_hapus);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_hapus_medis.setTypeface(typeface);
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
                        // Declare Condition
                        boolean success = false;

                        try {
                            // Delete From Database
                            db.deleteMedis(medisID);
                            success = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(DetailMedis.this, "Rekam Medis Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(DetailMedis.this, "Rekam Medis Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Rekam Medis Activity
                        Intent medis = new Intent(DetailMedis.this, RekamMedis.class);
                        lastActivity = System.currentTimeMillis();
                        medis.putExtra("lastActivity", lastActivity);
                        medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(medis);

                        // Clear Activity
                        finish();
                    }
                });
            }
        });
    }


    // Pressed Back Button
    @Override
    public void onBackPressed() {
        
        // Show Rekam Medis Activity
        Intent medis = new Intent(DetailMedis.this, RekamMedis.class);
        lastActivity = System.currentTimeMillis();
        medis.putExtra("lastActivity", lastActivity);
        medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(medis);
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
            session.clearSession(DetailMedis.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

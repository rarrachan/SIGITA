package com.syafira.SIGITA;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by syafira rarra on 05/13/2016.
 */
public class DetailRiwayat extends Activity {

    private TextView detail_riwayat;
    private TextView text_footer;
    private TextView titikdua;
    private TextView text_riwayat_nama;
    private TextView riwayat_nama;
    private TextView text_riwayat_usia;
    private TextView riwayat_usia;
    private TextView riwayat_bulan;
    private TextView text_riwayat_dokter;
    private TextView riwayat_dokter;
    private TextView text_riwayat_rumahsakit;
    private TextView riwayat_rumahsakit;
    private TextView text_riwayat_tanggal;
    private TextView riwayat_tanggal;
    private TextView text_riwayat_jenisvaksin;
    private TextView riwayat_jenisvaksin;
    private TextView text_riwayat_tinggibadan;
    private TextView riwayat_tinggibadan;
    private TextView riwayat_centimeter;
    private TextView text_riwayat_beratbadan;
    private TextView riwayat_beratbadan;
    private TextView riwayat_kilogram;
    private ImageView button_ubah;
    private ImageView button_hapus;
    private DBHelper db;
    private SessionManager session;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_riwayat);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneRiwayat(id);
        cursor.moveToFirst();

        // Load Widget
        detail_riwayat = (TextView) findViewById(R.id.detail_riwayat);
        text_footer = (TextView) findViewById(R.id.text_footer);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_riwayat_nama = (TextView) findViewById(R.id.text_riwayat_nama);
        riwayat_nama = (TextView) findViewById(R.id.riwayat_nama);
        text_riwayat_usia = (TextView) findViewById(R.id.text_riwayat_usia);
        riwayat_usia = (TextView) findViewById(R.id.riwayat_usia);
        riwayat_bulan = (TextView) findViewById(R.id.riwayat_bulan);
        text_riwayat_dokter = (TextView) findViewById(R.id.text_riwayat_dokter);
        riwayat_dokter = (TextView) findViewById(R.id.riwayat_dokter);
        text_riwayat_rumahsakit = (TextView) findViewById(R.id.text_riwayat_rumahsakit);
        riwayat_rumahsakit = (TextView) findViewById(R.id.riwayat_rumahsakit);
        text_riwayat_tanggal = (TextView) findViewById(R.id.text_riwayat_tanggal);
        riwayat_tanggal = (TextView) findViewById(R.id.riwayat_tanggal);
        text_riwayat_jenisvaksin = (TextView) findViewById(R.id.text_riwayat_jenisvaksin);
        riwayat_jenisvaksin = (TextView) findViewById(R.id.riwayat_jenisvaksin);
        text_riwayat_tinggibadan = (TextView) findViewById(R.id.text_riwayat_tinggibadan);
        riwayat_tinggibadan = (TextView) findViewById(R.id.riwayat_tinggibadan);
        riwayat_centimeter = (TextView) findViewById(R.id.riwayat_centimeter);
        text_riwayat_beratbadan = (TextView) findViewById(R.id.text_riwayat_beratbadan);
        riwayat_beratbadan = (TextView) findViewById(R.id.riwayat_beratbadan);
        riwayat_kilogram = (TextView) findViewById(R.id.riwayat_kilogram);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_riwayat.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_riwayat_nama.setTypeface(typeface);
        riwayat_nama.setTypeface(typeface);
        text_riwayat_usia.setTypeface(typeface);
        riwayat_usia.setTypeface(typeface);
        text_riwayat_dokter.setTypeface(typeface);
        riwayat_dokter.setTypeface(typeface);
        text_riwayat_rumahsakit.setTypeface(typeface);
        riwayat_rumahsakit.setTypeface(typeface);
        text_riwayat_tanggal.setTypeface(typeface);
        riwayat_tanggal.setTypeface(typeface);
        text_riwayat_jenisvaksin.setTypeface(typeface);
        text_riwayat_tinggibadan.setTypeface(typeface);
        riwayat_tinggibadan.setTypeface(typeface);
        riwayat_centimeter.setTypeface(typeface);
        text_riwayat_beratbadan.setTypeface(typeface);
        riwayat_beratbadan.setTypeface(typeface);
        riwayat_kilogram.setTypeface(typeface);

        riwayat_nama.setText(session.loadSession(this, "nama"));
        final int riwayatID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("riwayatID")));
        riwayat_bulan.setText(cursor.getString(cursor.getColumnIndex("riwayat_bulan")));
        riwayat_jenisvaksin.setText(cursor.getString(cursor.getColumnIndex("riwayat_vaksin")));
        riwayat_usia.setText(cursor.getString(cursor.getColumnIndex("riwayat_umur")));
            riwayat_tanggal.setText(cursor.getString(cursor.getColumnIndex("riwayat_tanggal")));
        if(!cursor.getString(cursor.getColumnIndex("riwayat_berat")).equals("") ) {
            riwayat_beratbadan.setText(cursor.getString(cursor.getColumnIndex("riwayat_berat")));
        }
        if(!cursor.getString(cursor.getColumnIndex("riwayat_tinggi")).equals("") ) {
            riwayat_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("riwayat_tinggi")));
        }
        if(!cursor.getString(cursor.getColumnIndex("riwayat_dokter")).equals("") ) {
            riwayat_dokter.setText(cursor.getString(cursor.getColumnIndex("riwayat_dokter")));
        }
        if(!cursor.getString(cursor.getColumnIndex("riwayat_rumahsakit")).equals("") ) {
            riwayat_rumahsakit.setText(cursor.getString(cursor.getColumnIndex("riwayat_rumahsakit")));
        }

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Riwayat Activity
                Intent ubah_riwayat = new Intent(DetailRiwayat.this, UbahRiwayat.class);
                // Put Intent Extra
                ubah_riwayat.putExtra("id", riwayatID);
                startActivity(ubah_riwayat);
                finish();
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(DetailRiwayat.this);
                dialog.setContentView(R.layout.hapus_riwayat);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                // Load Dialog Widget
                TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                TextView alert_hapus = (TextView) dialog.findViewById(R.id.alert_hapus);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_hapus.setTypeface(typeface);
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
                            db.deleteRiwayat(id);
                            success = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(DetailRiwayat.this, "Riwayat Imunisasi Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(DetailRiwayat.this, "Riwayat Imunisasi Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Riwayat Imunisai Activity
                        Intent riwayat = new Intent(DetailRiwayat.this, RiwayatImunisasi.class);
                        startActivity(riwayat);

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
        // Start Riwayat Imunisasi Activity
        Intent riwayat = new Intent(DetailRiwayat.this, RiwayatImunisasi.class);
        startActivity(riwayat);

        // Close This Activity
        finish();
    }
}

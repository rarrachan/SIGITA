package com.syafira.SIGITA;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by syafira rarra on 05/07/2016.
 */

public class TumbuhKembangGaleriDetail extends TumbuhKembangGaleri {

    // Declare
    private TextView detail_galeri;
    private TextView text_footer;
    private TextView text_galeri_nama;
    private TextView galeri_nama;
    private TextView text_galeri_tanggal;
    private TextView galeri_tanggal;
    private TextView text_galeri_usia;
    private TextView galeri_usia;
    private TextView text_galeri_keterangan;
    private TextView galeri_keterangan;
    private TextView text_galeri_foto;
    private ImageView galeri_foto;
    private ImageView button_ubah;
    private ImageView button_hapus;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tumbang_galeri_detail);

        // Session Manager
        session = new SessionManager();
        int profilID = Integer.parseInt(session.loadSession(this, "id"));

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int galeriID = fetchID.getIntExtra("galeriID", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneGaleri(galeriID, profilID);
        cursor.moveToFirst();

        // Load Widget
        detail_galeri = (TextView) findViewById(R.id.detail_galeri);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_galeri_nama = (TextView) findViewById(R.id.text_galeri_nama);
        galeri_nama = (TextView) findViewById(R.id.galeri_nama);
        text_galeri_tanggal = (TextView) findViewById(R.id.text_galeri_tanggal);
        galeri_tanggal = (TextView) findViewById(R.id.galeri_tanggal);
        text_galeri_usia = (TextView) findViewById(R.id.text_galeri_usia);
        galeri_usia = (TextView) findViewById(R.id.galeri_usia);
        text_galeri_keterangan = (TextView) findViewById(R.id.text_galeri_keterangan);
        galeri_keterangan = (TextView) findViewById(R.id.galeri_keterangan);
        text_galeri_foto = (TextView) findViewById(R.id.text_galeri_foto);
        galeri_foto = (ImageView) findViewById(R.id.galeri_foto);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_galeri.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_nama.setTypeface(typeface);
        galeri_nama.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);
        text_galeri_keterangan.setTypeface(typeface);
        galeri_keterangan.setTypeface(typeface);
        text_galeri_foto.setTypeface(typeface);

        // Set Text
        final String nama = session.loadSession(this, "nama");
        galeri_nama.setText(nama);
        galeri_tanggal.setText(cursor.getString(cursor.getColumnIndex("galeri_tanggal")));
        galeri_usia.setText(cursor.getString(cursor.getColumnIndex("galeri_usia")));
        galeri_keterangan.setText(cursor.getString(cursor.getColumnIndex("galeri_deskripsi")));
        final String galeri_foto_db = cursor.getString(cursor.getColumnIndex("galeri_foto"));
        final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + galeri_foto_db;
        galeri_foto.setImageDrawable(Drawable.createFromPath(foto_path));

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show Ubah Galeri Activity
                Intent ubah_galeri = new Intent(TumbuhKembangGaleriDetail.this, TumbuhKembangGaleriUbah.class);
                // Put Intent Extra
                lastActivity = System.currentTimeMillis();
                ubah_galeri.putExtra("lastActivity", lastActivity);
                ubah_galeri.putExtra("galeriID", galeriID);
                ubah_galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ubah_galeri);
                finish();
            }
        });

        galeri_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Image Zoom Activity
                Intent zoom = new Intent(TumbuhKembangGaleriDetail.this, ImageZoom.class);
                lastActivity = System.currentTimeMillis();
                zoom.putExtra("lastActivity", lastActivity);
                zoom.putExtra("foto_path", foto_path);
                startActivity(zoom);
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(TumbuhKembangGaleriDetail.this);
                dialog.setContentView(R.layout.tumbang_galeri_hapus);
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

                        // Show Hapus Galeri Activity
                        Intent hapus_galeri = new Intent(TumbuhKembangGaleriDetail.this, TumbuhKembangGaleriHapus.class);
                        // Put Intent Extra
                        hapus_galeri.putExtra("galeriID", galeriID);
                        hapus_galeri.putExtra("nama", nama);
                        hapus_galeri.putExtra("galeri_foto_db", galeri_foto_db);
                        hapus_galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(hapus_galeri);
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
        // Start Galeri Activity
        Intent galeri = new Intent(TumbuhKembangGaleriDetail.this, TumbuhKembangGaleri.class);
        lastActivity = System.currentTimeMillis();
        galeri.putExtra("lastActivity", lastActivity);
        galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(galeri);

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
            session.clearSession(TumbuhKembangGaleriDetail.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}
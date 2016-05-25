package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/02/2016.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class DetailProfil extends Activity {

    // Declare
    private TextView detail_profil;
    private TextView text_profil_nama;
    private TextView profil_nama;
    private TextView text_profil_jeniskelamin;
    private TextView profil_jeniskelamin;
    private TextView profil_golongandarah;
    private TextView text_profil_golongandarah;
    private TextView text_profil_tempatlahir;
    private TextView text_profil_tanggallahir;
    private TextView profil_tempatlahir;
    private TextView profil_tanggallahir;
    private TextView text_profil_alergi;
    private TextView profil_alergi;
    private TextView text_profil_penyakitkronis;
    private TextView profil_penyakitkronis;
    private TextView text_profil_panjanglahir;
    private TextView profil_panjanglahir;
    private TextView profil_centimeter;
    private TextView text_profil_beratlahir;
    private TextView profil_beratlahir;
    private TextView profil_gram;
    private TextView text_footer;
    private TextView titikdua;
    private TextView text_profil_foto;
    private ImageView button_ubah;
    private ImageView button_hapus;
    private ImageView profil_foto;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_profil);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneProfil(id);
        cursor.moveToFirst();

        // Session Manager
        session = new SessionManager();

        // Load Widget
        detail_profil = (TextView) findViewById(R.id.detail_profil);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_profil_nama = (TextView) findViewById(R.id.text_profil_nama);
        profil_nama = (TextView) findViewById(R.id.profil_nama);
        text_profil_jeniskelamin = (TextView) findViewById(R.id.text_profil_jeniskelamin);
        profil_jeniskelamin = (TextView) findViewById(R.id.profil_jeniskelamin);
        profil_golongandarah = (TextView) findViewById(R.id.profil_golongandarah);
        text_profil_golongandarah = (TextView) findViewById(R.id.text_profil_golongandarah);
        text_profil_tempatlahir = (TextView) findViewById(R.id.text_profil_tempatlahir);
        text_profil_tanggallahir = (TextView) findViewById(R.id.text_profil_tanggallahir);
        profil_tempatlahir = (TextView) findViewById(R.id.profil_tempatlahir);
        profil_tanggallahir = (TextView) findViewById(R.id.profil_tanggallahir);
        text_profil_alergi = (TextView) findViewById(R.id.text_profil_alergi);
        profil_alergi = (TextView) findViewById(R.id.profil_alergi);
        text_profil_penyakitkronis = (TextView) findViewById(R.id.text_profil_penyakitkronis);
        profil_penyakitkronis = (TextView) findViewById(R.id.profil_penyakitkronis);
        text_profil_panjanglahir = (TextView) findViewById(R.id.text_profil_panjanglahir);
        profil_panjanglahir = (TextView) findViewById(R.id.profil_panjanglahir);
        profil_centimeter = (TextView) findViewById(R.id.profil_centimeter);
        text_profil_beratlahir = (TextView) findViewById(R.id.text_profil_beratlahir);
        profil_beratlahir = (TextView) findViewById(R.id.profil_beratlahir);
        profil_gram = (TextView) findViewById(R.id.profil_gram);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_profil_foto = (TextView) findViewById(R.id.text_profil_foto);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);
        profil_foto = (ImageView) findViewById(R.id.profil_foto);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_profil.setTypeface(typeface);
        text_profil_foto.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_profil_nama.setTypeface(typeface);
        profil_nama.setTypeface(typeface);
        text_profil_jeniskelamin.setTypeface(typeface);
        profil_jeniskelamin.setTypeface(typeface);
        profil_golongandarah.setTypeface(typeface);
        text_profil_golongandarah.setTypeface(typeface);
        text_profil_tempatlahir.setTypeface(typeface);
        text_profil_tanggallahir.setTypeface(typeface);
        profil_tempatlahir.setTypeface(typeface);
        profil_tanggallahir.setTypeface(typeface);
        text_profil_alergi.setTypeface(typeface);
        profil_alergi.setTypeface(typeface);
        text_profil_penyakitkronis.setTypeface(typeface);
        profil_penyakitkronis.setTypeface(typeface);
        text_profil_panjanglahir.setTypeface(typeface);
        profil_panjanglahir.setTypeface(typeface);
        profil_centimeter.setTypeface(typeface);
        text_profil_beratlahir.setTypeface(typeface);
        profil_beratlahir.setTypeface(typeface);
        profil_gram.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Show Data From Database
        final int profil_id = cursor.getInt(cursor.getColumnIndex("profilID"));
        final String nama = cursor.getString(cursor.getColumnIndex("profil_nama"));
        profil_nama.setText(nama);
        if (cursor.getString(cursor.getColumnIndex("profil_jenisKelamin")).equals("L")) {
            profil_jeniskelamin.setText("Laki-Laki");
        } else {
            profil_jeniskelamin.setText("Perempuan");
        }
        profil_golongandarah.setText(cursor.getString(cursor.getColumnIndex("profil_golonganDarah")));
        profil_panjanglahir.setText(cursor.getString(cursor.getColumnIndex("profil_panjangLahir")));
        profil_beratlahir.setText(cursor.getString(cursor.getColumnIndex("profil_beratLahir")));
        profil_tempatlahir.setText(cursor.getString(cursor.getColumnIndex("profil_tempatLahir")));
        profil_tanggallahir.setText(cursor.getString(cursor.getColumnIndex("profil_tanggalLahir")));
        if (!cursor.getString(cursor.getColumnIndex("profil_alergi")).equals("")) {
            profil_alergi.setText(cursor.getString(cursor.getColumnIndex("profil_alergi")));
        }
        if (!cursor.getString(cursor.getColumnIndex("profil_penyakitKronis")).equals("")) {
            profil_penyakitkronis.setText(cursor.getString(cursor.getColumnIndex("profil_penyakitKronis")));
        }
        final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("profil_foto"));
        profil_foto.setImageDrawable(Drawable.createFromPath(foto_path));


        // Set OnClickListener
        button_ubah.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Profil Activity
                Intent ubah_profil = new Intent(DetailProfil.this, UbahProfil.class);
                // Put Intent Extra
                lastActivity = System.currentTimeMillis();
                ubah_profil.putExtra("lastActivity", lastActivity);
                ubah_profil.putExtra("id", profil_id);
                ubah_profil.putExtra("nama", nama);
                startActivity(ubah_profil);
                finish();
            }
        });
        profil_foto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Image Zoom Activity
                Intent zoom = new Intent(DetailProfil.this, ImageZoom.class);
                lastActivity = System.currentTimeMillis();
                zoom.putExtra("lastActivity", lastActivity);
                zoom.putExtra("foto_path", foto_path);
                startActivity(zoom);
            }
        });
        button_hapus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(DetailProfil.this);
                dialog.setContentView(R.layout.hapus_profil);
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
                button_batal.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close Dialog
                        dialog.dismiss();
                    }
                });
                button_ok.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Declare Condition
                        boolean success = false;

                        try {

                            // Turn Off Alarm Imunisasi
                            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            if (PendingIntent.getBroadcast(DetailProfil.this, profil_id,
                                    new Intent(DetailProfil.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE) != null) {
                                PendingIntent.getBroadcast(DetailProfil.this, profil_id,
                                        new Intent(DetailProfil.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE).cancel();
                                alarmManager.cancel(PendingIntent.getBroadcast(DetailProfil.this, profil_id,
                                        new Intent(DetailProfil.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE));
                            }

                            // Delete Folder Directory
                            File profilDirectory = new File(Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_"));
                            if (profilDirectory.isDirectory()) {
                                String[] children = profilDirectory.list();
                                for (String aChildren : children) {
                                    new File(profilDirectory, aChildren).delete();
                                }
                                profilDirectory.delete();
                            }

                            // Scan Gallery
                            File scanGallery = new File(Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_"), cursor.getString(cursor.getColumnIndex("profil_foto")));
                            MediaScannerConnection.scanFile(DetailProfil.this,
                                    new String[]{scanGallery.toString()}, null,
                                    new MediaScannerConnection.OnScanCompletedListener() {
                                        public void onScanCompleted(String path, Uri uri) {
                                        }
                                    });

                            // Delete From Database
                            db.deleteDokumentasiProfilID(profil_id);
                            db.deleteRiwayatProfilID(profil_id);
                            db.deleteGaleriProfilID(profil_id);
                            db.deleteMedisProfilID(profil_id);
                            db.deleteProfil(profil_id);

                            // Declare Condition
                            success = true;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(DetailProfil.this, "Profil Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(DetailProfil.this, "Profil Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Profil Activity
                        Intent profil = new Intent(DetailProfil.this, Profil.class);
                        lastActivity = System.currentTimeMillis();
                        profil.putExtra("lastActivity", lastActivity);
                        startActivity(profil);

                        // Clear Session
                        session.clearSession(DetailProfil.this);

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
        // Start Profil Activity
        Intent profil = new Intent(DetailProfil.this, Profil.class);
        lastActivity = System.currentTimeMillis();
        profil.putExtra("lastActivity", lastActivity);
        startActivity(profil);

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
            session.clearSession(DetailProfil.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}
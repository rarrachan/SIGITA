package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Typeface;

public class Profil extends Home {

    // Declare
    private TextView text_button_tambahprofil;
    private TextView text_button_profil;
    private TextView text_footer;
    private ImageView button_anaklaki;
    private ImageView button_anakperempuan;
    private LinearLayout TambahProfilLinearLayout;
    private LinearLayout ListProfilLinearLayout;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.profil);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        text_button_tambahprofil = (TextView) findViewById(R.id.text_button_tambahprofil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        TambahProfilLinearLayout = (LinearLayout) findViewById(R.id.TambahProfilLinearLayout);
        ListProfilLinearLayout = (LinearLayout) findViewById(R.id.ListProfilLinearLayout);

        // Set OnClickListener
        TambahProfilLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Tambah Profil Activity
                Intent tambah_profil = new Intent(Profil.this, ProfilTambah.class);
                lastActivity = System.currentTimeMillis();
                tambah_profil.putExtra("lastActivity", lastActivity);
                tambah_profil.putExtra("pathbefore", pathbefore);
                tambah_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                tambah_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tambah_profil);

                // Close This Activity
                finish();
            }
        });

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_tambahprofil.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Get Data from Database
        final Cursor cursor = db.getProfil();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                // Inlate New Dynamic Layout
                View profil_layout = getLayoutInflater().inflate(R.layout.profil_layout, ListProfilLinearLayout, false);

                // Load New Layout
                text_button_profil = (TextView) profil_layout.findViewById(R.id.text_button_profil);

                // Set Custom Font
                text_button_profil.setTypeface(typeface);

                // Put Data from Database into Layout
                text_button_profil.setText(cursor.getString(cursor.getColumnIndex("profil_nama")));
                final String nama = text_button_profil.getText().toString();
                final int id = cursor.getInt(cursor.getColumnIndex("profilID"));
                final String tanggallahir = cursor.getString(cursor.getColumnIndex("profil_tanggalLahir"));
                final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("profil_foto"));
                final String gender = cursor.getString(cursor.getColumnIndex("profil_jenisKelamin"));
                final String passcode = cursor.getString(cursor.getColumnIndex("profil_passcode"));
                
                // Check Gender
                if (gender.equals("Laki-laki")) {

                    // Hide Perempuan Image
                    button_anakperempuan = (ImageView) profil_layout.findViewById(R.id.button_anakperempuan);
                    button_anakperempuan.setVisibility(View.GONE);
                } else {

                    // Hide Lakilaki Image
                    button_anaklaki = (ImageView) profil_layout.findViewById(R.id.button_anaklaki);
                    button_anaklaki.setVisibility(View.GONE);
                }

                // Inset New Layout into Parent
                ListProfilLinearLayout.addView(profil_layout);

                // Set OnClickListener on New Layout
                profil_layout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create Dialog
                        final Dialog dialog_profil = new Dialog(Profil.this);
                        dialog_profil.setContentView(R.layout.dialog_profil);
                        dialog_profil.setCanceledOnTouchOutside(true);
                        dialog_profil.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog_profil.show();

                        // Declare
                        TextView profil_nama = (TextView) dialog_profil.findViewById(R.id.profil_nama);
                        ImageView foto_profil = (ImageView) dialog_profil.findViewById(R.id.foto_profil);
                        ImageView button_pilih = (ImageView) dialog_profil.findViewById(R.id.button_pilih);
                        ImageView button_detail_profil = (ImageView) dialog_profil.findViewById(R.id.button_detail);

                        // Set Content from Parent
                        profil_nama.setText(nama);
                        foto_profil.setImageDrawable(Drawable.createFromPath(foto_path));

                        // Set Custom Font
                        profil_nama.setTypeface(typeface);

                        // Get Value from Parent
                        final String session_id = Integer.valueOf(id).toString();

                        // Set OnClickListener on Dialog
                        foto_profil.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Show Image Zoom Activity
                                Intent zoom = new Intent(Profil.this, ImageZoom.class);
                                lastActivity = System.currentTimeMillis();
                                zoom.putExtra("lastActivity", lastActivity);
                                zoom.putExtra("foto_path", foto_path);
                                startActivity(zoom);
                            }
                        });

                        button_pilih.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                // Has ProfilPasscode
                                if (!passcode.equals("")) {
                                    Intent profilCekPasscode = new Intent(Profil.this, ProfilPasscodeCek.class);
                                    lastActivity = System.currentTimeMillis();
                                    profilCekPasscode.putExtra("lastActivity", lastActivity);
                                    profilCekPasscode.putExtra("id", id);
                                    profilCekPasscode.putExtra("nama", nama);
                                    profilCekPasscode.putExtra("gender", gender);
                                    profilCekPasscode.putExtra("tanggallahir", tanggallahir);
                                    profilCekPasscode.putExtra("passcode", passcode);
                                    profilCekPasscode.putExtra("action", "pilihprofil");
                                    profilCekPasscode.putExtra("pathbefore", pathbefore);
                                    profilCekPasscode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                    profilCekPasscode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(profilCekPasscode);
                                    finish();

                                    dialog_profil.dismiss();

                                // Has no ProfilPasscode
                                } else {
                                    // Create Session
                                    if (session.checkSession(Profil.this)) {
                                        session.clearSession(Profil.this);
                                    }
                                    session.createSession(Profil.this, "id", session_id);
                                    session.createSession(Profil.this, "nama", nama);
                                    session.createSession(Profil.this, "gender", gender);
                                    session.createSession(Profil.this, "tanggallahir", tanggallahir);

                                    // Close Dialog
                                    dialog_profil.dismiss();

                                    // Show Toast
                                    Toast.makeText(Profil.this, "Profil " + nama + " Dipilih", Toast.LENGTH_SHORT).show();

                                    // Start Home Activity
                                    lastActivity = System.currentTimeMillis();
                                    Intent index = new Intent(Profil.this, Home.class);
                                    index.putExtra("lastActivity", lastActivity);
                                    index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(index);

                                    // Close This Activity
                                    finish();
                                }
                            }
                        });

                        button_detail_profil.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                // Has ProfilPasscode
                                if (!passcode.equals("")) {
                                    // Close Dialog
                                    dialog_profil.dismiss();

                                    Intent profilCekPasscode = new Intent(Profil.this, ProfilPasscodeCek.class);
                                    lastActivity = System.currentTimeMillis();
                                    profilCekPasscode.putExtra("lastActivity", lastActivity);
                                    profilCekPasscode.putExtra("id", id);
                                    profilCekPasscode.putExtra("nama", nama);
                                    profilCekPasscode.putExtra("passcode", passcode);
                                    profilCekPasscode.putExtra("action", "detailprofil");
                                    profilCekPasscode.putExtra("pathbefore", pathbefore);
                                    profilCekPasscode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                    profilCekPasscode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(profilCekPasscode);
                                    finish();

                                // Has no ProfilPasscode
                                } else {
                                    // Close Dialog
                                    dialog_profil.dismiss();

                                    // Show Detail Profil Activity
                                    Intent detail_profil = new Intent(Profil.this, ProfilDetail.class);
                                    lastActivity = System.currentTimeMillis();
                                    detail_profil.putExtra("lastActivity", lastActivity);
                                    detail_profil.putExtra("id", id);
                                    detail_profil.putExtra("pathbefore", pathbefore);
                                    detail_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                    detail_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(detail_profil);

                                    // Close This Activity
                                    finish();
                                }
                            }
                        });
                    }
                });
            } while (cursor.moveToNext());

            // Close Databsae
            db.close();
            cursor.close();
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent fetchID = getIntent();
        String pathbefore = fetchID.getStringExtra("pathbefore");
        int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);
        
        switch (pathbefore) {

            case "home":
                // Start Home Activity
                Intent index = new Intent(this, Home.class);
                lastActivity = System.currentTimeMillis();
                index.putExtra("lastActivity", lastActivity);
                index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(index);

                // Close This Activity
                finish();
                break;

            case "gizi":
                // Start Gizi Activity
                Intent gizi = new Intent(this, Gizi.class);
                lastActivity = System.currentTimeMillis();
                gizi.putExtra("lastActivity", lastActivity);
                gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gizi);

                // Close This Activity
                finish();
                break;

            case "imunisasi":
                // Start Imunisasi Activity
                Intent imunisasi = new Intent(this, Imunisasi.class);
                lastActivity = System.currentTimeMillis();
                imunisasi.putExtra("lastActivity", lastActivity);
                imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(imunisasi);

                // Close This Activity
                finish();
                break;

            case "tumbuhkembang":
                // Start Tumbuh Kembang Activity
                Intent tumbuhkembang = new Intent(this, TumbuhKembang.class);
                lastActivity = System.currentTimeMillis();
                tumbuhkembang.putExtra("lastActivity", lastActivity);
                tumbuhkembang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tumbuhkembang);

                // Close This Activity
                finish();
                break;

            case "rekammedis":
                // Start Rekam Medis Activity
                Intent rekammedis = new Intent(this, RekamMedis.class);
                lastActivity = System.currentTimeMillis();
                rekammedis.putExtra("lastActivity", lastActivity);
                rekammedis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(rekammedis);

                // Close This Activity
                finish();
                break;

            case "alarmimunisasi":
                // Start Alarm Imunisasi Activity
                Intent alarmimunisasi = new Intent(this, ImunisasiAlarm.class);
                lastActivity = System.currentTimeMillis();
                alarmimunisasi.putExtra("lastActivity", lastActivity);
                alarmimunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(alarmimunisasi);

                // Close This Activity
                finish();
                break;

            case "jadwalimunisasi":
                // Start Jadwal Imunisasi Activity
                Intent jadwalimunisasi = new Intent(this, ImunisasiJadwal.class);
                lastActivity = System.currentTimeMillis();
                jadwalimunisasi.putExtra("lastActivity", lastActivity);
                jadwalimunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(jadwalimunisasi);

                // Close This Activity
                finish();
                break;

            case "detailjadwalimunisasi":
                // Start Detail Jadwal Imunisasi Activity
                Intent detailjadwalimunisasi = new Intent(this, ImunisasiJadwalDetail.class);
                lastActivity = System.currentTimeMillis();
                detailjadwalimunisasi.putExtra("lastActivity", lastActivity);
                detailjadwalimunisasi.putExtra("id", detailJadwalImunisasiID);
                detailjadwalimunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailjadwalimunisasi);

                // Close This Activity
                finish();
                break;

            case "riwayatimunisasi":
                // Start Riwayat Imunisasi Activity
                Intent riwayatimunisasi = new Intent(this, ImunisasiRiwayat.class);
                lastActivity = System.currentTimeMillis();
                riwayatimunisasi.putExtra("lastActivity", lastActivity);
                riwayatimunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(riwayatimunisasi);

                // Close This Activity
                finish();
                break;

            case "dokumentasigizi":
                // Start Dokumentasi Gizi Activity
                Intent dokumentasigizi = new Intent(this, GiziDokumentasi.class);
                lastActivity = System.currentTimeMillis();
                dokumentasigizi.putExtra("lastActivity", lastActivity);
                dokumentasigizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(dokumentasigizi);

                // Close This Activity
                finish();
                break;

            case "tahapantumbang":
                // Start Tahapan Tumbuh Kembang Activity
                Intent tahapantumbang = new Intent(this, TumbuhKembangTahapan.class);
                lastActivity = System.currentTimeMillis();
                tahapantumbang.putExtra("lastActivity", lastActivity);
                tahapantumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tahapantumbang);

                // Close This Activity
                finish();
                break;

            case "galeritumbang":
                // Start Galeri Tumbuh Kembang Activity
                Intent galeritumbang = new Intent(this, TumbuhKembangGaleri.class);
                lastActivity = System.currentTimeMillis();
                galeritumbang.putExtra("lastActivity", lastActivity);
                galeritumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(galeritumbang);

                // Close This Activity
                finish();
                break;
        }
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(Profil.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}
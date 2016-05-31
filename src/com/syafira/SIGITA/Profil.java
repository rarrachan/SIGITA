package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Typeface;

import java.io.File;

public class Profil extends Activity{

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
                Intent tambah_profil = new Intent(Profil.this, TambahProfil.class);
                lastActivity = System.currentTimeMillis();
                tambah_profil.putExtra("lastActivity", lastActivity);
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

                                if (!passcode.equals("")) {
                                    Intent profilCekPasscode = new Intent(Profil.this, ProfilCekPasscode.class);
                                    lastActivity = System.currentTimeMillis();
                                    profilCekPasscode.putExtra("lastActivity", lastActivity);
                                    profilCekPasscode.putExtra("id", id);
                                    profilCekPasscode.putExtra("nama", nama);
                                    profilCekPasscode.putExtra("gender", gender);
                                    profilCekPasscode.putExtra("tanggallahir", tanggallahir);
                                    profilCekPasscode.putExtra("passcode", passcode);
                                    profilCekPasscode.putExtra("action", "pilihprofil");
                                    profilCekPasscode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(profilCekPasscode);

                                    dialog_profil.dismiss();
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

                                    // Start Index Activity
                                    lastActivity = System.currentTimeMillis();
                                    Intent index = new Intent(Profil.this, Index.class);
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
                                if (!passcode.equals("")) {
                                    // Close Dialog
                                    dialog_profil.dismiss();

                                    Intent profilCekPasscode = new Intent(Profil.this, ProfilCekPasscode.class);
                                    lastActivity = System.currentTimeMillis();
                                    profilCekPasscode.putExtra("lastActivity", lastActivity);
                                    profilCekPasscode.putExtra("id", id);
                                    profilCekPasscode.putExtra("nama", nama);
                                    profilCekPasscode.putExtra("passcode", passcode);
                                    profilCekPasscode.putExtra("action", "detailprofil");
                                    startActivity(profilCekPasscode);
                                } else {
                                    // Close Dialog
                                    dialog_profil.dismiss();

                                    // Show Detail Profil Activity
                                    Intent detail_profil = new Intent(Profil.this, DetailProfil.class);
                                    lastActivity = System.currentTimeMillis();
                                    detail_profil.putExtra("lastActivity", lastActivity);
                                    detail_profil.putExtra("id", id);
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
            session.clearSession(Profil.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}
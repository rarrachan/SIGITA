package com.syafira.SIGITA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by syafira rarra on 04/27/2016.
 */
public class DetailJadwalImunisasi extends Activity {

    // Declare
    private TextView detail_jadwal_imunisasi;
    private TextView text_button_profil;
    private TextView text_jenis_vaksin;
    private TextView titikdua;
    private TextView jenis_vaksin;
    private TextView text_usia_vaksin;
    private TextView usia_vaksin;
    private TextView text_status_vaksin;
    private TextView status_vaksin;
    private TextView text_tanggal_vaksin;
    private TextView tanggal_vaksin;
    private TextView text_keterangan_vaksin;
    private TextView keterangan_vaksin;
    private TextView text_footer;
    private LinearLayout tanggalVaksinLayout;
    private LinearLayout ProfilLinearLayout;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_jadwal_imunisasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        Cursor cursor = db.getOneList(id);
        if(session.checkSession(this)) {
            int profilID = Integer.parseInt(session.loadSession(this, "id"));
            cursor = db.getListJoinRiwayat(id, profilID);
        }
        cursor.moveToFirst();

        // Load Widget
        detail_jadwal_imunisasi = (TextView) findViewById(R.id.detail_jadwal_imunisasi);
        tanggalVaksinLayout = (LinearLayout) findViewById(R.id.tanggalVaksinLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_jenis_vaksin = (TextView) findViewById(R.id.text_jenis_vaksin);
        titikdua = (TextView) findViewById(R.id.titikdua);
        jenis_vaksin = (TextView) findViewById(R.id.jenis_vaksin);
        text_usia_vaksin = (TextView) findViewById(R.id.text_usia_vaksin);
        usia_vaksin = (TextView) findViewById(R.id.usia_vaksin);
        text_status_vaksin = (TextView) findViewById(R.id.text_status_vaksin);
        status_vaksin = (TextView) findViewById(R.id.status_vaksin);
        text_tanggal_vaksin= (TextView) findViewById(R.id.text_tanggal_vaksin);
        tanggal_vaksin = (TextView) findViewById(R.id.tanggal_vaksin);
        text_keterangan_vaksin = (TextView) findViewById(R.id.text_keterangan_vaksin);
        keterangan_vaksin = (TextView) findViewById(R.id.keterangan_vaksin);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custom Font
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_jadwal_imunisasi.setTypeface(typeface);
        text_tanggal_vaksin.setTypeface(typeface);
        tanggal_vaksin.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_jenis_vaksin.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        jenis_vaksin.setTypeface(typeface);
        text_usia_vaksin.setTypeface(typeface);
        usia_vaksin.setTypeface(typeface);
        text_status_vaksin.setTypeface(typeface);
        status_vaksin.setTypeface(typeface);
        text_keterangan_vaksin.setTypeface(typeface);
        keterangan_vaksin.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
            if (cursor.getString(cursor.getColumnIndex("list_vaksin")).equals(cursor.getString(cursor.getColumnIndex("riwayat_vaksin")))) {
                tanggalVaksinLayout.setVisibility(View.VISIBLE);
                status_vaksin.setText("Sudah");
                tanggal_vaksin.setText(cursor.getString(cursor.getColumnIndex("riwayat_tanggal")));
            }
        }

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(DetailJadwalImunisasi.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("detailJadwalImunisasiID", id);
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", "detailjadwalimunisasi");
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);
                finish();
            }
        });

        // Show Data From Database
        final int list_id = cursor.getInt(cursor.getColumnIndex("listID"));
        jenis_vaksin.setText(cursor.getString(cursor.getColumnIndex("list_vaksin")));
        usia_vaksin.setText(cursor.getString(cursor.getColumnIndex("list_umur")));
        keterangan_vaksin.setText(cursor.getString(cursor.getColumnIndex("list_desc")));

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Jadwal Imunisasi Activity
        Intent jadwal_imunisasi = new Intent(this, JadwalImunisasi.class);
        lastActivity = System.currentTimeMillis();
        jadwal_imunisasi.putExtra("lastActivity", lastActivity);
        jadwal_imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(jadwal_imunisasi);

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
            session.clearSession(DetailJadwalImunisasi.this);

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
                text_button_profil.setText("Profil");
                tanggalVaksinLayout.setVisibility(View.GONE);
                status_vaksin.setText("-");
            }
        }
    }
}

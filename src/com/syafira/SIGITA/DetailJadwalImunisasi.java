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
    private TextView text_button_profil;
    private TextView text_jenis_vaksin;
    private TextView titikdua;
    private TextView jenis_vaksin;
    private TextView text_usia_vaksin;
    private TextView usia_vaksin;
    private TextView text_status_vaksin;
    private TextView status_vaksin;
    private TextView text_keterangan_vaksin;
    private TextView keterangan_vaksin;
    private TextView text_footer;
    private LinearLayout statusVaksinLayout;
    private SessionManager session;
    private DBHelper db;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_jadwal_imunisasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneList(id);
        cursor.moveToFirst();

        // Load Widget
        statusVaksinLayout = (LinearLayout) findViewById(R.id.statusVaksinLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_jenis_vaksin = (TextView) findViewById(R.id.text_jenis_vaksin);
        titikdua = (TextView) findViewById(R.id.titikdua);
        jenis_vaksin = (TextView) findViewById(R.id.jenis_vaksin);
        text_usia_vaksin = (TextView) findViewById(R.id.text_usia_vaksin);
        usia_vaksin = (TextView) findViewById(R.id.usia_vaksin);
        text_status_vaksin = (TextView) findViewById(R.id.text_status_vaksin);
        status_vaksin = (TextView) findViewById(R.id.status_vaksin);
        text_keterangan_vaksin = (TextView) findViewById(R.id.text_keterangan_vaksin);
        keterangan_vaksin = (TextView) findViewById(R.id.keterangan_vaksin);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custom Font
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
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
            statusVaksinLayout.setVisibility(View.VISIBLE);
            status_vaksin.setText(cursor.getString(cursor.getColumnIndex("list_vaksin")));
        }

        text_button_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Detail Profil Activity
                Intent profil = new Intent(DetailJadwalImunisasi.this, Profil.class);
                startActivity(profil);
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
        Intent jadwal_imunisasi = new Intent(DetailJadwalImunisasi.this, JadwalImunisasi.class);
        startActivity(jadwal_imunisasi);

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

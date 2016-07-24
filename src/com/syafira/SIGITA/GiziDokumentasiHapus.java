package com.syafira.SIGITA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

/**
 * Created by syafira rarra on 05/25/2016.
 */

public class GiziDokumentasiHapus extends GiziDokumentasiDetail {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int dokumentasiID = fetchID.getIntExtra("dokumentasiID", 0);

        // Declare Condition
        boolean success = false;

        try {
            DBHelper db = new DBHelper(this);
            db.open();

            // Delete From Database
            db.deleteDokumentasi(dokumentasiID);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(GiziDokumentasiHapus.this, "Dokumentasi Gizi Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(GiziDokumentasiHapus.this, "Dokumentasi Gizi Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Rekam Medis Activity
        Intent dokumentasi_gizi = new Intent(GiziDokumentasiHapus.this, GiziDokumentasi.class);
        long lastActivity = System.currentTimeMillis();
        dokumentasi_gizi.putExtra("lastActivity", lastActivity);
        dokumentasi_gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dokumentasi_gizi);

        // Clear Activity
        finish();
    }
}

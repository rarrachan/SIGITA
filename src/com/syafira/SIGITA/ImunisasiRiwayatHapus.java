package com.syafira.SIGITA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by syafira rarra on 07/14/2016.
 */
public class ImunisasiRiwayatHapus extends ImunisasiRiwayatDetail {

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);

        // Declare Condition
        boolean success = false;

        try {
            // Delete From Database
            DBHelper db = new DBHelper(this);
            db.open();
            db.deleteRiwayat(id);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(ImunisasiRiwayatHapus.this, "Riwayat Imunisasi Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(ImunisasiRiwayatHapus.this, "Riwayat Imunisasi Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Riwayat Imunisai Activity
        Intent riwayat = new Intent(ImunisasiRiwayatHapus.this, ImunisasiRiwayat.class);
        long lastActivity = System.currentTimeMillis();
        riwayat.putExtra("lastActivity", lastActivity);
        riwayat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(riwayat);

        // Clear Activity
        finish();
    }
}

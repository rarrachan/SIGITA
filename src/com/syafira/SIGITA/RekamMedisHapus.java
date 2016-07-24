package com.syafira.SIGITA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by syafira rarra on 07/14/2016.
 */
public class RekamMedisHapus extends RekamMedisDetail {

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int medisID = fetchID.getIntExtra("id", 0);

        // Declare Condition
        boolean success = false;

        try {
            // Delete From Database
            DBHelper db = new DBHelper(this);
            db.open();
            db.deleteMedis(medisID);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(RekamMedisHapus.this, "Rekam Medis Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(RekamMedisHapus.this, "Rekam Medis Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Rekam Medis Activity
        Intent medis = new Intent(RekamMedisHapus.this, RekamMedis.class);
        long lastActivity = System.currentTimeMillis();
        medis.putExtra("lastActivity", lastActivity);
        medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(medis);

        // Clear Activity
        finish();
    }
}

package com.syafira.SIGITA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by syafira rarra on 07/14/2016.
 */
public class CatatanKesehatanHapus extends CatatanKesehatanDetail {

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int kesehatanID = fetchID.getIntExtra("id", 0);

        // Declare Condition
        boolean success = false;

        try {
            // Delete From Database
            DBHelper db = new DBHelper(this);
            db.open();
            db.deleteKesehatan(kesehatanID);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(CatatanKesehatanHapus.this, "Catatan Kesehatan Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(CatatanKesehatanHapus.this, "Catatan Kesehatan Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Catatan Kesehatan Activity
        Intent kesehatan = new Intent(CatatanKesehatanHapus.this, CatatanKesehatan.class);
        long lastActivity = System.currentTimeMillis();
        kesehatan.putExtra("lastActivity", lastActivity);
        kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(kesehatan);

        // Clear Activity
        finish();
    }
}

package com.syafira.SIGITA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by syafira rarra on 07/14/2016.
 */

public class ProfilPasscodeHapus extends ProfilPasscode {

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Load Database
        DBHelper db = new DBHelper(ProfilPasscodeHapus.this);
        db.open();

        String newpasscode = "";
        boolean success = false;
        try {
            // Insert Data into Database
            db.updateProfilPasscode(id, newpasscode);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            Toast.makeText(getApplicationContext(), "Passcode Berhasil Dihapus", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Passcode Gagal Dihapus", Toast.LENGTH_LONG).show();
        }

        // Start Profil Activity
        Intent passcode = new Intent(ProfilPasscodeHapus.this, ProfilPasscode.class);
        long lastActivity = System.currentTimeMillis();
        passcode.putExtra("lastActivity", lastActivity);
        passcode.putExtra("id", id);
        passcode.putExtra("pathbefore", pathbefore);
        passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
        passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(passcode);

        // Close This Activity
        finish();

    }
}

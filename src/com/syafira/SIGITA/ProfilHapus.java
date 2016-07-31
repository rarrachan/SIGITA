package com.syafira.SIGITA;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by syafira rarra on 07/14/2016.
 */
public class ProfilHapus extends ProfilDetail {

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final String nama = fetchID.getStringExtra("nama");
        final int id = fetchID.getIntExtra("id", 0);
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Declare Condition
        boolean success = false;
        try {
            // Turn Off Alarm Imunisasi
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (PendingIntent.getBroadcast(ProfilHapus.this, id,
                    new Intent(ProfilHapus.this, ImunisasiAlarmReceiver.class), PendingIntent.FLAG_NO_CREATE) != null) {
                PendingIntent.getBroadcast(ProfilHapus.this, id,
                        new Intent(ProfilHapus.this, ImunisasiAlarmReceiver.class), PendingIntent.FLAG_NO_CREATE).cancel();
                alarmManager.cancel(PendingIntent.getBroadcast(ProfilHapus.this, id,
                        new Intent(ProfilHapus.this, ImunisasiAlarmReceiver.class), PendingIntent.FLAG_NO_CREATE));
            }

            // Delete Folder Directory
            File profilDirectory = new File(Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_"));
            if (profilDirectory.isDirectory()) {
                String[] children = profilDirectory.list();
                for (String aChildren : children) {
                    //Scan Gallery
                    getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.DATA + "=?", new String[]{profilDirectory + "/" + aChildren});
                }
                profilDirectory.delete();
            }

            // Delete From Database
            DBHelper db = new DBHelper(ProfilHapus.this);
            db.open();
            db.deleteDokumentasiProfilID(id);
            db.deleteRiwayatProfilID(id);
            db.deleteGaleriProfilID(id);
            db.deleteKesehatanProfilID(id);
            db.deleteProfil(id);

            // Declare Condition
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(ProfilHapus.this, "Profil Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(ProfilHapus.this, "Profil Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Profil Activity
        Intent profil = new Intent(ProfilHapus.this, Profil.class);
        long lastActivity = System.currentTimeMillis();
        profil.putExtra("lastActivity", lastActivity);
        profil.putExtra("pathbefore", pathbefore);
        profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
        profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profil);

        // Clear Session
        SessionManager session = new SessionManager();
        session.clearSession(ProfilHapus.this);

        // Clear Activity
        finish();
    }
}

package com.syafira.SIGITA;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by syafira rarra on 07/14/2016.
 */

public class TumbuhKembangGaleriHapus extends TumbuhKembangGaleriDetail {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int galeriID = fetchID.getIntExtra("galeriID", 0);
        String nama = fetchID.getStringExtra("nama");
        String galeri_foto_db = fetchID.getStringExtra("galeri_foto_db");

        // Declare Condition
        boolean success = false;

        try {
            // Get Path Directory
            String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

            // Folder Directory Using Old Name
            File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + nama.replaceAll(" ", "_"));

            // Old Directory, New Photo Name
            File foto = new File(photoDirectory, galeri_foto_db);

            // Remove Photo
            if (foto.exists()) {
                if (foto.delete())
                    getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.DATA + "=?", new String[]{new File(sdCardDirectory + "/SIGITA/" + nama.replaceAll(" ", "_")) + "/" + galeri_foto_db});
            }

            // Scan on Gallery
            MediaScannerConnection.scanFile(TumbuhKembangGaleriHapus.this,
                    new String[]{foto.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });

            // Delete From Database
            DBHelper db = new DBHelper(this);
            db.open();
            db.deleteGaleri(galeriID);

            // Declare Condition
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Condition
        if (success) {
            // Show Toast Success
            Toast.makeText(TumbuhKembangGaleriHapus.this, "Galeri Berhasil Terhapus", Toast.LENGTH_SHORT).show();
        } else {
            // Show Toast Failed
            Toast.makeText(TumbuhKembangGaleriHapus.this, "Galeri Gagal Terhapus", Toast.LENGTH_SHORT).show();
        }

        // Show Galeri Activity
        Intent galeri = new Intent(TumbuhKembangGaleriHapus.this, TumbuhKembangGaleri.class);
        long lastActivity = System.currentTimeMillis();
        galeri.putExtra("lastActivity", lastActivity);
        galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(galeri);

        // Clear Activity
        finish();
    }
}

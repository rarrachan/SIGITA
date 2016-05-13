package com.syafira.SIGITA;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by syafira rarra on 05/07/2016.
 */
public class DetailGaleri extends Activity {

    private TextView detail_galeri;
    private TextView titikdua;
    private TextView text_footer;
    private TextView text_galeri_nama;
    private TextView galeri_nama;
    private TextView text_galeri_tanggal;
    private TextView galeri_tanggal;
    private TextView text_galeri_usia;
    private TextView galeri_usia;
    private TextView text_keterangan_foto;
    private TextView keterangan_foto;
    private TextView text_galeri_foto;
    private ImageView galeri_foto;
    private ImageView button_ubah;
    private ImageView button_hapus;
    private SessionManager session;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_galeri);

        // Session Manager
        session = new SessionManager();
        int profilID = Integer.parseInt(session.loadSession(this, "id"));

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int galeriID = fetchID.getIntExtra("galeriID", 0);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneGaleri(galeriID, profilID);
        cursor.moveToFirst();

        // Load Widget
        detail_galeri = (TextView) findViewById(R.id.detail_galeri);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_galeri_nama = (TextView) findViewById(R.id.text_galeri_nama);
        galeri_nama = (TextView) findViewById(R.id.galeri_nama);
        text_galeri_tanggal = (TextView) findViewById(R.id.text_galeri_tanggal);
        galeri_tanggal = (TextView) findViewById(R.id.galeri_tanggal);
        text_galeri_usia = (TextView) findViewById(R.id.text_galeri_usia);
        galeri_usia = (TextView) findViewById(R.id.galeri_usia);
        text_keterangan_foto = (TextView) findViewById(R.id.text_galeri_keterangan_foto);
        keterangan_foto = (TextView) findViewById(R.id.galeri_keterangan_foto);
        text_galeri_foto = (TextView) findViewById(R.id.text_galeri_foto);
        galeri_foto = (ImageView) findViewById(R.id.galeri_foto);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_galeri.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_nama.setTypeface(typeface);
        galeri_nama.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);
        text_keterangan_foto.setTypeface(typeface);
        keterangan_foto.setTypeface(typeface);
        text_galeri_foto.setTypeface(typeface);

        final String nama = session.loadSession(this, "nama");
        galeri_nama.setText(nama);
        galeri_tanggal.setText(cursor.getString(cursor.getColumnIndex("galeri_tanggal")));
        galeri_usia.setText(cursor.getString(cursor.getColumnIndex("galeri_umur")));
        keterangan_foto.setText(cursor.getString(cursor.getColumnIndex("galeri_desc")));
        final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("galeri_foto"));
        galeri_foto.setImageDrawable(Drawable.createFromPath(foto_path));

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Profil Activity
                Intent ubah_galeri = new Intent(DetailGaleri.this, UbahGaleri.class);
                // Put Intent Extra
                ubah_galeri.putExtra("galeriID", galeriID);
                startActivity(ubah_galeri);
                finish();
            }
        });

        galeri_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Image Zoom Activity
                Intent zoom = new Intent(DetailGaleri.this, ImageZoom.class);
                zoom.putExtra("foto_path", foto_path);
                startActivity(zoom);
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(DetailGaleri.this);
                dialog.setContentView(R.layout.hapus_galeri);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                // Load Dialog Widget
                TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                TextView alert_hapus = (TextView) dialog.findViewById(R.id.alert_hapus);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_hapus.setTypeface(typeface);
                alert_warning.setTypeface(typeface);

                // Set OnClickListener Dialog
                button_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close Dialog
                        dialog.dismiss();
                    }
                });
                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Declare Condition
                        boolean success = false;

                        try {
                            // Get Path Directory
                            String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

                            // Folder Directory Using Old Name
                            File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + nama.replaceAll(" ", "_"));

                            // Old Directory, New Photo Name
                            File foto = new File(photoDirectory, cursor.getString(cursor.getColumnIndex("galeri_foto")));

                            // Remove Photo
                            if (foto.exists()) {
                                foto.delete();
                            }

                            // Delete From Database
                            db.deleteGaleri(galeriID);

                            // Declare Condition
                            success = true;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(DetailGaleri.this, "Galeri Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(DetailGaleri.this, "Galeri Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Profil Activity
                        Intent profil = new Intent(DetailGaleri.this, GaleriTumbang.class);
                        startActivity(profil);

                        // Clear Activity
                        finish();
                    }
                });
            }
        });
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Galeri Activity
        Intent galeri = new Intent(DetailGaleri.this, GaleriTumbang.class);
        startActivity(galeri);

        // Close This Activity
        finish();
    }
}
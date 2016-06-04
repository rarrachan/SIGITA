package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/26/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Index extends Activity {

    // Declare
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.index);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Widget
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profil = new Intent(Index.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", "index");
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);
                finish();
            }
        });

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Grid Menu
        String[] menu = {"Profil", "Gizi", "Imunisasi", "Tumbuh Kembang", "Rekam Medis", "Tentang"};
        int[] menu_image = {R.drawable.icon_profil, R.drawable.icon_gizi, R.drawable.icon_imunisasi, R.drawable.icon_tumbuhkembang, R.drawable.icon_rekammedis, R.drawable.icon_logo};

        List<HashMap<String, ?>> aList = new ArrayList<>();
        for (int a = 0; a < menu.length; a++) {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("nama_menu", menu[a]);
            hm.put("foto_menu", menu_image[a]);
            aList.add(hm);
        }

        final String[] from = {"foto_menu", "nama_menu"};
        int[] to = {R.id.index_menu, R.id.index_nama_menu};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.index_grid, from, to) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                TextView tv = (TextView) v.findViewById(R.id.index_nama_menu);
                tv.setLines(2);
                tv.setTypeface(typeface);
                return v;
            }
        };
        ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);

        grid.setAdapter(adapter);
        grid.setExpanded(true);
        grid.setVisibility(View.VISIBLE);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {

                    // Profil
                    case 0:
                        Intent profil = new Intent(Index.this, Profil.class);
                        lastActivity = System.currentTimeMillis();
                        profil.putExtra("lastActivity", lastActivity);
                        profil.putExtra("pathbefore", "index");
                        profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(profil);
                        finish();
                        break;

                    // Gizi
                    case 1:
                        Intent gizi = new Intent(Index.this, Gizi.class);
                        lastActivity = System.currentTimeMillis();
                        gizi.putExtra("lastActivity", lastActivity);
                        gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(gizi);
                        finish();
                        break;

                    // Imunisasi
                    case 2:
                        Intent imunisasi = new Intent(Index.this, Imunisasi.class);
                        lastActivity = System.currentTimeMillis();
                        imunisasi.putExtra("lastActivity", lastActivity);
                        imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(imunisasi);
                        finish();
                        break;

                    // Tumbuh Kembang
                    case 3:
                        Intent tumbang = new Intent(Index.this, TumbuhKembang.class);
                        lastActivity = System.currentTimeMillis();
                        tumbang.putExtra("lastActivity", lastActivity);
                        tumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(tumbang);
                        finish();
                        break;

                    // Rekam Medis
                    case 4:
                        // Check Session
                        if (session.checkSession(Index.this)) {
                            Intent medis = new Intent(Index.this, RekamMedis.class);
                            lastActivity = System.currentTimeMillis();
                            medis.putExtra("lastActivity", lastActivity);
                            medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(medis);
                            finish();
                        } else {
                            final Dialog dialog = new Dialog(Index.this);
                            dialog.setContentView(R.layout.alert_akses);
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();

                            // Load Dialog Widget
                            TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                            TextView alert_akses = (TextView) dialog.findViewById(R.id.alert_akses);
                            ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                            // Set Custom Font Dialog
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
                            alert_akses.setTypeface(typeface);
                            alert_warning.setTypeface(typeface);

                            // Set OnClickListener Dialog
                            button_ok.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Close Dialog
                                    dialog.dismiss();

                                    // Start Profil Activity
                                    Intent profil = new Intent(Index.this, Profil.class);
                                    lastActivity = System.currentTimeMillis();
                                    profil.putExtra("lastActivity", lastActivity);
                                    profil.putExtra("pathbefore", "index");
                                    profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(profil);
                                    finish();
                                }
                            });
                        }
                        break;

                    // Tentang
                    case 5:
                        Intent tentang = new Intent(Index.this, TentangSIGITA.class);
                        lastActivity = System.currentTimeMillis();
                        tentang.putExtra("lastActivity", lastActivity);
                        tentang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(tentang);
                        finish();
                        break;
                }
            }
        });

    }

    // Pressed Button Back
    @Override
    public void onBackPressed() {
        // Create Dialog
        new AlertDialog.Builder(this)
                .setTitle("Keluar SIGITA")
                .setMessage("Apa Anda yakin ingin keluar?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
//                        // Clear Session
                        session.clearSession(Index.this);
                        Index.super.onBackPressed();

                        // Close Application
                        Intent finish = new Intent(Intent.ACTION_MAIN);
                        finish.addCategory(Intent.CATEGORY_HOME);
                        finish.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(finish);
                    }
                }).create().show();
    }


    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();

        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(Index.this);

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
            }
        }
    }
}

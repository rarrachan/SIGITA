package com.syafira.SIGITA;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by syafira rarra on 05/04/2016.
 */
public class GaleriTumbang extends Activity {

    // Declare
    private TextView button_galeritumbang;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.galeri_tumbang);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        button_galeritumbang = (TextView) findViewById(R.id.button_galeritumbang);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custtom Layout
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_galeritumbang.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));

            ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profil = new Intent(GaleriTumbang.this, Profil.class);
                    lastActivity = System.currentTimeMillis();
                    profil.putExtra("lastActivity", lastActivity);
                    profil.putExtra("pathbefore", "galeritumbang");
                    profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(profil);
                    finish();
                }
            });

            int id = Integer.parseInt(session.loadSession(GaleriTumbang.this, "id"));
            String nama = session.loadSession(GaleriTumbang.this, "nama");

            List<HashMap<String, ?>> aList = new ArrayList<>();
            final HashMap<String, String> tambah_galeri = new HashMap<>();
            tambah_galeri.put("galeri_foto", Integer.toString(R.drawable.icon_tambahfoto));
            tambah_galeri.put("galeri_tanggal", "Tambah Galeri");
            aList.add(0, tambah_galeri);

            // Get Data from Database
            final Cursor cursor = db.getGaleri(id);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("galeri_foto"));
                    int galeri_id = cursor.getInt(cursor.getColumnIndex("galeriID"));
                    String foto_tanggal = cursor.getString(cursor.getColumnIndex("galeri_tanggal"));

                    HashMap<String, Object> hm = new HashMap<>();
                    hm.put("galeri_foto", foto_path);
                    hm.put("galeri_tanggal", foto_tanggal);
                    hm.put("galeriID", galeri_id);
                    aList.add(hm);
                } while (cursor.moveToNext());
            }

            // Keys used in Hashmap
            final String[] from = {"galeri_foto", "galeri_tanggal"};

            // Ids of views in listview_layout
            int[] to = {R.id.galeri_foto, R.id.galeri_tanggal_foto};

            final SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.galeri_grid, from, to){
                @Override
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);

                    TextView tv = (TextView) v.findViewById(R.id.galeri_tanggal_foto);
                    tv.setLines(2);
                    tv.setTypeface(typeface);
                    return v;
                }
            };
            final ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);

//            float scalefactor = getResources().getDisplayMetrics().density * 100;
//            Display display = getWindowManager().getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            int number = size.x;
//            int columns = (int) ((float) number / scalefactor);
//            grid.setNumColumns(columns);

            grid.setFocusable(false);
            grid.setAdapter(adapter);
            grid.setExpanded(true);
            grid.setVisibility(View.VISIBLE);

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    if (position == 0) {
                        Intent tambah_galeri = new Intent(GaleriTumbang.this, TambahGaleri.class);
                        lastActivity = System.currentTimeMillis();
                        tambah_galeri.putExtra("lastActivity", lastActivity);
                        tambah_galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(tambah_galeri);

                        // Close This Activity
                        finish();
                    }else{
                        HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                        int galeri_id = (Integer) obj.get("galeriID");

                        Intent detail_galeri = new Intent(GaleriTumbang.this, DetailGaleri.class);
                        lastActivity = System.currentTimeMillis();
                        detail_galeri.putExtra("lastActivity", lastActivity);
                        detail_galeri.putExtra("galeriID", galeri_id);
                        detail_galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(detail_galeri);
                        finish();
                    }
                }
            });

            db.close();
            cursor.close();

        } else {
            Intent tumbang = new Intent(this, TumbuhKembang.class);
            lastActivity = System.currentTimeMillis();
            tumbang.putExtra("lastActivity", lastActivity);
            tumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(tumbang);
            finish();
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent tumbang = new Intent(this, TumbuhKembang.class);
        lastActivity = System.currentTimeMillis();
        tumbang.putExtra("lastActivity", lastActivity);
        tumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(tumbang);

        // Close This Activity
        finish();

    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(GaleriTumbang.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();

        } else {
            // Check Session
            if (session.checkSession(this)) {
                // Set Profil Name
                text_button_profil.setText(session.loadSession(this, "nama"));
                ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);
                grid.setFocusable(false);
            } else {
                // Start Tumbuh Kembang Activity
                Intent tumbang = new Intent(GaleriTumbang.this, TumbuhKembang.class);
                lastActivity = System.currentTimeMillis();
                tumbang.putExtra("lastActivity", lastActivity);
                tumbang.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tumbang);
                finish();
            }
        }
    }
}

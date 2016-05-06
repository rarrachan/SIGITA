package com.syafira.SIGITA;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private TextView text_galeri_tanggal;
    private EditText galeri_tanggal;
    private TextView text_galeri_usia;
    private TextView galeri_usia;
    private SessionManager session;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.galeri_tumbang);

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
        text_galeri_tanggal = (TextView) findViewById(R.id.text_galeri_tanggal);
        galeri_tanggal = (EditText) findViewById(R.id.galeri_tanggal);
        text_galeri_usia = (TextView) findViewById(R.id.text_galeri_usia);
        galeri_usia = (TextView) findViewById(R.id.galeri_usia);

        // Set Custtom Layout
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_galeritumbang.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));

            ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profil = new Intent(GaleriTumbang.this, Profil.class);
                    startActivity(profil);
                }
            });

            galeri_tanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar mcurrentDate = Calendar.getInstance();
                    int mYear = mcurrentDate.get(Calendar.YEAR);
                    int mMonth = mcurrentDate.get(Calendar.MONTH);
                    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    // Create Dialog DataPicker
                    final DatePickerDialog mDatePicker = new DatePickerDialog(GaleriTumbang.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(selectedyear, selectedmonth, selectedday);
                            galeri_tanggal.setText(dateFormatter.format(newDate.getTime()));

                            String getDate = galeri_tanggal.getText().toString();
                            String strings[] = getDate.split("/");
                            int dayDate = Integer.parseInt(strings[0]);
                            int monthDate = Integer.parseInt(strings[1]);
                            int yearDate = Integer.parseInt(strings[2]);

                            String getBirthdayDate = session.loadSession(GaleriTumbang.this, "tanggallahir");
                            String str[] = getBirthdayDate.split("/");
                            int day = Integer.parseInt(str[0]);
                            int month = Integer.parseInt(str[1]);
                            int year = Integer.parseInt(str[2]);

                            int years = yearDate - year;
                            int months = monthDate - month;
                            int days = dayDate - day;
                            if (days < 0) {
                                months--;
                                days += newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                            }
                            if (months < 0) {
                                years--;
                                months += 12;
                            }

                            int mon = monthDate - month;
                            int y = 0;
                            while (y < years) {
                                mon += 12;
                                y += 1;
                            }

                            String umur = mon + " bulan / " + years + " tahun " + months + " bulan " + days + " hari";
                            galeri_usia.setText(umur);
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();
                }
            });

            galeri_tanggal.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {

                    int id = Integer.parseInt(session.loadSession(GaleriTumbang.this, "id"));
                    String nama = session.loadSession(GaleriTumbang.this, "nama");
                    String tanggal = galeri_tanggal.getText().toString();

                    List<HashMap<String, ?>> aList = new ArrayList<>();
                    HashMap<String, String> tambah_galeri = new HashMap<>();
                    tambah_galeri.put("galeri_foto", Integer.toString(R.drawable.icon_tambahfoto));
                    aList.add(0, tambah_galeri);

                    // Get Data from Database
                    Cursor cursor = db.getGaleri(id, tanggal);
                    cursor.moveToFirst();
                    if (!cursor.isAfterLast()) {
                        do {
                            String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("galeri_foto"));

                            HashMap<String, Object> hm = new HashMap<>();
                            hm.put("galeri_foto", foto_path);
                            aList.add(hm);
                        } while (cursor.moveToNext());
                    }

                    // Keys used in Hashmap
                    String[] from = {"galeri_foto"};

                    // Ids of views in listview_layout
                    int[] to = {R.id.galeri_foto};

                    SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.galeri_grid, from, to);
                    ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);

                    grid.setFocusable(false);
                    grid.setAdapter(adapter);
                    grid.setExpanded(true);
                    grid.setVisibility(View.VISIBLE);

                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            if (position == 0) {
                                Intent tambah_galeri = new Intent(GaleriTumbang.this, TambahGaleri.class);
                                tambah_galeri.putExtra("tanggal", galeri_tanggal.getText().toString());
                                tambah_galeri.putExtra("usia", galeri_usia.getText());
                                startActivity(tambah_galeri);
                            }
                        }
                    });
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);
                    grid.setFocusable(false);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);
                    grid.setFocusable(false);
                }

            });
        } else {
            finish();
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent tumbang = new Intent(this, TumbuhKembang.class);
        startActivity(tumbang);

        // Close This Activity
        finish();

    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }
        ExpandableHeightGridView grid = (ExpandableHeightGridView) findViewById(R.id.gridview);
        grid.setFocusable(false);
    }
}

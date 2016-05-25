package com.syafira.SIGITA;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by syafira rarra on 05/12/2016.
 */
public class UbahRiwayat extends Activity {

    private TextView ubah_riwayat;
    private TextView text_footer;
    private TextView titikdua;
    private TextView text_riwayat_nama;
    private TextView riwayat_nama;
    private TextView text_riwayat_usia;
    private TextView riwayat_usia;
    private TextView riwayat_bulan;
    private TextView text_riwayat_dokter;
    private EditText riwayat_dokter;
    private TextView text_riwayat_rumahsakit;
    private EditText riwayat_rumahsakit;
    private TextView text_riwayat_tanggal;
    private EditText riwayat_tanggal;
    private TextView text_riwayat_jenisvaksin;
    private Spinner riwayat_jenisvaksin;
    private TextView text_riwayat_tinggibadan;
    private EditText riwayat_tinggibadan;
    private TextView riwayat_centimeter;
    private TextView text_riwayat_beratbadan;
    private EditText riwayat_beratbadan;
    private TextView riwayat_kilogram;
    private ImageView button_simpan;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.ubah_riwayat);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneRiwayat(id);
        cursor.moveToFirst();

        // Load Widget
        ubah_riwayat = (TextView) findViewById(R.id.ubah_riwayat);
        text_footer = (TextView) findViewById(R.id.text_footer);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_riwayat_nama = (TextView) findViewById(R.id.text_riwayat_nama);
        riwayat_nama = (TextView) findViewById(R.id.riwayat_nama);
        text_riwayat_usia = (TextView) findViewById(R.id.text_riwayat_usia);
        riwayat_usia = (TextView) findViewById(R.id.riwayat_usia);
        riwayat_bulan = (TextView) findViewById(R.id.riwayat_bulan);
        text_riwayat_dokter = (TextView) findViewById(R.id.text_riwayat_dokter);
        riwayat_dokter = (EditText) findViewById(R.id.riwayat_dokter);
        text_riwayat_rumahsakit = (TextView) findViewById(R.id.text_riwayat_rumahsakit);
        riwayat_rumahsakit = (EditText) findViewById(R.id.riwayat_rumahsakit);
        text_riwayat_tanggal = (TextView) findViewById(R.id.text_riwayat_tanggal);
        riwayat_tanggal = (EditText) findViewById(R.id.riwayat_tanggal);
        text_riwayat_jenisvaksin = (TextView) findViewById(R.id.text_riwayat_jenisvaksin);
        riwayat_jenisvaksin = (Spinner) findViewById(R.id.riwayat_jenisvaksin);
        text_riwayat_tinggibadan = (TextView) findViewById(R.id.text_riwayat_tinggibadan);
        riwayat_tinggibadan = (EditText) findViewById(R.id.riwayat_tinggibadan);
        riwayat_centimeter = (TextView) findViewById(R.id.riwayat_centimeter);
        text_riwayat_beratbadan = (TextView) findViewById(R.id.text_riwayat_beratbadan);
        riwayat_beratbadan = (EditText) findViewById(R.id.riwayat_beratbadan);
        riwayat_kilogram = (TextView) findViewById(R.id.riwayat_kilogram);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        ubah_riwayat.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_riwayat_nama.setTypeface(typeface);
        riwayat_nama.setTypeface(typeface);
        text_riwayat_usia.setTypeface(typeface);
        riwayat_usia.setTypeface(typeface);
        text_riwayat_dokter.setTypeface(typeface);
        riwayat_dokter.setTypeface(typeface);
        text_riwayat_rumahsakit.setTypeface(typeface);
        riwayat_rumahsakit.setTypeface(typeface);
        text_riwayat_tanggal.setTypeface(typeface);
        riwayat_tanggal.setTypeface(typeface);
        text_riwayat_jenisvaksin.setTypeface(typeface);
        text_riwayat_tinggibadan.setTypeface(typeface);
        riwayat_tinggibadan.setTypeface(typeface);
        riwayat_centimeter.setTypeface(typeface);
        text_riwayat_beratbadan.setTypeface(typeface);
        riwayat_beratbadan.setTypeface(typeface);
        riwayat_kilogram.setTypeface(typeface);

        final List<String> list = new ArrayList<>();
        list.add(0, "Pilih");
        final ArrayAdapter dataAdapter = new ArrayAdapter<String>(UbahRiwayat.this, android.R.layout.simple_spinner_item, list) {
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(typeface);
                v.setTextAppearance(UbahRiwayat.this, android.R.style.TextAppearance_DeviceDefault_Medium);
                v.setTextColor(Color.parseColor("#FF0000"));
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v;
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                } else {
                    v = super.getDropDownView(position, null, parent);
                }
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        riwayat_jenisvaksin.setAdapter(dataAdapter);

        riwayat_nama.setText(session.loadSession(this, "nama"));
        final int riwayatID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("riwayatID")));
        final int bulan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("riwayat_bulan")));
        riwayat_bulan.setText(String.valueOf(bulan));
        final String vaksin = cursor.getString(cursor.getColumnIndex("riwayat_vaksin"));
        riwayat_tanggal.setText(cursor.getString(cursor.getColumnIndex("riwayat_tanggal")));
        riwayat_usia.setText(cursor.getString(cursor.getColumnIndex("riwayat_umur")));
        riwayat_beratbadan.setText(cursor.getString(cursor.getColumnIndex("riwayat_berat")));
        riwayat_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("riwayat_tinggi")));
        riwayat_dokter.setText(cursor.getString(cursor.getColumnIndex("riwayat_dokter")));

        Cursor cursor_vaksin = db.getOneListByBulan(bulan);
        cursor_vaksin.moveToFirst();
        if (!cursor_vaksin.isAfterLast()) {
            do {
                list.add(cursor_vaksin.getString(cursor_vaksin.getColumnIndex("list_vaksin")));
                for(int i = 0; i < dataAdapter.getCount(); i++) {
                    if(vaksin.equals(dataAdapter.getItem(i).toString())){
                        riwayat_jenisvaksin.setSelection(i);
                    }
                }
            } while (cursor_vaksin.moveToNext());
        }

        riwayat_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                final String getBirthdayDate = session.loadSession(UbahRiwayat.this, "tanggallahir");

                // Create Dialog DataPicker
                final DatePickerDialog mDatePicker = new DatePickerDialog(UbahRiwayat.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        riwayat_tanggal.setText(dateFormatter.format(newDate.getTime()));

                        String getBirthdayDate = session.loadSession(UbahRiwayat.this, "tanggallahir");
                        String str[] = getBirthdayDate.split("/");
                        int day = Integer.parseInt(str[0]);
                        int month = Integer.parseInt(str[1]);
                        int year = Integer.parseInt(str[2]);

                        String getDate = riwayat_tanggal.getText().toString();
                        String strings[] = getDate.split("/");
                        int dayDate = Integer.parseInt(strings[0]);
                        int monthDate = Integer.parseInt(strings[1]);
                        int yearDate = Integer.parseInt(strings[2]);

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

                        int mon = months;
                        int y = 0;
                        while (y < years) {
                            mon += 12;
                            y += 1;
                        }
                        if (mon < 0)
                            mon += 12;

                        String umur = mon + " bulan / " + years + " tahun " + months + " bulan " + days + " hari";
                        riwayat_usia.setText(umur);
                        riwayat_bulan.setText(String.valueOf(mon));

                        riwayat_jenisvaksin.setSelection(0);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                try {
                    Date startDate = dateFormatter.parse(getBirthdayDate);
                    mDatePicker.getDatePicker().setMinDate(startDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mDatePicker.show();
            }
        });

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                int profilID = Integer.parseInt(session.loadSession(UbahRiwayat.this, "id"));
                int bulan = Integer.valueOf(riwayat_bulan.getText().toString());
                String tanggal = riwayat_tanggal.getText().toString();
                String usia = riwayat_usia.getText().toString();
                String jenisvaksin = riwayat_jenisvaksin.getSelectedItem().toString();
                String dokter = riwayat_dokter.getText().toString();
                String rumahsakit = riwayat_rumahsakit.getText().toString();
                String tinggi = riwayat_tinggibadan.getText().toString();
                String berat = riwayat_beratbadan.getText().toString();

                if (TextUtils.isEmpty(tanggal) || riwayat_jenisvaksin.getSelectedItemPosition() == 0) {
                    //Show Toast
                    Toast.makeText(UbahRiwayat.this, "Kolom Belum Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    // Declare Condition
                    boolean success = false;

                    try {
                        //Insert Data into Database
                        db.updateRiwayat(id, profilID, tanggal, jenisvaksin, usia, bulan, berat, tinggi, dokter, rumahsakit);
                        success = true;
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Check Condition
                    if (success) {
                        // Show Toast Success
                        Toast.makeText(getApplicationContext(), "Riwayat Imunisasi Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                    } else {
                        // Show Toast Failed
                        Toast.makeText(getApplicationContext(), "Riwayat Imunisasi Gagal Tersimpan", Toast.LENGTH_LONG).show();
                    }

                    // Start Detail Riwayat Imunisasi Activity
                    Intent detail_riwayat = new Intent(UbahRiwayat.this, DetailRiwayat.class);
                    lastActivity = System.currentTimeMillis();
                    detail_riwayat.putExtra("lastActivity", lastActivity);
                    detail_riwayat.putExtra("id", riwayatID);
                    startActivity(detail_riwayat);

                    // Close This Activity
                    finish();
                }
            }
        });
    }

    // Remove SoftKeybard if Click Outside EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        // Remove Focus
        v.clearFocus();

        // SCROLL VIEW HACK
        ScrollView view = (ScrollView)findViewById(R.id.scrollView);
        view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
        return super.dispatchTouchEvent(event);
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);

        // Start Detail Riwayat Activity
        Intent detail_riwayat = new Intent(this, DetailRiwayat.class);
        lastActivity = System.currentTimeMillis();
        detail_riwayat.putExtra("lastActivity", lastActivity);
        detail_riwayat.putExtra("id", id);
        startActivity(detail_riwayat);

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
            session.clearSession(UbahRiwayat.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}
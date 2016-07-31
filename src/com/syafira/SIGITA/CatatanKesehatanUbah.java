package com.syafira.SIGITA;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by syafira rarra on 05/01/2016.
 */

public class CatatanKesehatanUbah extends CatatanKesehatanDetail {

    // Declare
    private TextView ubah_catatan_kesehatan;
    private TextView text_footer;
    private TextView text_kesehatan_nama;
    private TextView kesehatan_nama;
    private TextView text_kesehatan_tanggalberobat;
    private EditText kesehatan_tanggalberobat;
    private TextView text_kesehatan_dokter;
    private EditText kesehatan_dokter;
    private TextView text_kesehatan_rumahsakit;
    private EditText kesehatan_rumahsakit;
    private TextView text_kesehatan_tinggibadan;
    private EditText kesehatan_tinggibadan;
    private TextView kesehatan_centimeter;
    private TextView text_kesehatan_beratbadan;
    private EditText kesehatan_beratbadan;
    private TextView kesehatan_kilogram;
    private TextView text_kesehatan_keluhan;
    private EditText kesehatan_keluhan;
    private TextView text_kesehatan_tindakan;
    private EditText kesehatan_tindakan;
    private TextView text_kesehatan_obat;
    private EditText kesehatan_obat;
    private ImageView button_simpan;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.catatankesehatan_ubah);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneKesehatan(id);
        cursor.moveToFirst();

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        ubah_catatan_kesehatan = (TextView) findViewById(R.id.ubah_catatan_kesehatan);
        text_kesehatan_nama = (TextView) findViewById(R.id.text_kesehatan_nama);
        kesehatan_nama = (TextView) findViewById(R.id.kesehatan_nama);
        text_kesehatan_tanggalberobat = (TextView) findViewById(R.id.text_kesehatan_tanggalberobat);
        kesehatan_tanggalberobat = (EditText) findViewById(R.id.kesehatan_tanggalberobat);
        text_kesehatan_dokter = (TextView) findViewById(R.id.text_kesehatan_dokter);
        kesehatan_dokter = (EditText) findViewById(R.id.kesehatan_dokter);
        text_kesehatan_rumahsakit = (TextView) findViewById(R.id.text_kesehatan_rumahsakit);
        kesehatan_rumahsakit = (EditText) findViewById(R.id.kesehatan_rumahsakit);
        text_kesehatan_tinggibadan = (TextView) findViewById(R.id.text_kesehatan_tinggibadan);
        kesehatan_tinggibadan = (EditText) findViewById(R.id.kesehatan_tinggibadan);
        kesehatan_centimeter = (TextView) findViewById(R.id.kesehatan_centimeter);
        text_kesehatan_beratbadan = (TextView) findViewById(R.id.text_kesehatan_beratbadan);
        kesehatan_beratbadan = (EditText) findViewById(R.id.kesehatan_beratbadan);
        kesehatan_tinggibadan = (EditText) findViewById(R.id.kesehatan_tinggibadan);
        kesehatan_kilogram = (TextView) findViewById(R.id.kesehatan_kilogram);
        text_kesehatan_keluhan = (TextView) findViewById(R.id.text_kesehatan_keluhan);
        kesehatan_keluhan = (EditText) findViewById(R.id.kesehatan_keluhan);
        text_kesehatan_tindakan = (TextView) findViewById(R.id.text_kesehatan_tindakan);
        kesehatan_tindakan = (EditText) findViewById(R.id.kesehatan_tindakan);
        text_kesehatan_obat = (TextView) findViewById(R.id.text_kesehatan_obat);
        kesehatan_obat = (EditText) findViewById(R.id.kesehatan_obat);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        ubah_catatan_kesehatan.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_kesehatan_nama.setTypeface(typeface);
        kesehatan_nama.setTypeface(typeface);
        text_kesehatan_tanggalberobat.setTypeface(typeface);
        kesehatan_tanggalberobat.setTypeface(typeface);
        text_kesehatan_dokter.setTypeface(typeface);
        kesehatan_dokter.setTypeface(typeface);
        text_kesehatan_rumahsakit.setTypeface(typeface);
        kesehatan_rumahsakit.setTypeface(typeface);
        text_kesehatan_tinggibadan.setTypeface(typeface);
        kesehatan_tinggibadan.setTypeface(typeface);
        kesehatan_centimeter.setTypeface(typeface);
        text_kesehatan_beratbadan.setTypeface(typeface);
        kesehatan_beratbadan.setTypeface(typeface);
        kesehatan_kilogram.setTypeface(typeface);
        text_kesehatan_keluhan.setTypeface(typeface);
        kesehatan_keluhan.setTypeface(typeface);
        text_kesehatan_tindakan.setTypeface(typeface);
        kesehatan_tindakan.setTypeface(typeface);
        text_kesehatan_obat.setTypeface(typeface);
        kesehatan_obat.setTypeface(typeface);

        // Show Data From Database
        kesehatan_nama.setText(session.loadSession(this, "nama"));
        final int kesehatanID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("kesehatanID")));
        kesehatan_tanggalberobat.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tanggal")));
        kesehatan_dokter.setText(cursor.getString(cursor.getColumnIndex("kesehatan_dokter")));
        kesehatan_rumahsakit.setText(cursor.getString(cursor.getColumnIndex("kesehatan_rumahsakit")));
        kesehatan_beratbadan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_berat")));
        kesehatan_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tinggi")));
        kesehatan_keluhan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_keluhan")));
        kesehatan_tindakan.setText(cursor.getString(cursor.getColumnIndex("kesehatan_tindakan")));
        kesehatan_obat.setText(cursor.getString(cursor.getColumnIndex("kesehatan_obat")));

        kesehatan_tanggalberobat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare Calendar
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                final String getBirthdayDate = session.loadSession(CatatanKesehatanUbah.this, "tanggallahir");
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                // Create Dialog DataPicker
                DatePickerDialog mDatePicker = new DatePickerDialog(CatatanKesehatanUbah.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        kesehatan_tanggalberobat.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                Date date = null;
                try {
                    date = dateFormatter.parse(getBirthdayDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                mDatePicker.getDatePicker().setMinDate(cal.getTime().getTime());

                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                int profilID = Integer.parseInt(session.loadSession(CatatanKesehatanUbah.this, "id"));
                String tanggalberobat = kesehatan_tanggalberobat.getText().toString();
                String namadokter = kesehatan_dokter.getText().toString();
                String rumahsakit = kesehatan_rumahsakit.getText().toString();
                String tinggibadan = kesehatan_tinggibadan.getText().toString();
                String beratbadan = kesehatan_beratbadan.getText().toString();
                String keluhan = kesehatan_keluhan.getText().toString();
                String tindakan = kesehatan_tindakan.getText().toString();
                String obat = kesehatan_obat.getText().toString();

                // Check if Value Empty
                if (TextUtils.isEmpty(tanggalberobat)) {
                    // Show Toast
                    Toast.makeText(CatatanKesehatanUbah.this, "Kolom Tanggal Berobat Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(keluhan)) {
                    // Show Toast
                    Toast.makeText(CatatanKesehatanUbah.this, "Kolom Keluhan Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tindakan)) {
                    // Show Toast
                    Toast.makeText(CatatanKesehatanUbah.this, "Kolom Tindakan Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obat)) {
                    // Show Toast
                    Toast.makeText(CatatanKesehatanUbah.this, "Kolom Obat Belum Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    // Declare Condition
                    boolean success = false;

                    try {
                        // Update Data into Database
                        db.updateKesehatan(kesehatanID, profilID, tanggalberobat, namadokter, rumahsakit, tinggibadan, beratbadan,
                                keluhan, tindakan, obat);
                        success = true;
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Check Condition
                    if (success) {
                        // Show Toast Success
                        Toast.makeText(getApplicationContext(), "Catatan Kesehatan Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                    } else {
                        // Show Toast Failed
                        Toast.makeText(getApplicationContext(), "Catatan Kesehatan Gagal Tersimpan", Toast.LENGTH_LONG).show();
                    }

                    // Start Detail Catatan Kesehatan Activity
                    Intent detail_kesehatan = new Intent(CatatanKesehatanUbah.this, CatatanKesehatanDetail.class);
                    lastActivity = System.currentTimeMillis();
                    detail_kesehatan.putExtra("lastActivity", lastActivity);
                    detail_kesehatan.putExtra("id", kesehatanID);
                    detail_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(detail_kesehatan);

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

        // Start Detail kesehatan Activity
        Intent detail_kesehatan = new Intent(this, CatatanKesehatanDetail.class);
        lastActivity = System.currentTimeMillis();
        detail_kesehatan.putExtra("lastActivity", lastActivity);
        detail_kesehatan.putExtra("id", id);
        detail_kesehatan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(detail_kesehatan);

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
            session.clearSession(CatatanKesehatanUbah.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }

}

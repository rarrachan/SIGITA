package com.syafira.SIGITA;

import android.app.Activity;
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
public class UbahMedis extends Activity {

    // Declare
    private TextView ubah_rekam_medis;
    private TextView text_footer;
    private TextView titikdua;
    private TextView text_medis_nama;
    private TextView medis_nama;
    private TextView text_medis_tanggalberobat;
    private EditText medis_tanggalberobat;
    private TextView text_medis_dokter;
    private EditText medis_dokter;
    private TextView text_medis_rumahsakit;
    private EditText medis_rumahsakit;
    private TextView text_medis_tinggibadan;
    private EditText medis_tinggibadan;
    private TextView medis_centimeter;
    private TextView text_medis_beratbadan;
    private EditText medis_beratbadan;
    private TextView medis_kilogram;
    private TextView text_medis_keluhan;
    private EditText medis_keluhan;
    private TextView text_medis_tindakan;
    private EditText medis_tindakan;
    private TextView text_medis_obat;
    private EditText medis_obat;
    private ImageView button_simpan;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.ubah_medis);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneMedis(id);
        cursor.moveToFirst();

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        ubah_rekam_medis = (TextView) findViewById(R.id.ubah_rekam_medis);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_medis_nama = (TextView) findViewById(R.id.text_medis_nama);
        medis_nama = (TextView) findViewById(R.id.medis_nama);
        text_medis_tanggalberobat = (TextView) findViewById(R.id.text_medis_tanggalberobat);
        medis_tanggalberobat = (EditText) findViewById(R.id.medis_tanggalberobat);
        text_medis_dokter = (TextView) findViewById(R.id.text_medis_dokter);
        medis_dokter = (EditText) findViewById(R.id.medis_dokter);
        text_medis_rumahsakit = (TextView) findViewById(R.id.text_medis_rumahsakit);
        medis_rumahsakit = (EditText) findViewById(R.id.medis_rumahsakit);
        text_medis_tinggibadan = (TextView) findViewById(R.id.text_medis_tinggibadan);
        medis_tinggibadan = (EditText) findViewById(R.id.medis_tinggibadan);
        medis_centimeter = (TextView) findViewById(R.id.medis_centimeter);
        text_medis_beratbadan = (TextView) findViewById(R.id.text_medis_beratbadan);
        medis_beratbadan = (EditText) findViewById(R.id.medis_beratbadan);
        medis_tinggibadan = (EditText) findViewById(R.id.medis_tinggibadan);
        medis_kilogram = (TextView) findViewById(R.id.medis_kilogram);
        text_medis_keluhan = (TextView) findViewById(R.id.text_medis_keluhan);
        medis_keluhan = (EditText) findViewById(R.id.medis_keluhan);
        text_medis_tindakan = (TextView) findViewById(R.id.text_medis_tindakan);
        medis_tindakan = (EditText) findViewById(R.id.medis_tindakan);
        text_medis_obat = (TextView) findViewById(R.id.text_medis_obat);
        medis_obat = (EditText) findViewById(R.id.medis_obat);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        ubah_rekam_medis.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_medis_nama.setTypeface(typeface);
        medis_nama.setTypeface(typeface);
        text_medis_tanggalberobat.setTypeface(typeface);
        medis_tanggalberobat.setTypeface(typeface);
        text_medis_dokter.setTypeface(typeface);
        medis_dokter.setTypeface(typeface);
        text_medis_rumahsakit.setTypeface(typeface);
        medis_rumahsakit.setTypeface(typeface);
        text_medis_tinggibadan.setTypeface(typeface);
        medis_tinggibadan.setTypeface(typeface);
        medis_centimeter.setTypeface(typeface);
        text_medis_beratbadan.setTypeface(typeface);
        medis_beratbadan.setTypeface(typeface);
        medis_kilogram.setTypeface(typeface);
        text_medis_keluhan.setTypeface(typeface);
        medis_keluhan.setTypeface(typeface);
        text_medis_tindakan.setTypeface(typeface);
        medis_tindakan.setTypeface(typeface);
        text_medis_obat.setTypeface(typeface);
        medis_obat.setTypeface(typeface);

        // Show Data From Database
        medis_nama.setText(session.loadSession(this, "nama"));
        final int medisID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("medisID")));
        medis_tanggalberobat.setText(cursor.getString(cursor.getColumnIndex("medis_tanggal")));
        medis_dokter.setText(cursor.getString(cursor.getColumnIndex("medis_dokter")));
        medis_rumahsakit.setText(cursor.getString(cursor.getColumnIndex("medis_rumahsakit")));
        medis_beratbadan.setText(cursor.getString(cursor.getColumnIndex("medis_berat")));
        medis_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("medis_tinggi")));
        medis_keluhan.setText(cursor.getString(cursor.getColumnIndex("medis_keluhan")));
        medis_tindakan.setText(cursor.getString(cursor.getColumnIndex("medis_tindakan")));
        medis_obat.setText(cursor.getString(cursor.getColumnIndex("medis_obat")));

        medis_tanggalberobat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare Calendar
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                final String getBirthdayDate = session.loadSession(UbahMedis.this, "tanggallahir");
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                // Create Dialog DataPicker
                DatePickerDialog mDatePicker = new DatePickerDialog(UbahMedis.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        medis_tanggalberobat.setText(dateFormatter.format(newDate.getTime()));
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
                int profilID = Integer.parseInt(session.loadSession(UbahMedis.this, "id"));
                String tanggalberobat = medis_tanggalberobat.getText().toString();
                String namadokter = medis_dokter.getText().toString();
                String rumahsakit = medis_rumahsakit.getText().toString();
                String tinggibadan = medis_tinggibadan.getText().toString();
                String beratbadan = medis_beratbadan.getText().toString();
                String keluhan = medis_keluhan.getText().toString();
                String tindakan = medis_tindakan.getText().toString();
                String obat = medis_obat.getText().toString();

                // Check if Value Empty
                // Check if Value Empty
                if (TextUtils.isEmpty(tanggalberobat)) {
                    // Show Toast
                    Toast.makeText(UbahMedis.this, "Kolom Tanggal Berobat Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(keluhan)) {
                    // Show Toast
                    Toast.makeText(UbahMedis.this, "Kolom Keluhan Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tindakan)) {
                    // Show Toast
                    Toast.makeText(UbahMedis.this, "Kolom Tindakan Belum Terisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obat)) {
                    // Show Toast
                    Toast.makeText(UbahMedis.this, "Kolom Obat Belum Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    // Declare Condition
                    boolean success = false;

                    try {
                        // Update Data into Database
                        db.updateMedis(medisID, profilID, tanggalberobat, namadokter, rumahsakit, tinggibadan, beratbadan,
                                keluhan, tindakan, obat);
                        success = true;
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Check Condition
                    if (success) {
                        // Show Toast Success
                        Toast.makeText(getApplicationContext(), "Rekam Medis Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                    } else {
                        // Show Toast Failed
                        Toast.makeText(getApplicationContext(), "Rekam Medis Gagal Tersimpan", Toast.LENGTH_LONG).show();
                    }

                    // Start Detail Rekam Medis Activity
                    Intent detail_medis = new Intent(UbahMedis.this, DetailMedis.class);
                    lastActivity = System.currentTimeMillis();
                    detail_medis.putExtra("lastActivity", lastActivity);
                    detail_medis.putExtra("id", medisID);
                    detail_medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(detail_medis);

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

        // Start Detail Medis Activity
        Intent detail_medis = new Intent(this, DetailMedis.class);
        lastActivity = System.currentTimeMillis();
        detail_medis.putExtra("lastActivity", lastActivity);
        detail_medis.putExtra("id", id);
        detail_medis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(detail_medis);

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
            session.clearSession(UbahMedis.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }

}

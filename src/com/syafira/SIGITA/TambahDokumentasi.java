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
 * Created by syafira rarra on 05/25/2016.
 */

public class TambahDokumentasi extends Activity {

    // Declare
    private TextView tambah_dokumentasi;
    private TextView text_footer;
    private TextView text_dokumentasigizi_nama;
    private TextView dokumentasigizi_nama;
    private TextView text_dokumentasigizi_usia;
    private TextView dokumentasigizi_usia;
    private TextView dokumentasigizi_bulan;
    private TextView text_dokumentasigizi_tanggal;
    private EditText dokumentasigizi_tanggal;
    private TextView text_dokumentasigizi_tinggibadan;
    private EditText dokumentasigizi_tinggibadan;
    private TextView dokumentasigizi_centimeter;
    private TextView text_dokumentasigizi_beratbadan;
    private EditText dokumentasigizi_beratbadan;
    private TextView dokumentasigizi_kilogram;
    private ImageView button_simpan;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tambah_dokumentasi);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Widget
        tambah_dokumentasi = (TextView) findViewById(R.id.tambah_dokumentasi);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_dokumentasigizi_nama = (TextView) findViewById(R.id.text_dokumentasigizi_nama);
        dokumentasigizi_nama = (TextView) findViewById(R.id.dokumentasigizi_nama);
        text_dokumentasigizi_usia = (TextView) findViewById(R.id.text_dokumentasigizi_usia);
        dokumentasigizi_usia = (TextView) findViewById(R.id.dokumentasigizi_usia);
        text_dokumentasigizi_tanggal = (TextView) findViewById(R.id.text_dokumentasigizi_tanggal);
        dokumentasigizi_tanggal = (EditText) findViewById(R.id.dokumentasigizi_tanggal);
        text_dokumentasigizi_tinggibadan = (TextView) findViewById(R.id.text_dokumentasigizi_tinggibadan);
        dokumentasigizi_tinggibadan = (EditText) findViewById(R.id.dokumentasigizi_tinggibadan);
        dokumentasigizi_centimeter = (TextView) findViewById(R.id.dokumentasigizi_centimeter);
        text_dokumentasigizi_beratbadan = (TextView) findViewById(R.id.text_dokumentasigizi_beratbadan);
        dokumentasigizi_beratbadan = (EditText) findViewById(R.id.dokumentasigizi_beratbadan);
        dokumentasigizi_kilogram = (TextView) findViewById(R.id.dokumentasigizi_kilogram);
        dokumentasigizi_bulan = (TextView) findViewById(R.id.dokumentasigizi_bulan);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        tambah_dokumentasi.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_dokumentasigizi_nama.setTypeface(typeface);
        dokumentasigizi_nama.setTypeface(typeface);
        text_dokumentasigizi_usia.setTypeface(typeface);
        dokumentasigizi_usia.setTypeface(typeface);
        text_dokumentasigizi_tanggal.setTypeface(typeface);
        dokumentasigizi_tanggal.setTypeface(typeface);
        text_dokumentasigizi_tinggibadan.setTypeface(typeface);
        dokumentasigizi_tinggibadan.setTypeface(typeface);
        dokumentasigizi_centimeter.setTypeface(typeface);
        text_dokumentasigizi_beratbadan.setTypeface(typeface);
        dokumentasigizi_beratbadan.setTypeface(typeface);
        dokumentasigizi_kilogram.setTypeface(typeface);

        // Set Text
        dokumentasigizi_nama.setText(session.loadSession(this, "nama"));
        dokumentasigizi_bulan.setText("0");

        // Set OnClickListener
        dokumentasigizi_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                final String getBirthdayDate = session.loadSession(TambahDokumentasi.this, "tanggallahir");

                // Create Dialog DataPicker
                final DatePickerDialog mDatePicker = new DatePickerDialog(TambahDokumentasi.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        dokumentasigizi_tanggal.setText(dateFormatter.format(newDate.getTime()));

                        String getBirthdayDate = session.loadSession(TambahDokumentasi.this, "tanggallahir");
                        String str[] = getBirthdayDate.split("/");
                        int day = Integer.parseInt(str[0]);
                        int month = Integer.parseInt(str[1]);
                        int year = Integer.parseInt(str[2]);

                        String getDate = dokumentasigizi_tanggal.getText().toString();
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
                        dokumentasigizi_usia.setText(umur);
                        dokumentasigizi_bulan.setText(String.valueOf(mon));

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
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                String tanggal = dokumentasigizi_tanggal.getText().toString();
                String tinggiBadan = dokumentasigizi_tinggibadan.getText().toString();
                String beratBadan = dokumentasigizi_beratbadan.getText().toString();
                String usia = dokumentasigizi_usia.getText().toString();
                int bulan = Integer.valueOf(dokumentasigizi_bulan.getText().toString());
                int profilID = Integer.parseInt(session.loadSession(TambahDokumentasi.this, "id"));

                // Check if Value Empty
                if (TextUtils.isEmpty(tanggal)) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Kolom Tanggal Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tinggiBadan)) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Kolom Tinggi Badan Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(beratBadan)) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Kolom Berat Badan Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (bulan >= 0 && bulan < 24 && Float.parseFloat(tinggiBadan) < 45) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Kurang Dari 45 cm", Toast.LENGTH_SHORT).show();

                } else if (bulan >= 0 && bulan < 24 && Float.parseFloat(tinggiBadan) > 110) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Lebih Dari 110 cm", Toast.LENGTH_SHORT).show();

                } else if (bulan >= 24 && bulan <= 60 && Float.parseFloat(tinggiBadan) < 65) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Kurang Dari 65 cm", Toast.LENGTH_SHORT).show();

                } else if (bulan >= 24 && bulan <= 60 && Float.parseFloat(tinggiBadan) > 120) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Lebih Dari 120 cm", Toast.LENGTH_SHORT).show();

                } else if (bulan > 60) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Umur Tidak Boleh Lebih Dari 5 Tahun / 60 Bulan", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(tinggiBadan) < 45) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Kurang Dari 45 cm", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(tinggiBadan) > 120) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Tinggi Tidak Boleh Lebih Dari 120 cm", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratBadan) < 2) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Berat Tidak Boleh Kurang Dari 2 kg", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratBadan) > 30) {
                    // Show Toast
                    Toast.makeText(TambahDokumentasi.this, "Berat Tidak Boleh Lebih Dari 30 kg", Toast.LENGTH_SHORT).show();

                } else {

                    // Load Database
                    db = new DBHelper(TambahDokumentasi.this);
                    db.open();
                    Cursor cursorBBU;
                    Cursor cursorTBU;
                    Cursor cursorBBTB;
                    Cursor cursorIMTU;
                    if ((session.loadSession(TambahDokumentasi.this, "gender")).equals("Laki-laki")) {
                        cursorBBU = db.getLakiBBUList(bulan);
                        cursorTBU = db.getLakiTBUList(bulan);
                        if (bulan < 24) {
                            cursorBBTB = db.getLakiBBTBList_0_24(Float.parseFloat(tinggiBadan));
                        } else {
                            cursorBBTB = db.getLakiBBTBList_24_60(Float.parseFloat(tinggiBadan));
                        }
                        cursorIMTU = db.getLakiIMTUList(bulan);
                    } else {
                        cursorBBU = db.getPerempuanBBUList(bulan);
                        cursorTBU = db.getPerempuanTBUList(bulan);
                        if (bulan < 24) {
                            cursorBBTB = db.getPerempuanBBTBList_0_24(Float.parseFloat(tinggiBadan));
                        } else {
                            cursorBBTB = db.getPerempuanBBTBList_24_60(Float.parseFloat(tinggiBadan));
                        }
                        cursorIMTU = db.getPerempuanIMTUList(bulan);
                    }
                    cursorBBU.moveToFirst();
                    cursorTBU.moveToFirst();
                    cursorBBTB.moveToFirst();
                    cursorIMTU.moveToFirst();

                    // Berat Badan / Umur
                    float bbu = 0;
                    if ((session.loadSession(TambahDokumentasi.this, "gender")).equals("Laki-laki")) {
                        // Berat badan < Median
                        if (Float.parseFloat(beratBadan) < Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) {
                            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_min1sd"))));
                        } // Berat Badan > Median
                        else if (Float.parseFloat(beratBadan) > Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) {
                            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_1sd"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median"))));
                        } // Berat Badan == Median
                        else if (Float.parseFloat(beratBadan) == Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) {
                            bbu = (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_1sd")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_1sd"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_min1sd"))));
                        }
                    } else {
                        // Berat badan < Median
                        if (Float.parseFloat(beratBadan) < Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median")))) {
                            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_min1sd"))));
                        } // Berat Badan > Median
                        else if (Float.parseFloat(beratBadan) > Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median")))) {
                            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_1sd"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median"))));
                        } // Berat Badan == Median
                        else if (Float.parseFloat(beratBadan) == Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median")))) {
                            bbu = (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_median"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_1sd")))) /
                                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_1sd"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("perempuan_bbu_min1sd"))));
                        }
                    }

                    String status_bbu = null;
                    if (bbu < Float.parseFloat("-3")) {
                        status_bbu = "Gizi Buruk";
                    } else if (bbu >= Float.parseFloat("-3") && bbu < Float.parseFloat("-2")) {
                        status_bbu = "Gizi Kurang";
                    } else if (bbu >= Float.parseFloat("-2") && bbu <= Float.parseFloat("2")) {
                        status_bbu = "Gizi Baik";
                    } else if (bbu > Float.parseFloat("2")) {
                        status_bbu = "Gizi Lebih";
                    }

                    // Tinggi Badan / Umur
                    float tbu = 0;
                    if ((session.loadSession(TambahDokumentasi.this, "gender")).equals("Laki-laki")) {
                        // Tinggi Badan < Median
                        if (Float.parseFloat(tinggiBadan) < Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) {
                            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_min1sd"))));
                        } // Tinggi Badan > Median
                        else if (Float.parseFloat(tinggiBadan) > Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) {
                            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_1sd"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median"))));
                        } // Tinggi Badan == Median
                        else if (Float.parseFloat(tinggiBadan) == Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) {
                            tbu = (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_1sd")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_1sd"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_min1sd"))));
                        }
                    } else {
                        // Tinggi Badan < Median
                        if (Float.parseFloat(tinggiBadan) < Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median")))) {
                            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_min1sd"))));
                        } // Tinggi Badan > Median
                        else if (Float.parseFloat(tinggiBadan) > Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median")))) {
                            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_1sd"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median"))));
                        } // Tinggi Badan == Median
                        else if (Float.parseFloat(tinggiBadan) == Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median")))) {
                            tbu = (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_median"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_1sd")))) /
                                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_1sd"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("perempuan_tbu_min1sd"))));
                        }
                    }

                    String status_tbu = null;
                    if (tbu < Float.parseFloat("-3")) {
                        status_tbu = "Sangat Pendek";
                    } else if (tbu >= Float.parseFloat("-3") && tbu < Float.parseFloat("-2")) {
                        status_tbu = "Pendek";
                    } else if (tbu >= Float.parseFloat("-2") && tbu <= Float.parseFloat("2")) {
                        status_tbu = "Normal";
                    } else if (tbu > Float.parseFloat("2")) {
                        status_tbu = "Tinggi";
                    }

                    // Berat Badan / Tinggi Badan
                    float bbtb = 0;
                    if ((session.loadSession(TambahDokumentasi.this, "gender")).equals("Laki-laki")) {
                        // Tinggi Badan < Median
                        if (Float.parseFloat(beratBadan) < Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                            bbtb = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_min1sd"))));
                        } // Tinggi Badan > Median
                        else if (Float.parseFloat(beratBadan) > Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                            bbtb = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))));
                        } // Tinggi Badan == Median
                        else if (Float.parseFloat(beratBadan) == Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                            bbtb = (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_min1sd"))));
                        }
                    } else {
                        // Tinggi Badan < Median
                        if (Float.parseFloat(beratBadan) < Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
                            bbtb = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_min1sd"))));
                        } // Tinggi Badan > Median
                        else if (Float.parseFloat(beratBadan) > Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
                            bbtb = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median"))));
                        } // Tinggi Badan == Median
                        else if (Float.parseFloat(beratBadan) == Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
                            bbtb = (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_1sd")))) /
                                    (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_min1sd"))));
                        }
                    }

                    String status_bbtb = null;
                    if (bbtb < Float.parseFloat("-3")) {
                        status_bbtb = "Sangat Kurus";
                    } else if (bbtb >= Float.parseFloat("-3") && bbtb < Float.parseFloat("-2")) {
                        status_bbtb = "Kurus";
                    } else if (bbtb >= Float.parseFloat("-2") && bbtb <= Float.parseFloat("2")) {
                        status_bbtb = "Normal";
                    } else if (bbtb > Float.parseFloat("2")) {
                        status_bbtb = "Gemuk";
                    }

                    // Indeks Massa Tubuh / Umur
                    float imt = Float.parseFloat(beratBadan) / ((Float.parseFloat(tinggiBadan) / 100) * (Float.parseFloat(tinggiBadan) / 100));
                    float imtu = 0;
                    if ((session.loadSession(TambahDokumentasi.this, "gender")).equals("Laki-laki")) {
                        // Indeks Massa Tubuh < Median
                        if (imt < Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median")))) {
                            imtu = (imt - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_min1sd"))));
                        } // Indeks Massa Tubuh > Median
                        else if (imt > Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median")))) {
                            imtu = (imt - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_1sd"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median"))));
                        } // Indeks Massa Tubuh == Median
                        else if (imt == Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median")))) {
                            imtu = (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_median"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_1sd")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_1sd"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("laki_imtu_min1sd"))));
                        }
                    } else {
                        // Indeks Massa Tubuh < Median
                        if (imt < Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median")))) {
                            imtu = (imt - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_min1sd"))));
                        } // Indeks Massa Tubuh > Median
                        else if (imt > Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median")))) {
                            imtu = (imt - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_1sd"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median"))));
                        } // Indeks Massa Tubuh == Median
                        else if (imt == Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median")))) {
                            imtu = (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_median"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_1sd")))) /
                                    (Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_1sd"))) - Float.parseFloat(cursorIMTU.getString(cursorIMTU.getColumnIndex("perempuan_imtu_min1sd"))));
                        }
                    }

                    String status_imtu = null;
                    if (imtu < Float.parseFloat("-3")) {
                        status_imtu = "Sangat Kurus";
                    } else if (imtu >= Float.parseFloat("-3") && imtu < Float.parseFloat("-2")) {
                        status_imtu = "Kurus";
                    } else if (imtu >= Float.parseFloat("-2") && imtu <= Float.parseFloat("2")) {
                        status_imtu = "Normal";
                    } else if (imtu > Float.parseFloat("2")) {
                        status_imtu = "Gemuk";
                    }

                    // Declare Condition
                    boolean success = false;

                    try {
                        // Insert Data into Database
                        db.insertDokumentasi(profilID, tanggal, usia, bulan,
                                beratBadan, tinggiBadan, status_bbu, status_tbu, status_bbtb, status_imtu);
                        success = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Check Condition
                    if (success) {
                        // Show Toast Success
                        Toast.makeText(getApplicationContext(), "Dokumentasi Gizi Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                    } else {
                        // Show Toast Failed
                        Toast.makeText(getApplicationContext(), "Dokumentasi Gizi Gagal Tersimpan", Toast.LENGTH_LONG).show();
                    }

                    // Start Rekam Medis Activity
                    Intent dokumentasi_gizi = new Intent(TambahDokumentasi.this, DokumentasiGizi.class);
                    lastActivity = System.currentTimeMillis();
                    dokumentasi_gizi.putExtra("lastActivity", lastActivity);
                    dokumentasi_gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(dokumentasi_gizi);

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
        ScrollView view = (ScrollView) findViewById(R.id.scrollView);
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
        Intent dokumentasi_gizi = new Intent(TambahDokumentasi.this, DokumentasiGizi.class);
        lastActivity = System.currentTimeMillis();
        dokumentasi_gizi.putExtra("lastActivity", lastActivity);
        dokumentasi_gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dokumentasi_gizi);

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
            session.clearSession(TambahDokumentasi.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

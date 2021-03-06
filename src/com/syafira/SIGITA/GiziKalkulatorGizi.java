package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/07/2016.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GiziKalkulatorGizi extends Gizi {

    // Declare
    private TextView kalkulator_gizi;
    private TextView text_kalkulatorgizi_tanggallahir;
    private EditText kalkulatorgizi_tanggallahir;
    private TextView kalkulatorgizi_umur;
    private TextView kalkulatorgizi_bulan;
    private TextView text_kalkulatorgizi_jeniskelamin;
    private RadioGroup kalkulatorgizi_jeniskelamin;
    private RadioButton kalkulatorgizi_lakilaki;
    private RadioButton kalkulatorgizi_perempuan;
    private TextView text_kalkulatorgizi_tinggibadan;
    private EditText kalkulatorgizi_tinggibadan;
    private TextView kalkulatorgizi_centimeter;
    private TextView text_kalkulatorgizi_beratbadan;
    private EditText kalkulatorgizi_beratbadan;
    private TextView kalkulatorgizi_kilogram;
    private TextView text_footer;
    private ImageView button_hitung;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.gizi_kalkulatorgizi);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Widget
        kalkulator_gizi = (TextView) findViewById(R.id.kalkulator_gizi);
        text_kalkulatorgizi_tanggallahir = (TextView) findViewById(R.id.text_kalkulatorgizi_tanggallahir);
        kalkulatorgizi_tanggallahir = (EditText) findViewById(R.id.kalkulatorgizi_tanggallahir);
        kalkulatorgizi_umur = (TextView) findViewById(R.id.kalkulatorgizi_umur);
        kalkulatorgizi_bulan = (TextView) findViewById(R.id.kalkulatorgizi_bulan);
        text_kalkulatorgizi_jeniskelamin = (TextView) findViewById(R.id.text_kalkulatorgizi_jeniskelamin);
        kalkulatorgizi_lakilaki = (RadioButton) findViewById(R.id.kalkulatorgizi_lakilaki);
        kalkulatorgizi_perempuan = (RadioButton) findViewById(R.id.kalkulatorgizi_perempuan);
        text_kalkulatorgizi_tinggibadan = (TextView) findViewById(R.id.text_kalkulatorgizi_tinggibadan);
        kalkulatorgizi_tinggibadan = (EditText) findViewById(R.id.kalkulatorgizi_tinggibadan);
        kalkulatorgizi_centimeter = (TextView) findViewById(R.id.kalkulatorgizi_centimeter);
        text_kalkulatorgizi_beratbadan = (TextView) findViewById(R.id.text_kalkulatorgizi_beratbadan);
        kalkulatorgizi_beratbadan = (EditText) findViewById(R.id.kalkulatorgizi_beratbadan);
        kalkulatorgizi_kilogram = (TextView) findViewById(R.id.kalkulatorgizi_kilogram);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_hitung = (ImageView) findViewById(R.id.button_hitung);
        kalkulatorgizi_jeniskelamin = (RadioGroup) findViewById(R.id.kalkulatorgizi_jeniskelamin);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        kalkulator_gizi.setTypeface(typeface);
        text_kalkulatorgizi_tanggallahir.setTypeface(typeface);
        kalkulatorgizi_tanggallahir.setTypeface(typeface);
        text_kalkulatorgizi_jeniskelamin.setTypeface(typeface);
        kalkulatorgizi_lakilaki.setTypeface(typeface);
        kalkulatorgizi_perempuan.setTypeface(typeface);
        text_kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_centimeter.setTypeface(typeface);
        text_kalkulatorgizi_beratbadan.setTypeface(typeface);
        kalkulatorgizi_beratbadan.setTypeface(typeface);
        kalkulatorgizi_kilogram.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Set Text
        kalkulatorgizi_bulan.setText("0");

        // Set OnClickListener
        kalkulatorgizi_tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Declare Calendar
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                // Create Dialog DataPicker
                final DatePickerDialog mDatePicker = new DatePickerDialog(GiziKalkulatorGizi.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        
                        // Set DateFormatter
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                        // Get Today Date
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        String tglLahir = dateFormatter.format(newDate.getTime());
                        String hariIni = dateFormatter.format(Calendar.getInstance().getTimeInMillis());

                        // Set Text
                        kalkulatorgizi_tanggallahir.setText(tglLahir);

                        // Split Calender by "/" ==> dd/MM/yyyy into strings[0]/strings[1]/strings[2]
                        String str[] = tglLahir.split("/");
                        int day = Integer.parseInt(str[0]);
                        int month = Integer.parseInt(str[1]);
                        int year = Integer.parseInt(str[2]);

                        String strings[] = hariIni.split("/");
                        int dayDate = Integer.parseInt(strings[0]);
                        int monthDate = Integer.parseInt(strings[1]);
                        int yearDate = Integer.parseInt(strings[2]);

                        // (today calendar) - (birthday calendar)
                        int years = yearDate - year;
                        int months = monthDate - month;
                        int days = dayDate - day;

                        // days not negative
                        if (days < 0) {
                            months--;
                            days += newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                        }

                        // months not negative
                        if (months < 0) {
                            years--;
                            months += 12;
                        }

                        // for bulan and umur
                        int mon = monthDate - month;
                        int y = 0;
                        while (y < years) {
                            mon += 12;
                            y += 1;
                        }

                        // Set Text
                        String umur = mon + " bulan / " + years + " tahun " + months + " bulan " + days + " hari";
                        kalkulatorgizi_bulan.setText(String.valueOf(mon));
                        kalkulatorgizi_umur.setText(umur);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
            }
        });

        button_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                String tanggalLahir = kalkulatorgizi_tanggallahir.getText().toString();
                String tinggiBadan = kalkulatorgizi_tinggibadan.getText().toString();
                String beratBadan = kalkulatorgizi_beratbadan.getText().toString();
                String umur = kalkulatorgizi_umur.getText().toString();
                String bulan = kalkulatorgizi_bulan.getText().toString();

                // Check if Value Empty
                if (TextUtils.isEmpty(tanggalLahir)) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Kolom Tanggal Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tinggiBadan)) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Kolom Tinggi Badan Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(beratBadan)) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Kolom Berat Badan Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (kalkulatorgizi_jeniskelamin.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Kolom Jenis Kelamin Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (Integer.valueOf(bulan) > 60) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Umur Tidak Boleh Lebih Dari 5 Tahun / 60 Bulan", Toast.LENGTH_SHORT).show();

                } else if (Integer.valueOf(bulan) < 24 && Float.parseFloat(tinggiBadan) < 45) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Tinggi Tidak Boleh Kurang Dari 45 cm", Toast.LENGTH_SHORT).show();

                } else if (Integer.valueOf(bulan) < 24 && Float.parseFloat(tinggiBadan) > 110) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Tinggi Tidak Boleh Lebih Dari 110 cm", Toast.LENGTH_SHORT).show();

                } else if (Integer.valueOf(bulan) >= 24 && Float.parseFloat(tinggiBadan) < 65) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Tinggi Tidak Boleh Kurang Dari 65 cm", Toast.LENGTH_SHORT).show();

                } else if (Integer.valueOf(bulan) >= 24 && Float.parseFloat(tinggiBadan) > 120) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Tinggi Tidak Boleh Lebih Dari 120 cm", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratBadan) < 2) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Berat Tidak Boleh Kurang Dari 2 kg", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratBadan) > 30) {
                    // Show Toast
                    Toast.makeText(GiziKalkulatorGizi.this, "Berat Tidak Boleh Lebih Dari 30 kg", Toast.LENGTH_SHORT).show();

                // everything is not empty
                } else {

                    String jenisKelamin = ((RadioButton) findViewById(kalkulatorgizi_jeniskelamin.getCheckedRadioButtonId())).getText().toString();

                    Intent hasilkalkulatorgizi = new Intent(GiziKalkulatorGizi.this, GiziKalkulatorGiziHasil.class);
                    lastActivity = System.currentTimeMillis();
                    hasilkalkulatorgizi.putExtra("lastActivity", lastActivity);
                    hasilkalkulatorgizi.putExtra("umur", umur);
                    hasilkalkulatorgizi.putExtra("bulan", bulan);
                    hasilkalkulatorgizi.putExtra("tanggalLahir", tanggalLahir);
                    hasilkalkulatorgizi.putExtra("tinggiBadan", tinggiBadan);
                    hasilkalkulatorgizi.putExtra("beratBadan", beratBadan);
                    hasilkalkulatorgizi.putExtra("jenisKelamin", jenisKelamin);
                    hasilkalkulatorgizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(hasilkalkulatorgizi);

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
        // Start Home Activity
        Intent gizi = new Intent(this, Gizi.class);
        lastActivity = System.currentTimeMillis();
        gizi.putExtra("lastActivity", lastActivity);
        gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(gizi);

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
            session.clearSession(GiziKalkulatorGizi.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

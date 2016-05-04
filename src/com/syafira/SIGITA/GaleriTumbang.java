package com.syafira.SIGITA;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.galeri_tumbang);

        // Load Session Manager
        session = new SessionManager();

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
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_galeritumbang.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);

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
                        String umur = years + " tahun " + months + " bulan " + days + " hari";
                        galeri_usia.setText(umur);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
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
    }
}

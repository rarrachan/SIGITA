package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/07/2016.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class KalkulatorGizi extends Activity{

    // Declare
    private TextView kalkulator_gizi;
    private TextView text_kalkulatorgizi_tanggallahir;
    private TextView titikdua;
    private EditText kalkulatorgizi_tanggallahir;
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

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.kalkulator_gizi);

        // Load Widget
        kalkulator_gizi = (TextView) findViewById(R.id.kalkulator_gizi);
        text_kalkulatorgizi_tanggallahir = (TextView) findViewById(R.id.text_kalkulatorgizi_tanggallahir);
        titikdua = (TextView) findViewById(R.id.titikdua);
        kalkulatorgizi_tanggallahir = (EditText) findViewById(R.id.kalkulatorgizi_tanggallahir);
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
        kalkulatorgizi_jeniskelamin = (RadioGroup) findViewById(R.id.profil_jeniskelamin);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        kalkulator_gizi.setTypeface(typeface);
        text_kalkulatorgizi_tanggallahir.setTypeface(typeface);
        titikdua.setTypeface(typeface);
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

        // Set OnClickListener
        kalkulatorgizi_tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare Calendar
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                // Create Dialog DataPicker
                DatePickerDialog mDatePicker = new DatePickerDialog(KalkulatorGizi.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        kalkulatorgizi_tanggallahir.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        button_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                String usia = kalkulatorgizi_tanggallahir.getText().toString();
                String jenisKelamin = ((RadioButton) findViewById(kalkulatorgizi_jeniskelamin.getCheckedRadioButtonId())).getText().toString();
                String tinggiBadan = kalkulatorgizi_tinggibadan.getText().toString();
                String beratBadan = kalkulatorgizi_beratbadan.getText().toString();


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
        // Close This Activity
        finish();
    }
}

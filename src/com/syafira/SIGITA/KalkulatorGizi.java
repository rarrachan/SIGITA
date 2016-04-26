package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/07/2016.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class KalkulatorGizi extends Activity{

    // Declare
    private TextView kalkulator_gizi;
    private TextView text_kalkulatorgizi_usia;
    private TextView titikdua;
    private EditText kalkulatorgizi_usia;
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
        text_kalkulatorgizi_usia = (TextView) findViewById(R.id.text_kalkulatorgizi_usia);
        titikdua = (TextView) findViewById(R.id.titikdua);
        kalkulatorgizi_usia = (EditText) findViewById(R.id.kalkulatorgizi_usia);
        kalkulatorgizi_bulan = (TextView) findViewById(R.id.kalkulatorgizi_bulan);
        text_kalkulatorgizi_jeniskelamin = (TextView) findViewById(R.id.text_kalkulatorgizi_jeniskelamin);
        kalkulatorgizi_lakilaki = (RadioButton) findViewById(R.id.kalkulatorgizi_lakilaki);
        kalkulatorgizi_perempuan = (RadioButton) findViewById(R.id.kalkulatorgizi_perempuan);
        text_kalkulatorgizi_tinggibadan = (TextView) findViewById(R.id.text_kalkulatorgizi_tinggibadan);
        kalkulatorgizi_tinggibadan = (EditText) findViewById(R.id.kalkulatorgizi_tinggibadan);
        kalkulatorgizi_centimeter = (TextView) findViewById(R.id.kalkulatorgizi_centimeter);
        text_kalkulatorgizi_beratbadan = (TextView) findViewById(R.id.text_kalkulatorgizi_beratbadan);
        kalkulatorgizi_beratbadan = (EditText) findViewById(R.id.kalkulatorgizi_beratbadan);
        text_footer = (TextView) findViewById(R.id.text_footer);
        button_hitung = (ImageView) findViewById(R.id.button_hitung);
        kalkulatorgizi_jeniskelamin = (RadioGroup) findViewById(R.id.profil_jeniskelamin);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        kalkulator_gizi.setTypeface(typeface);
        text_kalkulatorgizi_usia.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        kalkulatorgizi_usia.setTypeface(typeface);
        kalkulatorgizi_bulan.setTypeface(typeface);
        text_kalkulatorgizi_jeniskelamin.setTypeface(typeface);
        kalkulatorgizi_lakilaki.setTypeface(typeface);
        kalkulatorgizi_perempuan.setTypeface(typeface);
        text_kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_centimeter.setTypeface(typeface);
        text_kalkulatorgizi_beratbadan.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Set OnClickListener
        button_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                String usia = kalkulatorgizi_usia.getText().toString();
                String jenisKelamin = ((RadioButton) findViewById(kalkulatorgizi_jeniskelamin.getCheckedRadioButtonId())).getText().toString();
                String tinggiBadan = kalkulatorgizi_tinggibadan.getText().toString();
                String beratBadan = kalkulatorgizi_beratbadan.getText().toString();


            }
        });
    }
}

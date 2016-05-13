package com.syafira.SIGITA;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by syafira rarra on 05/11/2016.
 */

public class HasilKalkulatorGizi extends Activity {

    // Declare
    private TextView hasil_kalkulator_gizi;
    private TextView text_kalkulatorgizi_usia;
    private TextView kalkulatorgizi_usia;
    private TextView text_kalkulatorgizi_tanggallahir;
    private TextView kalkulatorgizi_tanggallahir;
    private TextView text_kalkulatorgizi_jeniskelamin;
    private TextView kalkulatorgizi_jeniskelamin;
    private TextView text_kalkulatorgizi_tinggibadan;
    private TextView kalkulatorgizi_tinggibadan;
    private TextView kalkulatorgizi_centimeter;
    private TextView text_kalkulatorgizi_beratbadan;
    private TextView kalkulatorgizi_beratbadan;
    private TextView bbu;
    private TextView titikdua;
    private TextView hasil_bbu;
    private TextView tbu;
    private TextView hasil_tbu;
    private TextView bbtb;
    private TextView hasil_bbtb;
    private TextView imtu;
    private TextView hasil_imtu;
    private TextView text_footer;

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.hasil_kalkulator_gizi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        String tanggalLahir = fetchID.getStringExtra("tanggalLahir");
        String umur = fetchID.getStringExtra("umur");
        String tinggiBadan = fetchID.getStringExtra("tinggiBadan");
        String beratBadan = fetchID.getStringExtra("beratBadan");
        String jenisKelamin = fetchID.getStringExtra("jenisKelamin");

        // Load Widget
        hasil_kalkulator_gizi = (TextView) findViewById(R.id.hasil_kalkulator_gizi);
        text_kalkulatorgizi_tanggallahir = (TextView) findViewById(R.id.text_kalkulatorgizi_tanggallahir);
        kalkulatorgizi_tanggallahir = (TextView) findViewById(R.id.kalkulatorgizi_tanggallahir);
        text_kalkulatorgizi_usia = (TextView) findViewById(R.id.text_kalkulatorgizi_usia);
        kalkulatorgizi_usia = (TextView) findViewById(R.id.kalkulatorgizi_usia);
        text_kalkulatorgizi_jeniskelamin = (TextView) findViewById(R.id.text_kalkulatorgizi_jeniskelamin);
        kalkulatorgizi_jeniskelamin = (TextView) findViewById(R.id.kalkulatorgizi_jeniskelamin);
        text_kalkulatorgizi_tinggibadan = (TextView) findViewById(R.id.text_kalkulatorgizi_tinggibadan);
        kalkulatorgizi_tinggibadan = (TextView) findViewById(R.id.kalkulatorgizi_tinggibadan);
        kalkulatorgizi_centimeter = (TextView) findViewById(R.id.kalkulatorgizi_centimeter);
        text_kalkulatorgizi_beratbadan = (TextView) findViewById(R.id.text_kalkulatorgizi_beratbadan);
        kalkulatorgizi_beratbadan = (TextView) findViewById(R.id.kalkulatorgizi_beratbadan);
        bbu = (TextView) findViewById(R.id.bbu);
        titikdua = (TextView) findViewById(R.id.titikdua);
        hasil_bbu = (TextView) findViewById(R.id.hasil_bbu);
        tbu = (TextView) findViewById(R.id.tbu);
        hasil_tbu = (TextView) findViewById(R.id.hasil_tbu);
        bbtb = (TextView) findViewById(R.id.bbtb);
        hasil_bbtb = (TextView) findViewById(R.id.hasil_bbtb);
        imtu = (TextView) findViewById(R.id.imtu);
        hasil_imtu = (TextView) findViewById(R.id.hasil_imtu);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custom Font
        final Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        hasil_kalkulator_gizi.setTypeface(typeface);
        text_kalkulatorgizi_tanggallahir.setTypeface(typeface);
        kalkulatorgizi_tanggallahir.setTypeface(typeface);
        text_kalkulatorgizi_usia.setTypeface(typeface);
        kalkulatorgizi_usia.setTypeface(typeface);
        text_kalkulatorgizi_jeniskelamin.setTypeface(typeface);
        kalkulatorgizi_jeniskelamin.setTypeface(typeface);
        text_kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_tinggibadan.setTypeface(typeface);
        kalkulatorgizi_centimeter.setTypeface(typeface);
        text_kalkulatorgizi_beratbadan.setTypeface(typeface);
        kalkulatorgizi_beratbadan.setTypeface(typeface);
        bbu.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        hasil_bbu.setTypeface(typeface);
        tbu.setTypeface(typeface);
        hasil_tbu.setTypeface(typeface);
        bbtb.setTypeface(typeface);
        hasil_bbtb.setTypeface(typeface);
        imtu.setTypeface(typeface);
        hasil_imtu.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        kalkulatorgizi_tanggallahir.setText(tanggalLahir);
        kalkulatorgizi_usia.setText(umur);
        kalkulatorgizi_tinggibadan.setText(tinggiBadan);
        kalkulatorgizi_beratbadan.setText(beratBadan);
        if((jenisKelamin).equals("L")){
            kalkulatorgizi_jeniskelamin.setText("Laki-Laki");
        } else {
            kalkulatorgizi_jeniskelamin.setText("Perempuan");
        }

        tabHost = (TabHost) findViewById(android.R.id.tabhost);     // The activity TabHost
        tabHost.setup();

        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("BB/U").setIndicator("BB/U").setContent(R.id.bbu);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("TB/U").setIndicator("TB/U").setContent(R.id.tbu);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("BB/TB").setIndicator("BB/TB").setContent(R.id.bbtb);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("IMT/U").setIndicator("IMT/U").setContent(R.id.imtu);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setTypeface(typeface);
            tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabHost.getCurrentTab()){
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                tv.setTextColor(Color.parseColor("#D046F2"));
            } else {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                tv.setTextColor(Color.parseColor("#FF0000"));
            }

            // Set Height Tab Title
            final View view = tabHost.getTabWidget().getChildTabViewAt(i);
            if ( view != null ) {
                // reduce height of the tab
                view.getLayoutParams().height *= 0.66;

                //  get title text view
                final View textView = view.findViewById(android.R.id.title);
                if ( textView instanceof TextView ) {
                    // just in case check the type

                    // center text
                    ((TextView) textView).setGravity(Gravity.CENTER);
                    // wrap text
                    ((TextView) textView).setSingleLine(false);

                    // explicitly set layout parameters
                    textView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
            }
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#FF0000"));
                    if (i == tabHost.getCurrentTab()) {
                        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                        tv.setTextColor(Color.parseColor("#D046F2"));
                    } else {
                        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                        tv.setTextColor(Color.parseColor("#FF0000"));
                    }
                }
            }
        });

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Close This Activity
        finish();
    }
}
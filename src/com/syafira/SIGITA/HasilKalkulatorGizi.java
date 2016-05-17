package com.syafira.SIGITA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.hasil_kalkulator_gizi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        String tanggalLahir = fetchID.getStringExtra("tanggalLahir");
        String umur = fetchID.getStringExtra("umur");
        String bulan = fetchID.getStringExtra("bulan");
        String tinggiBadan = fetchID.getStringExtra("tinggiBadan");
        String beratBadan = fetchID.getStringExtra("beratBadan");
        String jenisKelamin = fetchID.getStringExtra("jenisKelamin");
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursorBBU = null;
        Cursor cursorTBU = null;
        if((jenisKelamin).equals("L")) {
            cursorBBU = db.getLakiBBUList(Integer.valueOf(bulan));
            cursorTBU = db.getLakiTBUList(Integer.valueOf(bulan));
        }
        cursorBBU.moveToFirst();
        cursorTBU.moveToFirst();

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

        // Berat Badan / Umur
        float bbu = 0;
        if(Float.parseFloat(beratBadan) < Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))){
            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) /
                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_min1sd"))));
        } else if(Float.parseFloat(beratBadan) > Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))){
            bbu = (Float.parseFloat(beratBadan) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median")))) /
                    (Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_1sd"))) - Float.parseFloat(cursorBBU.getString(cursorBBU.getColumnIndex("laki_bbu_median"))));
        }

        String status_bbu = null;
        if (bbu < Float.parseFloat("-3")){
            status_bbu = "Gizi Buruk";
        } else if (bbu >= Float.parseFloat("-3") && bbu < Float.parseFloat("-2")){
            status_bbu = "Gizi Kurang";
        } else if (bbu >= Float.parseFloat("-2") && bbu <= Float.parseFloat("2")) {
            status_bbu = "Gizi Baik";
        } else if (bbu > Float.parseFloat("2")) {
            status_bbu = "Gizi Lebih";
        }

        hasil_bbu.setText(status_bbu);

        // Tinggi Badan / Umur
        float tbu = 0;
        if(Float.parseFloat(tinggiBadan) < Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))){
            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) /
                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_min1sd"))));
        } else if(Float.parseFloat(tinggiBadan) > Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))){
            tbu = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median")))) /
                    (Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_1sd"))) - Float.parseFloat(cursorTBU.getString(cursorTBU.getColumnIndex("laki_tbu_median"))));
        }

        String status_tbu = null;
        if (bbu < Float.parseFloat("-3")){
            status_tbu = "Sangat Pendek";
        } else if (tbu >= Float.parseFloat("-3") && tbu < Float.parseFloat("-2")){
            status_tbu = "Pendek";
        } else if (tbu >= Float.parseFloat("-2") && tbu <= Float.parseFloat("2")) {
            status_tbu = "Normal";
        } else if (tbu > Float.parseFloat("2")) {
            status_tbu = "Tinggi";
        }

        hasil_tbu.setText(status_tbu);

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
                view.getLayoutParams().height *= 0.5;

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
        Intent gizi = new Intent(this, Gizi.class);
        lastActivity = System.currentTimeMillis();
        gizi.putExtra("lastActivity", lastActivity);
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
            session.clearSession(HasilKalkulatorGizi.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}

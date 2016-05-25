package com.syafira.SIGITA;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.*;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

    private XYMultipleSeriesDataset BBUdataset;
    private XYMultipleSeriesRenderer BBUmultiRenderer;
    private GraphicalView BBUChart;
    private XYMultipleSeriesDataset TBUdataset;
    private XYMultipleSeriesRenderer TBUmultiRenderer;
    private GraphicalView TBUChart;
    private XYMultipleSeriesDataset BBTBdataset;
    private XYMultipleSeriesRenderer BBTBmultiRenderer;
    private GraphicalView BBTBChart;
    private XYMultipleSeriesDataset IMTUdataset;
    private XYMultipleSeriesRenderer IMTUmultiRenderer;
    private GraphicalView IMTUChart;

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
        Cursor cursorBBU;
        Cursor cursorTBU;
        Cursor cursorBBTB;
        Cursor cursorIMTU;
        if ((jenisKelamin).equals("L")) {
            cursorBBU = db.getLakiBBUList(Integer.valueOf(bulan));
            cursorTBU = db.getLakiTBUList(Integer.valueOf(bulan));
            if (Integer.valueOf(bulan) <= 24) {
                cursorBBTB = db.getLakiBBTBList_0_24(Integer.valueOf(tinggiBadan));
            } else {
                cursorBBTB = db.getLakiBBTBList_24_60(Integer.valueOf(tinggiBadan));
            }
            cursorIMTU = db.getLakiIMTUList(Integer.valueOf(bulan));
        } else {
            cursorBBU = db.getPerempuanBBUList(Integer.valueOf(bulan));
            cursorTBU = db.getPerempuanTBUList(Integer.valueOf(bulan));
            if (Integer.valueOf(bulan) <= 24) {
                cursorBBTB = db.getPerempuanBBTBList_0_24(Integer.valueOf(tinggiBadan));
            } else {
                cursorBBTB = db.getPerempuanBBTBList_24_60(Integer.valueOf(tinggiBadan));
            }
            cursorIMTU = db.getPerempuanIMTUList(Integer.valueOf(bulan));
        }
        cursorBBU.moveToFirst();
        cursorTBU.moveToFirst();
        cursorBBTB.moveToFirst();
        cursorIMTU.moveToFirst();

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
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
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
        if ((jenisKelamin).equals("L")) {
            kalkulatorgizi_jeniskelamin.setText("Laki-Laki");
        } else {
            kalkulatorgizi_jeniskelamin.setText("Perempuan");
        }

        // Berat Badan / Umur
        float bbu = 0;
        if ((jenisKelamin).equals("L")) {
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

        hasil_bbu.setText(status_bbu);

        // Tinggi Badan / Umur
        float tbu = 0;
        if ((jenisKelamin).equals("L")) {
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
        if (bbu < Float.parseFloat("-3")) {
            status_tbu = "Sangat Pendek";
        } else if (tbu >= Float.parseFloat("-3") && tbu < Float.parseFloat("-2")) {
            status_tbu = "Pendek";
        } else if (tbu >= Float.parseFloat("-2") && tbu <= Float.parseFloat("2")) {
            status_tbu = "Normal";
        } else if (tbu > Float.parseFloat("2")) {
            status_tbu = "Tinggi";
        }

        hasil_tbu.setText(status_tbu);


        // Berat Badan / Tinggi Badan
        float bbtb = 0;
        if ((jenisKelamin).equals("L")) {
            // Tinggi Badan < Median
            if (Float.parseFloat(tinggiBadan) < Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                bbtb = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) /
                        (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_min1sd"))));
            } // Tinggi Badan > Median
            else if (Float.parseFloat(tinggiBadan) > Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                bbtb = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) /
                        (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))));
            } // Tinggi Badan == Median
            else if (Float.parseFloat(tinggiBadan) == Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median")))) {
                bbtb = (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd")))) /
                        (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("laki_bbtb_min1sd"))));
            }
        } else {
            // Tinggi Badan < Median
            if (Float.parseFloat(tinggiBadan) < Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
                bbtb = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) /
                        (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_min1sd"))));
            } // Tinggi Badan > Median
            else if (Float.parseFloat(tinggiBadan) > Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
                bbtb = (Float.parseFloat(tinggiBadan) - Float.parseFloat(cursorTBU.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) /
                        (Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_1sd"))) - Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median"))));
            } // Tinggi Badan == Median
            else if (Float.parseFloat(tinggiBadan) == Float.parseFloat(cursorBBTB.getString(cursorBBTB.getColumnIndex("perempuan_bbtb_median")))) {
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

        hasil_bbtb.setText(status_bbtb);

        // Indeks Massa Tubuh / Umur
        float imt = Float.parseFloat(beratBadan) / ((Float.parseFloat(tinggiBadan) / 100) * (Float.parseFloat(tinggiBadan) / 100));
        float imtu = 0;
        if ((jenisKelamin).equals("L")) {
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

        hasil_imtu.setText(status_imtu);

        //chart BBU
        List<String> BBUchartID = new ArrayList<>();
        List<String> BBUchartUmur = new ArrayList<>();
        List<String> BBUchartMin1sd = new ArrayList<>();
        List<String> BBUchartMin2sd = new ArrayList<>();
        List<String> BBUchartMin3sd = new ArrayList<>();
        List<String> BBUchartMedian = new ArrayList<>();
        List<String> BBUchart1sd = new ArrayList<>();
        List<String> BBUchart2sd = new ArrayList<>();
        List<String> BBUchart3sd = new ArrayList<>();
        if ((jenisKelamin).equals("L")) {
            Cursor cursorBBUchart = db.getLakiBBUAllList();
            cursorBBUchart.moveToFirst();
            if (!cursorBBUchart.isAfterLast()) {
                do {
                    BBUchartID.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_ID")));
                    BBUchartUmur.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_umur")));
                    BBUchartMin3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min3sd")));
                    BBUchartMin2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min2sd")));
                    BBUchartMin1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min1sd")));
                    BBUchartMedian.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_median")));
                    BBUchart1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_1sd")));
                    BBUchart2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_2sd")));
                    BBUchart3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_3sd")));

                } while (cursorBBUchart.moveToNext());
            }
        } else {
            Cursor cursorBBUchart = db.getPerempuanBBUAllList();
            cursorBBUchart.moveToFirst();
            if (!cursorBBUchart.isAfterLast()) {
                do {
                    BBUchartID.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_ID")));
                    BBUchartUmur.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_umur")));
                    BBUchartMin3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_min3sd")));
                    BBUchartMin2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_min2sd")));
                    BBUchartMin1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_min1sd")));
                    BBUchartMedian.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_median")));
                    BBUchart1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_1sd")));
                    BBUchart2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_2sd")));
                    BBUchart3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_3sd")));

                } while (cursorBBUchart.moveToNext());
            }
        }
        // Creating an  XYSeries for Income
        XYSeries BBUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries BBUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries BBUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries BBUseriesMedian = new XYSeries("Median");
        XYSeries BBUseries1sd = new XYSeries("1 SD");
        XYSeries BBUseries2sd = new XYSeries("2 SD");
        XYSeries BBUseries3sd = new XYSeries("3 SD");
        XYSeries BBUseriesBeratBadan = new XYSeries("Berat Badan");

        // Adding data to Income and Expense Series
        for (int i = 0; i < BBUchartID.size(); i++) {
            BBUseriesMin3sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin3sd.get(i)));
            BBUseriesMin2sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin2sd.get(i)));
            BBUseriesMin1sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin1sd.get(i)));
            BBUseriesMedian.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMedian.get(i)));
            BBUseries1sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart1sd.get(i)));
            BBUseries2sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart2sd.get(i)));
            BBUseries3sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart3sd.get(i)));
        }

        BBUseriesBeratBadan.add(Double.parseDouble(bulan), Double.parseDouble(beratBadan));

        // Creating a dataset to hold each series
        BBUdataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        BBUdataset.addSeries(BBUseriesMin3sd);
        BBUdataset.addSeries(BBUseriesMin2sd);
        BBUdataset.addSeries(BBUseriesMin1sd);
        BBUdataset.addSeries(BBUseriesMedian);
        BBUdataset.addSeries(BBUseries1sd);
        BBUdataset.addSeries(BBUseries2sd);
        BBUdataset.addSeries(BBUseries3sd);
        BBUdataset.addSeries(BBUseriesBeratBadan);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer BBUrendererMin1sd = new XYSeriesRenderer();
        BBUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrendererMin2sd = new XYSeriesRenderer();
        BBUrendererMin2sd.setColor(Color.parseColor("#FA9339"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrendererMin3sd = new XYSeriesRenderer();
        BBUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        BBUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrendererMedian = new XYSeriesRenderer();
        BBUrendererMedian.setColor(Color.parseColor("#17C21A"));
        BBUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrenderer1sd = new XYSeriesRenderer();
        BBUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        BBUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrenderer2sd = new XYSeriesRenderer();
        BBUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        BBUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrenderer3sd = new XYSeriesRenderer();
        BBUrenderer3sd.setColor(Color.parseColor("#C26717"));
        BBUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBUrendererBeratBadan = new XYSeriesRenderer();
        BBUrendererBeratBadan.setColor(Color.parseColor("#000000"));
        BBUrendererBeratBadan.setPointStyle(PointStyle.CIRCLE);
        BBUrendererBeratBadan.setFillPoints(true);
        BBUrendererBeratBadan.setLineWidth(2);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        BBUmultiRenderer = new XYMultipleSeriesRenderer();
        BBUmultiRenderer.setXLabels(0);
        BBUmultiRenderer.setChartTitle("Grafik Berat Badan Menurut Umur");
        BBUmultiRenderer.setXTitle("Umur");
        BBUmultiRenderer.setYTitle("Berat Badan");
        BBUmultiRenderer.setLabelsColor(Color.parseColor("#FF0000"));
        BBUmultiRenderer.setXLabelsColor(Color.parseColor("#FF0000"));
        BBUmultiRenderer.setYLabelsColor(0, Color.parseColor("#FF0000"));
        BBUmultiRenderer.setShowLegend(true);
        BBUmultiRenderer.setInScroll(true);
        BBUmultiRenderer.setMargins(new int[]{50, 30, 10, 10});
        BBUmultiRenderer.setPanEnabled(false, false);
        BBUmultiRenderer.setZoomEnabled(false, false);
        BBUmultiRenderer.setMarginsColor(Color.parseColor("#FFFBC9"));
        BBUmultiRenderer.setBackgroundColor(Color.parseColor("#FFF1B5"));
        BBUmultiRenderer.setTextTypeface(typeface);
        BBUmultiRenderer.setZoomButtonsVisible(false);
        BBUmultiRenderer.setXLabelsPadding(5);
        BBUmultiRenderer.setYLabelsPadding(5);
        BBUmultiRenderer.setXLabels(20);
        BBUmultiRenderer.setXAxisMin(-1, 0);
        BBUmultiRenderer.setXAxisMax(61, 0);
        BBUmultiRenderer.setYLabels(10);
        BBUmultiRenderer.setYAxisMin(0, 0);
        BBUmultiRenderer.setYAxisMax(31, 0);

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        BBUmultiRenderer.addSeriesRenderer(BBUrendererMin3sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrendererMin2sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrendererMin1sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrendererMedian);
        BBUmultiRenderer.addSeriesRenderer(BBUrenderer1sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrenderer2sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrenderer3sd);
        BBUmultiRenderer.addSeriesRenderer(BBUrendererBeratBadan);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout BBUchartContainer = (LinearLayout) findViewById(R.id.bbu_layout);

        // Creating a Line Chart
        BBUChart = ChartFactory.getLineChartView(HasilKalkulatorGizi.this, BBUdataset, BBUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        BBUchartContainer.addView(BBUChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        //chart TBU
        List<String> TBUchartID = new ArrayList<>();
        List<String> TBUchartUmur = new ArrayList<>();
        List<String> TBUchartMin1sd = new ArrayList<>();
        List<String> TBUchartMin2sd = new ArrayList<>();
        List<String> TBUchartMin3sd = new ArrayList<>();
        List<String> TBUchartMedian = new ArrayList<>();
        List<String> TBUchart1sd = new ArrayList<>();
        List<String> TBUchart2sd = new ArrayList<>();
        List<String> TBUchart3sd = new ArrayList<>();
        if ((jenisKelamin).equals("L")) {
            Cursor cursorTBUchart = db.getLakiTBUAllList();
            cursorTBUchart.moveToFirst();
            if (!cursorTBUchart.isAfterLast()) {
                do {
                    TBUchartID.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_ID")));
                    TBUchartUmur.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_umur")));
                    TBUchartMin3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min3sd")));
                    TBUchartMin2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min2sd")));
                    TBUchartMin1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min1sd")));
                    TBUchartMedian.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_median")));
                    TBUchart1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_1sd")));
                    TBUchart2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_2sd")));
                    TBUchart3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_3sd")));

                } while (cursorTBUchart.moveToNext());
            }
        } else {
            Cursor cursorTBUchart = db.getPerempuanTBUAllList();
            cursorTBUchart.moveToFirst();
            if (!cursorTBUchart.isAfterLast()) {
                do {
                    TBUchartID.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_ID")));
                    TBUchartUmur.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_umur")));
                    TBUchartMin3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_min3sd")));
                    TBUchartMin2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_min2sd")));
                    TBUchartMin1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_min1sd")));
                    TBUchartMedian.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_median")));
                    TBUchart1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_1sd")));
                    TBUchart2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_2sd")));
                    TBUchart3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_3sd")));

                } while (cursorTBUchart.moveToNext());
            }
        }
        // Creating an  XYSeries for Income
        XYSeries TBUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries TBUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries TBUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries TBUseriesMedian = new XYSeries("Median");
        XYSeries TBUseries1sd = new XYSeries("1 SD");
        XYSeries TBUseries2sd = new XYSeries("2 SD");
        XYSeries TBUseries3sd = new XYSeries("3 SD");
        XYSeries TBUseriesTinggiBadan = new XYSeries("Tinggi Badan");

        // Adding data to Income and Expense Series
        for (int i = 0; i < TBUchartID.size(); i++) {
            TBUseriesMin3sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin3sd.get(i)));
            TBUseriesMin2sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin2sd.get(i)));
            TBUseriesMin1sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin1sd.get(i)));
            TBUseriesMedian.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMedian.get(i)));
            TBUseries1sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart1sd.get(i)));
            TBUseries2sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart2sd.get(i)));
            TBUseries3sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart3sd.get(i)));
        }

        TBUseriesTinggiBadan.add(Double.parseDouble(bulan), Double.parseDouble(tinggiBadan));

        // Creating a dataset to hold each series
        TBUdataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        TBUdataset.addSeries(TBUseriesMin3sd);
        TBUdataset.addSeries(TBUseriesMin2sd);
        TBUdataset.addSeries(TBUseriesMin1sd);
        TBUdataset.addSeries(TBUseriesMedian);
        TBUdataset.addSeries(TBUseries1sd);
        TBUdataset.addSeries(TBUseries2sd);
        TBUdataset.addSeries(TBUseries3sd);
        TBUdataset.addSeries(TBUseriesTinggiBadan);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer TBUrendererMin1sd = new XYSeriesRenderer();
        TBUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrendererMin2sd = new XYSeriesRenderer();
        TBUrendererMin2sd.setColor(Color.parseColor("#FA9339"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrendererMin3sd = new XYSeriesRenderer();
        TBUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        TBUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrendererMedian = new XYSeriesRenderer();
        TBUrendererMedian.setColor(Color.parseColor("#17C21A"));
        TBUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrenderer1sd = new XYSeriesRenderer();
        TBUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        TBUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrenderer2sd = new XYSeriesRenderer();
        TBUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        TBUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrenderer3sd = new XYSeriesRenderer();
        TBUrenderer3sd.setColor(Color.parseColor("#C26717"));
        TBUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer TBUrendererBeratBadan = new XYSeriesRenderer();
        TBUrendererBeratBadan.setColor(Color.parseColor("#000000"));
        TBUrendererBeratBadan.setPointStyle(PointStyle.CIRCLE);
        TBUrendererBeratBadan.setFillPoints(true);
        TBUrendererBeratBadan.setLineWidth(2);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        TBUmultiRenderer = new XYMultipleSeriesRenderer();
        TBUmultiRenderer.setXLabels(0);
        TBUmultiRenderer.setChartTitle("Grafik Tinggi Badan Menurut Umur");
        TBUmultiRenderer.setXTitle("Umur");
        TBUmultiRenderer.setYTitle("Tinggi Badan");
        TBUmultiRenderer.setLabelsColor(Color.parseColor("#FF0000"));
        TBUmultiRenderer.setXLabelsColor(Color.parseColor("#FF0000"));
        TBUmultiRenderer.setYLabelsColor(0, Color.parseColor("#FF0000"));
        TBUmultiRenderer.setShowLegend(true);
        TBUmultiRenderer.setInScroll(true);
        TBUmultiRenderer.setMargins(new int[]{50, 30, 10, 10});
        TBUmultiRenderer.setPanEnabled(false, false);
        TBUmultiRenderer.setZoomEnabled(false, false);
        TBUmultiRenderer.setMarginsColor(Color.parseColor("#FFFBC9"));
        TBUmultiRenderer.setBackgroundColor(Color.parseColor("#FFF1B5"));
        TBUmultiRenderer.setTextTypeface(typeface);
        TBUmultiRenderer.setZoomButtonsVisible(false);
        TBUmultiRenderer.setXLabelsPadding(5);
        TBUmultiRenderer.setYLabelsPadding(5);
        TBUmultiRenderer.setXLabels(20);
        TBUmultiRenderer.setXAxisMin(-1, 0);
        TBUmultiRenderer.setXAxisMax(61, 0);
        TBUmultiRenderer.setYLabels(10);
        TBUmultiRenderer.setYAxisMin(40, 0);
        TBUmultiRenderer.setYAxisMax(126, 0);

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin3sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin2sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin1sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMedian);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer1sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer2sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer3sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererBeratBadan);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout TBUchartContainer = (LinearLayout) findViewById(R.id.tbu_layout);

        // Creating a Line Chart
        TBUChart = ChartFactory.getLineChartView(HasilKalkulatorGizi.this, TBUdataset, TBUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        TBUchartContainer.addView(TBUChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        //chart BBTB
        List<String> BBTBchartID = new ArrayList<>();
        List<String> BBTBchartTinggi = new ArrayList<>();
        List<String> BBTBchartMin1sd = new ArrayList<>();
        List<String> BBTBchartMin2sd = new ArrayList<>();
        List<String> BBTBchartMin3sd = new ArrayList<>();
        List<String> BBTBchartMedian = new ArrayList<>();
        List<String> BBTBchart1sd = new ArrayList<>();
        List<String> BBTBchart2sd = new ArrayList<>();
        List<String> BBTBchart3sd = new ArrayList<>();
        if ((jenisKelamin).equals("L")) {
            if (Integer.valueOf(bulan) <= 24) {
                Cursor cursorBBTBchart = db.getLakiBBTBAllList_0_24();
                cursorBBTBchart.moveToFirst();
                if (!cursorBBTBchart.isAfterLast()) {
                    do {
                        BBTBchartID.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_ID")));
                        BBTBchartTinggi.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_tb")));
                        BBTBchartMin3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min3sd")));
                        BBTBchartMin2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min2sd")));
                        BBTBchartMin1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min1sd")));
                        BBTBchartMedian.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_median")));
                        BBTBchart1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_1sd")));
                        BBTBchart2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_2sd")));
                        BBTBchart3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_3sd")));

                    } while (cursorBBTBchart.moveToNext());
                }
            } else {
                Cursor cursorBBTBchart = db.getLakiBBTBAllList_24_60();
                cursorBBTBchart.moveToFirst();
                if (!cursorBBTBchart.isAfterLast()) {
                    do {
                        BBTBchartID.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_ID")));
                        BBTBchartTinggi.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_tb")));
                        BBTBchartMin3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min3sd")));
                        BBTBchartMin2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min2sd")));
                        BBTBchartMin1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_min1sd")));
                        BBTBchartMedian.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_median")));
                        BBTBchart1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_1sd")));
                        BBTBchart2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_2sd")));
                        BBTBchart3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("laki_bbtb_3sd")));

                    } while (cursorBBTBchart.moveToNext());
                }
            }
        } else {
            if (Integer.valueOf(bulan) <= 24) {
                Cursor cursorBBTBchart = db.getPerempuanBBTBAllList_0_24();
                cursorBBTBchart.moveToFirst();
                if (!cursorBBTBchart.isAfterLast()) {
                    do {
                        BBTBchartID.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_ID")));
                        BBTBchartTinggi.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_tb")));
                        BBTBchartMin3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min3sd")));
                        BBTBchartMin2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min2sd")));
                        BBTBchartMin1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min1sd")));
                        BBTBchartMedian.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_median")));
                        BBTBchart1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_1sd")));
                        BBTBchart2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_2sd")));
                        BBTBchart3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_3sd")));

                    } while (cursorBBTBchart.moveToNext());
                }
            } else {
                Cursor cursorBBTBchart = db.getPerempuanBBTBAllList_24_60();
                cursorBBTBchart.moveToFirst();
                if (!cursorBBTBchart.isAfterLast()) {
                    do {
                        BBTBchartID.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_ID")));
                        BBTBchartTinggi.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_tb")));
                        BBTBchartMin3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min3sd")));
                        BBTBchartMin2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min2sd")));
                        BBTBchartMin1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_min1sd")));
                        BBTBchartMedian.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_median")));
                        BBTBchart1sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_1sd")));
                        BBTBchart2sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_2sd")));
                        BBTBchart3sd.add(cursorBBTBchart.getString(cursorBBTBchart.getColumnIndex("perempuan_bbtb_3sd")));

                    } while (cursorBBTBchart.moveToNext());
                }
            }
        }
        // Creating an  XYSeries for Income
        XYSeries BBTBseriesMin3sd = new XYSeries("-3 SD");
        XYSeries BBTBseriesMin2sd = new XYSeries("-2 SD");
        XYSeries BBTBseriesMin1sd = new XYSeries("-1 SD");
        XYSeries BBTBseriesMedian = new XYSeries("Median");
        XYSeries BBTBseries1sd = new XYSeries("1 SD");
        XYSeries BBTBseries2sd = new XYSeries("2 SD");
        XYSeries BBTBseries3sd = new XYSeries("3 SD");
        XYSeries BBTBseriesBeratBadan = new XYSeries("Berat Badan");

        // Adding data to Income and Expense Series
        for (int i = 0; i < BBTBchartID.size(); i++) {
            BBTBseriesMin3sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin3sd.get(i)));
            BBTBseriesMin2sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin2sd.get(i)));
            BBTBseriesMin1sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin1sd.get(i)));
            BBTBseriesMedian.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMedian.get(i)));
            BBTBseries1sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart1sd.get(i)));
            BBTBseries2sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart2sd.get(i)));
            BBTBseries3sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart3sd.get(i)));
        }

        BBTBseriesBeratBadan.add(Double.parseDouble(tinggiBadan), Double.parseDouble(beratBadan));

        // Creating a dataset to hold each series
        BBTBdataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        BBTBdataset.addSeries(BBTBseriesMin3sd);
        BBTBdataset.addSeries(BBTBseriesMin2sd);
        BBTBdataset.addSeries(BBTBseriesMin1sd);
        BBTBdataset.addSeries(BBTBseriesMedian);
        BBTBdataset.addSeries(BBTBseries1sd);
        BBTBdataset.addSeries(BBTBseries2sd);
        BBTBdataset.addSeries(BBTBseries3sd);
        BBTBdataset.addSeries(BBTBseriesBeratBadan);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer BBTBrendererMin1sd = new XYSeriesRenderer();
        BBTBrendererMin1sd.setColor(Color.parseColor("#FF33CC"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrendererMin2sd = new XYSeriesRenderer();
        BBTBrendererMin2sd.setColor(Color.parseColor("#FA9339"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrendererMin3sd = new XYSeriesRenderer();
        BBTBrendererMin3sd.setColor(Color.parseColor("#E31025"));
        BBTBrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrendererMedian = new XYSeriesRenderer();
        BBTBrendererMedian.setColor(Color.parseColor("#17C21A"));
        BBTBrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrenderer1sd = new XYSeriesRenderer();
        BBTBrenderer1sd.setColor(Color.parseColor("#2517C2"));
        BBTBrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrenderer2sd = new XYSeriesRenderer();
        BBTBrenderer2sd.setColor(Color.parseColor("#C2B617"));
        BBTBrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrenderer3sd = new XYSeriesRenderer();
        BBTBrenderer3sd.setColor(Color.parseColor("#C26717"));
        BBTBrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer BBTBrendererBeratBadan = new XYSeriesRenderer();
        BBTBrendererBeratBadan.setColor(Color.parseColor("#000000"));
        BBTBrendererBeratBadan.setPointStyle(PointStyle.CIRCLE);
        BBTBrendererBeratBadan.setFillPoints(true);
        BBTBrendererBeratBadan.setLineWidth(2);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        BBTBmultiRenderer = new XYMultipleSeriesRenderer();
        BBTBmultiRenderer.setXLabels(0);
        BBTBmultiRenderer.setChartTitle("Grafik Berat Badan Menurut Tinggi Badan");
        BBTBmultiRenderer.setXTitle("Tinggi Badan");
        BBTBmultiRenderer.setYTitle("Berat Badan");
        BBTBmultiRenderer.setLabelsColor(Color.parseColor("#FF0000"));
        BBTBmultiRenderer.setXLabelsColor(Color.parseColor("#FF0000"));
        BBTBmultiRenderer.setYLabelsColor(0, Color.parseColor("#FF0000"));
        BBTBmultiRenderer.setShowLegend(true);
        BBTBmultiRenderer.setInScroll(true);
        BBTBmultiRenderer.setMargins(new int[]{50, 30, 10, 10});
        BBTBmultiRenderer.setPanEnabled(false, false);
        BBTBmultiRenderer.setZoomEnabled(false, false);
        BBTBmultiRenderer.setMarginsColor(Color.parseColor("#FFFBC9"));
        BBTBmultiRenderer.setBackgroundColor(Color.parseColor("#FFF1B5"));
        BBTBmultiRenderer.setTextTypeface(typeface);
        BBTBmultiRenderer.setZoomButtonsVisible(false);
        BBTBmultiRenderer.setXLabelsPadding(5);
        BBTBmultiRenderer.setYLabelsPadding(5);
        BBTBmultiRenderer.setXLabels(20);
        BBTBmultiRenderer.setYLabels(10);
        if (Integer.valueOf(bulan) <= 24) {
            BBTBmultiRenderer.setXAxisMin(44, 0);
            BBTBmultiRenderer.setXAxisMax(111, 0);
            BBTBmultiRenderer.setYAxisMin(1, 0);
            BBTBmultiRenderer.setYAxisMax(26, 0);
        } else {
            BBTBmultiRenderer.setXAxisMin(64, 0);
            BBTBmultiRenderer.setXAxisMax(121, 0);
            BBTBmultiRenderer.setYAxisMin(5, 0);
            BBTBmultiRenderer.setYAxisMax(31, 0);
        }

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        BBTBmultiRenderer.addSeriesRenderer(BBTBrendererMin3sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrendererMin2sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrendererMin1sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrendererMedian);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrenderer1sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrenderer2sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrenderer3sd);
        BBTBmultiRenderer.addSeriesRenderer(BBTBrendererBeratBadan);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout BBTBchartContainer = (LinearLayout) findViewById(R.id.bbtb_layout);

        // Creating a Line Chart
        BBTBChart = ChartFactory.getLineChartView(HasilKalkulatorGizi.this, BBTBdataset, BBTBmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        BBTBchartContainer.addView(BBTBChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        //chart IMTU
        List<String> IMTUchartID = new ArrayList<>();
        List<String> IMTUchartUmur = new ArrayList<>();
        List<String> IMTUchartMin1sd = new ArrayList<>();
        List<String> IMTUchartMin2sd = new ArrayList<>();
        List<String> IMTUchartMin3sd = new ArrayList<>();
        List<String> IMTUchartMedian = new ArrayList<>();
        List<String> IMTUchart1sd = new ArrayList<>();
        List<String> IMTUchart2sd = new ArrayList<>();
        List<String> IMTUchart3sd = new ArrayList<>();
        if ((jenisKelamin).equals("L")) {
            Cursor cursorIMTUchart = db.getLakiIMTUAllList();
            cursorIMTUchart.moveToFirst();
            if (!cursorIMTUchart.isAfterLast()) {
                do {
                    IMTUchartID.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_ID")));
                    IMTUchartUmur.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_umur")));
                    IMTUchartMin3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min3sd")));
                    IMTUchartMin2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min2sd")));
                    IMTUchartMin1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min1sd")));
                    IMTUchartMedian.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_median")));
                    IMTUchart1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_1sd")));
                    IMTUchart2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_2sd")));
                    IMTUchart3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_3sd")));

                } while (cursorIMTUchart.moveToNext());
            }
        } else {
            Cursor cursorIMTUchart = db.getPerempuanIMTUAllList();
            cursorIMTUchart.moveToFirst();
            if (!cursorIMTUchart.isAfterLast()) {
                do {
                    IMTUchartID.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_ID")));
                    IMTUchartUmur.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_umur")));
                    IMTUchartMin3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_min3sd")));
                    IMTUchartMin2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_min2sd")));
                    IMTUchartMin1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_min1sd")));
                    IMTUchartMedian.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_median")));
                    IMTUchart1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_1sd")));
                    IMTUchart2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_2sd")));
                    IMTUchart3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_3sd")));

                } while (cursorIMTUchart.moveToNext());
            }
        }
        // Creating an  XYSeries for Income
        XYSeries IMTUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries IMTUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries IMTUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries IMTUseriesMedian = new XYSeries("Median");
        XYSeries IMTUseries1sd = new XYSeries("1 SD");
        XYSeries IMTUseries2sd = new XYSeries("2 SD");
        XYSeries IMTUseries3sd = new XYSeries("3 SD");
        XYSeries IMTUseriesIMT = new XYSeries("Indeks Massa Tubuh");

        // Adding data to Income and Expense Series
        for (int i = 0; i < IMTUchartID.size(); i++) {
            IMTUseriesMin3sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin3sd.get(i)));
            IMTUseriesMin2sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin2sd.get(i)));
            IMTUseriesMin1sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin1sd.get(i)));
            IMTUseriesMedian.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMedian.get(i)));
            IMTUseries1sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart1sd.get(i)));
            IMTUseries2sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart2sd.get(i)));
            IMTUseries3sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart3sd.get(i)));
        }

        IMTUseriesIMT.add(Double.parseDouble(bulan), (double) imt);

        // Creating a dataset to hold each series
        IMTUdataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        IMTUdataset.addSeries(IMTUseriesMin3sd);
        IMTUdataset.addSeries(IMTUseriesMin2sd);
        IMTUdataset.addSeries(IMTUseriesMin1sd);
        IMTUdataset.addSeries(IMTUseriesMedian);
        IMTUdataset.addSeries(IMTUseries1sd);
        IMTUdataset.addSeries(IMTUseries2sd);
        IMTUdataset.addSeries(IMTUseries3sd);
        IMTUdataset.addSeries(IMTUseriesIMT);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer IMTUrendererMin1sd = new XYSeriesRenderer();
        IMTUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrendererMin2sd = new XYSeriesRenderer();
        IMTUrendererMin2sd.setColor(Color.parseColor("#FA9339"));

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrendererMin3sd = new XYSeriesRenderer();
        IMTUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        IMTUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrendererMedian = new XYSeriesRenderer();
        IMTUrendererMedian.setColor(Color.parseColor("#17C21A"));
        IMTUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrenderer1sd = new XYSeriesRenderer();
        IMTUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        IMTUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrenderer2sd = new XYSeriesRenderer();
        IMTUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        IMTUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrenderer3sd = new XYSeriesRenderer();
        IMTUrenderer3sd.setColor(Color.parseColor("#C26717"));
        IMTUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer IMTUrendererBeratBadan = new XYSeriesRenderer();
        IMTUrendererBeratBadan.setColor(Color.parseColor("#000000"));
        IMTUrendererBeratBadan.setPointStyle(PointStyle.CIRCLE);
        IMTUrendererBeratBadan.setFillPoints(true);
        IMTUrendererBeratBadan.setLineWidth(2);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        IMTUmultiRenderer = new XYMultipleSeriesRenderer();
        IMTUmultiRenderer.setXLabels(0);
        IMTUmultiRenderer.setChartTitle("Grafik Indeks Massa Tubuh Menurut Umur");
        IMTUmultiRenderer.setXTitle("Umur");
        IMTUmultiRenderer.setYTitle("Indeks Massa Tubuh");
        IMTUmultiRenderer.setLabelsColor(Color.parseColor("#FF0000"));
        IMTUmultiRenderer.setXLabelsColor(Color.parseColor("#FF0000"));
        IMTUmultiRenderer.setYLabelsColor(0, Color.parseColor("#FF0000"));
        IMTUmultiRenderer.setShowLegend(true);
        IMTUmultiRenderer.setInScroll(true);
        IMTUmultiRenderer.setMargins(new int[]{50, 30, 10, 10});
        IMTUmultiRenderer.setPanEnabled(false, false);
        IMTUmultiRenderer.setZoomEnabled(false, false);
        IMTUmultiRenderer.setMarginsColor(Color.parseColor("#FFFBC9"));
        IMTUmultiRenderer.setBackgroundColor(Color.parseColor("#FFF1B5"));
        IMTUmultiRenderer.setTextTypeface(typeface);
        IMTUmultiRenderer.setZoomButtonsVisible(false);
        IMTUmultiRenderer.setXLabelsPadding(5);
        IMTUmultiRenderer.setYLabelsPadding(5);
        IMTUmultiRenderer.setXLabels(20);
        IMTUmultiRenderer.setXAxisMin(-1, 0);
        IMTUmultiRenderer.setXAxisMax(61, 0);
        IMTUmultiRenderer.setYLabels(10);
        IMTUmultiRenderer.setYAxisMin(10, 0);
        IMTUmultiRenderer.setYAxisMax(23, 0);

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin3sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin2sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin1sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMedian);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer1sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer2sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer3sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererBeratBadan);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout IMTUchartContainer = (LinearLayout) findViewById(R.id.imtu_layout);

        // Creating a Line Chart
        IMTUChart = ChartFactory.getLineChartView(HasilKalkulatorGizi.this, IMTUdataset, IMTUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        IMTUchartContainer.addView(IMTUChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        //Tab
        tabHost = (TabHost) findViewById(android.R.id.tabhost);     // The activity TabHost
        tabHost.setup();

        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("BB/U").setIndicator("BB/U").setContent(R.id.bbu_layout);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("TB/U").setIndicator("TB/U").setContent(R.id.tbu_layout);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("BB/TB").setIndicator("BB/TB").setContent(R.id.bbtb_layout);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("IMT/U").setIndicator("IMT/U").setContent(R.id.imtu_layout);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setTypeface(typeface);
            tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabHost.getCurrentTab()) {
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                tv.setTextColor(Color.parseColor("#D046F2"));
            } else {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                tv.setTextColor(Color.parseColor("#FF0000"));
            }

            // Set Height Tab Title
            final View view = tabHost.getTabWidget().getChildTabViewAt(i);
            if (view != null) {
                // reduce height of the tab
                view.getLayoutParams().height *= 0.7;

                //  get title text view
                final View textView = view.findViewById(android.R.id.title);
                if (textView instanceof TextView) {
                    // just in case check the type

                    // center text
                    ((TextView) textView).setGravity(Gravity.CENTER_VERTICAL);
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

        //chart
        if (BBUChart == null || TBUChart == null || BBTBChart == null) {
            LinearLayout bbu_layout = (LinearLayout) findViewById(R.id.bbu_layout);
            BBUChart = ChartFactory.getLineChartView(getBaseContext(), BBUdataset, BBUmultiRenderer);
            bbu_layout.addView(BBUChart);
            LinearLayout tbu_layout = (LinearLayout) findViewById(R.id.tbu_layout);
            TBUChart = ChartFactory.getLineChartView(getBaseContext(), TBUdataset, TBUmultiRenderer);
            tbu_layout.addView(TBUChart);
            LinearLayout bbtb_layout = (LinearLayout) findViewById(R.id.bbtb_layout);
            BBTBChart = ChartFactory.getLineChartView(getBaseContext(), BBTBdataset, BBTBmultiRenderer);
            bbtb_layout.addView(BBTBChart);
            LinearLayout imtu_layout = (LinearLayout) findViewById(R.id.imtu_layout);
            IMTUChart = ChartFactory.getLineChartView(getBaseContext(), IMTUdataset, IMTUmultiRenderer);
            imtu_layout.addView(IMTUChart);
        } else {
            BBUChart.repaint();
            TBUChart.repaint();
            BBTBChart.repaint();
            IMTUChart.repaint();
        }
    }
}

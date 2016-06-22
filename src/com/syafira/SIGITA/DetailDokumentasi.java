package com.syafira.SIGITA;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syafira rarra on 05/25/2016.
 */

public class DetailDokumentasi extends Activity {

    private TextView detail_dokumentasigizi;
    private TextView text_dokumentasigizi_nama;
    private TextView dokumentasigizi_nama;
    private TextView text_dokumentasigizi_tanggal;
    private TextView dokumentasigizi_tanggal;
    private TextView text_dokumentasigizi_usia;
    private TextView dokumentasigizi_usia;
    private TextView text_dokumentasigizi_tinggibadan;
    private TextView dokumentasigizi_tinggibadan;
    private TextView dokumentasigizi_centimeter;
    private TextView text_dokumentasigizi_beratbadan;
    private TextView dokumentasigizi_beratbadan;
    private TextView dokumentasigizi_kilogram;
    private TextView bbu;
    private TextView hasil_bbu;
    private TextView tbu;
    private TextView hasil_tbu;
    private TextView bbtb;
    private TextView hasil_bbtb;
    private TextView imtu;
    private TextView hasil_imtu;
    private TextView titikdua;
    private TextView text_footer;
    private ImageView button_ubah;
    private ImageView button_hapus;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;
    private TabHost tabHost;
    private TabHost tabBulanIni;
    private TabHost tabSeluruhBulan;

    private XYMultipleSeriesDataset BBUdatasetSeluruhBulan;
    private XYMultipleSeriesDataset BBUdatasetBulanIni;
    private XYMultipleSeriesRenderer BBUmultiRenderer;
    private GraphicalView BBUChartSeluruhBulan;
    private GraphicalView BBUChartBulanIni;
    private XYMultipleSeriesDataset TBUdatasetSeluruhBulan;
    private XYMultipleSeriesDataset TBUdatasetBulanIni;
    private XYMultipleSeriesRenderer TBUmultiRenderer;
    private GraphicalView TBUChartSeluruhBulan;
    private GraphicalView TBUChartBulanIni;
    private XYMultipleSeriesDataset BBTBdatasetSeluruhBulan;
    private XYMultipleSeriesDataset BBTBdatasetBulanIni;
    private XYMultipleSeriesRenderer BBTBmultiRenderer;
    private GraphicalView BBTBChartSeluruhBulan;
    private GraphicalView BBTBChartBulanIni;
    private XYMultipleSeriesDataset IMTUdatasetSeluruhBulan;
    private XYMultipleSeriesDataset IMTUdatasetBulanIni;
    private XYMultipleSeriesRenderer IMTUmultiRenderer;
    private GraphicalView IMTUChartSeluruhBulan;
    private GraphicalView IMTUChartBulanIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.detail_dokumentasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        final int dokumentasiID = fetchID.getIntExtra("dokumentasiID", 0);

        // Load Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneDokumentasi(dokumentasiID);
        cursor.moveToFirst();

        Cursor cursorBBU;
        Cursor cursorTBU;
        Cursor cursorBBTB;
        Cursor cursorIMTU;
        if ((session.loadSession(this, "gender")).equals("Laki-laki")) {
            cursorBBU = db.getLakiBBUList(cursor.getColumnIndex("dokumentasi_bulan"));
            cursorTBU = db.getLakiTBUList(cursor.getColumnIndex("dokumentasi_bulan"));
            if (cursor.getColumnIndex("dokumentasi_bulan") < 24) {
                cursorBBTB = db.getLakiBBTBList_0_24(Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))));
            } else {
                cursorBBTB = db.getLakiBBTBList_24_60(Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))));
            }
            cursorIMTU = db.getLakiIMTUList(cursor.getColumnIndex("dokumentasi_bulan"));
        } else {
            cursorBBU = db.getPerempuanBBUList(cursor.getColumnIndex("dokumentasi_bulan"));
            cursorTBU = db.getPerempuanTBUList(cursor.getColumnIndex("dokumentasi_bulan"));
            if (cursor.getColumnIndex("dokumentasi_bulan") < 24) {
                cursorBBTB = db.getPerempuanBBTBList_0_24(Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))));
            } else {
                cursorBBTB = db.getPerempuanBBTBList_24_60(Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))));
            }
            cursorIMTU = db.getPerempuanIMTUList(cursor.getColumnIndex("dokumentasi_bulan"));
        }
        cursorBBU.moveToFirst();
        cursorTBU.moveToFirst();
        cursorBBTB.moveToFirst();
        cursorIMTU.moveToFirst();


        // Load Widget
        detail_dokumentasigizi = (TextView) findViewById(R.id.detail_dokumentasigizi);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_dokumentasigizi_nama = (TextView) findViewById(R.id.text_dokumentasigizi_nama);
        dokumentasigizi_nama = (TextView) findViewById(R.id.dokumentasigizi_nama);
        text_dokumentasigizi_usia = (TextView) findViewById(R.id.text_dokumentasigizi_usia);
        dokumentasigizi_usia = (TextView) findViewById(R.id.dokumentasigizi_usia);
        text_dokumentasigizi_tanggal = (TextView) findViewById(R.id.text_dokumentasigizi_tanggal);
        dokumentasigizi_tanggal = (TextView) findViewById(R.id.dokumentasigizi_tanggal);
        text_dokumentasigizi_tinggibadan = (TextView) findViewById(R.id.text_dokumentasigizi_tinggibadan);
        dokumentasigizi_tinggibadan = (TextView) findViewById(R.id.dokumentasigizi_tinggibadan);
        dokumentasigizi_centimeter = (TextView) findViewById(R.id.dokumentasigizi_centimeter);
        text_dokumentasigizi_beratbadan = (TextView) findViewById(R.id.text_dokumentasigizi_beratbadan);
        dokumentasigizi_beratbadan = (TextView) findViewById(R.id.dokumentasigizi_beratbadan);
        dokumentasigizi_kilogram = (TextView) findViewById(R.id.dokumentasigizi_kilogram);
        bbu = (TextView) findViewById(R.id.bbu);
        hasil_bbu = (TextView) findViewById(R.id.hasil_bbu);
        tbu = (TextView) findViewById(R.id.tbu);
        hasil_tbu = (TextView) findViewById(R.id.hasil_tbu);
        bbtb = (TextView) findViewById(R.id.bbtb);
        hasil_bbtb = (TextView) findViewById(R.id.hasil_bbtb);
        imtu = (TextView) findViewById(R.id.imtu);
        hasil_imtu = (TextView) findViewById(R.id.hasil_imtu);
        button_ubah = (ImageView) findViewById(R.id.button_ubah);
        button_hapus = (ImageView) findViewById(R.id.button_hapus);
        text_footer = (TextView) findViewById(R.id.text_footer);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        detail_dokumentasigizi.setTypeface(typeface);
        titikdua.setTypeface(typeface);
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
        bbu.setTypeface(typeface);
        hasil_bbu.setTypeface(typeface);
        tbu.setTypeface(typeface);
        hasil_tbu.setTypeface(typeface);
        bbtb.setTypeface(typeface);
        hasil_bbtb.setTypeface(typeface);
        imtu.setTypeface(typeface);
        hasil_imtu.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Set Text
        int profilID = Integer.parseInt(session.loadSession(this, "id"));
        dokumentasigizi_nama.setText(session.loadSession(this, "nama"));
        dokumentasigizi_tanggal.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_tanggal")));
        dokumentasigizi_usia.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_usia")));
        dokumentasigizi_tinggibadan.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi")));
        dokumentasigizi_beratbadan.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_berat")));
        hasil_bbu.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_bbu")));
        hasil_tbu.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_tbu")));
        hasil_bbtb.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_bbtb")));
        hasil_imtu.setText(cursor.getString(cursor.getColumnIndex("dokumentasi_imtu")));

        // Chart profil
        List<String> dokumentasiChartID = new ArrayList<>();
        List<String> dokumentasiBulanChart = new ArrayList<>();
        List<String> dokumentasiTinggiChart = new ArrayList<>();
        List<String> dokumentasiBeratChart = new ArrayList<>();
        Cursor cursorDokumentasichart = db.getDokumentasiChart(profilID, cursor.getInt(cursor.getColumnIndex("dokumentasi_bulan")));
        cursorDokumentasichart.moveToFirst();
        if (!cursorDokumentasichart.isAfterLast()) {
            do {
                dokumentasiChartID.add(cursorDokumentasichart.getString(cursorDokumentasichart.getColumnIndex("dokumentasiID")));
                dokumentasiBulanChart.add(cursorDokumentasichart.getString(cursorDokumentasichart.getColumnIndex("dokumentasi_bulan")));
                dokumentasiTinggiChart.add(cursorDokumentasichart.getString(cursorDokumentasichart.getColumnIndex("dokumentasi_tinggi")));
                dokumentasiBeratChart.add(cursorDokumentasichart.getString(cursorDokumentasichart.getColumnIndex("dokumentasi_berat")));
            } while (cursorDokumentasichart.moveToNext());
        }

        // Chart BBU
        List<String> BBUchartID = new ArrayList<>();
        List<String> BBUchartUmur = new ArrayList<>();
        List<String> BBUchartMin1sd = new ArrayList<>();
        List<String> BBUchartMin2sd = new ArrayList<>();
        List<String> BBUchartMin3sd = new ArrayList<>();
        List<String> BBUchartMedian = new ArrayList<>();
        List<String> BBUchart1sd = new ArrayList<>();
        List<String> BBUchart2sd = new ArrayList<>();
        List<String> BBUchart3sd = new ArrayList<>();

        // Gender Laki-laki
        if ((session.loadSession(this, "gender").equals("Laki-laki"))) {
            Cursor cursorBBUchart = db.getLakiBBUAllList();
            cursorBBUchart.moveToFirst();
            if (!cursorBBUchart.isAfterLast()) {
                do {
                    BBUchartID.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_ID")));
                    BBUchartUmur.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_usia")));
                    BBUchartMin3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min3sd")));
                    BBUchartMin2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min2sd")));
                    BBUchartMin1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_min1sd")));
                    BBUchartMedian.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_median")));
                    BBUchart1sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_1sd")));
                    BBUchart2sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_2sd")));
                    BBUchart3sd.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("laki_bbu_3sd")));
                } while (cursorBBUchart.moveToNext());
            }

        // Gender Perempuan
        } else {
            Cursor cursorBBUchart = db.getPerempuanBBUAllList();
            cursorBBUchart.moveToFirst();
            if (!cursorBBUchart.isAfterLast()) {
                do {
                    BBUchartID.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_ID")));
                    BBUchartUmur.add(cursorBBUchart.getString(cursorBBUchart.getColumnIndex("perempuan_bbu_usia")));
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

        // Creating an  XYSeries for BBU
        XYSeries BBUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries BBUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries BBUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries BBUseriesMedian = new XYSeries("Median");
        XYSeries BBUseries1sd = new XYSeries("1 SD");
        XYSeries BBUseries2sd = new XYSeries("2 SD");
        XYSeries BBUseries3sd = new XYSeries("3 SD");
        XYSeries BBUseriesBeratBadan = new XYSeries("Berat Badan");
        XYSeries BBUseriesBeratBadanBulanIni = new XYSeries("Berat Badan");

        // Adding data to BBU to Chart
        for (int i = 0; i < BBUchartID.size(); i++) {
            BBUseriesMin3sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin3sd.get(i)));
            BBUseriesMin2sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin2sd.get(i)));
            BBUseriesMin1sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMin1sd.get(i)));
            BBUseriesMedian.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchartMedian.get(i)));
            BBUseries1sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart1sd.get(i)));
            BBUseries2sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart2sd.get(i)));
            BBUseries3sd.add(Double.parseDouble(BBUchartUmur.get(i)), Double.parseDouble(BBUchart3sd.get(i)));
        }
        for (int i = 0; i < dokumentasiChartID.size(); i++) {
            BBUseriesBeratBadan.add(Double.parseDouble(dokumentasiBulanChart.get(i)), Double.parseDouble(dokumentasiBeratChart.get(i)));
        }

        // Adding data from Profil to Chart
        BBUseriesBeratBadanBulanIni.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_bulan"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_berat"))));

        // Creating a dataset to hold each series
        BBUdatasetSeluruhBulan = new XYMultipleSeriesDataset();

        // Adding Seluruh Bulan Series to the dataset
        BBUdatasetSeluruhBulan.addSeries(BBUseriesMin3sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseriesMin2sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseriesMin1sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseriesMedian);
        BBUdatasetSeluruhBulan.addSeries(BBUseries1sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseries2sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseries3sd);
        BBUdatasetSeluruhBulan.addSeries(BBUseriesBeratBadan);

        // Creating a dataset to hold each series
        BBUdatasetBulanIni = new XYMultipleSeriesDataset();

        // Adding Bulan Ini Series to the dataset
        BBUdatasetBulanIni.addSeries(BBUseriesMin3sd);
        BBUdatasetBulanIni.addSeries(BBUseriesMin2sd);
        BBUdatasetBulanIni.addSeries(BBUseriesMin1sd);
        BBUdatasetBulanIni.addSeries(BBUseriesMedian);
        BBUdatasetBulanIni.addSeries(BBUseries1sd);
        BBUdatasetBulanIni.addSeries(BBUseries2sd);
        BBUdatasetBulanIni.addSeries(BBUseries3sd);
        BBUdatasetBulanIni.addSeries(BBUseriesBeratBadanBulanIni);

        // Creating XYSeriesRenderer to customize Min1sd
        XYSeriesRenderer BBUrendererMin1sd = new XYSeriesRenderer();
        BBUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));
        BBUrendererMin1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize Min2sd
        XYSeriesRenderer BBUrendererMin2sd = new XYSeriesRenderer();
        BBUrendererMin2sd.setColor(Color.parseColor("#FA9339"));
        BBUrendererMin2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize Min3sd
        XYSeriesRenderer BBUrendererMin3sd = new XYSeriesRenderer();
        BBUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        BBUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize Median
        XYSeriesRenderer BBUrendererMedian = new XYSeriesRenderer();
        BBUrendererMedian.setColor(Color.parseColor("#17C21A"));
        BBUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 1sd
        XYSeriesRenderer BBUrenderer1sd = new XYSeriesRenderer();
        BBUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        BBUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 2sd
        XYSeriesRenderer BBUrenderer2sd = new XYSeriesRenderer();
        BBUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        BBUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 3sd
        XYSeriesRenderer BBUrenderer3sd = new XYSeriesRenderer();
        BBUrenderer3sd.setColor(Color.parseColor("#C26717"));
        BBUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize BeratBadan
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

        // Adding All Renderes to multipleRenderer
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
        LinearLayout BBUchartContainerSeluruhBulan = (LinearLayout) findViewById(R.id.bbu_seluruhbulan_layout);
        LinearLayout BBUchartContainerBulanIni = (LinearLayout) findViewById(R.id.bbu_bulanini_layout);

        // Creating a Line Chart
        BBUChartSeluruhBulan = ChartFactory.getLineChartView(DetailDokumentasi.this, BBUdatasetSeluruhBulan, BBUmultiRenderer);
        BBUChartBulanIni = ChartFactory.getLineChartView(DetailDokumentasi.this, BBUdatasetBulanIni, BBUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        BBUchartContainerSeluruhBulan.addView(BBUChartSeluruhBulan, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        BBUchartContainerBulanIni.addView(BBUChartBulanIni, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


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

        // Gender Laki-laki
        if ((session.loadSession(this, "gender").equals("Laki-laki"))) {
            Cursor cursorTBUchart = db.getLakiTBUAllList();
            cursorTBUchart.moveToFirst();
            if (!cursorTBUchart.isAfterLast()) {
                do {
                    TBUchartID.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_ID")));
                    TBUchartUmur.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_usia")));
                    TBUchartMin3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min3sd")));
                    TBUchartMin2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min2sd")));
                    TBUchartMin1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_min1sd")));
                    TBUchartMedian.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_median")));
                    TBUchart1sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_1sd")));
                    TBUchart2sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_2sd")));
                    TBUchart3sd.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("laki_tbu_3sd")));

                } while (cursorTBUchart.moveToNext());
            }

        // Gender Perempuan            
        } else {
            Cursor cursorTBUchart = db.getPerempuanTBUAllList();
            cursorTBUchart.moveToFirst();
            if (!cursorTBUchart.isAfterLast()) {
                do {
                    TBUchartID.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_ID")));
                    TBUchartUmur.add(cursorTBUchart.getString(cursorTBUchart.getColumnIndex("perempuan_tbu_usia")));
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

        // Creating an  XYSeries for TBU
        XYSeries TBUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries TBUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries TBUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries TBUseriesMedian = new XYSeries("Median");
        XYSeries TBUseries1sd = new XYSeries("1 SD");
        XYSeries TBUseries2sd = new XYSeries("2 SD");
        XYSeries TBUseries3sd = new XYSeries("3 SD");
        XYSeries TBUseriesTinggiBadanSeluruhBulan = new XYSeries("Tinggi Badan");
        XYSeries TBUseriesTinggiBadanBulanIni = new XYSeries("Tinggi Badan");

        // Adding data to TBU to Chart
        for (int i = 0; i < TBUchartID.size(); i++) {
            TBUseriesMin3sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin3sd.get(i)));
            TBUseriesMin2sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin2sd.get(i)));
            TBUseriesMin1sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMin1sd.get(i)));
            TBUseriesMedian.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchartMedian.get(i)));
            TBUseries1sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart1sd.get(i)));
            TBUseries2sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart2sd.get(i)));
            TBUseries3sd.add(Double.parseDouble(TBUchartUmur.get(i)), Double.parseDouble(TBUchart3sd.get(i)));
        }
        for (int i = 0; i < dokumentasiBulanChart.size(); i++) {
            TBUseriesTinggiBadanSeluruhBulan.add(Double.parseDouble(dokumentasiBulanChart.get(i)), Double.parseDouble(dokumentasiTinggiChart.get(i)));
        }

        // Adding data from Profil to Chart
        TBUseriesTinggiBadanBulanIni.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_bulan"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))));

        // Creating a dataset to hold each series
        TBUdatasetSeluruhBulan = new XYMultipleSeriesDataset();

        // Adding Seluruh Bulan Series to the dataset
        TBUdatasetSeluruhBulan.addSeries(TBUseriesMin3sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseriesMin2sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseriesMin1sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseriesMedian);
        TBUdatasetSeluruhBulan.addSeries(TBUseries1sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseries2sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseries3sd);
        TBUdatasetSeluruhBulan.addSeries(TBUseriesTinggiBadanSeluruhBulan);

        // Creating a dataset to hold each series
        TBUdatasetBulanIni = new XYMultipleSeriesDataset();

        // Adding Bulan ini Series to the dataset
        TBUdatasetBulanIni.addSeries(TBUseriesMin3sd);
        TBUdatasetBulanIni.addSeries(TBUseriesMin2sd);
        TBUdatasetBulanIni.addSeries(TBUseriesMin1sd);
        TBUdatasetBulanIni.addSeries(TBUseriesMedian);
        TBUdatasetBulanIni.addSeries(TBUseries1sd);
        TBUdatasetBulanIni.addSeries(TBUseries2sd);
        TBUdatasetBulanIni.addSeries(TBUseries3sd);
        TBUdatasetBulanIni.addSeries(TBUseriesTinggiBadanBulanIni);

        // Creating XYSeriesRenderer to customize min1sd
        XYSeriesRenderer TBUrendererMin1sd = new XYSeriesRenderer();
        TBUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));
        TBUrendererMin1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min2sd
        XYSeriesRenderer TBUrendererMin2sd = new XYSeriesRenderer();
        TBUrendererMin2sd.setColor(Color.parseColor("#FA9339"));
        TBUrendererMin2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min3sd
        XYSeriesRenderer TBUrendererMin3sd = new XYSeriesRenderer();
        TBUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        TBUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize median
        XYSeriesRenderer TBUrendererMedian = new XYSeriesRenderer();
        TBUrendererMedian.setColor(Color.parseColor("#17C21A"));
        TBUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 1sd
        XYSeriesRenderer TBUrenderer1sd = new XYSeriesRenderer();
        TBUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        TBUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 2sd
        XYSeriesRenderer TBUrenderer2sd = new XYSeriesRenderer();
        TBUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        TBUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 3sd
        XYSeriesRenderer TBUrenderer3sd = new XYSeriesRenderer();
        TBUrenderer3sd.setColor(Color.parseColor("#C26717"));
        TBUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize TinggiBadan
        XYSeriesRenderer TBUrendererTinggiBadan = new XYSeriesRenderer();
        TBUrendererTinggiBadan.setColor(Color.parseColor("#000000"));
        TBUrendererTinggiBadan.setPointStyle(PointStyle.CIRCLE);
        TBUrendererTinggiBadan.setFillPoints(true);
        TBUrendererTinggiBadan.setLineWidth(2);

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

        // Adding All Renderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin3sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin2sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMin1sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererMedian);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer1sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer2sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrenderer3sd);
        TBUmultiRenderer.addSeriesRenderer(TBUrendererTinggiBadan);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout TBUchartContainerSeluruhBulan = (LinearLayout) findViewById(R.id.tbu_seluruhbulan_layout);
        LinearLayout TBUchartContainerBulanIni = (LinearLayout) findViewById(R.id.tbu_bulanini_layout);

        // Creating a Line Chart
        TBUChartSeluruhBulan = ChartFactory.getLineChartView(DetailDokumentasi.this, TBUdatasetSeluruhBulan, TBUmultiRenderer);
        TBUChartBulanIni = ChartFactory.getLineChartView(DetailDokumentasi.this, TBUdatasetBulanIni, TBUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        TBUchartContainerSeluruhBulan.addView(TBUChartSeluruhBulan, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        TBUchartContainerBulanIni.addView(TBUChartBulanIni, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        // Chart BBTB
        List<String> BBTBchartID = new ArrayList<>();
        List<String> BBTBchartTinggi = new ArrayList<>();
        List<String> BBTBchartMin1sd = new ArrayList<>();
        List<String> BBTBchartMin2sd = new ArrayList<>();
        List<String> BBTBchartMin3sd = new ArrayList<>();
        List<String> BBTBchartMedian = new ArrayList<>();
        List<String> BBTBchart1sd = new ArrayList<>();
        List<String> BBTBchart2sd = new ArrayList<>();
        List<String> BBTBchart3sd = new ArrayList<>();

        // Gender Laki-laki
        if ((session.loadSession(this, "gender").equals("Laki-laki"))) {

            // Bulan < 24
            if (cursor.getColumnIndex("dokumentasi_bulan") < 24) {
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

        // Gender Perempuam
        } else {
            if (cursor.getColumnIndex("dokumentasi_bulan") < 24) {
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

        // Creating an  XYSeries for BBTB
        XYSeries BBTBseriesMin3sd = new XYSeries("-3 SD");
        XYSeries BBTBseriesMin2sd = new XYSeries("-2 SD");
        XYSeries BBTBseriesMin1sd = new XYSeries("-1 SD");
        XYSeries BBTBseriesMedian = new XYSeries("Median");
        XYSeries BBTBseries1sd = new XYSeries("1 SD");
        XYSeries BBTBseries2sd = new XYSeries("2 SD");
        XYSeries BBTBseries3sd = new XYSeries("3 SD");
        XYSeries BBTBseriesBeratBadanSeluruhBulan = new XYSeries("Berat Badan");
        XYSeries BBTBseriesBeratBadanBulanIni = new XYSeries("Berat Badan");

        // Adding data BBTB to Chart
        for (int i = 0; i < BBTBchartID.size(); i++) {
            BBTBseriesMin3sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin3sd.get(i)));
            BBTBseriesMin2sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin2sd.get(i)));
            BBTBseriesMin1sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMin1sd.get(i)));
            BBTBseriesMedian.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchartMedian.get(i)));
            BBTBseries1sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart1sd.get(i)));
            BBTBseries2sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart2sd.get(i)));
            BBTBseries3sd.add(Double.parseDouble(BBTBchartTinggi.get(i)), Double.parseDouble(BBTBchart3sd.get(i)));
        }
        for (int i = 0; i < dokumentasiBulanChart.size(); i++) {
            BBTBseriesBeratBadanSeluruhBulan.add(Double.parseDouble(dokumentasiTinggiChart.get(i)), Double.parseDouble(dokumentasiBeratChart.get(i)));
        }

        // Adding data from Profil to Chart
        BBTBseriesBeratBadanBulanIni.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_berat"))));

        // Creating a dataset to hold each series
        BBTBdatasetSeluruhBulan = new XYMultipleSeriesDataset();
        
        // Adding Seluruh Bulan to the dataset
        BBTBdatasetSeluruhBulan.addSeries(BBTBseriesMin3sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseriesMin2sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseriesMin1sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseriesMedian);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseries1sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseries2sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseries3sd);
        BBTBdatasetSeluruhBulan.addSeries(BBTBseriesBeratBadanSeluruhBulan);

        // Creating a dataset to hold each series
        BBTBdatasetBulanIni = new XYMultipleSeriesDataset();

        // Adding Bulan Ini to the dataset
        BBTBdatasetBulanIni.addSeries(BBTBseriesMin3sd);
        BBTBdatasetBulanIni.addSeries(BBTBseriesMin2sd);
        BBTBdatasetBulanIni.addSeries(BBTBseriesMin1sd);
        BBTBdatasetBulanIni.addSeries(BBTBseriesMedian);
        BBTBdatasetBulanIni.addSeries(BBTBseries1sd);
        BBTBdatasetBulanIni.addSeries(BBTBseries2sd);
        BBTBdatasetBulanIni.addSeries(BBTBseries3sd);
        BBTBdatasetBulanIni.addSeries(BBTBseriesBeratBadanBulanIni);

        // Creating XYSeriesRenderer to customize min1sd
        XYSeriesRenderer BBTBrendererMin1sd = new XYSeriesRenderer();
        BBTBrendererMin1sd.setColor(Color.parseColor("#FF33CC"));
        BBTBrendererMin1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min2sd
        XYSeriesRenderer BBTBrendererMin2sd = new XYSeriesRenderer();
        BBTBrendererMin2sd.setColor(Color.parseColor("#FA9339"));
        BBTBrendererMin2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min3sd
        XYSeriesRenderer BBTBrendererMin3sd = new XYSeriesRenderer();
        BBTBrendererMin3sd.setColor(Color.parseColor("#E31025"));
        BBTBrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize median
        XYSeriesRenderer BBTBrendererMedian = new XYSeriesRenderer();
        BBTBrendererMedian.setColor(Color.parseColor("#17C21A"));
        BBTBrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 1sd
        XYSeriesRenderer BBTBrenderer1sd = new XYSeriesRenderer();
        BBTBrenderer1sd.setColor(Color.parseColor("#2517C2"));
        BBTBrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 2sd
        XYSeriesRenderer BBTBrenderer2sd = new XYSeriesRenderer();
        BBTBrenderer2sd.setColor(Color.parseColor("#C2B617"));
        BBTBrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 3sd
        XYSeriesRenderer BBTBrenderer3sd = new XYSeriesRenderer();
        BBTBrenderer3sd.setColor(Color.parseColor("#C26717"));
        BBTBrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize beratbadan
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
        if (cursor.getColumnIndex("dokumentasi_bulan") < 24) {
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

        // Adding All Renderer to multipleRenderer
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
        LinearLayout BBTBchartContainerSeluruhBulan = (LinearLayout) findViewById(R.id.bbtb_seluruhbulan_layout);
        LinearLayout BBTBchartContainerBulanIni = (LinearLayout) findViewById(R.id.bbtb_bulanini_layout);

        // Creating a Line Chart
        BBTBChartSeluruhBulan = ChartFactory.getLineChartView(DetailDokumentasi.this, BBTBdatasetSeluruhBulan, BBTBmultiRenderer);
        BBTBChartBulanIni = ChartFactory.getLineChartView(DetailDokumentasi.this, BBTBdatasetBulanIni, BBTBmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        BBTBchartContainerSeluruhBulan.addView(BBTBChartSeluruhBulan, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        BBTBchartContainerBulanIni.addView(BBTBChartBulanIni, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));


        // Chart IMTU
        List<String> IMTUchartID = new ArrayList<>();
        List<String> IMTUchartUmur = new ArrayList<>();
        List<String> IMTUchartMin1sd = new ArrayList<>();
        List<String> IMTUchartMin2sd = new ArrayList<>();
        List<String> IMTUchartMin3sd = new ArrayList<>();
        List<String> IMTUchartMedian = new ArrayList<>();
        List<String> IMTUchart1sd = new ArrayList<>();
        List<String> IMTUchart2sd = new ArrayList<>();
        List<String> IMTUchart3sd = new ArrayList<>();

        // Gender Laki-laki
        if ((session.loadSession(this, "gender")).equals("Laki-laki")) {
            Cursor cursorIMTUchart = db.getLakiIMTUAllList();
            cursorIMTUchart.moveToFirst();
            if (!cursorIMTUchart.isAfterLast()) {
                do {
                    IMTUchartID.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_ID")));
                    IMTUchartUmur.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_usia")));
                    IMTUchartMin3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min3sd")));
                    IMTUchartMin2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min2sd")));
                    IMTUchartMin1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_min1sd")));
                    IMTUchartMedian.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_median")));
                    IMTUchart1sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_1sd")));
                    IMTUchart2sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_2sd")));
                    IMTUchart3sd.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("laki_imtu_3sd")));

                } while (cursorIMTUchart.moveToNext());
            }
        // Gender Perempuan
        } else {
            Cursor cursorIMTUchart = db.getPerempuanIMTUAllList();
            cursorIMTUchart.moveToFirst();
            if (!cursorIMTUchart.isAfterLast()) {
                do {
                    IMTUchartID.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_ID")));
                    IMTUchartUmur.add(cursorIMTUchart.getString(cursorIMTUchart.getColumnIndex("perempuan_imtu_usia")));
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

        // Creating an  XYSeries for Chart
        XYSeries IMTUseriesMin3sd = new XYSeries("-3 SD");
        XYSeries IMTUseriesMin2sd = new XYSeries("-2 SD");
        XYSeries IMTUseriesMin1sd = new XYSeries("-1 SD");
        XYSeries IMTUseriesMedian = new XYSeries("Median");
        XYSeries IMTUseries1sd = new XYSeries("1 SD");
        XYSeries IMTUseries2sd = new XYSeries("2 SD");
        XYSeries IMTUseries3sd = new XYSeries("3 SD");
        XYSeries IMTUseriesIMTSeluruhBulan = new XYSeries("Indeks Massa Tubuh");
        XYSeries IMTUseriesIMTBulanIni = new XYSeries("Indeks Massa Tubuh");

        // Adding data to Chart
        for (int i = 0; i < IMTUchartID.size(); i++) {
            IMTUseriesMin3sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin3sd.get(i)));
            IMTUseriesMin2sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin2sd.get(i)));
            IMTUseriesMin1sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMin1sd.get(i)));
            IMTUseriesMedian.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchartMedian.get(i)));
            IMTUseries1sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart1sd.get(i)));
            IMTUseries2sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart2sd.get(i)));
            IMTUseries3sd.add(Double.parseDouble(IMTUchartUmur.get(i)), Double.parseDouble(IMTUchart3sd.get(i)));
        }
        for (int i = 0; i < dokumentasiBulanChart.size(); i++) {
            float imtSeluruhBulan = Float.parseFloat(dokumentasiBeratChart.get(i)) / ((Float.parseFloat(dokumentasiTinggiChart.get(i)) / 100) * (Float.parseFloat(dokumentasiTinggiChart.get(i)) / 100));
            IMTUseriesIMTSeluruhBulan.add(Double.parseDouble(dokumentasiBulanChart.get(i)), (double) imtSeluruhBulan);
        }

        // Adding data from Profil to Chart
        float imtBulanIni = Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_berat"))) / ((Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))) / 100) * (Float.parseFloat(cursor.getString(cursor.getColumnIndex("dokumentasi_tinggi"))) / 100));
        IMTUseriesIMTBulanIni.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("dokumentasi_bulan"))), (double) imtBulanIni);

        // Creating a dataset to hold each series
        IMTUdatasetSeluruhBulan = new XYMultipleSeriesDataset();

        // Adding Seluruh Bulan to the dataset
        IMTUdatasetSeluruhBulan.addSeries(IMTUseriesMin3sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseriesMin2sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseriesMin1sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseriesMedian);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseries1sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseries2sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseries3sd);
        IMTUdatasetSeluruhBulan.addSeries(IMTUseriesIMTSeluruhBulan);

        // Creating a dataset to hold each series
        IMTUdatasetBulanIni = new XYMultipleSeriesDataset();

        // Adding Bulan Ini to the dataset
        IMTUdatasetBulanIni.addSeries(IMTUseriesMin3sd);
        IMTUdatasetBulanIni.addSeries(IMTUseriesMin2sd);
        IMTUdatasetBulanIni.addSeries(IMTUseriesMin1sd);
        IMTUdatasetBulanIni.addSeries(IMTUseriesMedian);
        IMTUdatasetBulanIni.addSeries(IMTUseries1sd);
        IMTUdatasetBulanIni.addSeries(IMTUseries2sd);
        IMTUdatasetBulanIni.addSeries(IMTUseries3sd);
        IMTUdatasetBulanIni.addSeries(IMTUseriesIMTBulanIni);

        // Creating XYSeriesRenderer to customize min1sd
        XYSeriesRenderer IMTUrendererMin1sd = new XYSeriesRenderer();
        IMTUrendererMin1sd.setColor(Color.parseColor("#FF33CC"));
        IMTUrendererMin1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min2sd
        XYSeriesRenderer IMTUrendererMin2sd = new XYSeriesRenderer();
        IMTUrendererMin2sd.setColor(Color.parseColor("#FA9339"));
        IMTUrendererMin2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize min3sd
        XYSeriesRenderer IMTUrendererMin3sd = new XYSeriesRenderer();
        IMTUrendererMin3sd.setColor(Color.parseColor("#E31025"));
        IMTUrendererMin3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize median
        XYSeriesRenderer IMTUrendererMedian = new XYSeriesRenderer();
        IMTUrendererMedian.setColor(Color.parseColor("#17C21A"));
        IMTUrendererMedian.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 1sd
        XYSeriesRenderer IMTUrenderer1sd = new XYSeriesRenderer();
        IMTUrenderer1sd.setColor(Color.parseColor("#2517C2"));
        IMTUrenderer1sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 2sd
        XYSeriesRenderer IMTUrenderer2sd = new XYSeriesRenderer();
        IMTUrenderer2sd.setColor(Color.parseColor("#C2B617"));
        IMTUrenderer2sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize 3sd
        XYSeriesRenderer IMTUrenderer3sd = new XYSeriesRenderer();
        IMTUrenderer3sd.setColor(Color.parseColor("#C26717"));
        IMTUrenderer3sd.setFillPoints(true);

        // Creating XYSeriesRenderer to customize beratbadan
        XYSeriesRenderer IMTUrendererIMT = new XYSeriesRenderer();
        IMTUrendererIMT.setColor(Color.parseColor("#000000"));
        IMTUrendererIMT.setPointStyle(PointStyle.CIRCLE);
        IMTUrendererIMT.setFillPoints(true);
        IMTUrendererIMT.setLineWidth(2);

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

        // Adding All Renderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin3sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin2sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMin1sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererMedian);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer1sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer2sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrenderer3sd);
        IMTUmultiRenderer.addSeriesRenderer(IMTUrendererIMT);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout IMTUchartContainerSeluruhBulan = (LinearLayout) findViewById(R.id.imtu_seluruhbulan_layout);
        LinearLayout IMTUchartContainerBulanIni = (LinearLayout) findViewById(R.id.imtu_bulanini_layout);

        // Creating a Line Chart
        IMTUChartSeluruhBulan = ChartFactory.getLineChartView(DetailDokumentasi.this, IMTUdatasetSeluruhBulan, IMTUmultiRenderer);
        IMTUChartBulanIni = ChartFactory.getLineChartView(DetailDokumentasi.this, IMTUdatasetBulanIni, IMTUmultiRenderer);

        // Adding the Line Chart to the LinearLayout
        IMTUchartContainerSeluruhBulan.addView(IMTUChartSeluruhBulan, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        IMTUchartContainerBulanIni.addView(IMTUChartBulanIni, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));

        // Tab Grafik Bulan Ini and Grafik Seluruh Bulan
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("Grafik Bulan Ini").setIndicator("Grafik Bulan Ini").setContent(R.id.bulanIniLayout);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Grafik Seluruh Bulan").setIndicator("Grafik Seluruh Bulan").setContent(R.id.seluruhBulanLayout);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

        // Tab inside Grafik Bulan Ini
        tabBulanIni = (TabHost) findViewById(R.id.tabHostBulanIni);
        tabBulanIni.setup();
        TabHost.TabSpec specBulanIni;
        specBulanIni = tabBulanIni.newTabSpec("BB/U").setIndicator("BB/U").setContent(R.id.bbu_bulanini_layout);
        tabBulanIni.addTab(specBulanIni);
        specBulanIni = tabBulanIni.newTabSpec("TB/U").setIndicator("TB/U").setContent(R.id.tbu_bulanini_layout);
        tabBulanIni.addTab(specBulanIni);
        specBulanIni = tabBulanIni.newTabSpec("BB/TB").setIndicator("BB/TB").setContent(R.id.bbtb_bulanini_layout);
        tabBulanIni.addTab(specBulanIni);
        specBulanIni = tabBulanIni.newTabSpec("IMT/U").setIndicator("IMT/U").setContent(R.id.imtu_bulanini_layout);
        tabBulanIni.addTab(specBulanIni);
        tabBulanIni.setCurrentTab(0);

        // Tab iside Grafik Seluruh Bulan
        tabSeluruhBulan = (TabHost) findViewById(R.id.tabHostSeluruhBulan);
        tabSeluruhBulan.setup();
        TabHost.TabSpec specSeluruhBulan;
        specSeluruhBulan = tabSeluruhBulan.newTabSpec("BB/U").setIndicator("BB/U").setContent(R.id.bbu_seluruhbulan_layout);
        tabSeluruhBulan.addTab(specSeluruhBulan);
        specSeluruhBulan = tabSeluruhBulan.newTabSpec("TB/U").setIndicator("TB/U").setContent(R.id.tbu_seluruhbulan_layout);
        tabSeluruhBulan.addTab(specSeluruhBulan);
        specSeluruhBulan = tabSeluruhBulan.newTabSpec("BB/TB").setIndicator("BB/TB").setContent(R.id.bbtb_seluruhbulan_layout);
        tabSeluruhBulan.addTab(specSeluruhBulan);
        specSeluruhBulan = tabSeluruhBulan.newTabSpec("IMT/U").setIndicator("IMT/U").setContent(R.id.imtu_seluruhbulan_layout);
        tabSeluruhBulan.addTab(specSeluruhBulan);
        tabSeluruhBulan.setCurrentTab(0);

        // Tab Set Focusable
        tabHost.setFocusable(false);
        tabSeluruhBulan.setFocusable(false);
        tabBulanIni.setFocusable(false);

        // Background color and text color tab
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabHost.getCurrentTab()) {
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                tv.setTextColor(Color.parseColor("#D046F2"));
                tv.setTypeface(typeface);
            } else {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                tv.setTextColor(Color.parseColor("#FF0000"));
                tv.setTypeface(typeface);
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

        // Tab OnChangeListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#FF0000"));
                    if (i == tabHost.getCurrentTab()) {
                        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                        tv.setTextColor(Color.parseColor("#D046F2"));
                        tv.setTypeface(typeface);
                    } else {
                        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                        tv.setTextColor(Color.parseColor("#FF0000"));
                        tv.setTypeface(typeface);
                    }
                }
            }
        });

        // Background color and text color tab
        for (int i = 0; i < tabBulanIni.getTabWidget().getChildCount(); i++) {
            tabBulanIni.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
            TextView tv = (TextView) tabBulanIni.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabBulanIni.getCurrentTab()) {
                tabBulanIni.getTabWidget().getChildAt(tabBulanIni.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                tv.setTextColor(Color.parseColor("#D046F2"));
                tv.setTypeface(typeface);
            } else {
                tabBulanIni.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                tv.setTextColor(Color.parseColor("#FF0000"));
                tv.setTypeface(typeface);
            }

            // Set Height Tab Title
            final View view = tabBulanIni.getTabWidget().getChildTabViewAt(i);
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

        // Tab OnChangeListener
        tabBulanIni.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabBulanIni.getTabWidget().getChildCount(); i++) {
                    tabBulanIni.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                    TextView tv = (TextView) tabBulanIni.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#FF0000"));
                    if (i == tabBulanIni.getCurrentTab()) {
                        tabBulanIni.getTabWidget().getChildAt(tabBulanIni.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                        tv.setTextColor(Color.parseColor("#D046F2"));
                        tv.setTypeface(typeface);
                    } else {
                        tabBulanIni.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                        tv.setTextColor(Color.parseColor("#FF0000"));
                        tv.setTypeface(typeface);
                    }
                }
            }
        });

        // Background color and text color tab
        for (int i = 0; i < tabSeluruhBulan.getTabWidget().getChildCount(); i++) {
            tabSeluruhBulan.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
            TextView tv = (TextView) tabSeluruhBulan.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabSeluruhBulan.getCurrentTab()) {
                tabSeluruhBulan.getTabWidget().getChildAt(tabSeluruhBulan.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                tv.setTextColor(Color.parseColor("#D046F2"));
                tv.setTypeface(typeface);
            } else {
                tabSeluruhBulan.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                tv.setTextColor(Color.parseColor("#FF0000"));
                tv.setTypeface(typeface);
            }

            // Set Height Tab Title
            final View view = tabSeluruhBulan.getTabWidget().getChildTabViewAt(i);
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

        // Tab OnChangeListener
        tabSeluruhBulan.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabSeluruhBulan.getTabWidget().getChildCount(); i++) {
                    tabSeluruhBulan.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                    TextView tv = (TextView) tabSeluruhBulan.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#FF0000"));
                    if (i == tabSeluruhBulan.getCurrentTab()) {
                        tabSeluruhBulan.getTabWidget().getChildAt(tabSeluruhBulan.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFBC9"));
                        tv.setTextColor(Color.parseColor("#D046F2"));
                        tv.setTypeface(typeface);
                    } else {
                        tabSeluruhBulan.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                        tv.setTextColor(Color.parseColor("#FF0000"));
                        tv.setTypeface(typeface);
                    }
                }
            }
        });

        // Set OnClickListener
        button_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Ubah Riwayat Activity
                Intent ubah_dokumentasi = new Intent(DetailDokumentasi.this, UbahDokumentasi.class);
                // Put Intent Extra
                lastActivity = System.currentTimeMillis();
                ubah_dokumentasi.putExtra("lastActivity", lastActivity);
                ubah_dokumentasi.putExtra("dokumentasiID", dokumentasiID);
                ubah_dokumentasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ubah_dokumentasi);
                finish();
            }
        });

        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(DetailDokumentasi.this);
                dialog.setContentView(R.layout.hapus_dokumentasi);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                // Load Dialog Widget
                TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                TextView alert_hapus_dokumentasi = (TextView) dialog.findViewById(R.id.alert_hapus);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_hapus_dokumentasi.setTypeface(typeface);
                alert_warning.setTypeface(typeface);

                // Set OnClickListener Dialog
                button_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close Dialog
                        dialog.dismiss();
                    }
                });
                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Declare Condition
                        boolean success = false;

                        try {
                            // Delete From Database
                            db.deleteDokumentasi(dokumentasiID);
                            success = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(DetailDokumentasi.this, "Dokumentasi Gizi Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(DetailDokumentasi.this, "Dokumentasi Gizi Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Rekam Medis Activity
                        Intent dokumentasi_gizi = new Intent(DetailDokumentasi.this, DokumentasiGizi.class);
                        lastActivity = System.currentTimeMillis();
                        dokumentasi_gizi.putExtra("lastActivity", lastActivity);
                        dokumentasi_gizi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(dokumentasi_gizi);

                        // Clear Activity
                        finish();
                    }
                });
            }
        });

        // Set Scrollview On Top
        final ScrollView main = (ScrollView) findViewById(R.id.scrollView);
        main.post(new Runnable() {
            public void run() {
                main.scrollTo(0,0);
            }
        });

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        Intent gizi = new Intent(this, DokumentasiGizi.class);
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
            session.clearSession(DetailDokumentasi.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }

        // Chart
        if (BBUChartSeluruhBulan == null || BBUChartBulanIni == null ||
                TBUChartSeluruhBulan == null || TBUChartBulanIni == null ||
                BBTBChartSeluruhBulan == null || BBTBChartBulanIni == null ||
                IMTUChartSeluruhBulan == null || IMTUChartBulanIni == null) {
            LinearLayout bbu_seluruhbulan_layout = (LinearLayout) findViewById(R.id.bbu_seluruhbulan_layout);
            BBUChartSeluruhBulan = ChartFactory.getLineChartView(getBaseContext(), BBUdatasetSeluruhBulan, BBUmultiRenderer);
            bbu_seluruhbulan_layout.addView(BBUChartSeluruhBulan);

            LinearLayout bbu_bulaini_layout = (LinearLayout) findViewById(R.id.bbu_bulanini_layout);
            BBUChartBulanIni = ChartFactory.getLineChartView(getBaseContext(), BBUdatasetBulanIni, BBUmultiRenderer);
            bbu_bulaini_layout.addView(BBUChartBulanIni);

            LinearLayout tbu_seluruhbulan_layout = (LinearLayout) findViewById(R.id.tbu_seluruhbulan_layout);
            TBUChartSeluruhBulan = ChartFactory.getLineChartView(getBaseContext(), TBUdatasetSeluruhBulan, TBUmultiRenderer);
            tbu_seluruhbulan_layout.addView(TBUChartSeluruhBulan);

            LinearLayout tbu_bulanini_layout = (LinearLayout) findViewById(R.id.tbu_bulanini_layout);
            TBUChartBulanIni = ChartFactory.getLineChartView(getBaseContext(), TBUdatasetBulanIni, TBUmultiRenderer);
            tbu_bulanini_layout.addView(TBUChartBulanIni);

            LinearLayout bbtb_seluruhbulan_layout = (LinearLayout) findViewById(R.id.bbtb_seluruhbulan_layout);
            BBTBChartSeluruhBulan = ChartFactory.getLineChartView(getBaseContext(), BBTBdatasetSeluruhBulan, BBTBmultiRenderer);
            bbtb_seluruhbulan_layout.addView(BBTBChartSeluruhBulan);

            LinearLayout bbtb_bulanini_layout = (LinearLayout) findViewById(R.id.bbtb_bulanini_layout);
            BBTBChartBulanIni = ChartFactory.getLineChartView(getBaseContext(), BBTBdatasetBulanIni, BBTBmultiRenderer);
            bbtb_bulanini_layout.addView(BBTBChartBulanIni);

            LinearLayout imtu_seluruhbulan_layout = (LinearLayout) findViewById(R.id.imtu_seluruhbulan_layout);
            IMTUChartSeluruhBulan = ChartFactory.getLineChartView(getBaseContext(), IMTUdatasetSeluruhBulan, IMTUmultiRenderer);
            imtu_seluruhbulan_layout.addView(IMTUChartSeluruhBulan);

            LinearLayout imtu_bulanini_layout = (LinearLayout) findViewById(R.id.imtu_bulanini_layout);
            IMTUChartBulanIni = ChartFactory.getLineChartView(getBaseContext(), IMTUdatasetBulanIni, IMTUmultiRenderer);
            imtu_bulanini_layout.addView(IMTUChartBulanIni);

        } else {

            BBUChartSeluruhBulan.repaint();
            BBUChartBulanIni.repaint();
            TBUChartSeluruhBulan.repaint();
            TBUChartBulanIni.repaint();
            BBTBChartSeluruhBulan.repaint();
            BBTBChartBulanIni.repaint();
            IMTUChartSeluruhBulan.repaint();
            IMTUChartBulanIni.repaint();

        }
    }
}
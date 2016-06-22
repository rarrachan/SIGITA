package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/09/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Declare
    private static final String db_name = "SIGITA.db";
    private static final int db_version = 1;
    private SQLiteDatabase db;

    // Create Database
    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    //Start Activity
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create All Table Inside Database
        createProfil(db);
        createLakiBBU(db);
        createLakiTBU(db);
        createLakiBBTB_0_24(db);
        createLakiBBTB_24_60(db);
        createLakiIMTU(db);
        createPerempuanBBU(db);
        createPerempuanTBU(db);
        createPerempuanBBTB_0_24(db);
        createPerempuanBBTB_24_60(db);
        createPerempuanIMTU(db);
        createDokumentasi(db);
        createList(db);
        createRiwayat(db);
        createTahap(db);
        createGaleri(db);
        createMedis(db);
    }

    // Upgrade Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profil");
        db.execSQL("DROP TABLE IF EXISTS antropometri_laki_bbu");
        db.execSQL("DROP TABLE IF EXISTS antropometri_laki_tbu");
        db.execSQL("DROP TABLE IF EXISTS antropometri_laki_bbtb_0_24");
        db.execSQL("DROP TABLE IF EXISTS antropometri_laki_bbtb_24_60");
        db.execSQL("DROP TABLE IF EXISTS antropometri_laki_imtu");
        db.execSQL("DROP TABLE IF EXISTS antropometri_perempuan_bbu");
        db.execSQL("DROP TABLE IF EXISTS antropometri_perempuan_tbu");
        db.execSQL("DROP TABLE IF EXISTS antropometri_perempuan_bbtb_0_24");
        db.execSQL("DROP TABLE IF EXISTS antropometri_perempuan_bbtb_24_60");
        db.execSQL("DROP TABLE IF EXISTS antropometri_perempuan_imtu");
        db.execSQL("DROP TABLE IF EXISTS dokumentasi_gizi");
        db.execSQL("DROP TABLE IF EXISTS list_imunisasi");
        db.execSQL("DROP TABLE IF EXISTS riwayat_imunisasi");
        db.execSQL("DROP TABLE IF EXISTS tahap_tumbang");
        db.execSQL("DROP TABLE IF EXISTS galeri_tumbang");
        db.execSQL("DROP TABLE IF EXISTS rekam_medis");
        onCreate(db);
    }

    // Open Database
    public void open() throws SQLException {
        this.db = getWritableDatabase();
    }

    // Close Database
    public void close() {
        this.db.close();
    }

    // Create Table Profil
    public void createProfil(SQLiteDatabase db) {
    	
        // Create Table Profil
        String create_profil = "create table profil (" +
                "profilID integer primary key autoincrement, " +
                "profil_nama text not null," +
                "profil_tempatLahir text not null," +
                "profil_tanggalLahir text not null," +
                "profil_jenisKelamin text not null, " +
                "profil_golonganDarah text not null," +
                "profil_panjangLahir real not null, " +
                "profil_beratLahir real not null, " +
                "profil_alergi text, " +
                "profil_penyakitKronis text," +
                "profil_passcode text, " +
                "profil_foto text not null);";
        db.execSQL(create_profil);
    }

    // Insert Profil to Database
    public long insertProfil(String nama, String tmptLahir, String tglLahir, String jenisKelamin,
                             String golonganDarah, String panjangLahir, String beratLahir,
                             String alergi, String penyakitKronis, String passcode, String fotoPath) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("profil_nama", nama);
        values.put("profil_tempatLahir", tmptLahir);
        values.put("profil_tanggalLahir", tglLahir);
        values.put("profil_jenisKelamin", jenisKelamin);
        values.put("profil_golonganDarah", golonganDarah);
        values.put("profil_panjangLahir", panjangLahir);
        values.put("profil_beratLahir", beratLahir);
        values.put("profil_alergi", alergi);
        values.put("profil_penyakitKronis", penyakitKronis);
        values.put("profil_passcode", passcode);
        values.put("profil_foto", fotoPath);

        // Insert Data
        return db.insert("profil", null, values);
    }

    // Get Profil from Database
    public Cursor getProfil() {
        Cursor cursor = db.rawQuery("SELECT * FROM profil", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Profil From Database
    public Cursor getOneProfil(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM profil WHERE profilID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Update Profil to Database
    public long updateProfil(Integer id, String nama, String tmptLahir, String tglLahir,
                             String jenisKelamin, String golonganDarah, String panjangLahir,
                             String beratLahir, String alergi, String penyakitKronis, String fotoPath) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("profil_nama", nama);
        values.put("profil_tempatLahir", tmptLahir);
        values.put("profil_tanggalLahir", tglLahir);
        values.put("profil_jenisKelamin", jenisKelamin);
        values.put("profil_golonganDarah", golonganDarah);
        values.put("profil_panjangLahir", panjangLahir);
        values.put("profil_beratLahir", beratLahir);
        values.put("profil_alergi", alergi);
        values.put("profil_penyakitKronis", penyakitKronis);
        values.put("profil_foto", fotoPath);

        // Update Data
        return db.update("profil", values, "profilID = " + id, null);
    }

    // Update Profil Passcode to Database
    public long updateProfilPasscode(Integer id, String passcode) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("profil_passcode", passcode);

        // Update Data
        return db.update("profil", values, "profilID = " + id, null);
    }

    // Delete Profil from Database
    public boolean deleteProfil(int id) {

        // Delete Data
        return db.delete("profil", "profilID =" + id, null) < 1;
    }

    // Create Table Laki-laki BBU
    public void createLakiBBU(SQLiteDatabase db) {

        // Create Table Laki-laki BBU
        String create_laki_bbu = "create table antropometri_laki_bbu (" +
                "laki_bbu_ID integer primary key autoincrement, " +
                "laki_bbu_usia real not null," +
                "laki_bbu_min3sd real not null, " +
                "laki_bbu_min2sd real not null, " +
                "laki_bbu_min1sd real not null, " +
                "laki_bbu_median real not null, " +
                "laki_bbu_1sd real not null, " +
                "laki_bbu_2sd real not null, " +
                "laki_bbu_3sd real not null " +
                ");";
        db.execSQL(create_laki_bbu);

        // Insert Laki-laki BBU After Create Table
        insertLakiBBU(db);
    }

    // Insert Laki-laki BBU to Database
    public void insertLakiBBU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('0', '2.1', '2.5', '2.9', '3.3', '3.9', '4.4', '5.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('1', '2.9', '3.4', '3.9', '4.5', '5.1', '5.8', '6.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('2', '3.8', '4.3', '4.9', '5.6', '6.3', '7.1', '8.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('3', '4.4', '5.0', '5.7', '6.4', '7.2', '8.0', '9.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('4', '4.9', '5.6', '6.2', '7.0', '7.8', '8.7', '9.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('5', '5.3', '6.0', '6.7', '7.5', '8.4', '9.3', '10.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('6', '5.7', '6.4', '7.1', '7.9', '8.8', '9.8', '10.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('7', '5.9', '6.7', '7.4', '8.3', '9.2', '10.3', '11.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('8', '6.2', '6.9', '7.7', '8.6', '9.6', '10.7', '11.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('9', '6.4', '7.1', '8.0', '8.9', '9.9', '11.0', '12.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('10', '6.6', '7.4', '8.2', '9.2', '10.2', '11.4', '12.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('11', '6.8', '7.6', '8.4', '9.4', '10.5', '11.7', '13.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('12', '6.9', '7.7', '8.6', '9.6', '10.8', '12.0', '13.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('13', '7.1', '7.9', '8.8', '9.9', '11.0', '12.3', '13.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('14', '7.2', '8.1', '9.0', '10.1', '11.3', '12.6', '14.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('15', '7.4', '8.3', '9.2', '10.3', '11.5', '12.8', '14.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('16', '7.5', '8.4', '9.4', '10.5', '11.7', '13.1', '14.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('17', '7.7', '8.6', '9.6', '10.7', '12.0', '13.4', '14.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('18', '7.8', '8.8', '9.8', '10.9', '12.2', '13.7', '15.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('19', '8.0', '8.9', '10.0', '11.1', '12.5', '13.9', '15.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('20', '8.1', '9.1', '10.1', '11.3', '12.7', '14.2', '15.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('21', '8.2', '9.2', '10.3', '11.5', '12.9', '14.5', '16.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('22', '8.4', '9.4', '10.5', '11.8', '13.2', '14.7', '16.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('23', '8.5', '9.5', '10.7', '12.0', '13.4', '15.0', '16.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('24', '8.6', '9.7', '10.8', '12.2', '13.4', '15.0', '16.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('25', '8.8', '9.8', '11.0', '12.4', '13.9', '15.5', '17.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('26', '8.9', '10.0', '11.2', '12.5', '14.1', '15.8', '17.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('27', '9.0', '10.1', '11.3', '12.7', '14.3', '16.1', '18.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('28', '9.1', '10.2', '11.5', '12.9', '14.5', '16.3', '18.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('29', '9.2', '10.4', '11.7', '13.1', '14.8', '16.6', '18.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('30', '9.4', '10.5', '11.8', '13.3', '15.0', '16.9', '19.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('31', '9.5', '10.7', '12.0', '13.5', '15.2', '17.1', '19.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('32', '9.6', '10.8', '12.1', '13.7', '15.4', '17.4', '19.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('33', '9.7', '10.9', '12.3', '13.8', '15.6', '17.6', '19.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('34', '9.8', '11.0', '12.4', '14.0', '15.8', '17.8', '20.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('35', '9.9', '11.2', '12.6', '14.2', '16.0', '18.1', '20.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('36', '10.0', '11.3', '12.7', '14.3', '16.2', '18.3', '20.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('37', '10.1', '11.4', '12.9', '14.5', '16.4', '18.6', '21.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('38', '10.2', '11.5', '13.0', '14.7', '16.6', '18.8', '21.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('39', '10.3', '11.6', '13.1', '14.8', '16.8', '19.0', '21.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('40', '10.4', '11.8', '13.3', '15.0', '17.0', '19.3', '21.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('41', '10.5', '11.9', '13.4', '15.2', '17.2', '19.5', '22.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('42', '10.6', '12.0', '13.6', '15.3', '17.4', '19.7', '22.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('43', '10.7', '12.1', '13.7', '15.5', '17.6', '20.0', '22.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('44', '10.8', '12.2', '13.8', '15.7', '17.8', '20.2', '23.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('45', '10.9', '12.4', '14.0', '15.8', '18.0', '20.5', '23.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('46', '11.0', '12.5', '14.1', '16.0', '18.2', '20.7', '23.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('47', '11.1', '12.6', '14.3', '16.2', '18.4', '20.9', '23.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('48', '11.2', '12.7', '14.4', '16.3', '18.6', '21.2', '24.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('49', '11.3', '12.8', '14.5', '16.5', '18.8', '21.4', '24.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('50', '11.4', '13.1', '14.8', '16.8', '19.2', '21.9', '25.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('51', '11.5', '13.1', '14.8', '16.8', '19.2', '21.9', '25.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('52', '11.6', '13.2', '15.0', '17.0', '19.4', '22.2', '25.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('53', '11.7', '13.3', '15.1', '17.2', '19.6', '22.4', '25.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('54', '11.8', '13.4', '15.2', '17.3', '19.8', '22.7', '26.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('55', '11.9', '13.5', '15.4', '17.5', '20.0', '22.9', '26.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('56', '12.0', '13.6', '15.5', '17.7', '20.2', '23.2', '26.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('57', '12.1', '13.7', '15.6', '17.8', '20.4', '23.4', '26.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('58', '12.2', '13.8', '15.8', '18.0', '20.6', '23.7', '27.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('59', '12.3', '14.0', '15.9', '18.2', '20.8', '23.9', '27.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_usia, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('60', '12.4', '14.1', '16.0', '18.3', '21.0', '24.2', '27.9');");
    }

    // Get One Laki-laki BBU List From Database
    public Cursor getLakiBBUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbu WHERE laki_bbu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Laki-laki BBU From Database
    public Cursor getLakiBBUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Laki-laki TBU
    public void createLakiTBU(SQLiteDatabase db) {

        // Create Table Laki-laki TBU
        String create_laki_tbu = "create table antropometri_laki_tbu (" +
                "laki_tbu_ID integer primary key autoincrement, " +
                "laki_tbu_usia real not null," +
                "laki_tbu_min3sd real not null, " +
                "laki_tbu_min2sd real not null, " +
                "laki_tbu_min1sd real not null, " +
                "laki_tbu_median real not null, " +
                "laki_tbu_1sd real not null, " +
                "laki_tbu_2sd real not null, " +
                "laki_tbu_3sd real not null " +
                ");";
        db.execSQL(create_laki_tbu);

        // Insert Laki-laki TBU After Create Table
        insertLakiTBU(db);
    }

    // Insert  Laki-laki TBU
    public void insertLakiTBU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('0', '44.2', '46.1', '48.0', '49.9', '51.8', '53.7', '55.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('1', '48.9', '50.8', '52.8', '54.7', '56.7', '58.6', '60.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('2', '52.4', '54.4', '56.4', '58.4', '60.4', '62.4', '64.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('3', '55.3', '57.3', '59.4', '61.4', '63.9', '66.0', '68.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('4', '57.6', '59.7', '61.8', '63.9', '66.0', '68.0', '70.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('5', '59.6', '61.7', '63.8', '65.9', '68.0', '70.1', '72.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('6', '61.2', '63.3', '65.5', '67.6', '69.8', '71.9', '74.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('7', '62.7', '64.8', '67.0', '69.2', '71.3', '73.5', '75.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('8', '64.0', '66.2', '68.4', '70.6', '72.8', '75.0', '77.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('9', '65.2', '67.5', '69.7', '72.0', '74.2', '76.5', '78.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('10', '66.4', '68.7', '71.0', '73.3', '75.6', '77.9', '80.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('11', '67.6', '69.9', '72.2', '74.5', '76.9', '79.2', '81.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('12', '68.6', '71.0', '73.4', '75.5', '78.1', '80.5', '82.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('13', '69.6', '72.1', '74.5', '76.9', '79.3', '81.8', '84.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('14', '70.6', '73.1', '74.5', '78.0', '80.5', '83.0', '85.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('15', '71.6', '74.1', '76.6', '79.1', '81.7', '84.2', '86.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('16', '72.5', '75.0', '77.6', '80.2', '82.8', '85.4', '88.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('17', '73.3', '76.0', '78.6', '81.2', '83.9', '86.5', '89.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('18', '74.2', '76.9', '79.6', '82.3', '85.0', '87.7', '90.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('19', '75.0', '77.7', '80.5', '83.2', '86.0', '88.8', '91.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('20', '75.8', '78.6', '81.4', '84.2', '87.0', '89.8', '92.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('21', '76.5', '79.4', '82.3', '85.1', '88.0', '90.0', '93.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('22', '77.2', '80.2', '83.1', '86.0', '89.0', '91.9', '94.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('23', '78.0', '81.0', '83.9', '86.9', '89.9', '92.9', '95.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('24', '78.0', '81.0', '84.1', '87.1', '90.2', '93.2', '96.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('25', '78.6', '81.7', '84.9', '88.0', '91.1', '94.2', '97.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('26', '79.3', '82.5', '85.6', '88.8', '92.0', '95.2', '98.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('27', '79.9', '83.1', '86.4', '89.6', '92.9', '96.1', '99.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('28', '80.5', '83.8', '87.1', '90.4', '93.7', '97.0', '100.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('29', '81.1', '85.5', '87.8', '91.2', '94.5', '97.9', '101.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('30', '81.7', '85.1', '88.5', '91.9', '95.3', '98.7', '102.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('31', '82.3', '85.7', '89.2', '92.7', '96.1', '99.6', '103.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('32', '82.8', '86.4', '89.9', '93.4', '96.9', '100.4', '103.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('33', '83.4', '86.9', '90.5', '94.1', '97.6', '101.2', '104.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('34', '83.9', '87.5', '91.1', '94.8', '98.4', '102.0', '105.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('35', '84.4', '88.1', '91.8', '95.4', '99.1', '102.7', '106.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('36', '85.0', '88.7', '92.4', '96.1', '99.8', '103.5', '107.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('37', '85.5', '89.2', '93.0', '96.7', '100.5', '104.2', '108.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('38', '86.0', '89.8', '93.6', '97.4', '101.2', '105.0', '108.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('39', '86.5', '90.3', '94.2', '98.0', '101.8', '105.7', '109.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('40', '87.0', '90.9', '94.7', '98.6', '102.5', '106.4', '110.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('41', '87.5', '91.4', '95.3', '99.2', '103.2', '107.1', '111.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('42', '88.0', '91.9', '95.9', '99.9', '103.8', '107.8', '111.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('43', '88.4', '92.4', '96.4', '100.4', '104.5', '108.5', '112.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('44', '88.9', '93.0', '97.0', '101.0', '105.1', '109.1', '113.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('45', '89.4', '93.5', '97.5', '101.6', '105.7', '109.8', '113.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('46', '89.8', '94.0', '98.1', '102.2', '106.3', '110.4', '114.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('47', '90.3', '94.4', '98.6', '102.8', '106.9', '111.1', '115.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('48', '90.7', '94.9', '99.1', '103.3', '107.5', '111.7', '115.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('49', '91.2', '95.4', '99.7', '103.9', '108.1', '112.4', '116.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('50', '91.6', '95.9', '100.2', '104.4', '108.7', '113.0', '117.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('51', '92.1', '96.4', '100.7', '105.0', '109.3', '113.6', '117.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('52', '92.5', '96.9', '101.2', '105.6', '109.9', '114.2', '118.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('53', '93.0', '97.4', '101.7', '106.1', '110.5', '114.9', '118.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('54', '93.4', '97.8', '102.3', '106.7', '111.1', '115.5', '119.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('55', '93.9', '98.3', '102.8', '107.2', '111.7', '116.1', '120.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('56', '94.3', '98.8', '103.3', '107.8', '112.3', '116.7', '121.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('57', '94.7', '99.3', '103.8', '108.3', '112.8', '117.4', '121.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('58', '95.2', '99.7', '104.3', '108.9', '113.4', '118.0', '122.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('59', '95.6', '100.2', '104.8', '109.4', '114.0', '118.6', '123.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_usia, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('60', '96.1', '100.7', '105.3', '110.0', '114.6', '119.2', '123.9');");
    }

    // Get One Laki-laki TBU List From Database
    public Cursor getLakiTBUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_tbu WHERE laki_tbu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Laki-laki TBU List From Database
    public Cursor getLakiTBUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_tbu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Laki-laki BBTB 0-24
    public void createLakiBBTB_0_24(SQLiteDatabase db) {

        // Create Table Laki-laki BBTB 0-24
        String create_laki_bbtb_0_24 = "create table antropometri_laki_bbtb_0_24 (" +
                "laki_bbtb_ID integer primary key autoincrement, " +
                "laki_bbtb_tb real not null," +
                "laki_bbtb_min3sd real not null, " +
                "laki_bbtb_min2sd real not null, " +
                "laki_bbtb_min1sd real not null, " +
                "laki_bbtb_median real not null, " +
                "laki_bbtb_1sd real not null, " +
                "laki_bbtb_2sd real not null, " +
                "laki_bbtb_3sd real not null " +
                ");";
        db.execSQL(create_laki_bbtb_0_24);

        // Insert Laki-laki BBTB 0-24 After Create Table
        insertLakiBBTB_0_24(db);
    }

    // Insert Laki-laki BBTB 0-24
    public void insertLakiBBTB_0_24(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('45.0', '1.9', '2.0', '2.2', '2.4', '2.7', '3.0', '3.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('45.5', '1.9', '2.1', '2.3', '2.5', '2.8', '3.1', '3.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('46.0', '2.0', '2.2', '2.4', '2.6', '2.9', '3.1', '3.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('46.5', '2.1', '2.3', '2.5', '2.7', '3.0', '3.2', '3.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('47.0', '2.1', '2.3', '2.5', '2.8', '3.0', '3.3', '3.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('47.5', '2.2', '2.4', '2.6', '2.9', '3.1', '3.4', '3.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('48.0', '2.3', '2.5', '2.7', '2.9', '3.2', '3.6', '3.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('48.5', '2.3', '2.6', '2.8', '3.0', '3.3', '3.7', '4.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('49.0', '2.4', '2.6', '2.9', '3.1', '3.4', '3.8', '4.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('49.5', '2.5', '2.7', '3.0', '3.2', '3.5', '3.9', '4.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('50.0', '2.6', '2.8', '3.0', '3.3', '3.6', '4.0', '4.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('50.5', '2.7', '2.9', '3.1', '3.4', '3.8', '4.1', '4.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('51.0', '2.7', '3.0', '3.2', '3.5', '3.9', '4.2', '4.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('51.5', '2.8', '3.1', '3.3', '3.6', '4.0', '4.4', '4.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('52.0', '2.9', '3.2', '3.5', '3.8', '4.1', '4.5', '5.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('52.5', '3.0', '3.3', '3.6', '3.9', '4.2', '4.6', '5.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('53.0', '3.1', '3.4', '3.7', '4.0', '4.4', '4.6', '5.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('53.5', '3.2', '3.5', '3.8', '4.1', '4.5', '4.9', '5.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('54.0', '3.3', '3.6', '3.9', '4.3', '4.7', '5.1', '5.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('54.5', '3.4', '3.7', '4.0', '4.4', '4.8', '5.3', '5.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('55.0', '3.6', '3.8', '4.2', '4.5', '5.0', '5.4', '6.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('55.5', '3.7', '4.0', '4.3', '4.7', '5.1', '5.6', '6.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('56.0', '3.8', '4.1', '4.4', '4.8', '5.3', '5.8', '6.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('56.5', '3.9', '4.2', '4.6', '5.0', '5.4', '5.9', '6.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('57.0', '4.0', '4.3', '4.7', '5.1', '5.6', '6.1', '6.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('57.5', '4.1', '4.5', '4.9', '5.3', '5.7', '6.3', '6.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('58.0', '4.3', '4.6', '5.0', '5.4', '5.9', '6.4', '7.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('58.5', '4.4', '4.7', '5.1', '5.6', '6.1', '6.6', '7.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('59.0', '4.5', '4.8', '5.3', '5.7', '6.2', '6.8', '7.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('59.5', '4.6', '5.0', '5.4', '5.9', '6.4', '7.0', '7.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('60.0', '4.7', '5.1', '5.5', '6.0', '6.5', '7.1', '7.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('60.5', '4.8', '5.2', '5.6', '6.1', '6.7', '7.3', '8.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('61.0', '4.9', '5.3', '5.8', '6.3', '6.8', '7.4', '8.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('61.5', '5.0', '5.4', '5.9', '6.4', '7.0', '7.6', '8.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('62.0', '5.1', '5.6', '6.0', '6.5', '7.1', '7.7', '8.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('62.5', '5.2', '5.7', '6.1', '6.7', '7.2', '7.9', '8.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('63.0', '5.3', '5.8', '6.2', '6.8', '7.4', '8.0', '8.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('63.5', '5.4', '5.9', '6.4', '6.9', '7.5', '8.2', '8.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('64.0', '5.5', '6.0', '6.5', '7.0', '7.6', '8.3', '9.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('64.5', '5.6', '6.1', '6.6', '7.1', '7.8', '8.5', '9.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('65.0', '5.7', '6.2', '6.7', '7.3', '7.9', '8.6', '9.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('65.5', '5.8', '6.3', '6.8', '7.4', '8.0', '8.7', '9.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('66.0', '5.9', '6.4', '6.9', '7.5', '8.2', '8.9', '9.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('66.5', '6.0', '6.5', '7.0', '7.6', '8.3', '9.0', '9.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('67.0', '6.1', '6.6', '7.1', '7.7', '8.4', '9.2', '10.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('68.0', '6.3', '6.8', '7.3', '8.0', '8.7', '9.4', '10.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('68.5', '6.4', '6.9', '7.5', '8.1', '8.8', '9.6', '10.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('69.0', '6.5', '7.0', '7.6', '8.2', '8.9', '9.7', '10.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('69.5', '6.6', '7.1', '7.7', '8.3', '9.0', '9.8', '10.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('70.0', '6.6', '7.2', '7.8', '8.4', '9.2', '10.0', '10.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('70.5', '6.7', '7.3', '7.9', '8.5', '9.3', '10.1', '11.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('71.0', '6.8', '7.4', '8.0', '8.6', '9.4', '10.2', '11.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('71.5', '6.9', '7.5', '8.1', '8.8', '9.5', '10.4', '11.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('72.0', '7.0', '7.6', '8.2', '8.9', '9.6', '10.5', '11.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('72.5', '7.1', '7.6', '8.3', '9.0', '9.8', '10.6', '11.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('73.0', '7.2', '7.7', '8.4', '9.1', '9.9', '10.8', '11.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('73.5', '7.2', '7.8', '8.5', '9.2', '10.0', '10.9', '11.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('74.0', '7.3', '7.9', '8.6', '9.3', '10.1', '11.0', '12.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('74.5', '7.4', '8.0', '8.7', '9.4', '10.2', '11.2', '12.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('75.0', '7.5', '8.1', '8.8', '9.5', '10.3', '11.3', '12.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('75.5', '7.6', '8.2', '8.8', '9.6', '10.4', '11.4', '12.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('76.0', '7.6', '8.3', '8.9', '9.7', '10.6', '11.5', '12.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('76.5', '7.7', '8.3', '9.0', '9.8', '10.7', '11.6', '12.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('77.0', '7.8', '8.4', '9.1', '9.9', '10.8', '11.7', '12.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('77.5', '7.9', '8.5', '9.2', '10.0', '10.9', '11.9', '13.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('78.0', '7.9', '8.6', '9.3', '10.1', '11.0', '12.0', '13.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('78.5', '8.0', '8.7', '9.4', '10.2', '11.1', '12.1', '13.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('79.0', '8.1', '8.7', '9.5', '10.3', '11.2', '12.2', '13.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('79.5', '8.2', '8.9', '9.5', '10.4', '11.3', '12.3', '13.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('80.0', '8.2', '8.9', '9.6', '10.4', '11.4', '12.4', '13.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('80.5', '8.3', '9.0', '9.7', '10.5', '11.5', '12.5', '13.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('81.0', '8.4', '9.1', '9.8', '10.6', '11.6', '12.6', '13.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('81.5', '8.5', '9.1', '9.9', '10.7', '11.7', '12.7', '13.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('82.0', '8.5', '9.2', '10.0', '10.8', '11.8', '12.8', '14.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('82.5', '8.6', '9.3', '10.1', '10.9', '11.9', '13.0', '14.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('83.0', '8.7', '9.4', '10.2', '11.0', '12.0', '13.1', '14.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('83.5', '8.8', '9.5', '10.3', '11.2', '12.1', '13.2', '14.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('84.0', '8.9', '9.6', '10.4', '11.3', '12.2', '13.3', '14.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('84.5', '9.0', '9.7', '10.5', '11.4', '12.4', '13.5', '14.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('85.0', '9.1', '9.8', '10.6', '11.5', '12.5', '13.6', '14.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('85.5', '9.2', '9.9', '10.7', '11.6', '12.6', '13.7', '15.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('86.0', '9.3', '10.0', '10.8', '11.7', '12.8', '13.9', '15.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('86.5', '9.4', '10.1', '11.0', '11.9', '12.9', '14.0', '15.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('87.0', '9.5', '10.2', '11.1', '12.0', '13.0', '14.2', '15.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('87.5', '9.5', '10.4', '11.2', '12.1', '13.2', '14.3', '15.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('88.0', '9.7', '10.5', '11.3', '12.2', '13.3', '14.5', '15.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('88.5', '9.8', '10.6', '11.4', '12.4', '13.4', '14.6', '15.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('89.0', '9.9', '10.7', '11.5', '12.5', '13.5', '14.7', '16.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('89.5', '10.0', '10.8', '11.6', '12.6', '13.7', '14.9', '16.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('90.0', '10.1', '10.9', '11.8', '12.7', '13.8', '15.0', '16.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('90.5', '10.2', '11.0', '11.9', '12.8', '13.9', '15.1', '16.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('91.0', '10.3', '11.1', '12.0', '13.0', '14.1', '15.3', '16.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('91.5', '10.4', '11.2', '12.1', '13.1', '14.2', '15.4', '16.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('92.0', '10.5', '11.3', '12.2', '13.2', '14.3', '15.6', '17.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('92.5', '10.6', '11.4', '12.3', '13.3', '14.4', '15.7', '17.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('93.0', '10.7', '11.5', '12.4', '13.4', '14.6', '15.8', '17.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('93.5', '10.7', '11.6', '12.5', '13.5', '14.7', '16.0', '17.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('94.0', '10.8', '11.7', '12.6', '13.7', '14.8', '16.1', '17.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('94.5', '10.9', '11.8', '12.7', '13.8', '14.9', '16.3', '17.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('95.0', '11.0', '11.9', '12.8', '13.9', '15.1', '16.4', '17.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('95.5', '11.1', '12.0', '12.9', '14.0', '15.2', '16.5', '18.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('96.0', '11.2', '12.1', '13.1', '14.1', '15.3', '16.7', '18.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('96.5', '11.3', '12.2', '13.2', '14.3', '15.5', '16.8', '18.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('97.0', '11.4', '12.3', '13.3', '14.4', '15.6', '17.0', '18.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('97.5', '11.5', '12.4', '13.4', '14.5', '15.7', '17.1', '18.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('98.0', '11.6', '12.5', '13.5', '14.6', '15.9', '17.3', '18.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('98.5', '11.7', '12.6', '13.6', '14.8', '16.0', '17.5', '19.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('99.0', '11.8', '12.7', '13.7', '14.9', '16.2', '17.6', '19.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('99.5', '11.9', '12.8', '13.9', '15.0', '16.3', '17.8', '19.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('100.0', '12.0', '12.9', '14.0', '15.2', '16.5', '18.0', '19.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('100.5', '12.1', '13.0', '14.1', '15.3', '16.6', '18.1', '19.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('101.0', '12.2', '13.2', '14.2', '15.4', '16.8', '18.3', '20.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('101.5', '12.3', '13.3', '14.4', '15.6', '16.9', '18.5', '20.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('102.0', '12.4', '13.4', '14.5', '15.7', '17.1', '18.7', '20.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('102.5', '12.5', '13.5', '14.6', '15.9', '17.3', '18.8', '20.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('103.0', '12.6', '13.6', '14.8', '16.0', '17.4', '19.0', '20.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('103.5', '12.7', '13.7', '14.9', '16.2', '17.6', '19.2', '21.0');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('104.0', '12.8', '13.9', '15.0', '16.3', '17.8', '19.4', '21.2');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('104.5', '12.9', '14.0', '15.2', '16.2', '17.9', '19.6', '21.5');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('105.0', '13.0', '14.1', '15.3', '16.6', '18.1', '19.8', '21.7');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('105.5', '13.2', '14.2', '15.4', '16.8', '18.3', '20.0', '21.9');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('106.0', '13.3', '14.4', '15.6', '16.9', '18.5', '20.2', '22.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('106.5', '13.4', '14.5', '15.7', '17.1', '18.6', '20.4', '22.4');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('107.0', '13.5', '14.6', '15.9', '17.3', '18.8', '20.6', '22.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('107.5', '13.6', '14.7', '16.0', '17.4', '19.0', '20.8', '22.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('108.0', '13.7', '14.9', '16.2', '17.6', '19.2', '21.0', '23.1');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('108.5', '13.8', '15.0', '16.3', '17.8', '19.4', '21.2', '23.3');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('109.0', '14.0', '15.1', '16.5', '17.8', '19.4', '21.4', '23.6');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('109.5', '14.1', '15.3', '16.6', '18.1', '19.8', '21.7', '23.8');");
        db.execSQL("insert into antropometri_laki_bbtb_0_24 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('110.0', '14.2', '15.4', '16.8', '18.3', '20.0', '21.9', '24.1');");
    }

    // Get One Laki-laki BBTB 0-24 List From Database
    public Cursor getLakiBBTBList_0_24(Float tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_0_24 WHERE laki_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Laki-laki BBTB 0-24 List From Database
    public Cursor getLakiBBTBAllList_0_24() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_0_24", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Laki-laki BBTB 24-60
    public void createLakiBBTB_24_60(SQLiteDatabase db) {

        // Create Table Laki-laki BBTB 24-60
        String create_laki_bbtb_24_60 = "create table antropometri_laki_bbtb_24_60(" +
                "laki_bbtb_ID integer primary key autoincrement, " +
                "laki_bbtb_tb real not null," +
                "laki_bbtb_min3sd real not null, " +
                "laki_bbtb_min2sd real not null, " +
                "laki_bbtb_min1sd real not null, " +
                "laki_bbtb_median real not null, " +
                "laki_bbtb_1sd real not null, " +
                "laki_bbtb_2sd real not null, " +
                "laki_bbtb_3sd real not null " +
                ");";
        db.execSQL(create_laki_bbtb_24_60);

        // Insert Laki-laki BBTB 24-60 After Create Table
        insertLakiBBTB_24_60(db);
    }

    // Insert Laki-laki BBTB 24-60
    public void insertLakiBBTB_24_60(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('65.0', '5.9', '6.3', '6.9', '7.4', '8.1', '8.8', '9.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('65.5', '6.0', '6.4', '7.0', '7.6', '8.2', '8.9', '9.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('66.0', '6.1', '6.5', '7.1', '7.7', '8.3', '9.1', '9.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('66.5', '6.1', '6.6', '7.2', '7.8', '8.5', '9.2', '10.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('67.0', '6.2', '6.7', '7.3', '7.9', '8.6', '9.4', '10.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('67.5', '6.3', '6.8', '7.4', '8.0', '8.7', '9.5', '10.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('68.0', '6.4', '6.9', '7.5', '8.1', '8.8', '9.6', '10.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('68.5', '6.5', '7.0', '7.6', '8.2', '9.0', '9.8', '10.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('69.0', '6.6', '7.1', '7.7', '8.4', '9.1', '9.9', '10.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('69.5', '6.7', '7.2', '7.8', '8.5', '9.2', '10.0', '11.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('70.0', '6.8', '7.3', '7.9', '8.6', '9.3', '10.2', '11.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('70.5', '6.9', '7.4', '8.0', '8.7', '9.5', '10.3', '11.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('71.0', '6.9', '7.5', '8.1', '8.8', '9.6', '10.4', '11.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('71.5', '7.0', '7.6', '8.2', '8.9', '9.7', '10.6', '11.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('72.0', '7.1', '7.7', '8.3', '9.0', '9.8', '10.7', '11.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('72.5', '7.2', '7.8', '8.4', '9.1', '9.9', '10.8', '12.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('73.0', '7.3', '7.9', '8.5', '9.2', '10.0', '11.0', '12.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('73.5', '7.4', '7.9', '8.6', '9.3', '10.2', '11.1', '12.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('74.0', '7.4', '8.0', '8.7', '9.4', '10.3', '11.2', '12.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('74.5', '7.5', '8.1', '8.8', '9.5', '10.4', '11.3', '12.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('75.0', '7.6', '8.2', '8.9', '9.6', '10.5', '11.4', '12.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('75.5', '7.7', '8.3', '9.0', '9.7', '10.6', '11.6', '12.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('76.0', '7.7', '8.4', '9.1', '9.8', '10.7', '11.7', '12.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('76.5', '7.8', '8.5', '9.2', '9.9', '10.8', '11.8', '12.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('77.0', '7.9', '8.5', '9.2', '10.0', '10.9', '11.9', '13.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('77.5', '8.0', '8.6', '9.3', '10.1', '11.0', '12.0', '13.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('78.0', '8.0', '8.7', '9.4', '10.2', '11.1', '12.1', '13.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('78.5', '8.1', '8.8', '9.5', '10.3', '11.2', '12.2', '13.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('79.0', '8.2', '8.8', '9.6', '10.4', '11.3', '12.3', '13.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('79.5', '8.3', '8.9', '9.7', '10.5', '11.4', '12.4', '13.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('80.0', '8.3', '9.0', '9.7', '10.6', '11.5', '12.6', '13.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('80.5', '8.4', '9.1', '9.8', '10.7', '11.6', '12.7', '13.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('81.0', '8.5', '9.2', '9.9', '10.8', '11.7', '12.8', '14.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('81.5', '8.6', '9.3', '10.0', '10.9', '11.8', '12.9', '14.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('82.0', '8.7', '9.3', '10.1', '11.0', '11.9', '13.0', '14.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('82.5', '8.7', '9.4', '10.2', '11.1', '12.1', '13.1', '14.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('83.0', '8.8', '9.5', '10.3', '11.2', '12.2', '13.3', '14.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('83.5', '8.9', '9.6', '10.4', '11.3', '12.3', '13.4', '14.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('84.0', '9.0', '9.7', '10.5', '11.4', '12.4', '13.5', '14.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('84.5', '9.1', '9.9', '10.7', '11.5', '12.5', '13.7', '14.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('85.0', '9.2', '10.0', '10.8', '11.7', '12.7', '13.8', '15.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('85.5', '9.3', '10.1', '10.9', '11.8', '12.8', '13.9', '15.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('86.0', '9.4', '10.2', '11.0', '11.9', '12.9', '14.1', '15.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('86.5', '9.5', '10.3', '11.1', '12.0', '13.1', '14.2', '15.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('87.0', '9.6', '10.4', '11.2', '12.2', '13.2', '14.4', '15.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('87.5', '9.7', '10.5', '11.3', '12.3', '13.3', '14.5', '15.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('88.0', '9.8', '10.6', '11.5', '12.4', '13.5', '14.7', '16.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('88.5', '9.9', '10.7', '11.6', '12.5', '13.6', '14.8', '16.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('89.0', '10.0', '10.8', '11.7', '12.6', '13.7', '14.9', '16.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('89.5', '10.1', '10.9', '11.8', '12.8', '13.9', '15.1', '16.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('90.0', '10.2', '11.0', '11.9', '12.9', '14.0', '15.2', '16.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('90.5', '10.3', '11.1', '12.0', '13.0', '14.1', '15.3', '16.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('91.0', '10.4', '11.2', '12.1', '13.1', '14.2', '15.5', '16.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('91.5', '10.5', '11.3', '12.2', '13.2', '14.4', '15.6', '17.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('92.0', '10.6', '11.4', '12.3', '13.4', '14.5', '15.8', '17.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('92.5', '10.7', '11.5', '12.4', '13.5', '14.6', '15.9', '17.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('93.0', '10.8', '11.6', '12.6', '13.6', '14.7', '16.0', '17.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('93.5', '10.9', '11.7', '12.7', '13.7', '14.9', '16.2', '17.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('94.0', '11.0', '11.8', '12.8', '13.8', '15.0', '16.3', '17.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('94.5', '11.1', '11.9', '12.9', '13.9', '15.1', '16.5', '17.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('95.0', '11.1', '12.0', '13.0', '14.1', '15.3', '16.6', '18.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('95.5', '11.2', '12.1', '13.1', '14.2', '15.4', '16.7', '18.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('96.0', '11.3', '12.2', '13.2', '14.3', '15.5', '16.9', '18.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('96.5', '11.4', '12.3', '13.3', '14.4', '15.7', '17.0', '18.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('97.0', '11.5', '12.4', '13.4', '14.6', '15.8', '17.2', '18.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('97.5', '11.6', '12.5', '13.6', '14.7', '15.9', '17.4', '18.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('98.0', '11.7', '12.6', '13.7', '14.8', '16.1', '17.5', '19.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('98.5', '11.8', '12.8', '13.8', '14.9', '16.2', '17.7', '19.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('99.0', '11.9', '12.9', '13.9', '15.1', '16.4', '17.9', '19.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('99.5', '12.0', '13.0', '14.0', '15.2', '16.5', '18.0', '19.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('100.0', '12.1', '13.1', '14.2', '15.4', '16.7', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('100.5', '12.2', '13.2', '14.3', '15.5', '16.9', '18.4', '20.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('101.0', '12.3', '13.3', '14.4', '15.6', '17.0', '18.5', '20.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('101.5', '12.4', '13.4', '14.5', '15.8', '17.2', '18.7', '20.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('102.0', '12.5', '13.6', '14.7', '15.9', '17.3', '18.9', '20.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('102.5', '12.6', '13.7', '14.8', '16.1', '17.5', '19.1', '20.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('103.0', '12.8', '13.8', '14.9', '16.2', '17.7', '19.3', '21.1');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('103.5', '12.9', '13.9', '15.1', '16.4', '17.8', '19.5', '21.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('104.0', '13.0', '14.0', '15.2', '16.5', '18.0', '19.7', '21.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('104.5', '13.1', '14.2', '15.4', '16.7', '18.2', '19.9', '21.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('105.0', '13.2', '14.3', '15.5', '16.8', '18.4', '20.1', '22.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('106.0', '13.4', '14.5', '15.8', '17.2', '18.7', '20.5', '22.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('106.5', '13.5', '14.7', '15.9', '17.3', '18.9', '20.7', '22.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('107.0', '13.7', '14.8', '16.1', '17.5', '19.1', '20.9', '22.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('107.5', '13.8', '14.9', '16.2', '17.7', '19.3', '21.1', '23.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('108.0', '13.9', '15.1', '16.4', '17.8', '19.5', '21.3', '23.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('108.5', '14.0', '15.2', '16.5', '18.0', '19.7', '21.5', '23.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('109.0', '14.1', '15.3', '16.7', '18.2', '19.8', '21.8', '23.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('109.5', '14.3', '15.5', '16.8', '18.3', '20.0', '22.0', '24.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('110.0', '14.4', '15.6', '17.0', '18.5', '20.2', '22.2', '24.4');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('110.5', '14.5', '15.8', '17.1', '18.7', '20.4', '22.4', '24.7');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('111.0', '14.6', '15.9', '17.3', '18.9', '20.7', '22.7', '25.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('111.5', '14.8', '16.0', '17.5', '19.1', '20.9', '22.9', '25.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('112.0', '14.9', '16.2', '17.6', '19.2', '21.1', '23.1', '25.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('112.5', '15.0', '16.3', '17.8', '19.4', '21.3', '23.4', '25.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('113.0', '15.2', '16.5', '18.0', '19.6', '21.5', '23.6', '26.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('113.5', '15.3', '16.5', '18.1', '19.8', '21.7', '23.9', '26.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('114.0', '15.4', '16.8', '18.3', '20.0', '21.9', '24.1', '26.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('114.5', '15.6', '16.9', '18.5', '20.2', '22.1', '24.4', '26.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('115.0', '15.7', '17.1', '18.6', '20.4', '22.4', '24.6', '27.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('115.5', '15.8', '17.2', '18.8', '20.6', '22.6', '24.9', '27.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('116.0', '16.0', '17.4', '19.0', '20.8', '22.8', '25.1', '27.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('116.5', '16.1', '17.5', '19.2', '21.0', '23.0', '25.4', '28.0');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('117.0', '16.2', '17.7', '19.3', '21.2', '23.3', '25.6', '28.3');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('117.5', '16.4', '17.9', '19.5', '21.4', '23.5', '25.9', '28.6');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('118.0', '16.5', '18.0', '19.7', '21.6', '23.7', '26.1', '28.9');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('118.5', '16.7', '18.2', '19.9', '21.8', '23.9', '26.4', '29.2');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('119.0', '16.8', '18.3', '20.0', '22.0', '24.1', '26.6', '29.5');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('119.5', '16.9', '18.5', '20.2', '22.2', '24.4', '26.9', '29.8');");
        db.execSQL("insert into antropometri_laki_bbtb_24_60 (laki_bbtb_tb, laki_bbtb_min3sd, laki_bbtb_min2sd, laki_bbtb_min1sd, laki_bbtb_median, laki_bbtb_1sd, laki_bbtb_2sd, laki_bbtb_3sd) values " +
                "('120.0', '17.1', '18.6', '20.4', '22.4', '24.6', '27.2', '30.1');");
    }

    // Get One Laki-laki BBTB 24-60 List From Database
    public Cursor getLakiBBTBList_24_60(Float tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_24_60 WHERE laki_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Laki-laki BBTB 24-60 List From Database
    public Cursor getLakiBBTBAllList_24_60() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_24_60", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Laki-laki IMTU
    public void createLakiIMTU(SQLiteDatabase db) {

        // Create Table Laki-laki IMTU
        String create_laki_imtu = "create table antropometri_laki_imtu (" +
                "laki_imtu_ID integer primary key autoincrement, " +
                "laki_imtu_usia real not null," +
                "laki_imtu_min3sd real not null, " +
                "laki_imtu_min2sd real not null, " +
                "laki_imtu_min1sd real not null, " +
                "laki_imtu_median real not null, " +
                "laki_imtu_1sd real not null, " +
                "laki_imtu_2sd real not null, " +
                "laki_imtu_3sd real not null " +
                ");";
        db.execSQL(create_laki_imtu);

        // Insert Laki-laki IMTU After Create Table
        insertLakiIMTU(db);
    }

    // Insert Laki-laki IMTU
    public void insertLakiIMTU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('0', '10.2', '11.1', '12.2', '13.4', '14.8', '16.3', '18.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('1', '11.3', '12.4', '13.6', '14.9', '16.3', '17.8', '19.4');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('2', '12.5', '13.7', '15.0', '16.3', '17.8', '19.4', '21.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('3', '13.1', '14.3', '15.5', '16.9', '18.4', '20.0', '21.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('4', '13.4', '14.5', '15.8', '17.2', '18.7', '20.3', '22.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('5', '13.5', '14.7', '15.9', '17.3', '18.8', '20.5', '22.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('6', '13.6', '14.7', '16.0', '17.3', '18.8', '20.5', '22.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('7', '13.7', '14.8', '16.0', '17.3', '18.8', '20.5', '22.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('8', '13.6', '14.7', '15.9', '17.3', '18.7', '20.4', '22.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('9', '13.6', '14.7', '15.8', '17.2', '18.6', '20.3', '22.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('10', '13.5', '14.6', '15.7', '17.0', '18.5', '20.1', '22.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('11', '13.4', '14.5', '15.6', '16.9', '18.4', '20.0', '21.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('12', '13.4', '14.4', '15.5', '16.8', '18.2', '19.8', '21.6');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('13', '13.3', '14.3', '15.4', '16.7', '18.1', '19.7', '21.5');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('14', '13.2', '14.2', '15.3', '16.6', '18.0', '19.5', '21.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('15', '13.1', '14.1', '15.2', '16.4', '17.6', '19.1', '21.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('16', '13.1', '14.0', '15.1', '16.3', '17.7', '19.3', '21.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('17', '13.0', '13.9', '15.0', '16.2', '17.6', '19.1', '20.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('18', '12.9', '13.9', '14.9', '16.1', '17.5', '19.0', '20.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('19', '12.9', '13.8', '14.9', '16.1', '17.4', '18.9', '20.7');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('20', '12.8', '13.7', '14.8', '16.0', '17.3', '18.8', '20.6');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('21', '12.8', '13.7', '14.7', '15.9', '17.2', '18.7', '20.5');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('22', '12.7', '13.6', '14.7', '15.8', '17.2', '18.7', '20.4');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('23', '12.7', '13.6', '14.6', '15.8', '17.1', '18.6', '20.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('24', '12.9', '13.8', '14.8', '16.0', '17.3', '18.9', '20.6');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('25', '12.8', '13.8', '14.8', '16.0', '17.3', '18.8', '20.5');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('26', '12.8', '13.7', '14.7', '15.9', '17.3', '18.8', '20.5');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('27', '12.7', '13.7', '14.7', '15.9', '17.2', '18.7', '20.4');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('28', '12.7', '13.6', '14.7', '15.9', '17.2', '18.7', '20.4');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('29', '12.7', '13.6', '14.7', '15.8', '17.1', '18.6', '20.3');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('30', '12.6', '13.6', '14.6', '15.8', '17.1', '18.6', '20.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('31', '12.6', '13.5', '14.6', '15.8', '17.1', '18.6', '20.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('32', '12.5', '13.5', '14.6', '15.7', '17.0', '18.5', '20.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('33', '12.5', '13.4', '14.5', '15.7', '17.0', '18.4', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('34', '12.5', '13.4', '14.5', '15.6', '16.9', '18.4', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('35', '12.4', '13.4', '14.5', '15.6', '16.9', '18.4', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('36', '12.4', '13.4', '14.4', '15.6', '16.9', '18.4', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('37', '12.4', '13.3', '14.4', '15.6', '16.9', '18.3', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('38', '12.3', '13.3', '14.4', '15.5', '16.8', '18.3', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('39', '12.3', '13.3', '14.3', '15.5', '16.8', '18.3', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('40', '12.3', '13.2', '14.3', '15.5', '16.8', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('41', '12.2', '13.2', '14.3', '15.5', '16.8', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('42', '12.2', '13.2', '14.3', '15.4', '16.8', '18.2', '19.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('43', '12.2', '13.2', '14.2', '15.4', '16.7', '18.2', '19.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('44', '12.2', '13.1', '14.2', '15.4', '16.7', '18.2', '19.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('45', '12.2', '13.1', '14.2', '15.4', '16.7', '18.2', '19.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('46', '12.1', '13.1', '14.2', '15.4', '16.7', '18.2', '19.8');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('47', '12.1', '13.1', '14.2', '15.3', '16.7', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('48', '12.1', '13.1', '14.1', '15.3', '16.7', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('49', '12.1', '13.0', '14.1', '15.3', '16.7', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('50', '12.1', '13.0', '14.1', '15.3', '16.7', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('51', '12.1', '13.0', '14.1', '15.3', '16.6', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('52', '12.0', '13.0', '14.1', '15.3', '16.6', '18.2', '19.9');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('53', '12.0', '13.0', '14.1', '15.3', '16.6', '18.2', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('54', '12.0', '13.0', '14.0', '15.3', '16.6', '18.2', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('55', '12.0', '13.0', '14.0', '15.3', '16.6', '18.2', '20.0');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('56', '12.0', '12.9', '14.0', '15.2', '16.6', '18.2', '20.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('57', '12.0', '12.9', '14.0', '15.3', '16.6', '18.3', '20.1');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('58', '12.0', '12.9', '14.0', '15.2', '16.6', '18.3', '20.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('59', '12.0', '12.9', '14.0', '15.2', '16.6', '18.3', '20.2');");
        db.execSQL("insert into antropometri_laki_imtu (laki_imtu_usia, laki_imtu_min3sd, laki_imtu_min2sd, laki_imtu_min1sd, laki_imtu_median, laki_imtu_1sd, laki_imtu_2sd, laki_imtu_3sd) values " +
                "('60', '12.0', '12.9', '14.0', '15.2', '16.6', '18.3', '20.3');");
    }

    // Get One Laki-laki IMTU List From Database
    public Cursor getLakiIMTUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_imtu WHERE laki_imtu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Laki-laki IMTU List From Database
    public Cursor getLakiIMTUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_imtu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Perempuan BBU
    public void createPerempuanBBU(SQLiteDatabase db) {

        // Create Table Perempuan BBU
        String create_perempuan_bbu = "create table antropometri_perempuan_bbu (" +
                "perempuan_bbu_ID integer primary key autoincrement, " +
                "perempuan_bbu_usia real not null," +
                "perempuan_bbu_min3sd real not null, " +
                "perempuan_bbu_min2sd real not null, " +
                "perempuan_bbu_min1sd real not null, " +
                "perempuan_bbu_median real not null, " +
                "perempuan_bbu_1sd real not null, " +
                "perempuan_bbu_2sd real not null, " +
                "perempuan_bbu_3sd real not null " +
                ");";
        db.execSQL(create_perempuan_bbu);

        // Insert Perempuan BBU After Create Table
        insertPerempuanBBU(db);
    }

    // Insert Perempuan BBU
    public void insertPerempuanBBU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('0', '2.0', '2.4', '2.8', '3.2', '3.7', '4.2', '4.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('1', '2.7', '3.2', '3.6', '4.2', '4.8', '5.5', '6.2');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('2', '3.4', '3.9', '4.5', '5.1', '5.8', '6.6', '7.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('3', '4.0', '4.5', '5.2', '5.8', '6.6', '7.5', '8.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('4', '4.4', '5.0', '5.7', '6.4', '7.3', '8.2', '9.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('5', '4.8', '5.4', '6.1', '6.9', '7.8', '8.8', '10.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('6', '5.1', '5.7', '6.5', '7.3', '8.2', '9.3', '10.6');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('7', '5.3', '6.0', '6.8', '7.6', '8.6', '9.8', '11.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('8', '5.6', '6.3', '7.0', '7.9', '9.0', '10.2', '11.6');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('9', '5.8', '6.5', '7.3', '8.2', '9.3', '10.5', '12.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('10', '5.9', '6.7', '7.5', '8.5', '9.6', '10.9', '12.4');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('11', '6.1', '6.9', '7.7', '8.7', '9.9', '11.2', '12.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('12', '6.3', '7.0', '7.9', '8.9', '10.1', '11.5', '13.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('13', '6.4', '7.2', '8.1', '9.2', '10.4', '11.8', '13.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('14', '6.6', '7.4', '8.3', '9.4', '10.6', '12.1', '13.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('15', '6.7', '7.6', '8.5', '9.6', '10.9', '12.4', '14.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('16', '6.9', '7.7', '8.7', '9.8', '11.1', '12.6', '14.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('17', '7.0', '7.9', '8.9', '10.0', '11.4', '12.9', '14.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('18', '7.2', '8.1', '9.1', '10.2', '11.6', '13.2', '15.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('19', '7.3', '8.2', '9.2', '10.4', '11.8', '13.5', '15.4');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('20', '7.5', '8.4', '9.4', '10.6', '12.1', '13.7', '15.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('21', '7.6', '8.6', '9.6', '10.9', '12.3', '14.0', '16.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('22', '7.8', '8.7', '9.8', '11.1', '12.5', '14.3', '16.4');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('23', '7.9', '8.9', '10.0', '11.3', '12.8', '14.6', '16.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('24', '8.1', '9.0', '10.2', '11.5', '13.0', '14.8', '17.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('25', '8.2', '9.2', '10.3', '11.7', '13.3', '15.1', '17.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('26', '8.4', '9.4', '10.5', '11.9', '13.5', '15.4', '17.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('27', '8.5', '9.5', '10.7', '12.1', '13.7', '15.7', '18.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('28', '8.6', '9.7', '10.9', '12.3', '14.0', '16.0', '18.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('29', '8.8', '9.8', '11.1', '12.5', '14.2', '16.2', '18.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('30', '8.9', '10.0', '11.2', '12.7', '14.4', '16.5', '19.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('31', '9.0', '10.1', '11.4', '12.9', '14.7', '16.8', '19.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('32', '9.1', '10.3', '11.6', '13.1', '15.1', '17.3', '20.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('34', '9.4', '10.5', '11.9', '13.5', '15.4', '17.6', '20.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('35', '9.5', '10.7', '12.0', '13.7', '15.6', '17.9', '20.6');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('36', '9.6', '10.8', '12.2', '13.9', '15.8', '18.1', '20.9');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('37', '9.7', '10.9', '12.4', '14.0', '16.0', '18.4', '21.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('38', '9.8', '11.1', '12.5', '14.2', '16.3', '18.7', '21.6');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('39', '9.9', '11.2', '12.7', '14.4', '16.5', '19.0', '22.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('40', '10.1', '11.3', '12.8', '14.6', '16.7', '19.2', '22.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('41', '10.2', '11.5', '13.0', '14.8', '16.9', '19.5', '22.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('42', '10.3', '11.6', '13.1', '15.0', '17.2', '19.8', '23.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('43', '10.4', '11.7', '13.3', '15.2', '17.4', '20.1', '23.4');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('44', '10.5', '11.8', '13.4', '15.3', '17.6', '20.4', '23.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('45', '10.6', '12.0', '13.6', '15.5', '17.8', '20.7', '24.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('46', '10.7', '12.1', '13.7', '15.7', '18.1', '20.9', '24.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('47', '10.8', '12.2', '13.9', '15.9', '18.3', '21.2', '24.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('48', '10.9', '12.3', '14.0', '16.1', '18.5', '21.5', '25.2');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('49', '11.0', '12.4', '14.2', '16.3', '18.8', '21.8', '25.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('50', '11.1', '12.6', '14.3', '16.4', '19.0', '22.1', '25.9');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('51', '11.2', '12.7', '14.5', '16.6', '19.2', '22.4', '26.3');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('52', '11.3', '12.8', '14.6', '16.8', '19.4', '22.6', '26.6');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('53', '11.4', '12.9', '14.8', '17.0', '19.7', '22.9', '27.0');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('54', '11.5', '13.0', '14.9', '17.2', '19.9', '23.2', '27.4');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('55', '11.6', '13.2', '15.1', '17.3', '20.1', '23.5', '27.7');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('56', '11.7', '13.3', '15.2', '17.5', '20.3', '23.8', '28.1');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('57', '11.8', '13.4', '15.3', '17.7', '20.6', '24.1', '28.5');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('58', '11.9', '13.5', '15.5', '17.9', '20.8', '24.4', '28.8');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('59', '12.0', '13.6', '15.6', '18.0', '21.0', '24.6', '29.2');");
        db.execSQL("insert into antropometri_perempuan_bbu (perempuan_bbu_usia, perempuan_bbu_min3sd, perempuan_bbu_min2sd, perempuan_bbu_min1sd, perempuan_bbu_median, perempuan_bbu_1sd, perempuan_bbu_2sd, perempuan_bbu_3sd) values " +
                "('60', '12.1', '13.7', '15.8', '18.2', '21.2', '24.9', '29.5');");
    }

    // Get One Perempuan BBU List From Database
    public Cursor getPerempuanBBUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbu WHERE perempuan_bbu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Perempuan BBU List From Database
    public Cursor getPerempuanBBUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Perempuan TBU
    public void createPerempuanTBU(SQLiteDatabase db) {

        // Create Table Perempuan TBU
        String create_perempuan_tbu = "create table antropometri_perempuan_tbu (" +
                "perempuan_tbu_ID integer primary key autoincrement, " +
                "perempuan_tbu_usia real not null," +
                "perempuan_tbu_min3sd real not null, " +
                "perempuan_tbu_min2sd real not null, " +
                "perempuan_tbu_min1sd real not null, " +
                "perempuan_tbu_median real not null, " +
                "perempuan_tbu_1sd real not null, " +
                "perempuan_tbu_2sd real not null, " +
                "perempuan_tbu_3sd real not null " +
                ");";
        db.execSQL(create_perempuan_tbu);

        // Insert Perempuan TBU After Create Table
        insertPerempuanTBU(db);
    }

    // Insert Perempuan TBU
    public void insertPerempuanTBU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('0', '43.6', '45.4', '47.3', '49.1', '51.0', '52.9', '54.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('1', '47.8', '49.8', '51.7', '53.7', '55.6', '57.6', '59.5');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('2', '51.0', '53.0', '55.0', '57.1', '59.1', '61.1', '63.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('3', '53.5', '55.6', '57.7', '59.8', '61.9', '64.0', '66.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('4', '55.6', '57.8', '59.9', '62.1', '64.3', '66.4', '68.6');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('5', '57.4', '59.6', '61.8', '64.0', '66.2', '68.5', '70.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('6', '58.9', '61.2', '63.5', '65.7', '68.0', '70.3', '72.5');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('7', '60.3', '62.7', '65.0', '67.3', '69.6', '71.9', '74.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('8', '61.7', '64.0', '66.4', '68.7', '71.1', '73.5', '75.8');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('9', '62.9', '65.3', '67.7', '70.1', '72.6', '75.0', '77.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('10', '64.1', '66.5', '69.0', '71.5', '73.9', '76.4', '78.9');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('11', '65.2', '67.7', '70.3', '72.8', '75.3', '77.8', '80.3');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('12', '66.3', '68.9', '71.4', '74.0', '76.6', '79.2', '81.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('13', '67.3', '70.0', '72.6', '75.2', '77.8', '80.5', '83.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('14', '68.3', '71.0', '73.7', '76.4', '79.1', '81.7', '84.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('15', '69.3', '72.0', '74.8', '77.5', '80.2', '83.0', '85.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('16', '70.2', '73.0', '75.8', '78.6', '81.4', '84.2', '87.0');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('17', '71.1', '74.0', '76.8', '79.7', '82.5', '85.4', '88.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('18', '72.0', '74.9', '77.8', '80.7', '83.6', '86.5', '89.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('19', '72.8', '75.8', '78.8', '81.7', '84.7', '87.6', '90.6');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('20', '73.7', '76.7', '79.7', '82.7', '85.7', '88.7', '91.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('21', '74.5', '77.5', '80.6', '83.7', '86.7', '89.8', '92.9');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('22', '75.2', '78.4', '81.5', '84.6', '87.7', '90.8', '94.0');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('23', '76.0', '79.2', '82.3', '85.5', '88.7', '91.9', '95.0');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('24', '76.0', '79.3', '82.5', '85.7', '88.9', '92.2', '95.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('25', '76.8', '80.0', '83.3', '86.6', '89.9', '93.1', '96.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('26', '77.5', '80.8', '84.1', '87.4', '90.8', '94.1', '97.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('27', '78.1', '81.5', '84.9', '88.3', '91.7', '95.0', '98.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('28', '78.8', '82.2', '85.7', '89.1', '92.5', '96.0', '99.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('29', '79.5', '82.9', '86.4', '89.9', '93.4', '96.9', '100.3');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('30', '80.1', '83.6', '87.1', '90.7', '94.2', '97.7', '101.3');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('31', '80.7', '84.3', '87.9', '91.4', '95.0', '98.6', '102.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('32', '81.3', '84.9', '88.6', '92.2', '95.8', '99.4', '103.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('33', '81.9', '85.6', '89.3', '92.9', '96.6', '100.3', '103.9');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('34', '82.5', '86.2', '89.9', '93.6', '97.4', '101.1', '104.8');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('35', '83.1', '86.8', '90.6', '94.4', '98.1', '101.9', '105.6');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('36', '83.6', '87.4', '91.2', '95.1', '98.9', '102.7', '106.5');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('37', '84.2', '88.0', '91.9', '95.7', '99.6', '103.4', '107.3');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('38', '84.7', '88.6', '92.5', '96.4', '100.3', '104.2', '108.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('39', '85.3', '89.2', '93.1', '97.1', '101.0', '105.0', '106.9');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('40', '85.8', '89.8', '93.8', '97.7', '101.7', '105.7', '109.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('41', '86.3', '90.4', '94.4', '98.4', '102.4', '106.4', '110.5');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('42', '86.8', '90.9', '95.0', '99.0', '103.1', '107.2', '111.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('43', '87.4', '91.5', '95.6', '99.7', '103.8', '107.9', '112.0');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('44', '87.9', '92.0', '96.2', '100.3', '104.5', '108.6', '112.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('45', '88.4', '92.5', '96.7', '100.9', '105.1', '109.3', '113.5');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('46', '88.9', '93.1', '97.3', '101.5', '105.8', '110.0', '114.2');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('47', '89.3', '93.6', '97.9', '102.1', '106.4', '110.7', '114.9');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('48', '89.8', '94.1', '98.4', '102.7', '107.0', '111.3', '115.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('49', '90.3', '94.6', '99.0', '103.3', '107.7', '112.0', '116.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('50', '90.7', '95.1', '99.5', '103.9', '108.3', '112.7', '117.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('51', '91.2', '95.6', '100.1', '104.5', '108.9', '113.3', '117.7');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('52', '91.7', '96.1', '100.6', '105.0', '109.5', '114.0', '118.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('53', '92.1', '96.6', '101.1', '105.6', '110.1', '114.6', '119.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('54', '92.6', '97.1', '101.6', '106.2', '110.7', '115.2', '119.8');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('55', '93.0', '97.6', '102.2', '106.7', '111.3', '115.9', '120.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('56', '93.4', '98.1', '102.7', '107.3', '111.9', '116.5', '121.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('57', '93.9', '98.5', '103.2', '107.8', '112.5', '117.1', '121.8');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('58', '94.3', '99.0', '103.7', '108.4', '113.0', '117.7', '122.4');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('59', '94.7', '99.5', '104.2', '108.9', '113.6', '118.3', '123.1');");
        db.execSQL("insert into antropometri_perempuan_tbu (perempuan_tbu_usia, perempuan_tbu_min3sd, perempuan_tbu_min2sd, perempuan_tbu_min1sd, perempuan_tbu_median, perempuan_tbu_1sd, perempuan_tbu_2sd, perempuan_tbu_3sd) values " +
                "('60', '95.2', '99.9', '104.7', '109.4', '114.2', '118.9', '123.7');");
    }

    // Get One Perempuan TBU List From Database
    public Cursor getPerempuanTBUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_tbu WHERE perempuan_tbu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Perempuan TBU List From Database
    public Cursor getPerempuanTBUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_tbu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Perempuan BBTB 0-24
    public void createPerempuanBBTB_0_24(SQLiteDatabase db) {

        // Create Table Perempuan BBTB 0-24
        String create_perempuan_bbtb_0_24 = "create table antropometri_perempuan_bbtb_0_24 (" +
                "perempuan_bbtb_ID integer primary key autoincrement, " +
                "perempuan_bbtb_tb real not null," +
                "perempuan_bbtb_min3sd real not null, " +
                "perempuan_bbtb_min2sd real not null, " +
                "perempuan_bbtb_min1sd real not null, " +
                "perempuan_bbtb_median real not null, " +
                "perempuan_bbtb_1sd real not null, " +
                "perempuan_bbtb_2sd real not null, " +
                "perempuan_bbtb_3sd real not null " +
                ");";
        db.execSQL(create_perempuan_bbtb_0_24);

        // Insert Perempuan BBTB 0-24 After Create Table
        insertPerempuanBBTB_0_24(db);
    }

    // Insert Perempuan BBTB 0-24
    public void insertPerempuanBBTB_0_24(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('45.0', '1.9', '2.1', '2.3', '2.5', '2.7', '3.0', '3.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('45.5', '2.0', '2.1', '2.3', '2.5', '2.8', '3.1', '3.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('46.0', '2.0', '2.2', '2.4', '2.6', '2.9', '3.2', '3.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('46.5', '2.1', '2.3', '2.5', '2.7', '3.0', '3.3', '3.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('47.0', '2.2', '2.4', '2.6', '2.9', '3.2', '3.3', '3.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('47.5', '2.2', '2.4', '2.6', '2.9', '3.0', '3.4', '3.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('48.0', '2.3', '2.5', '2.7', '3.0', '3.3', '3.6', '3.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('48.5', '2.4', '2.6', '2.8', '3.1', '3.4', '3.7', '4.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('49.0', '2.4', '2.6', '2.9', '3.2', '3.5', '3.8', '4.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('49.5', '2.5', '2.7', '3.0', '3.3', '3.6', '3.9', '4.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('50.0', '2.6', '2.8', '3.1', '3.4', '3.7', '4.0', '4.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('50.5', '2.7', '2.9', '3.2', '3.5', '3.8', '4.2', '4.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('51.0', '2.8', '3.0', '3.3', '3.6', '3.9', '4.3', '4.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('51.5', '2.8', '3.1', '3.4', '3.7', '4.0', '4.4', '4.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('52.0', '2.9', '3.2', '3.5', '3.8', '4.2', '4.6', '5.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('52.5', '3.0', '3.3', '3.6', '3.9', '4.3', '4.7', '5.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('53.0', '3.1', '3.4', '3.7', '4.0', '4.4', '4.9', '5.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('53.5', '3.2', '3.5', '3.8', '4.2', '4.6', '5.0', '5.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('54.0', '3.3', '3.6', '3.9', '4.3', '4.7', '5.2', '5.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('54.5', '3.4', '3.7', '4.0', '4.4', '4.8', '5.3', '5.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('55.0', '3.5', '3.8', '4.2', '4.5', '5.0', '5.5', '6.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('55.5', '3.6', '3.9', '4.3', '4.7', '5.1', '5.7', '6.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('56.0', '3.7', '4.0', '4.4', '4.8', '5.3', '5.8', '6.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('56.5', '3.8', '4.1', '4.5', '5.0', '5.4', '6.0', '6.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('57.0', '3.9', '4.3', '4.6', '5.1', '5.6', '6.1', '6.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('57.5', '4.0', '4.4', '4.8', '5.2', '5.7', '6.3', '7.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('58.0', '4.1', '4.5', '4.9', '5.4', '5.9', '6.5', '7.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('58.5', '4.2', '4.6', '5.0', '5.5', '6.0', '6.6', '7.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('59.0', '4.3', '4.7', '5.1', '5.6', '6.2', '6.8', '7.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('59.5', '4.4', '4.8', '5.3', '5.7', '6.3', '6.9', '7.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('60.0', '4.5', '4.9', '5.4', '5.9', '6.4', '7.1', '7.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('60.5', '4.6', '5.0', '5.5', '6.0', '6.6', '7.3', '8.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('61.0', '4.7', '5.1', '5.6', '6.1', '6.7', '7.4', '8.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('61.5', '4.8', '5.2', '5.7', '6.3', '6.9', '7.6', '8.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('62.0', '4.9', '5.3', '5.8', '6.4', '7.0', '7.7', '8.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('62.5', '5.0', '5.4', '5.9', '6.5', '7.1', '7.8', '8.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('63.0', '5.1', '5.5', '6.0', '6.6', '7.3', '8.0', '8.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('63.5', '5.2', '5.6', '6.2', '6.7', '7.4', '8.1', '9.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('64.0', '5.3', '5.7', '6.3', '6.9', '7.5', '8.3', '9.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('64.5', '5.4', '5.8', '6.4', '7.0', '7.6', '8.4', '9.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('65.0', '5.5', '5.9', '6.5', '7.1', '7.8', '8.6', '9.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('65.5', '5.5', '6.0', '6.6', '7.2', '7.9', '8.7', '9.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('66.0', '5.6', '6.1', '6.7', '7.3', '8.0', '8.8', '9.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('66.5', '5.7', '6.2', '6.8', '7.4', '8.1', '9.0', '9.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('67.0', '5.8', '6.3', '6.9', '7.5', '8.3', '9.1', '10.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('67.5', '5.9', '6.4', '7.0', '7.6', '8.4', '9.2', '10.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('68.0', '6.0', '6.5', '7.1', '7.7', '8.5', '9.4', '10.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('68.5', '6.1', '6.6', '7.2', '7.9', '8.6', '9.5', '10.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('69.0', '6.1', '6.7', '7.3', '8.0', '8.7', '9.6', '10.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('69.5', '6.2', '6.8', '7.4', '8.1', '8.8', '9.7', '10.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('70.0', '6.3', '6.9', '7.5', '8.2', '9.0', '9.9', '10.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('70.5', '6.4', '6.9', '7.6', '8.3', '9.1', '10.0', '11.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('71.0', '6.5', '7.0', '7.7', '8.4', '9.2', '10.1', '11.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('71.5', '6.5', '7.1', '7.7', '8.5', '9.3', '10.2', '11.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('72.0', '6.6', '7.2', '7.8', '8.6', '9.4', '10.3', '11.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('72.5', '6.7', '7.3', '7.9', '8.7', '9.5', '10.5', '11.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('73.0', '6.8', '7.4', '8.0', '8.8', '9.6', '10.6', '11.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('73.5', '6.9', '7.4', '8.1', '8.9', '9.7', '10.7', '11.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('74.0', '6.9', '7.5', '8.2', '9.0', '9.8', '10.8', '11.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('74.5', '7.0', '7.6', '8.3', '9.1', '9.9', '10.9', '12.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('75.0', '7.1', '7.7', '8.4', '9.1', '10.0', '11.0', '12.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('75.5', '7.1', '7.8', '8.5', '9.2', '10.1', '11.1', '12.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('76.0', '7.2', '7.8', '8.5', '9.3', '10.2', '11.2', '12.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('76.5', '7.3', '7.9', '8.6', '9.4', '10.3', '11.4', '12.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('77.0', '7.4', '8.0', '8.7', '9.5', '10.4', '11.5', '12.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('77.5', '7.4', '8.1', '8.8', '9.6', '10.5', '11.6', '12.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('78.0', '7.4', '8.2', '8.9', '9.7', '10.6', '11.7', '12.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('78.5', '7.6', '8.2', '9.0', '9.8', '10.7', '11.8', '13.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('79.0', '7.7', '8.3', '9.1', '9.9', '10.8', '11.9', '13.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('79.5', '7.7', '8.4', '9.1', '10.0', '10.9', '12.0', '13.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('80.0', '7.8', '8.5', '9.2', '10.1', '11.0', '12.1', '13.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('80.5', '7.9', '8.6', '9.3', '10.2', '11.2', '12.3', '13.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('81.0', '8.0', '8.7', '9.4', '10.3', '11.3', '12.4', '13.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('81.5', '8.1', '8.8', '9.5', '10.4', '11.4', '12.5', '13.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('82.0', '8.1', '8.8', '9.6', '10.5', '11.5', '12.6', '13.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('82.5', '8.4', '9.1', '9.9', '10.9', '11.9', '13.1', '14.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('83.0', '8.3', '9.0', '9.8', '10.7', '11.8', '12.9', '14.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('83.5', '8.4', '9.1', '9.9', '10.9', '11.9', '13.1', '14.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('84.0', '8.5', '9.2', '10.1', '11.0', '12.0', '13.2', '14.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('84.5', '8.6', '9.3', '10.2', '11.1', '12.1', '13.1', '14.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('85.0', '8.7', '9.4', '10.3', '11.2', '12.3', '13.5', '14.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('85.5', '8.8', '9.5', '10.4', '11.3', '12.4', '13.6', '15.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('86.0', '8.9', '9.7', '10.5', '11.5', '12.6', '13.8', '15.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('86.5', '9.0', '9.8', '10.6', '11.6', '12.7', '13.9', '15.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('87.0', '9.1', '9.9', '10.7', '11.7', '12.8', '14.1', '15.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('87.5', '9.2', '10.0', '10.9', '11.8', '13.0', '14.2', '15.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('88.0', '9.3', '10.1', '11.0', '12.0', '13.1', '14.4', '15.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('88.5', '9.4', '10.2', '11.1', '12.1', '13.2', '14.5', '16.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('89.0', '9.5', '10.3', '11.2', '12.2', '13.4', '14.7', '16.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('89.5', '9.6', '10.4', '11.3', '12.3', '13.5', '14.8', '16.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('90.0', '9.7', '10.5', '11.4', '12.5', '13.7', '15.0', '16.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('90.5', '9.8', '10.6', '11.5', '12.6', '13.8', '15.1', '16.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('91.0', '9.9', '10.7', '11.7', '12.7', '13.9', '15.3', '16.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('91.5', '10.0', '10.8', '11.8', '12.8', '14.1', '15.5', '17.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('92.0', '10.1', '10.9', '11.9', '13.0', '14.2', '15.6', '17.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('92.5', '10.1', '11.0', '12.0', '13.1', '14.3', '15.8', '17.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('93.0', '10.2', '11.1', '12.1', '13.2', '14.5', '15.9', '17.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('93.5', '10.3', '11.2', '12.2', '13.3', '14.6', '16.1', '17.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('94.0', '10.4', '11.3', '12.3', '13.5', '14.7', '16.2', '17.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('94.5', '10.5', '11.4', '12.4', '13.6', '14.9', '16.4', '18.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('95.0', '10.6', '11.5', '12.6', '13.7', '15.0', '16.5', '18.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('95.5', '10.7', '11.6', '12.7', '13.8', '15.2', '16.7', '18.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('96.0', '10.8', '11.7', '12.8', '14.0', '15.3', '16.8', '18.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('96.5', '10.9', '11.8', '12.9', '14.1', '15.4', '17.0', '18.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('97.0', '11.0', '12.0', '13.0', '14.2', '15.6', '17.1', '18.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('97.5', '11.1', '12.1', '13.1', '14.4', '15.7', '17.3', '19.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('98.0', '11.2', '12.2', '13.3', '14.5', '15.9', '17.5', '19.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('98.5', '11.3', '12.3', '13.4', '14.6', '16.0', '17.6', '19.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('99.0', '11.4', '12.4', '13.5', '14.8', '16.2', '17.8', '19.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('99.5', '11.5', '12.5', '13.6', '14.9', '16.3', '18.0', '19.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('100.0', '11.6', '12.6', '13.7', '15.0', '16.5', '18.1', '20.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('100.5', '11.7', '12.7', '13.9', '15.2', '16.6', '18.3', '20.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('101.0', '11.8', '12.8', '14.0', '15.3', '16.8', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('101.5', '11.9', '13.0', '14.1', '15.5', '17.0', '18.7', '20.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('102.0', '12.0', '13.1', '14.3', '15.6', '17.1', '18.9', '20.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('102.5', '12.1', '13.2', '14.3', '15.8', '17.3', '19.0', '21.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('103.0', '12.3', '13.3', '14.5', '15.9', '17.5', '19.2', '21.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('103.5', '12.4', '13.5', '14.7', '16.1', '17.6', '19.4', '21.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('104.0', '12.5', '13.6', '14.8', '16.2', '17.8', '19.6', '21.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('104.5', '12.6', '13.7', '15.0', '16.4', '18.0', '19.8', '21.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('105.0', '12.7', '13.8', '15.1', '16.5', '18.2', '20.0', '22.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('105.5', '12.8', '14.0', '15.3', '16.7', '18.4', '20.2', '22.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('106.0', '13.0', '14.1', '15.4', '16.9', '18.5', '20.5', '22.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('106.5', '13.1', '14.3', '15.6', '17.1', '18.7', '20.7', '22.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('107.0', '13.2', '14.4', '15.7', '17.2', '18.9', '20.9', '23.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('107.5', '13.3', '14.5', '15.9', '17.4', '19.1', '21.1', '23.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('108.0', '13.5', '14.7', '16.0', '17.6', '19.3', '21.3', '23.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('108.5', '13.6', '14.8', '16.2', '17.8', '19.5', '21.6', '23.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('109.0', '13.7', '15.0', '16.4', '18.0', '19.7', '21.8', '24.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('109.5', '13.9', '15.1', '16.5', '18.1', '20.0', '22.0', '24.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_0_24 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('110.0', '14.0', '15.3', '16.7', '18.3', '20.2', '22.3', '24.7');");
    }

    // Get One Perempuan BBTB 0-24 List From Database
    public Cursor getPerempuanBBTBList_0_24(Float tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbtb_0_24 WHERE perempuan_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Perempuan BBTB 0-24 List From Database
    public Cursor getPerempuanBBTBAllList_0_24() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbtb_0_24", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Perempuan BBTB 24-60
    public void createPerempuanBBTB_24_60(SQLiteDatabase db) {

        // Create Table Perempuan BBTB 24-60
        String create_perempuan_bbtb_24_60 = "create table antropometri_perempuan_bbtb_24_60(" +
                "perempuan_bbtb_ID integer primary key autoincrement, " +
                "perempuan_bbtb_tb real not null," +
                "perempuan_bbtb_min3sd real not null, " +
                "perempuan_bbtb_min2sd real not null, " +
                "perempuan_bbtb_min1sd real not null, " +
                "perempuan_bbtb_median real not null, " +
                "perempuan_bbtb_1sd real not null, " +
                "perempuan_bbtb_2sd real not null, " +
                "perempuan_bbtb_3sd real not null " +
                ");";
        db.execSQL(create_perempuan_bbtb_24_60);

        // Insert Perempuan BBTB 24-60 After Create Table
        insertPerempuanBBTB_24_60(db);
    }

	// Insert Perempuan BBTB 24-60
    public void insertPerempuanBBTB_24_60(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('65.0', '5.6', '6.1', '6.6', '7.2', '7.9', '8.7', '9.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('65.5', '5.7', '6.2', '6.7', '7.4', '8.1', '8.9', '9.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('66.0', '5.8', '6.3', '6.8', '7.5', '8.2', '9.0', '10.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('66.5', '5.8', '6.4', '6.9', '7.6', '8.3', '9.1', '10.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('67.0', '5.9', '6.4', '7.0', '7.7', '8.4', '9.3', '10.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('67.5', '6.0', '6.5', '7.1', '7.8', '8.5', '9.4', '10.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('68.0', '6.1', '6.6', '7.2', '7.9', '8.7', '9.5', '10.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('68.5', '6.2', '6.7', '7.3', '8.0', '8.8', '9.7', '10.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('69.0', '6.3', '6.8', '7.4', '8.1', '8.9', '9.8', '10.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('69.5', '6.3', '6.9', '7.5', '8.2', '9.0', '9.9', '10.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('70.0', '6.4', '7.0', '7.6', '8.3', '9.1', '10.0', '11.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('70.5', '6.5', '7.1', '7.7', '8.4', '9.2', '10.1', '11.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('71.0', '6.6', '7.1', '7.8', '8.5', '9.3', '10.3', '11.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('71.5', '6.7', '7.2', '7.9', '8.6', '9.4', '10.4', '11.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('72.0', '6.7', '7.3', '8.0', '8.7', '9.5', '10.5', '11.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('72.5', '6.8', '7.4', '8.1', '8.8', '9.7', '10.6', '11.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('73.0', '6.9', '7.5', '8.1', '8.9', '9.8', '10.7', '11.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('73.5', '7.0', '7.6', '8.2', '9.0', '9.9', '10.8', '12.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('74.0', '7.0', '7.6', '8.3', '9.1', '10.0', '11.0', '12.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('74.5', '7.1', '7.7', '8.4', '9.2', '10.1', '11.1', '12.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('75.0', '7.2', '7.8', '8.5', '9.3', '10.2', '11.2', '12.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('75.5', '7.2', '7.9', '8.6', '9.4', '10.3', '11.3', '12.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('76.0', '7.3', '8.0', '8.7', '9.5', '10.4', '11.4', '12.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('76.5', '7.4', '8.0', '8.7', '9.6', '10.6', '11.6', '12.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('77.0', '7.5', '8.1', '8.8', '9.6', '10.6', '11.6', '12.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('77.5', '7.5', '8.2', '8.9', '9.7', '10.7', '11.7', '12.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('78.0', '7.6', '8.3', '9.0', '9.8', '10.8', '11.8', '13.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('78.5', '7.7', '8.4', '9.1', '9.9', '10.9', '12.0', '13.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('79.0', '7.8', '8.4', '9.2', '10.0', '11.0', '12.1', '13.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('79.5', '7.8', '8.5', '9.3', '10.1', '11.1', '12.2', '13.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('80.0', '7.9', '8.6', '9.4', '10.2', '11.2', '12.3', '13.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('80.5', '8.0', '8.7', '9.5', '10.3', '11.3', '12.4', '13.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('81.0', '8.1', '8.8', '9.6', '10.4', '11.4', '12.6', '13.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('81.5', '8.2', '8.9', '9.7', '10.6', '11.6', '12.7', '14.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('82.0', '8.3', '9.0', '9.8', '10.7', '11.7', '12.8', '14.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('82.5', '8.4', '9.1', '9.9', '10.8', '11.8', '13.0', '14.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('83.0', '8.5', '9.2', '10.0', '10.9', '11.9', '13.1', '14.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('83.5', '8.5', '9.3', '10.1', '11.0', '12.1', '13.3', '14.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('84.0', '8.6', '9.4', '10.2', '11.1', '12.2', '13.4', '14.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('84.5', '8.7', '9.5', '10.3', '11.3', '12.3', '13.5', '14.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('85.0', '8.8', '9.6', '10.4', '11.4', '12.5', '13.7', '15.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('85.5', '8.9', '9.7', '10.6', '11.5', '12.6', '13.8', '15.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('86.0', '9.0', '9.8', '10.7', '11.6', '12.7', '14.0', '15.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('86.5', '9.1', '9.9', '10.8', '11.8', '12.9', '14.2', '15.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('87.0', '9.2', '10.0', '10.9', '11.9', '13.0', '14.3', '15.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('87.5', '9.3', '10.1', '11.0', '12.0', '13.2', '14.5', '15.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('88.0', '9.4', '10.2', '11.1', '12.1', '13.3', '14.6', '16.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('88.5', '9.5', '10.3', '11.2', '12.3', '13.4', '14.8', '16.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('89.0', '9.6', '10.4', '11.4', '12.4', '13.6', '14.9', '16.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('89.5', '9.7', '10.5', '11.5', '12.5', '13.7', '15.1', '16.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('90.0', '9.8', '10.6', '11.6', '12.6', '13.8', '15.2', '16.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('90.5', '9.9', '10.7', '11.7', '12.8', '14.0', '15.4', '16.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('91.0', '10.0', '10.9', '11.8', '12.9', '14.1', '15.5', '17.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('91.5', '10.1', '11.0', '11.9', '13.0', '14.3', '15.7', '17.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('92.0', '10.2', '11.1', '12.0', '13.1', '14.4', '15.8', '17.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('92.5', '10.3', '11.2', '12.1', '13.3', '14.5', '16.0', '17.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('93.0', '10.4', '11.3', '12.3', '13.4', '14.7', '16.1', '17.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('93.5', '10.5', '11.4', '12.4', '13.5', '14.8', '16.3', '17.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('94.0', '10.6', '11.5', '12.5', '13.6', '14.9', '16.4', '18.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('94.5', '10.7', '11.6', '12.6', '13.8', '15.1', '16.6', '18.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('95.0', '10.8', '11.7', '12.7', '13.9', '15.2', '16.7', '18.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('95.5', '10.8', '11.8', '12.8', '14.0', '15.4', '16.9', '18.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('96.0', '10.9', '11.9', '12.9', '14.1', '15.5', '17.0', '18.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('96.5', '11.0', '12.0', '13.1', '14.3', '15.6', '17.2', '19.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('97.0', '11.1', '12.1', '13.2', '14.4', '15.8', '17.4', '19.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('97.5', '11.2', '12.2', '13.3', '14.5', '15.9', '17.5', '19.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('98.0', '11.3', '12.3', '13.4', '14.7', '16.1', '17.7', '19.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('98.5', '11.4', '12.4', '13.5', '14.8', '16.2', '17.9', '19.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('99.0', '11.5', '12.5', '13.7', '14.9', '16.4', '18.0', '19.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('99.5', '11.6', '12.7', '13.8', '15.1', '16.5', '18.2', '20.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('100.0', '11.7', '12.8', '13.9', '15.2', '16.7', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('100.5', '11.9', '12.9', '14.1', '15.4', '16.9', '18.6', '20.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('101.0', '12.0', '13.0', '14.2', '15.5', '17.0', '18.7', '20.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('101.5', '12.1', '13.1', '14.3', '15.7', '17.2', '18.9', '20.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('102.0', '12.2', '13.3', '14.5', '15.8', '17.4', '19.1', '21.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('102.5', '12.3', '13.4', '14.6', '16.0', '17.5', '19.3', '21.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('103.0', '12.4', '13.5', '14.7', '16.1', '17.7', '19.5', '21.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('103.5', '12.5', '13.6', '14.9', '16.3', '17.9', '19.7', '21.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('104.0', '12.6', '13.8', '15.0', '16.4', '18.1', '19.9', '22.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('104.5', '12.8', '13.9', '15.2', '16.6', '18.2', '20.1', '22.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('105.0', '12.9', '14.0', '15.3', '16.8', '18.4', '20.3', '22.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('105.5', '13.0', '14.2', '15.5', '16.9', '18.6', '20.5', '22.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('106.0', '13.1', '14.3', '15.6', '17.1', '18.8', '20.8', '23.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('106.5', '13.3', '14.5', '15.8', '17.3', '19.0', '21.0', '23.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('107.0', '13.4', '14.6', '15.9', '17.5', '19.2', '21.2', '23.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('107.5', '13.5', '14.7', '16.1', '17.7', '19.4', '21.4', '23.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('108.0', '13.7', '14.9', '16.3', '17.8', '19.6', '21.7', '24.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('108.5', '13.8', '15.0', '16.4', '18.0', '19.8', '21.9', '24.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('109.0', '13.9', '15.2', '16.6', '18.2', '20.0', '22.1', '24.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('110.0', '14.1', '15.4', '16.8', '18.4', '20.3', '22.4', '24.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('110.5', '14.4', '15.7', '17.1', '18.8', '20.7', '22.9', '25.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('111.0', '14.5', '15.8', '17.3', '19.0', '20.9', '23.1', '25.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('111.5', '14.7', '16.0', '17.5', '19.2', '21.2', '23.4', '26.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('112.0', '14.8', '16.2', '17.7', '19.4', '21.4', '23.6', '26.2');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('112.5', '15.0', '16.3', '17.9', '19.6', '21.6', '23.9', '26.5');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('113.0', '15.1', '16.5', '18.0', '19.8', '21.8', '24.2', '26.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('113.5', '15.3', '16.7', '18.2', '20.2', '22.1', '24.4', '27.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('114.0', '15.4', '16.8', '18.4', '20.2', '22.3', '24.7', '27.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('114.5', '15.6', '17.0', '18.6', '20.5', '22.6', '25.0', '27.8');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('115.0', '15.7', '17.5', '18.8', '20.7', '22.8', '25.2', '28.1');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('115.5', '15.9', '17.3', '19.0', '20.9', '23.0', '25.5', '28.4');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('116.0', '16.0', '17.5', '19.2', '21.1', '23.3', '25.8', '28.7');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('116.5', '16.2', '17.7', '19.4', '21.3', '23.5', '26.1', '29.0');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('117.0', '16.3', '17.8', '19.6', '21.5', '23.8', '26.3', '29.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('117.5', '16.5', '18.0', '19.8', '21.7', '24.0', '26.6', '29.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('118.0', '16.6', '18.2', '19.9', '22.0', '24.2', '26.9', '29.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('118.5', '16.8', '18.4', '20.1', '22.2', '24.5', '27.2', '30.3');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('119.0', '16.9', '18.5', '20.3', '22.4', '24.7', '27.4', '30.6');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('119.5', '17.1', '18.7', '20.5', '22.6', '25.0', '27.7', '30.9');");
        db.execSQL("insert into antropometri_perempuan_bbtb_24_60 (perempuan_bbtb_tb, perempuan_bbtb_min3sd, perempuan_bbtb_min2sd, perempuan_bbtb_min1sd, perempuan_bbtb_median, perempuan_bbtb_1sd, perempuan_bbtb_2sd, perempuan_bbtb_3sd) values " +
                "('120.0', '17.3', '18.9', '20.7', '22.8', '25.2', '28.0', '31.2');");
    }

    // Get One Perempuan BBTB 24-60 List From Database
    public Cursor getPerempuanBBTBList_24_60(Float tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbtb_24_60 WHERE perempuan_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Perempuan BBTB 24-60 List From Database
    public Cursor getPerempuanBBTBAllList_24_60() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_bbtb_24_60", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Perempuan IMTU
    public void createPerempuanIMTU(SQLiteDatabase db) {

        // Create Table Perempuan IMTU
        String create_perempuan_imtu = "create table antropometri_perempuan_imtu (" +
                "perempuan_imtu_ID integer primary key autoincrement, " +
                "perempuan_imtu_usia real not null," +
                "perempuan_imtu_min3sd real not null, " +
                "perempuan_imtu_min2sd real not null, " +
                "perempuan_imtu_min1sd real not null, " +
                "perempuan_imtu_median real not null, " +
                "perempuan_imtu_1sd real not null, " +
                "perempuan_imtu_2sd real not null, " +
                "perempuan_imtu_3sd real not null " +
                ");";
        db.execSQL(create_perempuan_imtu);

        // Insert Perempuan IMTU After Create Table
        insertPerempuanIMTU(db);
    }

    // Insert Perempuan IMTU 
    public void insertPerempuanIMTU(SQLiteDatabase db) {

        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('0', '10.1', '11.1', '12.1', '13.3', '14.6', '16.0', '17.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('1', '10.8', '12.0', '13.2', '14.6', '16.0', '17.5', '19.1');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('2', '11.8', '13.0', '14.3', '15.8', '17.3', '19.0', '20.7');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('3', '12.4', '13.6', '14.9', '16.4', '17.9', '19.7', '21.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('4', '12.7', '13.9', '15.2', '16.7', '18.3', '20.0', '22.0');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('5', '12.9', '14.1', '15.4', '16.8', '18.4', '20.2', '22.2');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('6', '13.0', '14.1', '15.5', '16.9', '18.5', '20.3', '22.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('7', '13.0', '14.2', '15.5', '16.9', '18.5', '20.3', '22.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('8', '13.0', '14.1', '15.4', '16.8', '18.4', '20.2', '22.2');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('9', '12.9', '14.1', '15.3', '16.7', '18.3', '20.1', '22.1');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('10', '12.9', '14.0', '15.2', '16.6', '18.2', '19.9', '21.9');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('11', '12.8', '13.9', '15.1', '16.5', '18.0', '19.8', '21.8');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('12', '12.7', '13.8', '15.0', '16.4', '17.9', '19.6', '21.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('13', '12.6', '13.7', '14.9', '16.2', '17.7', '19.5', '21.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('14', '12.6', '13.6', '14.8', '16.1', '17.6', '19.3', '21.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('15', '12.5', '13.5', '14.7', '16.0', '17.5', '19.2', '21.1');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('16', '12.4', '13.5', '14.6', '15.9', '17.4', '19.1', '21.0');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('17', '12.4', '13.4', '14.5', '15.8', '17.3', '18.9', '20.9');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('18', '12.3', '13.3', '14.4', '15.7', '17.2', '18.8', '20.8');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('19', '12.3', '13.3', '14.4', '15.7', '17.1', '18.8', '20.7');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('20', '12.2', '13.2', '14.3', '15.6', '17.0', '18.7', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('21', '12.2', '13.2', '14.3', '15.6', '17.0', '18.6', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('22', '12.2', '13.1', '14.2', '15.5', '17.0', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('23', '12.2', '13.1', '14.2', '15.4', '16.9', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('24', '12.4', '13.3', '14.4', '15.7', '17.1', '18.7', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('25', '12.4', '13.3', '14.4', '15.7', '17.1', '18.7', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('26', '12.3', '13.3', '14.4', '15.6', '17.0', '18.7', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('27', '12.3', '13.3', '14.4', '15.6', '17.0', '18.6', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('28', '12.3', '13.3', '14.3', '15.6', '17.0', '18.6', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('29', '12.3', '13.2', '14.3', '15.6', '17.0', '18.6', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('30', '12.3', '13.2', '14.3', '15.5', '16.9', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('31', '12.2', '13.2', '14.3', '15.5', '16.9', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('32', '12.2', '13.2', '14.3', '15.5', '16.9', '18.5', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('33', '12.2', '13.1', '14.2', '15.5', '16.9', '18.5', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('34', '12.2', '13.1', '14.2', '15.4', '16.8', '18.5', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('35', '12.1', '13.1', '14.2', '15.4', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('36', '12.1', '13.1', '14.2', '15.4', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('37', '12.1', '13.1', '14.1', '15.4', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('38', '12.1', '13.0', '14.1', '15.4', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('39', '12.0', '13.0', '14.1', '15.3', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('40', '12.0', '13.0', '14.1', '15.3', '16.8', '18.4', '20.3');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('41', '12.0', '13.0', '14.1', '15.3', '16.8', '18.4', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('42', '12.0', '12.9', '14.0', '15.3', '16.8', '18.4', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('43', '11.9', '12.9', '14.0', '15.3', '16.8', '18.4', '20.4');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('44', '11.9', '12.9', '14.0', '15.3', '16.8', '18.5', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('45', '11.9', '12.9', '14.0', '15.3', '16.8', '18.5', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('46', '11.9', '12.9', '14.0', '15.3', '16.8', '18.5', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('47', '11.8', '12.8', '14.0', '15.3', '16.8', '18.5', '20.5');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('48', '11.8', '12.8', '14.0', '15.3', '16.8', '18.5', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('49', '11.8', '12.8', '13.9', '15.3', '16.8', '18.5', '20.6');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('50', '11.8', '12.8', '13.9', '15.3', '16.8', '18.6', '20.7');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('51', '11.8', '12.8', '13.9', '15.3', '16.8', '18.6', '20.7');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('52', '11.7', '12.8', '13.9', '15.2', '16.8', '18.6', '20.7');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('53', '11.7', '12.7', '13.9', '15.3', '16.8', '18.6', '20.8');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('54', '11.7', '12.7', '13.9', '15.3', '16.8', '18.7', '20.8');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('55', '11.7', '12.7', '13.9', '15.3', '16.8', '18.7', '20.9');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('56', '11.7', '12.7', '13.9', '15.3', '16.8', '18.7', '20.9');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('57', '11.7', '12.7', '13.9', '15.3', '16.9', '18.7', '21.0');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('58', '11.7', '12.7', '13.9', '15.3', '16.9', '18.8', '21.0');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('59', '11.6', '12.7', '13.9', '15.3', '16.9', '18.8', '21.0');");
        db.execSQL("insert into antropometri_perempuan_imtu (perempuan_imtu_usia, perempuan_imtu_min3sd, perempuan_imtu_min2sd, perempuan_imtu_min1sd, perempuan_imtu_median, perempuan_imtu_1sd, perempuan_imtu_2sd, perempuan_imtu_3sd) values " +
                "('60', '11.6', '12.7', '13.9', '15.3', '16.9', '18.8', '21.1');");
    }

    // Get One Perempuan IMTU List From Database
    public Cursor getPerempuanIMTUList(Integer usia) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_imtu WHERE perempuan_imtu_usia = " + usia, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get All Perempuan IMTU List From Database
    public Cursor getPerempuanIMTUAllList() {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_perempuan_imtu", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Dokumentasi Gizi 
    public void createDokumentasi(SQLiteDatabase db) {

        // Create Table Dokumentasi Gizi
        String create_dokumentasi = "create table dokumentasi_gizi (" +
                "dokumentasiID integer primary key autoincrement, " +
                "dokumentasi_profilID integer not null, " +
                "dokumentasi_tanggal text not null, " +
                "dokumentasi_usia text not null, " +
                "dokumentasi_bulan real not null, " +
                "dokumentasi_tinggi real not null," +
                "dokumentasi_berat real not null," +
                "dokumentasi_bbu text," +
                "dokumentasi_tbu text," +
                "dokumentasi_bbtb text," +
                "dokumentasi_imtu text," +
                "FOREIGN KEY ( dokumentasi_profilID ) REFERENCES profil ( profilID ));";
        db.execSQL(create_dokumentasi);
    }

    // Insert Dokumentasi Gizi to Database
    public long insertDokumentasi(Integer profilID, String tanggal, String usia, Integer bulan,
                                  String beratBadan, String tinggiBadan, String status_bbu, String status_tbu, String status_bbtb, String status_imtu) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("dokumentasi_profilID", profilID);
        values.put("dokumentasi_tanggal", tanggal);
        values.put("dokumentasi_usia", usia);
        values.put("dokumentasi_bulan", bulan);
        values.put("dokumentasi_berat", beratBadan);
        values.put("dokumentasi_tinggi", tinggiBadan);
        values.put("dokumentasi_bbu", status_bbu);
        values.put("dokumentasi_tbu", status_tbu);
        values.put("dokumentasi_bbtb", status_bbtb);
        values.put("dokumentasi_imtu", status_imtu);

        // Insert Data
        return db.insert("dokumentasi_gizi", null, values);
    }

    // Get Dokumentasi Gizi from Database
    public Cursor getDokumentasi(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM dokumentasi_gizi WHERE dokumentasi_profilID = " + id + " ORDER BY SUBSTR(dokumentasi_tanggal, 7, 4) DESC, SUBSTR(dokumentasi_tanggal, 4, 2) DESC, SUBSTR(dokumentasi_tanggal, 1, 2) DESC", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get Dokumentasi Gizi Chart from Database
    public Cursor getDokumentasiChart(Integer id, Integer bulan) {
        Cursor cursor = db.rawQuery("SELECT * FROM dokumentasi_gizi WHERE dokumentasi_profilID = " + id + " AND dokumentasi_bulan BETWEEN 0 AND " + bulan, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Dokumentasi Gizi From Database
    public Cursor getOneDokumentasi(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM dokumentasi_gizi WHERE dokumentasiID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Update Dokumentasi Gizi to Database
    public long updateDokumentasi(Integer dokumentasiID, Integer profilID, String tanggal, String usia, Integer bulan,
                                  String berat, String tinggi, String bbu, String tbu, String bbtb, String imtu) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("dokumentasi_profilID", profilID);
        values.put("dokumentasi_tanggal", tanggal);
        values.put("dokumentasi_usia", usia);
        values.put("dokumentasi_bulan", bulan);
        values.put("dokumentasi_tinggi", tinggi);
        values.put("dokumentasi_berat", berat);
        values.put("dokumentasi_bbu", bbu);
        values.put("dokumentasi_tbu", tbu);
        values.put("dokumentasi_bbtb", bbtb);
        values.put("dokumentasi_imtu", imtu);

        // Update Data
        return db.update("dokumentasi_gizi", values, "dokumentasiID = " + dokumentasiID, null);
    }

    // Delete Dokumentasi Gizi  from Database
    public boolean deleteDokumentasi(int id) {
        
        // Delete Data
        return db.delete("dokumentasi_gizi", "dokumentasiID =" + id, null) < 1;
    }

    // Delete Dokumentasi Gizi from ProfilID from Database
    public boolean deleteDokumentasiProfilID(int profilID) {
        
        // Delete Data
        return db.delete("dokumentasi_gizi", "dokumentasi_profilID =" + profilID, null) < 1;
    }

    // Create Table List Imunisasi
    public void createList(SQLiteDatabase db) {

        // Create Table List Imunisasi
        String create_list_imunisasi = "create table list_imunisasi (" +
                "listID integer primary key autoincrement, " +
                "list_vaksin text not null, " +
                "list_usia text not null, " +
                "list_desc text not null," +
                "list_bulan real not null" +
                ");";
        db.execSQL(create_list_imunisasi);

        // Insert List Imunisasi After Create Table
        insertList(db);
    }

    // Insert List Imunisasi
    public void insertList(SQLiteDatabase db) {

        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('BCG', '0 - 3 Bulan', 'BCG merupakan singkatan dari Bacille Calmette-Guerin. BCG diberikan sejak lahir. Apabila umur > 3 bulan harus dilakukan uji tuberkulin terlebih dulu, BCG diberikan apabila uji tuberkulin negatif.', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hepatitis B (1)', '12 jam setelah lahir', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio-0', 'Kunjungan Pertama', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hepatitis B (2)', '1 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '1');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio (1)', '2 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).' , '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('DPT (1)', '2 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hib (1)', '2 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (1)', '2 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio (2)', '4 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('DPT (2)', '4 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hib (2)', '4 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (2)', '4 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hepatitis B (3)', '6 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio (3)', '6 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('DPT (3)', '6 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hib (3)', '6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (3)', '6 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Influenza (1)', '6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Campak (1)', '9 Bulan', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.', '9');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (4)', '12 - 15 Bulan / 1 Tahun - 1 Tahun 3 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Varisela', '1 - 18 Tahun', 'Vaksin ini dapat diberikan setelah umur 12 bulan, terbaik pada umur sebelum masuk sekolah dasar. Apabila diberikan pada umur lebih dari 12 tahun, perlu 2 dosis dengan interval minimal 4 minggu.', '12');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('MMR (1)', '15 Bulan / 1 Tahun 3 Bulan', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hib (4)', '15 - 18 Bulan / 1 Tahun 3 Bulan - 1 Tahun 6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('DPT (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Influenza (2)', '18 Bulan / 1 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Tifoid (1)', '24 Bulan / 2 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.', '24');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hepatitis A (1)', '24 Bulan / 2 Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.', '24');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Influenza (3)', '30 Bulan / 2 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '30');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Hepatitis A (2)', '36 Bulan / 3 Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.', '36');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Influenza (4)', '42 Bulan / 3 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '42');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Influenza (5)', '54 Bulan / 4 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '54');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Polio (5)', '60 Bulan / 5 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('DPT (5)', '60 Bulan / 5 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Campak (2)', '60 Bulan / 5 Tahun', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('Tifoid (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_usia, list_desc, list_bulan) values " +
                "('MMR (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.', '60');");
    }

    // Get List Imunisasi from Database
    public Cursor getList() {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Imunisasi From Database
    public Cursor getOneList(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi WHERE listID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Imunisasi Join Riwayat Imunisasi From Database
    public Cursor getListJoinRiwayat(Integer id, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi ON list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin AND riwayat_imunisasi.riwayat_profilID = " + profilID + " WHERE list_imunisasi.listID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Imunisasi By Bulan Join Riwayat Imunisasi From Database For Tambah Riwayat
    public Cursor getOneListByBulanLeftJoinRiwayat(Integer bulan) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi on list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin WHERE list_imunisasi.list_bulan <= " + bulan + " AND riwayat_imunisasi.riwayatID ISNULL", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Imunisasi By Bulan Join Riwayat Imunisasi From Database For Ubah Riwayat
    public Cursor getOneListByBulanLeftJoinRiwayatID(Integer bulan, Integer riwayatID) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi on list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin WHERE list_imunisasi.list_bulan <= " + bulan + " AND (riwayat_imunisasi.riwayatID == " + riwayatID + " OR riwayat_imunisasi.riwayatID ISNULL)", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Imunisasi Join Riwayat Imunisasi Closest Value From Database
    public Cursor getListJoinRiwayatClosestValue(Integer bulan, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi ON list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin AND riwayat_imunisasi.riwayat_profilID = " + profilID + " WHERE list_imunisasi.list_bulan BETWEEN 0 AND " + bulan + "+1 AND riwayat_imunisasi.riwayatID ISNULL ORDER BY ABS( " + bulan + " - riwayat_imunisasi.riwayat_bulan)", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Riwayat Imunisasi
    public void createRiwayat(SQLiteDatabase db) {

        // Create Table Riwayat Imunisasi
        String create_riwayat = "create table riwayat_imunisasi (" +
                "riwayatID integer primary key autoincrement, " +
                "riwayat_profilID integer not null, " +
                "riwayat_vaksin text not null," +
                "riwayat_tanggal text not null," +
                "riwayat_usia text not null," +
                "riwayat_bulan real, " +
                "riwayat_berat real, " +
                "riwayat_tinggi real, " +
                "riwayat_dokter text, " +
                "riwayat_rumahsakit text, " +
                "FOREIGN KEY ( riwayat_profilID  ) REFERENCES profil  ( profilID ));";
        db.execSQL(create_riwayat);
    }

    // Insert Riwayat Imunisasi to Database
    public long insertRiwayat(Integer profilID, String tanggal, String vaksin, String usia, Integer bulan,
                              String berat, String tinggi, String dokter, String rumahsakit) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("riwayat_profilID", profilID);
        values.put("riwayat_tanggal", tanggal);
        values.put("riwayat_vaksin", vaksin);
        values.put("riwayat_usia", usia);
        values.put("riwayat_bulan", bulan);
        values.put("riwayat_berat", berat);
        values.put("riwayat_tinggi", tinggi);
        values.put("riwayat_dokter", dokter);
        values.put("riwayat_rumahsakit", rumahsakit);

        // Insert Data
        return db.insert("riwayat_imunisasi", null, values);
    }

    // Get Riwayat Imunisasi from Database
    public Cursor getRiwayat(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM riwayat_imunisasi WHERE riwayat_profilID = " + id + " ORDER BY SUBSTR(riwayat_tanggal, 7, 4) DESC, SUBSTR(riwayat_tanggal, 4, 2) DESC, SUBSTR(riwayat_tanggal, 1, 2) DESC", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Riwayat Imunisasi From Database
    public Cursor getOneRiwayat(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM riwayat_imunisasi WHERE riwayatID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Update Riwayat Imunisasi to Database
    public long updateRiwayat(Integer riwayatID, Integer profilID, String tanggal, String vaksin, String usia, int bulan,
                              String berat, String tinggi, String dokter, String rumahsakit) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("riwayat_profilID", profilID);
        values.put("riwayat_tanggal", tanggal);
        values.put("riwayat_vaksin", vaksin);
        values.put("riwayat_usia", usia);
        values.put("riwayat_bulan", bulan);
        values.put("riwayat_berat", berat);
        values.put("riwayat_tinggi", tinggi);
        values.put("riwayat_dokter", dokter);
        values.put("riwayat_rumahsakit", rumahsakit);

        // Update Data
        return db.update("riwayat_imunisasi", values, "riwayatID = " + riwayatID, null);
    }

    // Delete Riwayat Imunisasi from Database
    public boolean deleteRiwayat(int id) {
        
        // Delete Data
        return db.delete("riwayat_imunisasi", "riwayatID =" + id, null) < 1;
    }

    // Delete Riwayat Imunisasi from ProfilID from Database
    public boolean deleteRiwayatProfilID(int profilID) {
       
        // Delete Data
        return db.delete("riwayat_imunisasi", "riwayat_profilID =" + profilID, null) < 1;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createTahap(SQLiteDatabase db) {

        // Create Table Tahap Tumbuh Kembang
        String create_tahap_tumbang = "create table tahap_tumbang (" +
                "tahapID integer primary key autoincrement, " +
                "tahap_usia text," +
                "tahap_gerakan_kasar text, " +
                "tahap_gerakan_halus text, " +
                "tahap_komunikasi text, " +
                "tahap_sosial_kemandirian text " +
                ");";
        db.execSQL(create_tahap_tumbang);

        // Insert Tahapan Tumbuh Kembang After Create Table
        insertTahapan(db);
    }

    // Insert Tahapan Tumbuh Kembang
    public void insertTahapan(SQLiteDatabase db) {

        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('1 Bulan', 'Tangan dan kaki bergerak aktif', 'Kepala menoleh ke samping kanan-kiri', 'Bereaksi terhadap bunyi lonceng', 'Menatap wajah ibu / pengasuh');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('2 Bulan', 'Mengangkat kepala ketika tengkurap', '', 'Bersuara', 'Tersenyum spontan');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('3 Bulan', 'Kepala tegak ketika didudukkan', 'Memegang mainan', 'Tertawa / berteriak', 'Memandang tangannya');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('4 Bulan', 'Tengkurap-terlentang sendiri', '', '', '');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('5 Bulan', '', 'Meraih / menggapai', 'Menoleh ke suara', 'Meraih mainan');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('6 Bulan', 'Duduk tanpa pegangan', '', '', 'Memasukkan benda ke mulut');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('7 Bulan', '', 'Mengambil dengan tangan kanan dan kiri', 'Bersuara', '');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('8 Bulan', 'Berdiri berpegangan', '', '', '');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('9 Bulan', '', 'Menjimpit', '', 'Melambaikan tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('10 Bulan', '', 'Memukul mainan dengan kedua tangan', '', 'Bertepuk tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('11 Bulan', '', '', 'Memanggil papa-mama', 'Menunjuk dan meminta');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('12 Bulan', 'Berdiri tanpa berpegangan', 'Memasukkan mainan ke cangkir', '', 'Bermain dengan orang lain');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('15 Bulan / 1 Tahun 3 Bulan', 'Berjalan', 'Mencoret-coret', 'Berbicara 2 kata', 'Minum dari gelas');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('18 Bulan / 1 Tahun 6 Bulan', 'Berlari - Naik tangga', 'Menumpuk 2 mainan', 'Berbicara beberapa kata', 'Menyuapi boneka menggunakan sendok');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('24 Bulan / 2 Tahun', 'Menendang Bola', 'Menumpuk 4 mainan', 'Menunjuk gambar', 'Melepaskan pakaian - Memakai pakaian - Menyikat gigi');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('30 Bulan / 2 Tahun 6 Bulan', 'Melompat', '', 'Menunjuk bagian tubuh', 'Mencuci dan mengeringkan tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('36 Bulan / 3 Tahun', '', 'Menggambar garis tegak', 'Menyebutkan warna benda', 'Menyebutkan nama teman');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('42 Bulan / 3 Tahun 6 Bulan', 'Naik sepeda roda tiga', 'Menggambar lingkaran', 'Bercerita singkat menyebutkan penggunaan benda', 'Memakai baju kaos');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('48 Bulan / 4 Tahun', '', 'Menggambar tanda tambah', '', 'Memakai baju tanpa dibantu');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('56 Bulan / 4 Tahun 6 bulan', '', 'Menggambar manusia (kepala, badan, kaki)', '', 'Bermain kartu - Menyikat gigi tanpa dibantu');");
        db.execSQL("insert into tahap_tumbang (tahap_usia, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('62 Bulan / 5 Tahun', '', '', 'Menghitung mainan', 'Mengambil makanan sendiri');");
    }

    // Get Tahapan Tumbuh Kembang from Database
    public Cursor getTahap() {
        Cursor cursor = db.rawQuery("SELECT * FROM tahap_tumbang", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Galeri Tumbuh Kembang
    public void createGaleri(SQLiteDatabase db) {

        // Create Table Galeri Tumbuh Kembang
        String create_galeri = "create table galeri_tumbang(" +
                "galeriID integer primary key autoincrement, " +
                "galeri_profilID integer not null, " +
                "galeri_tanggal text not null," +
                "galeri_usia text," +
                "galeri_foto text not null," +
                "galeri_desc text not null, " +
                "FOREIGN KEY (  galeri_profilID ) REFERENCES profil ( profilID ));";
        db.execSQL(create_galeri);
    }

    // Insert Galeri Tumbuh Kembang to Database
    public long insertGaleri(Integer profilID, String tanggal, String usia, String foto, String desc) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("galeri_profilID", profilID);
        values.put("galeri_tanggal", tanggal);
        values.put("galeri_usia", usia);
        values.put("galeri_foto", foto);
        values.put("galeri_desc", desc);

        // Insert Data
        return db.insert("galeri_tumbang", null, values);
    }

    // Get Galeri Tumbuh Kembang from Database
    public Cursor getGaleri(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM galeri_tumbang WHERE galeri_profilID = " + id + " ORDER BY SUBSTR(galeri_tanggal, 7, 4) DESC, SUBSTR(galeri_tanggal, 4, 2) DESC, SUBSTR(galeri_tanggal, 1, 2) DESC", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Galeri Tumbuh Kembang From Database
    public Cursor getOneGaleri(Integer galeriID, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM galeri_tumbang WHERE galeriID = " + galeriID + " AND galeri_profilID = " + profilID, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Insert Galeri Tumbuh Kembang to Database
    public long updateGaleri(Integer galeriID, Integer profilID, String tanggal, String usia, String foto, String desc) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("galeri_tanggal", tanggal);
        values.put("galeri_usia", usia);
        values.put("galeri_foto", foto);
        values.put("galeri_desc", desc);

        // Update Data
        return db.update("galeri_tumbang", values, "galeriID = " + galeriID + " AND galeri_profilID = " + profilID, null);
    }

    // Delete Galeri Tumbuh Kembang from Database
    public boolean deleteGaleri(int id) {
        
        // Delete Data
        return db.delete("galeri_tumbang", "galeriID =" + id, null) < 1;
    }

    // Delete Galeri Tumbuh Kembang from ProfilID from Database
    public boolean deleteGaleriProfilID(int id) {
        
        // Delete Data
        return db.delete("galeri_tumbang", "galeri_profilID =" + id, null) < 1;
    }

    // Create Table Rekam Medis
    public void createMedis(SQLiteDatabase db) {

        // Create Table Rekam Medis
        String create_rekam_medis = "create table rekam_medis (" +
                " medisID integer primary key autoincrement, " +
                " medis_profilID integer not null, " +
                " medis_tanggal text not null," +
                " medis_berat real, " +
                " medis_tinggi real, " +
                " medis_keluhan text, " +
                " medis_tindakan text, " +
                " medis_obat text, " +
                " medis_dokter text, " +
                " medis_rumahsakit text, " +
                " FOREIGN KEY ( medis_profilID  ) REFERENCES profil  ( profilID ));";
        db.execSQL(create_rekam_medis);
    }

    // Insert Rekam Medis to Database
    public long insertMedis(int id, String tanggalberobat, String namadokter, String rumahsakit, String tinggibadan,
                            String beratbadan, String keluhan, String tindakan, String obat) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("medis_profilID", id);
        values.put("medis_tanggal", tanggalberobat);
        values.put("medis_berat", beratbadan);
        values.put("medis_tinggi", tinggibadan);
        values.put("medis_keluhan", keluhan);
        values.put("medis_tindakan", tindakan);
        values.put("medis_obat", obat);
        values.put("medis_dokter", namadokter);
        values.put("medis_rumahsakit", rumahsakit);

        // Insert Data
        return db.insert("rekam_medis", null, values);
    }

    // Get Rekam Medis from Database
    public Cursor getMedis(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM rekam_medis WHERE medis_profilID = " + id + " ORDER BY SUBSTR(medis_tanggal, 7, 4) DESC, SUBSTR(medis_tanggal, 4, 2) DESC, SUBSTR(medis_tanggal, 1, 2) DESC", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Rekam Medis From Database
    public Cursor getOneMedis(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM rekam_medis WHERE medisID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Update Rekam Medis to Database
    public long updateMedis(int medisID, int profilID, String tanggalberobat, String namadokter, String rumahsakit, String tinggibadan,
                            String beratbadan, String keluhan, String tindakan, String obat) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("medis_tanggal", tanggalberobat);
        values.put("medis_berat", beratbadan);
        values.put("medis_tinggi", tinggibadan);
        values.put("medis_keluhan", keluhan);
        values.put("medis_tindakan", tindakan);
        values.put("medis_obat", obat);
        values.put("medis_dokter", namadokter);
        values.put("medis_rumahsakit", rumahsakit);

        // Update Data
        return db.update("rekam_medis", values, "medisID = " + medisID + " AND medis_profilID = " + profilID, null);
    }

    // Delete Rekam Medis from Database
    public boolean deleteMedis(int id) {
        
        // Delete Data
        return db.delete("rekam_medis", "medisID =" + id, null) < 1;
    }

    // Delete Rekam Medis from ProfilID from Database
    public boolean deleteMedisProfilID(int profilID) {
        
        // Delete Data
        return db.delete("rekam_medis", "medis_profilID =" + profilID, null) < 1;
    }
}
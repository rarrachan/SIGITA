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
        createList(db);
        createTahap(db);
        createRiwayat(db);
        createGaleri(db);
        createMedis(db);
    }

    // Upgrade Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profil");
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
                "profil_foto text not null);";
        db.execSQL(create_profil);
    }

    // Insert Profil to Database
    public long insertProfil(String nama, String tmptLahir, String tglLahir, String jenisKelamin,
                             String golonganDarah, String panjangLahir, String beratLahir,
                             String alergi, String penyakitKronis, String fotoPath) {

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

    // Delete Profil from Database
    public boolean deleteProfil(int id) {
        // Delete Data
        return db.delete("profil", "profilID =" + id, null) < 1;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createLakiBBU(SQLiteDatabase db) {
        // Create Table Tahap Tumbuh Kembang
        String create_laki_bbu= "create table antropometri_laki_bbu (" +
                "laki_bbu_ID integer primary key autoincrement, " +
                "laki_bbu_umur real not null," +
                "laki_bbu_min3sd real not null, " +
                "laki_bbu_min2sd real not null, " +
                "laki_bbu_min1sd real not null, " +
                "laki_bbu_median real not null, " +
                "laki_bbu_1sd real not null, " +
                "laki_bbu_2sd real not null, " +
                "laki_bbu_3sd real not null " +
                ");";
        db.execSQL(create_laki_bbu);
        insertLakiBBU(db);
    }

    // Insert List Imunisasi
    public void insertLakiBBU(SQLiteDatabase db) {
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('0', '2.1', '2.5', '2.9', '3.3', '3.9', '4.4', '5.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('1', '2.9', '3.4', '3.9', '4.5', '5.1', '5.8', '6.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('2', '3.8', '4.3', '4.9', '5.6', '6.3', '7.1', '8.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('3', '4.4', '5.0', '5.7', '6.4', '7.2', '8.0', '9.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('4', '4.9', '5.6', '6.2', '7.0', '7.8', '8.7', '9.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('5', '5.3', '6.0', '6.7', '7.5', '8.4', '9.3', '10.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('6', '5.7', '6.4', '7.1', '7.9', '8.8', '9.8', '10.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('7', '5.9', '6.7', '7.4', '8.3', '9.2', '10.3', '11.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('8', '6.2', '6.9', '7.7', '8.6', '9.6', '10.7', '11.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('9', '6.4', '7.1', '8.0', '8.9', '9.9', '11.0', '12.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('10', '6.6', '7.4', '8.2', '9.2', '10.2', '11.4', '12.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('11', '6.8', '7.6', '8.4', '9.4', '10.5', '11.7', '13.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('12', '6.9', '7.7', '8.6', '9.6', '10.8', '12.0', '13.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('13', '7.1', '7.9', '8.8', '9.9', '11.0', '12.3', '13.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('14', '7.2', '8.1', '9.0', '10.1', '11.3', '12.6', '14.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('15', '7.4', '8.3', '9.2', '10.3', '11.5', '12.8', '14.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('16', '7.5', '8.4', '9.4', '10.5', '11.7', '13.1', '14.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('17', '7.7', '8.6', '9.6', '10.7', '12.0', '13.4', '14.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('18', '7.8', '8.8', '9.8', '10.9', '12.2', '13.7', '15.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('19', '8.0', '8.9', '10.0', '11.1', '12.5', '13.9', '15.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('20', '8.1', '9.1', '10.1', '11.3', '12.7', '14.2', '15.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('21', '8.2', '9.2', '10.3', '11.5', '12.9', '14.5', '16.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('22', '8.4', '9.4', '10.5', '11.8', '13.2', '14.7', '16.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('23', '8.5', '9.5', '10.7', '12.0', '13.4', '15.0', '16.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('24', '8.6', '9.7', '10.8', '12.2', '13.4', '15.0', '16.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('25', '8.8', '9.8', '11.0', '12.4', '13.9', '15.5', '17.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('26', '8.9', '10.0', '11.2', '12.5', '14.1', '15.8', '17.8');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('27', '9.0', '10.1', '11.3', '12.7', '14.3', '16.1', '18.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('28', '9.1', '10.2', '11.5', '12.9', '14.5', '16.3', '18.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('29', '9.2', '10.4', '11.7', '13.1', '14.8', '16.6', '18.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('30', '9.4', '10.5', '11.8', '13.3', '15.0', '16.9', '19.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('31', '9.5', '10.7', '12.0', '13.5', '15.2', '17.1', '19.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('32', '9.6', '10.8', '12.1', '13.7', '15.4', '17.4', '19.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('33', '9.7', '10.9', '12.3', '13.8', '15.6', '17.6', '19.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('34', '9.8', '11.0', '12.4', '14.0', '15.8', '17.8', '20.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('35', '9.9', '11.2', '12.6', '14.2', '16.0', '18.1', '20.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('36', '10.0', '11.3', '12.7', '14.3', '16.2', '18.3', '20.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('37', '10.1', '11.4', '12.9', '14.5', '16.4', '18.6', '21.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('38', '10.2', '11.5', '13.0', '14.7', '16.6', '18.8', '21.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('39', '10.3', '11.6', '13.1', '14.8', '16.8', '19.0', '21.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('40', '10.4', '11.8', '13.3', '15.0', '17.0', '19.3', '21.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('41', '10.5', '11.9', '13.4', '15.2', '17.2', '19.5', '22.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('42', '10.6', '12.0', '13.6', '15.3', '17.4', '19.7', '22.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('43', '10.7', '12.1', '13.7', '15.5', '17.6', '20.0', '22.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('44', '10.8', '12.2', '13.8', '15.7', '17.8', '20.2', '23.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('45', '10.9', '12.4', '14.0', '15.8', '18.0', '20.5', '23.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('46', '11.0', '12.5', '14.1', '16.0', '18.2', '20.7', '23.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('47', '11.1', '12.6', '14.3', '16.2', '18.4', '20.9', '23.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('48', '11.2', '12.7', '14.4', '16.3', '18.6', '21.2', '24.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('49', '11.3', '12.8', '14.5', '16.5', '18.8', '21.4', '24.5');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('50', '11.4', '13.1', '14.8', '16.8', '19.2', '21.9', '25.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('51', '11.5', '13.1', '14.8', '16.8', '19.2', '21.9', '25.1');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('52', '11.6', '13.2', '15.0', '17.0', '19.4', '22.2', '25.4');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('53', '11.7', '13.3', '15.1', '17.2', '19.6', '22.4', '25.7');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('54', '11.8', '13.4', '15.2', '17.3', '19.8', '22.7', '26.0');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('55', '11.9', '13.5', '15.4', '17.5', '20.0', '22.9', '26.3');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('56', '12.0', '13.6', '15.5', '17.7', '20.2', '23.2', '26.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('57', '12.1', '13.7', '15.6', '17.8', '20.4', '23.4', '26.9');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('58', '12.2', '13.8', '15.8', '18.0', '20.6', '23.7', '27.2');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('59', '12.3', '14.0', '15.9', '18.2', '20.8', '23.9', '27.6');");
        db.execSQL("insert into antropometri_laki_bbu (laki_bbu_umur, laki_bbu_min3sd, laki_bbu_min2sd, laki_bbu_min1sd, laki_bbu_median, laki_bbu_1sd, laki_bbu_2sd, laki_bbu_3sd) values " +
                "('60', '12.4', '14.1', '16.0', '18.3', '21.0', '24.2', '27.9');");
    }

    // Get One List From Database
    public Cursor getLakiBBUList(Integer umur) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbu WHERE laki_bbu_umur = " + umur, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createLakiTBU(SQLiteDatabase db) {
        // Create Table Tahap Tumbuh Kembang
        String create_laki_tbu  = "create table antropometri_laki_tbu (" +
                "laki_tbu_ID integer primary key autoincrement, " +
                "laki_tbu_umur real not null," +
                "laki_tbu_min3sd real not null, " +
                "laki_tbu_min2sd real not null, " +
                "laki_tbu_min1sd real not null, " +
                "laki_tbu_median real not null, " +
                "laki_tbu_1sd real not null, " +
                "laki_tbu_2sd real not null, " +
                "laki_tbu_3sd real not null " +
                ");";
        db.execSQL(create_laki_tbu);
        insertLakiTBU(db);
    }

    // Insert List Imunisasi
    public void insertLakiTBU(SQLiteDatabase db) {
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('0', '44.2', '46.1', '48.0', '49.9', '51.8', '53.7', '55.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('1', '48.9', '50.8', '52.8', '54.7', '56.7', '58.6', '60.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('2', '52.4', '54.4', '56.4', '58.4', '60.4', '62.4', '64.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('3', '55.3', '57.3', '59.4', '61.4', '63.9', '66.0', '68.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('4', '57.6', '59.7', '61.8', '63.9', '66.0', '68.0', '70.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('5', '59.6', '61.7', '63.8', '65.9', '68.0', '70.1', '72.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('6', '61.2', '63.3', '65.5', '67.6', '69.8', '71.9', '74.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('7', '62.7', '64.8', '67.0', '69.2', '71.3', '73.5', '75.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('8', '64.0', '66.2', '68.4', '70.6', '72.8', '75.0', '77.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('9', '65.2', '67.5', '69.7', '72.0', '74.2', '76.5', '78.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('10', '66.4', '68.7', '71.0', '73.3', '75.6', '77.9', '80.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('11', '67.6', '69.9', '72.2', '74.5', '76.9', '79.2', '81.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('12', '68.6', '71.0', '73.4', '75.5', '78.1', '80.5', '82.9.');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('13', '69.6', '72.1', '74.5', '76.9', '79.3', '81.8', '84.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('14', '70.6', '73.1', '74.5', '78.0', '80.5', '83.0', '85.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('15', '71.6', '74.1', '76.6', '79.1', '81.7', '84.2', '86.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('16', '72.5', '75.0', '77.6', '80.2', '82.8', '85.4', '88.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('17', '73.3', '76.0', '78.6', '81.2', '83.9', '86.5', '89.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('18', '74.2', '76.9', '79.6', '82.3', '85,0', '87.7', '90.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('19', '75.0', '77.7', '80.5', '83.2', '86,0', '88.8', '91.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('20', '75.8', '78.6', '81.4', '84.2', '87.0', '89.8', '92.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('21', '76.5', '79.4', '82.3', '85.1', '88.0', '90.0', '93.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('22', '77.2', '80.2', '83.1', '86.0', '89.0', '91.9', '94.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('23', '78.0', '81.0', '83.9', '86.9', '89.9', '92.9', '95.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('24', '78.0', '81.0', '84.1', '87.1', '90.2', '93.2', '96.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('25', '78.6', '81.7', '84.9', '88.0', '91.1', '94.2', '97.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('26', '79.3', '82.5', '85.6', '88.8', '92.0', '95.2', '98.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('27', '79.9', '83.1', '86.4', '89.6', '92.9', '96.1', '99.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('28', '80.5', '83.8', '87.1', '90.4', '93.7', '97.0', '100.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('29', '81.1', '85.5', '87.8', '91.2', '94.5', '97.9', '101.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('30', '81.7', '85.1', '88.5', '91.9', '95.3', '98.7', '102.1');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('31', '82.3', '85.7', '89.2', '92.7', '96.1', '99.6', '103.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('32', '82.8', '86.4', '89.9', '93.4', '96.9', '100.4', '103.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('33', '83.4', '86.9', '90.5', '94.1', '97.6', '101.2', '104.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('34', '83.9', '87.5', '91.1', '94.8', '98.4', '102.0', '105.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('35', '84.4', '88.1', '91.8', '95.4', '99.1', '102.7', '106.4');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('36', '85.0', '88.7', '92.4', '96.1', '99.8', '103.5', '107.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('37', '85.5', '89.2', '93.0', '96.7', '100.5', '104.2', '108.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('38', '86.0', '89.8', '93.6', '97.4', '101.2', '105.0', '108.8');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('39', '86.5', '90.3', '94.2', '98.0', '101.8', '105.7', '109.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('40', '87.0', '90.9', '94.7', '98.6', '102.5', '106.4', '110.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('41', '87.5', '91.4', '95.3', '99.2', '103.2', '107.1', '111.0');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('42', '88.0', '91.9', '95.9', '99.9', '103.8', '107.8', '111.7');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('43', '88.4', '92.4', '96.4', '100.4', '104.5', '108.5', '112.5');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('44', '88.9', '93.0', '97.0', '101.0', '105.1', '109.1', '113.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('45', '89.4', '93.5', '97.5', '101.6', '105.7', '109.8', '113.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('46', '89.8', '94.0', '98.1', '102.2', '106.3', '110.4', '114.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('47', '90.3', '94.4', '98.6', '102.8', '106.9', '111.1', '115.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('48', '90.7', '94.9', '99.1', '103.3', '107.5', '111.7', '115.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('49', '91.2', '95.4', '99.7', '103.9', '108.1', '112.4', '116.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('50', '91.6', '95.9', '100.2', '104.4', '108.7', '113.0', '117.3');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('51', '92.1', '96.4', '100.7', '105.0', '109.3', '113.6', '117.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('52', '92.5', '96.9', '101.2', '105.6', '109.9', '114.2', '118.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('53', '93.0', '97.4', '101.7', '106.1', '110.5', '114.9', '118.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('54', '93.4', '97.8', '102.3', '106.7', '111.1', '115.5', '119.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('55', '93.9', '98.3', '102.8', '107.2', '111.7', '116.1', '120.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('56', '94.3', '98.8', '103.3', '107.8', '112.3', '116.7', '121.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('57', '94.7', '99.3', '103.8', '108.3', '112.8', '117.4', '121.9');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('58', '95.2', '99.7', '104.3', '108.9', '113.4', '118.0', '122.6');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('59', '95.6', '100.2', '104.8', '109.4', '114.0', '118.6', '123.2');");
        db.execSQL("insert into antropometri_laki_tbu (laki_tbu_umur, laki_tbu_min3sd, laki_tbu_min2sd, laki_tbu_min1sd, laki_tbu_median, laki_tbu_1sd, laki_tbu_2sd, laki_tbu_3sd) values " +
                "('60', '96.1', '100.7', '105.3', '110.0', '114.6', '119.2', '123.9');");
    }

    // Get One List From Database
    public Cursor getLakiTBUList(Integer umur) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_tbu WHERE laki_tbu_umur = " + umur, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createLakiBBTB_0_24(SQLiteDatabase db) {
        // Create Table Tahap Tumbuh Kembang
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
        insertLakiBBTB_0_24(db);
    }

    // Insert List Imunisasi
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
                "('97.5, '11.5', '12.4', '13.4', '14.5', '15.7', '17.1', '18.7');");
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

    // Get One List From Database
    public Cursor getLakiBBTBList_0_24(Integer tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_0_24 WHERE laki_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createLakiBBTB_24_60(SQLiteDatabase db) {
        // Create Table Tahap Tumbuh Kembang
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
        insertLakiBBTB_24_60(db);
    }

    // Insert List Imunisasi
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
        // masi lanjut
    }

    // Get One List From Database
    public Cursor getLakiBBTBList_24_60(Integer tinggi) {
        Cursor cursor = db.rawQuery("SELECT * FROM antropometri_laki_bbtb_24_60 WHERE laki_bbtb_tb = " + tinggi, null);
        cursor.moveToFirst();
        return cursor;
    }

        // Create Table List Imunisasi
    public void createList(SQLiteDatabase db) {
        // Create Table List Imunisasi
        String create_list_imunisasi = "create table list_imunisasi (" +
                "listID integer primary key autoincrement, " +
                "list_vaksin text not null, " +
                "list_umur text not null, " +
                "list_desc text not null," +
                "list_bulan integer not null" +
                ");";
        db.execSQL(create_list_imunisasi);
        insertList(db);
    }

    // Insert List Imunisasi
    public void insertList(SQLiteDatabase db) {
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('BCG', '0 - 3 Bulan', 'BCG merupakan singkatan dari Bacille Calmette-Guerin. BCG diberikan sejak lahir. Apabila umur > 3 bulan harus dilakukan uji tuberkulin terlebih dulu, BCG diberikan apabila uji tuberkulin negatif.', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hepatitis B (1)', '12 jam setelah lahir', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio-0', 'Kunjungan Pertama', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '0');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hepatitis B (2)', '1 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '1');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio (1)', '2 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).' , '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('DPT (1)', '2 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hib (1)', '2 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (1)', '2 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '2');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio (2)', '4 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('DPT (2)', '4 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hib (2)', '4 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (2)', '4 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '4');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hepatitis B (3)', '6 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio (3)', '6 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('DPT (3)', '6 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hib (3)', '6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (3)', '6 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Influenza (1)', '6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '6');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Campak (1)', '9 Bulan', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.', '9');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Pneumokokus (PVC) (4)', '12 - 15 Bulan / 1 Tahun - 1 Tahun 3 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Varisela', '1 - 18 Tahun', 'Vaksin ini dapat diberikan setelah umur 12 bulan, terbaik pada umur sebelum masuk sekolah dasar. Apabila diberikan pada umur lebih dari 12 tahun, perlu 2 dosis dengan interval minimal 4 minggu.', '12');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hib (4)', '15 - 18 Bulan / 1 Tahun 3 Bulan - 1 Tahun 6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('MMR (1)', '15 Bulan / 1 Tahun 3 Bulan', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.', '15');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('DPT (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Influenza (2)', '18 Bulan / 1 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '18');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Influenza (3)', '24 Bulan / 2 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '24');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Tifoid (1)', '24 Bulan / 2 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.', '24');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hepatitis A (1)', '24 Bulan / 2 Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.', '24');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Hepatitis A (2)', '36 Bulan / 3   Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.', '36');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Influenza (4)', '36 Bulan / 3 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '36');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Influenza (4)', '48 Bulan / 4 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.', '48');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Polio (5)', '60 Bulan / 5 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('DPT (5)', '60 Bulan / 5 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Campak (2)', '60 Bulan / 5 Tahun', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('Tifoid (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.', '60');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc, list_bulan) values " +
                "('MMR (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.', '60');");
    }

    // Get List from Database
    public Cursor getList() {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List From Database
    public Cursor getOneList(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi WHERE listID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Join Riwayat From Database
    public Cursor getListJoinRiwayat(Integer id, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi ON list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin AND riwayat_imunisasi.riwayat_profilID = " + profilID + " WHERE list_imunisasi.listID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List From Database
    public Cursor getOneListByBulan(Integer bulan) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi WHERE list_bulan <= " + bulan, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One List Join Riwayat From Database
    public Cursor getListJoinRiwayatClosestValue(Integer bulan, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM list_imunisasi LEFT OUTER JOIN riwayat_imunisasi ON list_imunisasi.list_vaksin = riwayat_imunisasi.riwayat_vaksin AND riwayat_imunisasi.riwayat_profilID = " + profilID + " WHERE list_imunisasi.list_bulan BETWEEN " + bulan + " AND " + bulan + "+1 AND riwayat_imunisasi.riwayatID ISNULL ORDER BY ABS( " + bulan + " - riwayat_imunisasi.riwayat_bulan)", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Riwayat Imunisasi
    public void createRiwayat(SQLiteDatabase db) {
        // Create Table Profil
        String create_riwayat = "create table riwayat_imunisasi (" +
                "riwayatID integer primary key autoincrement, " +
                "riwayat_profilID integer not null, " +
                "riwayat_vaksin text not null," +
                "riwayat_tanggal text not null," +
                "riwayat_umur text not null," +
                "riwayat_bulan real, " +
                "riwayat_berat real, " +
                "riwayat_tinggi real, " +
                "riwayat_dokter text, " +
                "riwayat_rumahsakit text, " +
                "FOREIGN KEY ( riwayat_vaksin  ) REFERENCES profil  ( list_vaksin )" +
                "FOREIGN KEY ( riwayat_profilID  ) REFERENCES profil  ( profilID ));";
        db.execSQL(create_riwayat);
    }

    // Insert Riwayat Imunisasi to Database

    public long insertRiwayat(Integer profilID, String tanggal, String vaksin, String umur, Integer bulan,
                              String berat, String tinggi, String dokter, String rumahsakit) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("riwayat_profilID", profilID);
        values.put("riwayat_tanggal", tanggal);
        values.put("riwayat_vaksin", vaksin);
        values.put("riwayat_umur", umur);
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
        Cursor cursor = db.rawQuery("SELECT * FROM riwayat_imunisasi WHERE riwayat_profilID = " + id , null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Profil From Database
    public Cursor getOneRiwayat(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM riwayat_imunisasi WHERE riwayatID = " + id, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Update Riwayat Imunisasi to Database
    public long updateRiwayat(Integer riwayatID, Integer profilID, String tanggal, String vaksin, String umur, int bulan,
                              String berat, String tinggi, String dokter, String rumahsakit) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("riwayat_profilID", profilID);
        values.put("riwayat_tanggal", tanggal);
        values.put("riwayat_vaksin", vaksin);
        values.put("riwayat_umur", umur);
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

    // Delete Riwyat Imunisasi from ProfilID from Database
    public boolean deleteRiwayatProfilID(int profilID) {
        // Delete Data
        return db.delete("riwayat_imunisasi", "riwayat_profilID =" + profilID, null) < 1;
    }

    // Create Table Tahapan Tumbuh Kembang
    public void createTahap(SQLiteDatabase db) {
        // Create Table Tahap Tumbuh Kembang
        String create_tahap_tumbang = "create table tahap_tumbang (" +
                "tahapID integer primary key autoincrement, " +
                "tahap_umur text," +
                "tahap_gerakan_kasar text, " +
                "tahap_gerakan_halus text, " +
                "tahap_komunikasi text, " +
                "tahap_sosial_kemandirian text " +
                ");";
        db.execSQL(create_tahap_tumbang);
        insertTahapan(db);
    }

    // Insert Tahapan Tumbuh Kembang
    public void insertTahapan(SQLiteDatabase db) {
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('1 Bulan', 'Tangan dan kaki bergerak aktif', 'Kepala menoleh ke samping kanan-kiri', 'Bereaksi terhadap bunyi lonceng', 'Menatap wajah ibu / pengasuh');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('2 Bulan', 'Mengangkat kepala ketika tengkurap', '', 'Bersuara', 'Tersenyum spontan');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('3 Bulan', 'Kepala tegak ketika didudukkan', 'Memegang mainan', 'Tertawa / berteriak', 'Memandang tangannya');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('4 Bulan', 'Tengkurap-terlentang sendiri', '', '', '');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('5 Bulan', '', 'Meraih / menggapai', 'Menoleh ke suara', 'Meraih mainan');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('6 Bulan', 'Duduk tanpa pegangan', '', '', 'Memasukkan benda ke mulut');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('7 Bulan', '', 'Mengambil dengan tangan kanan dan kiri', 'Bersuara', '');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('8 Bulan', 'Berdiri berpegangan', '', '', '');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('9 Bulan', '', 'Menjimpit', '', 'Melambaikan tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('10 Bulan', '', 'Memukul mainan dengan kedua tangan', '', 'Bertepuk tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('11 Bulan', '', '', 'Memanggil papa-mama', 'Menunjuk dan meminta');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('12 Bulan', 'Berdiri tanpa berpegangan', 'Memasukkan mainan ke cangkir', '', 'Bermain dengan orang lain');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('15 Bulan / 1 Tahun 3 Bulan', 'Berjalan', 'Mencoret-coret', 'Berbicara 2 kata', 'Minum dari gelas');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('18 Bulan / 1 Tahun 6 Bulan', 'Berlari - Naik tangga', 'Menumpuk 2 mainan', 'Berbicara beberapa kata', 'Menyuapi boneka menggunakan sendok');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('24 Bulan / 2 Tahun', 'Menendang Bola', 'Menumpuk 4 mainan', 'Menunjuk gambar', 'Melepaskan pakaian - Memakai pakaian - Menyikat gigi');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('30 Bulan / 2 Tahun 6 Bulan', 'Melompat', '', 'Menunjuk bagian tubuh', 'Mencuci dan mengeringkan tangan');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('36 Bulan / 3 Tahun', '', 'Menggambar garis tegak', 'Menyebutkan warna benda', 'Menyebutkan nama teman');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('42 Bulan / 3 Tahun 6 Bulan', 'Naik sepeda roda tiga', 'Menggambar lingkaran', 'Bercerita singkat menyebutkan penggunaan benda', 'Memakai baju kaos');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('48 Bulan / 4 Tahun', '', 'Menggambar tanda tambah', '', 'Memakai baju tanpa dibantu');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('56 Bulan / 4 Tahun 6 bulan', '', 'Menggambar manusia (kepala, badan, kaki)', '', 'Bermain kartu - Menyikat gigi tanpa dibantu');");
        db.execSQL("insert into tahap_tumbang (tahap_umur, tahap_gerakan_kasar, tahap_gerakan_halus, tahap_komunikasi, tahap_sosial_kemandirian) values " +
                "('62 Bulan / 5 Tahun', '', '', 'Menghitung mainan', 'Mengambil makanan sendiri');");
    }

    // Get Tahapan from Database
    public Cursor getTahap() {
        Cursor cursor = db.rawQuery("SELECT * FROM tahap_tumbang", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Create Table Galeri
    public void createGaleri(SQLiteDatabase db) {
        // Create Table Profil
        String create_galeri = "create table galeri_tumbang(" +
                "galeriID integer primary key autoincrement, " +
                "galeri_profilID integer not null, " +
                "galeri_tanggal text not null," +
                "galeri_umur text," +
                "galeri_foto text not null," +
                "galeri_desc text not null, " +
                "FOREIGN KEY (  galeri_profilID ) REFERENCES profil ( profilID ));";
        db.execSQL(create_galeri);
    }

    // Insert Galeri to Database
    public long insertGaleri(Integer profilID, String tanggal, String umur, String foto, String desc) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("galeri_profilID", profilID);
        values.put("galeri_tanggal", tanggal);
        values.put("galeri_umur", umur);
        values.put("galeri_foto", foto);
        values.put("galeri_desc", desc);

        // Insert Data
        return db.insert("galeri_tumbang", null, values);
    }

    // Get Profil from Database
    public Cursor getGaleri(Integer id) {
        Cursor cursor = db.rawQuery("SELECT * FROM galeri_tumbang WHERE galeri_profilID = " + id + " ORDER BY galeri_foto ASC", null);
        cursor.moveToFirst();
        return cursor;
    }

    // Get One Galeri From Database
    public Cursor getOneGaleri(Integer galeriID, Integer profilID) {
        Cursor cursor = db.rawQuery("SELECT * FROM galeri_tumbang WHERE galeriID = " + galeriID + " AND galeri_profilID = " + profilID, null);
        cursor.moveToFirst();
        return cursor;
    }

    // Insert Galeri to Database
    public long updateGaleri(Integer galeriID, Integer profilID, String tanggal, String umur, String foto, String desc) {

        // Open Database to Write
        SQLiteDatabase db = this.getWritableDatabase();

        // Get Value
        ContentValues values = new ContentValues();
        values.put("galeri_tanggal", tanggal);
        values.put("galeri_umur", umur);
        values.put("galeri_foto", foto);
        values.put("galeri_desc", desc);

        // Update Data
        return db.update("galeri_tumbang", values, "galeriID = " + galeriID + " AND galeri_profilID = " + profilID, null);
    }

    // Delete Galeri from Database
    public boolean deleteGaleri(int id) {
        // Delete Data
        return db.delete("galeri_tumbang", "galeriID =" + id, null) < 1;
    }

    // Delete Galeri from ProfilID from Database
    public boolean deleteGaleriProfilID(int id) {
        // Delete Data
        return db.delete("galeri_tumbang", "galeri_profilID =" + id, null) < 1;
    }

    // Create Table Rekam Medis
    public void createMedis(SQLiteDatabase db) {
        // Create Table List Imunisasi
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
        Cursor cursor = db.rawQuery("SELECT * FROM rekam_medis WHERE medis_profilID = " + id , null);
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

    // Delete Medis from Database
    public boolean deleteMedis(int id) {
        // Delete Data
        return db.delete("rekam_medis", "medisID =" + id, null) < 1;
    }

    // Delete Medis from ProfilID from Database
    public boolean deleteMedisProfilID(int profilID) {
        // Delete Data
        return db.delete("rekam_medis", "medis_profilID =" + profilID, null) < 1;
    }
}
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
        createList(db);
        createTahap(db);
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
                "profil_foto text);";
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

    // Create Table List Imunisasi
    public void createList(SQLiteDatabase db) {
        // Create Table List Imunisasi
        String create_list_imunisasi = "create table list_imunisasi (" +
                "listID integer primary key autoincrement, " +
                "list_vaksin text not null, " +
                "list_umur text not null, " +
                "list_desc text not null" +
                ");";
        db.execSQL(create_list_imunisasi);
        insertList(db);
    }

    // Insert List Imunisasi
    public void insertList(SQLiteDatabase db) {
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('BCG', '0 - 3 Bulan', 'BCG merupakan singkatan dari Bacille Calmette-Guerin. BCG diberikan sejak lahir. Apabila umur > 3 bulan harus dilakukan uji tuberkulin terlebih dulu, BCG diberikan apabila uji tuberkulin negatif.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hepatitis B (1)', '12 jam setelah lahir', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio-0', 'Kunjungan Pertama', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hepatitis B (2)', '1 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio (1)', '2 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('DPT (1)', '2 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hib (1)', '2 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Pneumokokus (PVC) (1)', '2 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio (2)', '4 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('DPT (2)', '4 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hib (2)', '4 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Pneumokokus (PVC) (2)', '4 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hepatitis B (3)', '6 Bulan', 'Diberikan dalam waktu 12 jam setelah lahir, dilanjutkan pada umur 1 dan 3 hingga 6 bulan. Interval dosis minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio (3)', '6 Bulan', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('DPT (3)', '6 Bulan', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hib (3)', '6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Pneumokokus (PVC) (3)', '6 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Influenza (1)', '6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Campak (1)', '9 Bulan', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Pneumokokus (PVC) (4)', '12 - 15 Bulan / 1 Tahun - 1 Tahun 3 Bulan', 'Pada anak yang belum mendapatkan PCV pada umur 1 tahun PCV diberikan dua kali dengan interval 2 bulan. Pada umur 2 hingga 5 tahun diberikan satu kali.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Varisela', '1 - 18 Tahun', 'Vaksin ini dapat diberikan setelah umur 12 bulan, terbaik pada umur sebelum masuk sekolah dasar. Apabila diberikan pada umur lebih dari 12 tahun, perlu 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hib (4)', '15 - 18 Bulan / 1 Tahun 3 Bulan - 1 Tahun 6 Bulan', 'Diberikan mulai umur 2 bulan dengan interval 2 bulan. Diberikan terpisah atau kombinasi.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('MMR (1)', '15 Bulan / 1 Tahun 3 Bulan', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('DPT (4)', '18 - 24 Bulan / 1 Tahun 6 Bulan - 2 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Influenza (2)', '18 Bulan / 1 Tahun 6 Bulan', 'Pemberian berulang 1x setiap tahun. Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Influenza (3)', '24 Bulan / 2 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Tifoid (1)', '24 Bulan / 2 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hepatitis A (1)', '24 Bulan / 2 Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Hepatitis A (2)', '36 Bulan / 3   Tahun', 'Hepatitis A diberikan pada umur > 2 tahun, dua kali dengan interval 6 hingga 12 bulan.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Influenza (4)', '36 Bulan / 3 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Influenza (4)', '48 Bulan / 4 Tahun', 'Pemberian berulang 1x setiap tahun.  Umur < 8 tahun yang mendapat vaksin influenza trivalen (TIV) pertama kalinya harus mendapat 2 dosis dengan interval minimal 4 minggu.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Polio (5)', '60 Bulan / 5 Tahun', 'Polio-0 diberikan pada saat kunjungan pertama. Untuk bayi yang lahir di RB/RS OPV diberikan pada saat bayi dipulangkan (untuk menghindari transmisi virus vaksin kepada bayi lain).');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('DPT (5)', '60 Bulan / 5 Tahun', 'DPT merupakan singaktan dari Difteri, Pertutis, dan Tetanus. Diberikan pada umur > 6 minggu, DTwP atau secara kombinasi dengan Hep B atau Hib. Ulangan DTP umur 18 bulan dan 5 tahun. Umur 12 tahun mendapat TT pada program BIAS SD kelas VI.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Campak (2)', '60 Bulan / 5 Tahun', 'Campak-1 umur 9 bulan, campak-2 diberikan pada program BIAS pada SD kelas 1, umur 6 tahun.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('Tifoid (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Tifoid polisakarida injeksi diberikan pada umur > 2 tahun, diulang setiap 3 tahun.');");
        db.execSQL("insert into list_imunisasi (list_vaksin, list_umur, list_desc) values " +
                "('MMR (2)', '60 - 84 Bulan / 5 - 7 Tahun', 'Diberikan pada umur 12 bulan, apabila belum mendapatkan vaksin campak umur 9 bulan. Selanjutnya MMR ulangan diberikan pada umur 5-7 tahun.');");
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
        Cursor cursor = db.rawQuery("SELECT * FROM rekam_medis WHERE medis_profilID = " + id + " ORDER BY medis_tanggal ASC", null);
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
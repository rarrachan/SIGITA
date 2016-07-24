package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfilUbah extends ProfilDetail implements OnClickListener {

    // Declare
    private TextView ubah_profil;
    private TextView profil_id;
    private TextView profil_nama_sebelumnya;
    private TextView text_profil_nama;
    private EditText profil_nama;
    private TextView text_profil_jeniskelamin;
    private RadioGroup profil_jeniskelamin;
    private RadioButton profil_lakilaki;
    private RadioButton profil_perempuan;
    private TextView text_profil_golongandarah;
    private RadioGroup profil_golongandarah;
    private RadioButton profil_A;
    private RadioButton profil_B;
    private RadioButton profil_AB;
    private RadioButton profil_O;
    private TextView text_profil_tempatlahir;
    private TextView text_profil_tanggallahir;
    private EditText profil_tempatlahir;
    private EditText profil_tanggallahir;
    private TextView text_profil_alergi;
    private EditText profil_alergi;
    private TextView text_profil_penyakitkronis;
    private EditText profil_penyakitkronis;
    private TextView text_profil_panjanglahir;
    private EditText profil_panjanglahir;
    private TextView profil_centimeter;
    private TextView text_profil_beratlahir;
    private EditText profil_beratlahir;
    private TextView profil_gram;
    private TextView text_footer;
    private ImageView profil_foto;
    private ImageView button_simpan;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.ubah_profil);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        String nama_sebelumnya = fetchID.getStringExtra("nama");
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Open Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getOneProfil(id);
        cursor.moveToFirst();

        // Load Widget
        ubah_profil = (TextView) findViewById(R.id.ubah_profil);
        profil_id = (TextView) findViewById(R.id.profil_id);
        profil_nama_sebelumnya = (TextView) findViewById(R.id.profil_nama_sebelumnya);
        text_profil_nama = (TextView) findViewById(R.id.text_profil_nama);
        profil_nama = (EditText) findViewById(R.id.profil_nama);
        text_profil_jeniskelamin = (TextView) findViewById(R.id.text_profil_jeniskelamin);
        profil_lakilaki = (RadioButton) findViewById(R.id.profil_lakilaki);
        profil_perempuan = (RadioButton) findViewById(R.id.profil_perempuan);
        text_profil_tempatlahir = (TextView) findViewById(R.id.text_profil_tempatlahir);
        text_profil_tanggallahir = (TextView) findViewById(R.id.text_profil_tanggallahir);
        profil_tempatlahir = (EditText) findViewById(R.id.profil_tempatlahir);
        profil_tanggallahir = (EditText) findViewById(R.id.profil_tanggallahir);
        text_profil_alergi = (TextView) findViewById(R.id.text_profil_alergi);
        profil_alergi = (EditText) findViewById(R.id.profil_alergi);
        text_profil_penyakitkronis = (TextView) findViewById(R.id.text_profil_penyakitkronis);
        profil_penyakitkronis = (EditText) findViewById(R.id.profil_penyakitkronis);
        text_profil_panjanglahir = (TextView) findViewById(R.id.text_profil_panjanglahir);
        profil_panjanglahir = (EditText) findViewById(R.id.profil_panjanglahir);
        profil_centimeter = (TextView) findViewById(R.id.profil_centimeter);
        text_profil_beratlahir = (TextView) findViewById(R.id.text_profil_beratlahir);
        profil_beratlahir = (EditText) findViewById(R.id.profil_beratlahir);
        profil_gram = (TextView) findViewById(R.id.profil_gram);
        text_footer = (TextView) findViewById(R.id.text_footer);
        profil_foto = (ImageView) findViewById(R.id.profil_foto);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);
        profil_jeniskelamin = (RadioGroup) findViewById(R.id.profil_jeniskelamin);
        text_profil_golongandarah =(TextView) findViewById(R.id.text_profil_golongandarah);
        profil_golongandarah = (RadioGroup) findViewById(R.id.profil_golongandarah);
        profil_A = (RadioButton) findViewById(R.id.profil_A);
        profil_B = (RadioButton) findViewById(R.id.profil_B);
        profil_AB = (RadioButton) findViewById(R.id.profil_AB);
        profil_O = (RadioButton) findViewById(R.id.profil_O);

        // Set OnClickLIstener
        profil_foto.setOnClickListener(this);
        profil_tanggallahir.setOnClickListener(this);
        button_simpan.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        ubah_profil.setTypeface(typeface);
        text_profil_nama.setTypeface(typeface);
        profil_nama.setTypeface(typeface);
        text_profil_jeniskelamin.setTypeface(typeface);
        profil_lakilaki.setTypeface(typeface);
        profil_perempuan.setTypeface(typeface);
        text_profil_tempatlahir.setTypeface(typeface);
        text_profil_tanggallahir.setTypeface(typeface);
        profil_tempatlahir.setTypeface(typeface);
        profil_tanggallahir.setTypeface(typeface);
        text_profil_alergi.setTypeface(typeface);
        profil_alergi.setTypeface(typeface);
        text_profil_penyakitkronis.setTypeface(typeface);
        profil_penyakitkronis.setTypeface(typeface);
        text_profil_panjanglahir.setTypeface(typeface);
        profil_panjanglahir.setTypeface(typeface);
        profil_centimeter.setTypeface(typeface);
        text_profil_beratlahir.setTypeface(typeface);
        profil_beratlahir.setTypeface(typeface);
        profil_gram.setTypeface(typeface);
        text_profil_golongandarah.setTypeface(typeface);
        profil_A.setTypeface(typeface);
        profil_B.setTypeface(typeface);
        profil_AB.setTypeface(typeface);
        profil_O.setTypeface(typeface);
        text_profil_golongandarah.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        // Show Data From Database
        profil_id.setText(Integer.valueOf(id).toString());
        profil_nama_sebelumnya.setText(nama_sebelumnya);
        profil_nama.setText(cursor.getString(cursor.getColumnIndex("profil_nama")));
        if (cursor.getString(cursor.getColumnIndex("profil_jenisKelamin")).equals("L")) {
            profil_lakilaki.setChecked(true);
        } else {
            profil_perempuan.setChecked(true);
        }
        if (cursor.getString(cursor.getColumnIndex("profil_golonganDarah")).equals("A")) {
            profil_A.setChecked(true);
        } else if (cursor.getString(cursor.getColumnIndex("profil_golonganDarah")).equals("B")) {
            profil_B.setChecked(true);
        } else if (cursor.getString(cursor.getColumnIndex("profil_golonganDarah")).equals("AB")) {
            profil_AB.setChecked(true);
        } else if (cursor.getString(cursor.getColumnIndex("profil_golonganDarah")).equals("O")) {
            profil_O.setChecked(true);
        }
        profil_panjanglahir.setText(cursor.getString(cursor.getColumnIndex("profil_panjangLahir")));
        profil_beratlahir.setText(cursor.getString(cursor.getColumnIndex("profil_beratLahir")));
        profil_tempatlahir.setText(cursor.getString(cursor.getColumnIndex("profil_tempatLahir")));
        profil_tanggallahir.setText(cursor.getString(cursor.getColumnIndex("profil_tanggalLahir")));
        profil_alergi.setText(cursor.getString(cursor.getColumnIndex("profil_alergi")));
        profil_penyakitkronis.setText(cursor.getString(cursor.getColumnIndex("profil_penyakitKronis")));
        final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + cursor.getString(cursor.getColumnIndex("profil_nama")).replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("profil_foto"));
        if (Drawable.createFromPath(foto_path) != null) {
            profil_foto.setImageDrawable(Drawable.createFromPath(foto_path));
        }
    }

    // OnClick Activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Get Photo
            case R.id.profil_foto:
                // Declare Option Dialog
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                // Ceate Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfilUbah.this);
                builder.setTitle("Choose Photo");
                // Set OnClickListener Dialog
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // Use Camera
                        if (options[item].equals("Take Photo")) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(intent, 1);

                        }
                        // From Gallery
                        else if (options[item].equals("Choose from Gallery")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);

                        }
                        // Cancel
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                break;

            // Get Tanggal Lahir
            case R.id.profil_tanggallahir:
                // Declare Calendar
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                // Create Dialog DataPicker
                DatePickerDialog mDatePicker = new DatePickerDialog(ProfilUbah.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        profil_tanggallahir.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.show();
                break;

            // Simpan
            case R.id.button_simpan:
                // Get Value
                final String nama_sebelumnya = profil_nama_sebelumnya.getText().toString().replaceAll(" ", "_");
                String profilID = profil_id.getText().toString();
                final int id = Integer.parseInt(profilID);
                String nama = profil_nama.getText().toString();
                String panjangLahir = profil_panjanglahir.getText().toString();
                String beratLahir = profil_beratlahir.getText().toString();
                String tmptLahir = profil_tempatlahir.getText().toString();
                String tglLahir = profil_tanggallahir.getText().toString();
                String alergi = profil_alergi.getText().toString();
                String penyakitKronis = profil_penyakitkronis.getText().toString();
                BitmapDrawable drawable_foto = (BitmapDrawable) profil_foto.getDrawable();

                // Change Space into UnderScore
                String foto = "profil_" + nama.replaceAll(" ", "_").toLowerCase() + ".jpg";
                String foto_sebelumnya = "profil_" + nama_sebelumnya.replaceAll(" ", "_").toLowerCase() + ".jpg";
                String namaFolder = nama.replaceAll(" ", "_");
                String fotoPath = foto.replaceAll(" ", "_").toLowerCase();

                // Check if Value Empty
                if (TextUtils.isEmpty(nama) ) {
                    // Show Toast
                    Toast.makeText(this, "Kolom Nama Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(panjangLahir)) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Panjang Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(beratLahir)) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Berat Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tmptLahir)) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Tempat Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tglLahir)) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Tanggal Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (profil_jeniskelamin.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Jenis Kelamin Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (profil_golongandarah.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Kolom Golongan Darah Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(panjangLahir) < 45) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Panjang Lahir Tidak Boleh Kurang Dari 45 cm", Toast.LENGTH_SHORT).show();

                }  else if (Float.parseFloat(panjangLahir) > 120) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Panjang Lahir Tidak Boleh Lebih Dari 120 cm", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratLahir) < 2) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Berat Lahir Tidak Boleh Kurang Dari 2 kg", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratLahir) > 30) {
                    // Show Toast
                    Toast.makeText(ProfilUbah.this, "Berat Lahir Tidak Boleh Lebih Dari 30 kg", Toast.LENGTH_SHORT).show();

                } else {

                    String golonganDarah = ((RadioButton) findViewById(profil_golongandarah.getCheckedRadioButtonId())).getText().toString();
                    String jenisKelamin = ((RadioButton) findViewById(profil_jeniskelamin.getCheckedRadioButtonId())).getText().toString();

                    //check sd card
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        // Get Path Directory
                        String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

                        // Folder Directory Using Old Name
                        File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + nama_sebelumnya);

                        // Folder Directory Using New Name
                        File photoDirectoryBaru = new File(sdCardDirectory + "/SIGITA/" + namaFolder);

                        // Old Directory, Old Photo Name
                        File profil_foto_sebelumnya = new File(photoDirectory, foto_sebelumnya);

                        // Old Directory, New Photo Name
                        File profil_foto = new File(photoDirectory, foto);

                        // Declare Condition
                        boolean success = false;

                        // Remove Photo
                        if (profil_foto.exists()) {
                            profil_foto.delete();
                        } else if (profil_foto_sebelumnya.exists()) {
                            profil_foto_sebelumnya.delete();
                        }

                        try {
                            // Set Location Directory
                            FileOutputStream outStream;
                            outStream = new FileOutputStream(profil_foto);

                            // Compressed Photo
                            Bitmap bitmap = drawable_foto.getBitmap();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                            /* 100 to keep full quality of the image */

                            // Put Photo Into Directory
                            outStream.flush();
                            outStream.close();

                            // Scan Gallery
                            MediaScannerConnection.scanFile(ProfilUbah.this,
                                    new String[]{profil_foto.toString()}, null,
                                    new MediaScannerConnection.OnScanCompletedListener() {
                                        public void onScanCompleted(String path, Uri uri) {
                                        }
                                    });

                            // Declare Condition
                            success = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Change Folder Name
                        if (!nama_sebelumnya.equals(namaFolder)) {
                            profil_foto_sebelumnya.renameTo(profil_foto);
                            photoDirectory.renameTo(photoDirectoryBaru);
                            success = true;
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(getApplicationContext(), "Profil Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(getApplicationContext(), "Profil Gagal Tersimpan", Toast.LENGTH_LONG).show();
                        }
                    }

                    // Update Data into Database
                    db.updateProfil(id, nama, tmptLahir, tglLahir, jenisKelamin, golonganDarah, panjangLahir, beratLahir, alergi, penyakitKronis, fotoPath);

                    // Check Session
                    SessionManager session = new SessionManager();
                    if(session.checkSession(this)){
                        if (session.loadSession(this, "id").equals(profilID)) {
                            session.clearSession(ProfilUbah.this);
                            session.createSession(ProfilUbah.this, "id", profilID);
                            session.createSession(ProfilUbah.this, "nama", nama);
                            session.createSession(ProfilUbah.this, "gender", jenisKelamin);
                            session.createSession(ProfilUbah.this, "tanggallahir", tglLahir);
                        }
                    }

                    Intent fetchID = getIntent();
                    String pathbefore = fetchID.getStringExtra("pathbefore");
                    int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

                    // Start Profil Activity
                    Intent detail_profil = new Intent(this, ProfilDetail.class);
                    lastActivity = System.currentTimeMillis();
                    detail_profil.putExtra("lastActivity", lastActivity);
                    detail_profil.putExtra("id", id);
                    detail_profil.putExtra("pathbefore", pathbefore);
                    detail_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                    detail_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(detail_profil);

                    // Close This Activity
                    finish();
                }
                break;
        }
    }

    // Activity for Dialog Get Photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // Use Camera
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    // Rotate Image
                    ExifInterface exif = new ExifInterface(f.getAbsolutePath());
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                    Matrix matrix = new Matrix();

                    if ((orientation == ExifInterface.ORIENTATION_ROTATE_180)) {
                        matrix.postRotate(180);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                        matrix.postRotate(90);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                        matrix.postRotate(270);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    }

                    // Resize Large Image
                    int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);

                    // Set Image
                    profil_foto.setImageBitmap(scaled);

                    // Save Image Temporary
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // From Gallery
            else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmapOptions.inSampleSize = 2;
                    bitmap = BitmapFactory.decodeFile(picturePath, bitmapOptions);

                    // Rotate Image
                    ExifInterface exif = new ExifInterface(picturePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                    Matrix matrix = new Matrix();

                    if ((orientation == ExifInterface.ORIENTATION_ROTATE_180)) {
                        matrix.postRotate(180);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                        matrix.postRotate(90);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                        matrix.postRotate(270);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                    }

                    // Resize Large Image
                    int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);

                    // Set Image
                    profil_foto.setImageBitmap(scaled);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        String pathbefore = fetchID.getStringExtra("pathbefore");
        int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Start Profil Activity
        Intent detail_profil = new Intent(this, ProfilDetail.class);
        lastActivity = System.currentTimeMillis();
        detail_profil.putExtra("lastActivity", lastActivity);
        detail_profil.putExtra("id", id);
        detail_profil.putExtra("pathbefore", pathbefore);
        detail_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
        detail_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(detail_profil);

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
            session.clearSession(ProfilUbah.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}
package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahProfil extends Activity implements OnClickListener {

    // Declare
    private TextView tambah_profil;
    private TextView text_profil_nama;
    private EditText profil_nama;
    private TextView text_profil_jeniskelamin;
    private RadioGroup profil_jeniskelamin;
    private RadioButton profil_lakilaki;
    private RadioButton profil_perempuan;
    private RadioGroup profil_golongandarah;
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
    private ImageView button_simpan;
    private ImageView profil_foto;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tambah_profil);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        tambah_profil = (TextView) findViewById(R.id.tambah_profil);
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
        profil_golongandarah = (RadioGroup) findViewById(R.id.profil_golongandarah);

        // Set OnClickListener
        button_simpan.setOnClickListener(this);
        profil_foto.setOnClickListener(this);
        profil_tanggallahir.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        tambah_profil.setTypeface(typeface);
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
        text_footer.setTypeface(typeface);

    }

    // OnClick Activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Get Photo
            case R.id.profil_foto:
                // Declare Option Dialog
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                // Create Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahProfil.this);
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
                DatePickerDialog mDatePicker = new DatePickerDialog(TambahProfil.this, new DatePickerDialog.OnDateSetListener() {
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
                final String nama = profil_nama.getText().toString();
                final String panjangLahir = profil_panjanglahir.getText().toString();
                final String beratLahir = profil_beratlahir.getText().toString();
                final String tmptLahir = profil_tempatlahir.getText().toString();
                final String tglLahir = profil_tanggallahir.getText().toString();
                final String alergi = profil_alergi.getText().toString();
                final String penyakitKronis = profil_penyakitkronis.getText().toString();
                final String passcode = "";
                final BitmapDrawable drawable_foto = (BitmapDrawable) profil_foto.getDrawable();

                // Change Space into UnderScore
                final String foto = "profil_" + nama.replaceAll(" ", "_").toLowerCase() + ".jpg";
                final String namaFolder = nama.replaceAll(" ", "_");

                // Check if Value Empty
                if (TextUtils.isEmpty(nama) ) {
                    // Show Toast
                    Toast.makeText(this, "Kolom Nama Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(panjangLahir)) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Panjang Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(beratLahir)) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Berat Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tmptLahir)) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Tempat Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(tglLahir)) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Tanggal Lahir Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (profil_jeniskelamin.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Jenis Kelamin Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (profil_golongandarah.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Kolom Golongan Darah Belum Terisi", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(panjangLahir) < 45) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Panjang Lahir Tidak Boleh Kurang Dari 45 cm", Toast.LENGTH_SHORT).show();

                }  else if (Float.parseFloat(panjangLahir) > 120) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Panjang Lahir Tidak Boleh Lebih Dari 120 cm", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratLahir) < 2) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Berat Lahir Tidak Boleh Kurang Dari 2 kg", Toast.LENGTH_SHORT).show();

                } else if (Float.parseFloat(beratLahir) > 30) {
                    // Show Toast
                    Toast.makeText(TambahProfil.this, "Berat Lahir Tidak Boleh Lebih Dari 30 kg", Toast.LENGTH_SHORT).show();

                } else {
                    // Create Dialog
                    final Dialog dialog = new Dialog(TambahProfil.this);
                    dialog.setContentView(R.layout.alert_tambah_passcode);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();

                    // Load Dialog Widget
                    TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                    TextView alert_tambah = (TextView) dialog.findViewById(R.id.alert_tambah);
                    ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                    ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                    // Set Custom Font Dialog
                    Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
                    alert_tambah.setTypeface(typeface);
                    alert_warning.setTypeface(typeface);

                    // Set OnClickListener Dialog
                    button_batal.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close Dialog
                            dialog.dismiss();

                            String jenisKelamin = ((RadioButton) findViewById(profil_jeniskelamin.getCheckedRadioButtonId())).getText().toString();
                            String golonganDarah = ((RadioButton) findViewById(profil_golongandarah.getCheckedRadioButtonId())).getText().toString();
                            //check sd card
                            String state = Environment.getExternalStorageState();
                            if (Environment.MEDIA_MOUNTED.equals(state)) {
                                // Get Path Directory
                                String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

                                // Create Directory
                                File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + namaFolder);
                                photoDirectory.mkdirs();

                                // Declare Condition
                                boolean success = false;

                                try {
                                    FileOutputStream outStream;

                                    // Set Location Directory
                                    File profil_foto = new File(photoDirectory, foto);
                                    outStream = new FileOutputStream(profil_foto);

                                    // Compressed Photo
                                    Bitmap bitmap = drawable_foto.getBitmap();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                            /* 100 to keep full quality of the image */

                                    // Put Photo into Directory
                                    outStream.flush();
                                    outStream.close();

                                    // Scan Gallery
                                    MediaScannerConnection.scanFile(TambahProfil.this,
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

                                // Check Condition
                                if (success) {
                                    // Show Toast Success
                                    Toast.makeText(getApplicationContext(), "Profil Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                                } else {
                                    // Show Toast Failed
                                    Toast.makeText(getApplicationContext(), "Profil Gagal Tersimpan", Toast.LENGTH_LONG).show();
                                }
                            }

                            // Insert Data into Database
                            db.insertProfil(nama, tmptLahir, tglLahir, jenisKelamin, golonganDarah,
                                    panjangLahir, beratLahir, alergi, penyakitKronis, passcode, foto);


                            Intent fetchID = getIntent();
                            String pathbefore = fetchID.getStringExtra("pathbefore");
                            int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

                            // Start Profil Activity
                            Intent profil = new Intent(TambahProfil.this, Profil.class);
                            lastActivity = System.currentTimeMillis();
                            profil.putExtra("lastActivity", lastActivity);
                            profil.putExtra("pathbefore", pathbefore);
                            profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                            profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(profil);

                            // Close This Activity
                            finish();
                        }
                    });

                    button_ok.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close Dialog
                            dialog.dismiss();

                            String jenisKelamin = ((RadioButton) findViewById(profil_jeniskelamin.getCheckedRadioButtonId())).getText().toString();
                            String golonganDarah = ((RadioButton) findViewById(profil_golongandarah.getCheckedRadioButtonId())).getText().toString();

                            Bitmap bitmap = drawable_foto.getBitmap();
                            ByteArrayOutputStream bs = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);

                            Intent fetchID = getIntent();
                            String pathbefore = fetchID.getStringExtra("pathbefore");
                            int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

                            Intent profilPasscode = new Intent(TambahProfil.this, ProfilPasscode.class);
                            profilPasscode.putExtra("nama", nama);
                            profilPasscode.putExtra("tmptLahir", tmptLahir);
                            profilPasscode.putExtra("tglLahir", tglLahir);
                            profilPasscode.putExtra("jenisKelamin", jenisKelamin);
                            profilPasscode.putExtra("golonganDarah", golonganDarah);
                            profilPasscode.putExtra("panjangLahir", panjangLahir);
                            profilPasscode.putExtra("beratLahir", beratLahir);
                            profilPasscode.putExtra("alergi", alergi);
                            profilPasscode.putExtra("penyakitKronis", penyakitKronis);
                            profilPasscode.putExtra("byteArray", bs.toByteArray());
                            lastActivity = System.currentTimeMillis();
                            profilPasscode.putExtra("lastActivity", lastActivity);
                            profilPasscode.putExtra("action", "tambahprofil");
                            profilPasscode.putExtra("pathbefore", pathbefore);
                            profilPasscode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                            profilPasscode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(profilPasscode);

                            finish();
                        }
                    });
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
                    bitmap = BitmapFactory.decodeFile(picturePath,
                            bitmapOptions);

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
        Intent fetchID = getIntent();
        String pathbefore = fetchID.getStringExtra("pathbefore");
        int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Start Profil Activity
        Intent profil = new Intent(TambahProfil.this, Profil.class);
        lastActivity = System.currentTimeMillis();
        profil.putExtra("lastActivity", lastActivity);
        profil.putExtra("pathbefore", pathbefore);
        profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
        profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(profil);

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
            session.clearSession(TambahProfil.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}
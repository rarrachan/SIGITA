package com.syafira.SIGITA;

import android.app.Activity;
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
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by syafira rarra on 05/07/2016.
 */
public class UbahGaleri extends Activity {

    private TextView tambah_galeri;
    private TextView titikdua;
    private TextView text_footer;
    private TextView text_galeri_nama;
    private TextView galeri_nama;
    private TextView text_galeri_tanggal;
    private EditText galeri_tanggal;
    private TextView text_galeri_usia;
    private TextView galeri_usia;
    private TextView text_keterangan_foto;
    private TextView keterangan_foto;
    private TextView text_galeri_foto;
    private ImageView galeri_foto;
    private ImageView button_simpan;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tambah_galeri);

        // Session Manager
        session = new SessionManager();
        int profilID = Integer.parseInt(session.loadSession(this, "id"));

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final int galeriID = fetchID.getIntExtra("galeriID", 0);
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Database
        db = new DBHelper(this);
        db.open();
        final Cursor cursor = db.getOneGaleri(galeriID, profilID);
        cursor.moveToFirst();

        // Load Widget
        tambah_galeri = (TextView) findViewById(R.id.tambah_galeri);
        titikdua = (TextView) findViewById(R.id.titikdua);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_galeri_nama = (TextView) findViewById(R.id.text_galeri_nama);
        galeri_nama = (TextView) findViewById(R.id.galeri_nama);
        text_galeri_tanggal = (TextView) findViewById(R.id.text_galeri_tanggal);
        galeri_tanggal = (EditText) findViewById(R.id.galeri_tanggal);
        text_galeri_usia = (TextView) findViewById(R.id.text_galeri_usia);
        galeri_usia = (TextView) findViewById(R.id.galeri_usia);
        text_keterangan_foto = (TextView) findViewById(R.id.text_galeri_keterangan_foto);
        keterangan_foto = (TextView) findViewById(R.id.galeri_keterangan_foto);
        text_galeri_foto = (TextView) findViewById(R.id.text_galeri_foto);
        galeri_foto = (ImageView) findViewById(R.id.galeri_foto);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        tambah_galeri.setTypeface(typeface);
        titikdua.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_nama.setTypeface(typeface);
        galeri_nama.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);
        text_keterangan_foto.setTypeface(typeface);
        keterangan_foto.setTypeface(typeface);
        text_galeri_foto.setTypeface(typeface);

        final String nama = session.loadSession(this, "nama");
        galeri_nama.setText(nama);
        galeri_tanggal.setText(cursor.getString(cursor.getColumnIndex("galeri_tanggal")));
        galeri_usia.setText(cursor.getString(cursor.getColumnIndex("galeri_umur")));
        keterangan_foto.setText(cursor.getString(cursor.getColumnIndex("galeri_desc")));
        final String foto_path = android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_") + "/" + cursor.getString(cursor.getColumnIndex("galeri_foto"));
        if (Drawable.createFromPath(foto_path) != null) {
            galeri_foto.setImageDrawable(Drawable.createFromPath(foto_path));
        }

        galeri_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                final String getBirthdayDate = session.loadSession(UbahGaleri.this, "tanggallahir");

                // Create Dialog DataPicker
                final DatePickerDialog mDatePicker = new DatePickerDialog(UbahGaleri.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get date and time    */

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        galeri_tanggal.setText(dateFormatter.format(newDate.getTime()));

                        String str[] = getBirthdayDate.split("/");
                        int day = Integer.parseInt(str[0]);
                        int month = Integer.parseInt(str[1]);
                        int year = Integer.parseInt(str[2]);

                        String getDate = galeri_tanggal.getText().toString();
                        String strings[] = getDate.split("/");
                        int dayDate = Integer.parseInt(strings[0]);
                        int monthDate = Integer.parseInt(strings[1]);
                        int yearDate = Integer.parseInt(strings[2]);

                        int years = yearDate - year;
                        int months = monthDate - month;
                        int days = dayDate - day;
                        if (days < 0) {
                            months--;
                            days += newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                        }
                        if (months < 0) {
                            years--;
                            months += 12;
                        }

                        int mon = months;
                        int y = 0;
                        while (y < years) {
                            mon += 12;
                            y += 1;
                        }
                        if (mon < 0)
                            mon += 12;

                        String umur = mon + " bulan / " + years + " tahun " + months + " bulan " + days + " hari";
                        galeri_usia.setText(umur);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                try {
                    Date startDate = dateFormatter.parse(getBirthdayDate);
                    mDatePicker.getDatePicker().setMinDate(startDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mDatePicker.show();
            }
        });

        galeri_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare Option Dialog
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                // Create Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(UbahGaleri.this);
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
            }
        });

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value
                int profilID = Integer.parseInt(session.loadSession(UbahGaleri.this, "id"));
                String nama = galeri_nama.getText().toString();
                String tgl = galeri_tanggal.getText().toString();
                String umur = galeri_usia.getText().toString();
                String keterangan = keterangan_foto.getText().toString();
                BitmapDrawable drawable_foto = (BitmapDrawable) galeri_foto.getDrawable();

                // Change Space into UnderScore
                String foto = tgl.replaceAll("/", "") + "_" + nama.replaceAll(" ", "_").toLowerCase() + ".jpg";
                String namaFolder = nama.replaceAll(" ", "_");

                if (TextUtils.isEmpty(keterangan) || TextUtils.isEmpty(tgl)) {
                    // Show Toast
                    Toast.makeText(UbahGaleri.this, "Kolom Belum Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    //check sd card
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        // Get Path Directory
                        String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

                        // Create Directory
                        File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + namaFolder);
                        photoDirectory.mkdirs();

                        File foto_lama = new File(photoDirectory, cursor.getString(cursor.getColumnIndex("galeri_foto")));
                        File foto_baru = new File(photoDirectory, foto);

                        // Declare Condition
                        boolean success = false;

                        // Remove Photo
                        if (foto_lama.exists()) {
                            foto_lama.delete();
                        } else if (foto_baru.exists()) {
                            foto_baru.delete();
                        }

                        try {
                            FileOutputStream outStream;
                            outStream = new FileOutputStream(foto_baru);

                            // Compressed Photo
                            Bitmap bitmap = drawable_foto.getBitmap();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                            /* 100 to keep full quality of the image */

                            // Put Photo into Directory
                            outStream.flush();
                            outStream.close();

                            // Scan Gallery
                            MediaScannerConnection.scanFile(UbahGaleri.this,
                                    new String[]{foto_baru.toString()}, null,
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
                            Toast.makeText(getApplicationContext(), "Galeri Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(getApplicationContext(), "Galeri Gagal Tersimpan", Toast.LENGTH_LONG).show();
                        }

                        // Insert Data into Database
                        db.updateGaleri(galeriID, profilID, tgl, umur, foto, keterangan);

                        // Start Detail Galeri Activity
                        Intent detail_galeri = new Intent(UbahGaleri.this, DetailGaleri.class);
                        lastActivity = System.currentTimeMillis();
                        detail_galeri.putExtra("lastActivity", lastActivity);
                        detail_galeri.putExtra("galeriID", galeriID);
                        startActivity(detail_galeri);

                        // Close This Activity
                        finish();
                    }

                }
            }
        });
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

                    // Set Image
                    galeri_foto.setImageBitmap(bitmap);

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

                    // Set Image
                    galeri_foto.setImageBitmap(bitmap);
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
        ScrollView view = (ScrollView) findViewById(R.id.scrollView);
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
        int galeriID = fetchID.getIntExtra("galeriID", 0);

        // Start Detail Galeri Activity
        Intent detail_galeri = new Intent(this, DetailGaleri.class);
        lastActivity = System.currentTimeMillis();
        detail_galeri.putExtra("lastActivity", lastActivity);
        detail_galeri.putExtra("galeriID", galeriID);
        startActivity(detail_galeri);

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
            session.clearSession(UbahGaleri.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}

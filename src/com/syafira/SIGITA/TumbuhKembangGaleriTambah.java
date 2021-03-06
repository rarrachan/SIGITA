package com.syafira.SIGITA;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
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
import java.util.*;

/**
 * Created by syafira rarra on 05/06/2016.
 */

public class TumbuhKembangGaleriTambah extends TumbuhKembangGaleri {

    private TextView tambah_galeri;
    private TextView text_footer;
    private TextView text_galeri_nama;
    private TextView galeri_nama;
    private TextView text_galeri_tanggal;
    private EditText galeri_tanggal;
    private TextView text_galeri_usia;
    private TextView galeri_usia;
    private TextView text_galeri_keterangan;
    private EditText galeri_keterangan;
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
        setContentView(R.layout.tumbang_galeri_tambah);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        tambah_galeri = (TextView) findViewById(R.id.tambah_galeri);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_galeri_nama = (TextView) findViewById(R.id.text_galeri_nama);
        galeri_nama = (TextView) findViewById(R.id.galeri_nama);
        text_galeri_tanggal = (TextView) findViewById(R.id.text_galeri_tanggal);
        galeri_tanggal = (EditText) findViewById(R.id.galeri_tanggal);
        text_galeri_usia = (TextView) findViewById(R.id.text_galeri_usia);
        galeri_usia = (TextView) findViewById(R.id.galeri_usia);
        text_galeri_keterangan = (TextView) findViewById(R.id.text_galeri_keterangan);
        galeri_keterangan = (EditText) findViewById(R.id.galeri_keterangan);
        text_galeri_foto = (TextView) findViewById(R.id.text_galeri_foto);
        galeri_foto = (ImageView) findViewById(R.id.galeri_foto);
        button_simpan = (ImageView) findViewById(R.id.button_simpan);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        tambah_galeri.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_galeri_nama.setTypeface(typeface);
        galeri_nama.setTypeface(typeface);
        text_galeri_tanggal.setTypeface(typeface);
        galeri_tanggal.setTypeface(typeface);
        text_galeri_usia.setTypeface(typeface);
        galeri_usia.setTypeface(typeface);
        text_galeri_keterangan.setTypeface(typeface);
        galeri_keterangan.setTypeface(typeface);
        text_galeri_foto.setTypeface(typeface);

        galeri_nama.setText(session.loadSession(this, "nama"));

        galeri_tanggal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            final Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            final String getBirthdayDate = session.loadSession(TumbuhKembangGaleriTambah.this, "tanggallahir");

            // Create Dialog DataPicker
            final DatePickerDialog mDatePicker = new DatePickerDialog(TumbuhKembangGaleriTambah.this, new DatePickerDialog.OnDateSetListener() {
              public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                /*      Your code   to get date and time    */
                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedyear, selectedmonth, selectedday);
                galeri_tanggal.setText(dateFormatter.format(newDate.getTime()));

                String getBirthdayDate = session.loadSession(TumbuhKembangGaleriTambah.this, "tanggallahir");
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
              }}, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

            try {
              Date startDate = dateFormatter.parse(getBirthdayDate);
              mDatePicker.getDatePicker().setMinDate(startDate.getTime());
            } catch (ParseException e) {
              e.printStackTrace();
            }
            mDatePicker.getDatePicker().setCalendarViewShown(false);
            mDatePicker.show();
          }
        });

        galeri_foto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            // Declare Option Dialog
            final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

            // Create Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(TumbuhKembangGaleriTambah.this);
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
            int profilID = Integer.parseInt(session.loadSession(TumbuhKembangGaleriTambah.this, "id"));
            String nama = galeri_nama.getText().toString();
            String tgl = galeri_tanggal.getText().toString();
            String umur = galeri_usia.getText().toString();
            String keterangan = galeri_keterangan.getText().toString();
            BitmapDrawable drawable_foto = (BitmapDrawable) galeri_foto.getDrawable();

            // Change Space into UnderScore
            String foto = tgl.replaceAll("/", "") + "_" + nama.replaceAll(" ", "_").toLowerCase() + ".jpg";
            String namaFolder = nama.replaceAll(" ", "_");

            if (TextUtils.isEmpty(keterangan)) {
              // Show Toast
              Toast.makeText(TumbuhKembangGaleriTambah.this, "Kolom Keterangan Galeri Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(tgl)) {
              // Show Toast
              Toast.makeText(TumbuhKembangGaleriTambah.this, "Kolom Tanggal Belum Terisi", Toast.LENGTH_SHORT).show();
            } else {
              //check sd card
              String state = Environment.getExternalStorageState();
              if (Environment.MEDIA_MOUNTED.equals(state)) {
                // Get Path Directory
                String sdCardDirectory = Environment.getExternalStorageDirectory().toString();

                // Create Directory
                final File photoDirectory = new File(sdCardDirectory + "/SIGITA/" + namaFolder);
                photoDirectory.mkdirs();

                // Declare Condition
                boolean success = false;

                try {
                  FileOutputStream outStream;

                  // Set Location Directory
                  File foto_galeri = new File(photoDirectory, foto);
                  int counter = 1;
                  while (foto_galeri.exists()) {
                    foto = tgl.replaceAll("/", "") + "_" + nama.replaceAll(" ", "_").toLowerCase() + "_" + (counter++) + ".jpg";
                    foto_galeri = new File(photoDirectory, foto);
                  }
                  outStream = new FileOutputStream(foto_galeri);

                  // Compressed Photo
                  Bitmap bitmap = drawable_foto.getBitmap();
                  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                  /* 100 to keep full quality of the image */

                  // Put Photo into Directory
                  outStream.flush();
                  outStream.close();

                  // Scan Gallery
                  MediaScannerConnection.scanFile(TumbuhKembangGaleriTambah.this,
                    new String[] { foto_galeri.toString() }, null,
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
                db.insertGaleri(profilID, tgl, umur, foto, keterangan);

                // Start Profil Activity
                Intent galeri = new Intent(TumbuhKembangGaleriTambah.this, TumbuhKembangGaleri.class);
                lastActivity = System.currentTimeMillis();
                galeri.putExtra("lastActivity", lastActivity);
                galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(galeri);

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
        bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);

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
        galeri_foto.setImageBitmap(scaled);

        // Save Image Temporary
        String path = android.os.Environment.getExternalStorageDirectory() + File.separator + "Phoenix" + File.separator + "default";
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
          galeri_foto.setImageBitmap(scaled);

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
      // Start galeri Activity
      Intent galeri = new Intent(TumbuhKembangGaleriTambah.this, TumbuhKembangGaleri.class);
      lastActivity = System.currentTimeMillis();
      galeri.putExtra("lastActivity", lastActivity);
      galeri.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(galeri);

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
          session.clearSession(TumbuhKembangGaleriTambah.this);

          Intent splash = new Intent(this, Splash.class);
          splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(splash);
          finish();
        }
      }
    }
package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 03/27/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
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
    private TextView titikdua;
    private ImageView button_simpan;
    private ImageView profil_foto;
    private DBHelper db;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tambah_profil);

        // Load Database
        db = new DBHelper(this);
        db.open();

        // Load Widget
        tambah_profil = (TextView) findViewById(R.id.tambah_profil);
        titikdua = (TextView) findViewById(R.id.titikdua);
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
        titikdua.setTypeface(typeface);
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
                mDatePicker.show();
                break;

            // Simpan
            case R.id.button_simpan:
                // Get Value
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
                String namaFolder = nama.replaceAll(" ", "_");

                // Check if Value Empty
                if (TextUtils.isEmpty(nama) ||
                        TextUtils.isEmpty(panjangLahir) ||
                        TextUtils.isEmpty(beratLahir) ||
                        TextUtils.isEmpty(tmptLahir) ||
                        TextUtils.isEmpty(tglLahir) ||
                        profil_jeniskelamin.getCheckedRadioButtonId() == -1 ||
                        profil_golongandarah.getCheckedRadioButtonId() == -1) {
                    // Show Toast
                    Toast.makeText(this, "Kolom Belum Terisi", Toast.LENGTH_SHORT).show();
                    return;
                } else {
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
//                        else
//                    {
//                        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//                        File directory = cw.getDir("SIGITA/" + nama, Context.MODE_PRIVATE);
//                        File profil_foto = new File(directory, foto);
//                        Bitmap bitmap = drawable_foto.getBitmap();
//                        FileOutputStream outStream;
//                        boolean success = false;
//                        try {
//
//                            outStream = new FileOutputStream(profil_foto);
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//        /* 100 to keep full quality of the image */
//                            outStream.flush();
//                            outStream.close();
//                            success = true;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        if (success) {
//                            Toast.makeText(getApplicationContext(), "Image saved with success in INTERNAL",
//                                    Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(),
//                                    "Error during image saving in INTERNAL", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    File f = new File(android.os.Environment.getExternalStorageDirectory() + "/SIGITA/" + namaFolder, foto.replaceAll(" ", "_").toLowerCase());
//                    File f = new File(android.os.Environment.getExternalStorageDirectory(), foto.replaceAll(" ", "_").toLowerCase());
//                    String fotoPath = f.getAbsoluteFile().toString();

                    // Insert Data into Database
                    db.insertProfil(nama, tmptLahir, tglLahir, jenisKelamin, golonganDarah,
                            panjangLahir, beratLahir, alergi, penyakitKronis, foto);

                    // Start Profil Activity
                    Intent profil = new Intent(this, Profil.class);
                    startActivity(profil);

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

                    // Set Image
                    profil_foto.setImageBitmap(bitmap);

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
                    profil_foto.setImageBitmap(bitmap);
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
        // Start Profil Activity
        Intent profil = new Intent(TambahProfil.this, Profil.class);
        startActivity(profil);

        // Close This Activity
        finish();
    }
}
package com.syafira.SIGITA;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by syafira rarra on 05/30/2016.
 */
public class ProfilCekPasscode extends Activity {

    private TextView text_footer;
    private TextView profil_passcode;
    private EditText pass1;
    private EditText pass2;
    private EditText pass3;
    private EditText pass4;
    private EditText passcode;
    private ImageView pass1image;
    private ImageView pass2image;
    private ImageView pass3image;
    private ImageView pass4image;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.profil_cek_passcode);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        final String nama = fetchID.getStringExtra("nama");
        final int id = fetchID.getIntExtra("id", 0);
        final String gender = fetchID.getStringExtra("gender");
        final String tanggallahir = fetchID.getStringExtra("tanggallahir");
        final String pass = fetchID.getStringExtra("passcode");
        final String action = fetchID.getStringExtra("action");

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        profil_passcode = (TextView) findViewById(R.id.profil_passcode);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        pass3 = (EditText) findViewById(R.id.pass3);
        pass4 = (EditText) findViewById(R.id.pass4);
        passcode = (EditText) findViewById(R.id.passcode);
        pass1image = (ImageView) findViewById(R.id.pass1image);
        pass2image = (ImageView) findViewById(R.id.pass2image);
        pass3image = (ImageView) findViewById(R.id.pass3image);
        pass4image = (ImageView) findViewById(R.id.pass4image);

        // Set Custom Font
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        profil_passcode.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        //prevent keyboard hide when user click enter
        pass1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return actionId == EditorInfo.IME_ACTION_DONE;
            }
        });
        pass2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return actionId == EditorInfo.IME_ACTION_DONE;
            }
        });
        pass3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return actionId == EditorInfo.IME_ACTION_DONE;
            }
        });
        pass4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return actionId == EditorInfo.IME_ACTION_DONE;
            }
        });

        pass1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass1.getText().toString().trim().length() == 1) {

                    pass1.clearFocus();
                    pass2.requestFocus();
                    pass1image.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }
            }
        });

        pass2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass2.getText().toString().trim().length() == 1) {

                    pass2.clearFocus();
                    pass3.requestFocus();
                    pass2image.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }
            }
        });

        pass3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass3.getText().toString().trim().length() == 1) {

                    pass3.clearFocus();
                    pass4.requestFocus();

                    pass3image.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }

            }
        });

        pass4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass4.getText().toString().trim().length() == 1) {

                    pass4.clearFocus();
                    pass4image.setBackgroundResource(R.drawable.pin_txt_bg_star);

                    //Hide keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(pass4.getWindowToken(), 0);

                    //get value
                    passcode.setText(pass1.getText().toString());
                    passcode.append(pass2.getText().toString());
                    passcode.append(pass3.getText().toString());
                    passcode.append(pass4.getText().toString());

                    String newpasscode = passcode.getText().toString();

                    switch (action) {
                        case "pilihprofil":
                            if (newpasscode.equals(pass)) {
                                // Create Session
                                if (session.checkSession(ProfilCekPasscode.this)) {
                                    session.clearSession(ProfilCekPasscode.this);
                                }
                                session.createSession(ProfilCekPasscode.this, "id", String.valueOf(id));
                                session.createSession(ProfilCekPasscode.this, "nama", nama);
                                session.createSession(ProfilCekPasscode.this, "gender", gender);
                                session.createSession(ProfilCekPasscode.this, "tanggallahir", tanggallahir);


                                // Start Index Activity
                                lastActivity = System.currentTimeMillis();
                                Intent index = new Intent(ProfilCekPasscode.this, Index.class);
                                index.putExtra("lastActivity", lastActivity);
                                index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(index);

                                // Close This Activity
                                finish();
                            } else {
                                Toast.makeText(ProfilCekPasscode.this, "Passcode Salah", Toast.LENGTH_SHORT).show();

                                // Start Index Activity
                                lastActivity = System.currentTimeMillis();
                                Intent profil = new Intent(ProfilCekPasscode.this, Profil.class);
                                profil.putExtra("lastActivity", lastActivity);
                                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(profil);

                                // Close This Activity
                                finish();
                            }
                            break;
                        case "passcode":
                            if (newpasscode.equals(pass)) {
                                // Start Index Activity
                                lastActivity = System.currentTimeMillis();
                                Intent createnewpasscode = new Intent(ProfilCekPasscode.this, Passcode.class);
                                createnewpasscode.putExtra("lastActivity", lastActivity);
                                createnewpasscode.putExtra("id", id);
                                startActivity(createnewpasscode);

                                // Close This Activity
                                finish();
                            } else {
                                Toast.makeText(ProfilCekPasscode.this, "Passcode Salah", Toast.LENGTH_SHORT).show();

                                // Close This Activity
                                finish();
                            }
                            break;
                        case "detailprofil":
                            if (newpasscode.equals(pass)) {
                                // Show Detail Profil Activity
                                lastActivity = System.currentTimeMillis();
                                Intent detail_profil = new Intent(ProfilCekPasscode.this, DetailProfil.class);
                                lastActivity = System.currentTimeMillis();
                                detail_profil.putExtra("lastActivity", lastActivity);
                                detail_profil.putExtra("id", id);
                                startActivity(detail_profil);

                                // Close This Activity
                                finish();
                            } else {
                                Toast.makeText(ProfilCekPasscode.this, "Passcode Salah", Toast.LENGTH_SHORT).show();

                                // Close This Activity
                                finish();
                            }
                            break;
                        case "ubahpasscode":
                            if (newpasscode.equals(pass)) {
                                // Start Index Activity
                                lastActivity = System.currentTimeMillis();
                                Intent createnewpasscode = new Intent(ProfilCekPasscode.this, ProfilPasscode.class);
                                createnewpasscode.putExtra("lastActivity", lastActivity);
                                createnewpasscode.putExtra("id", id);
                                createnewpasscode.putExtra("pass", pass);
                                createnewpasscode.putExtra("action", "ubahpasscode");
                                startActivity(createnewpasscode);

                                // Close This Activity
                                finish();
                            } else {
                                Toast.makeText(ProfilCekPasscode.this, "Passcode Salah", Toast.LENGTH_SHORT).show();

                                // Close This Activity
                                finish();
                            }
                            break;
                        case "hapuspasscode":
                            if (newpasscode.equals(pass)) {
                                // Load Database
                                DBHelper db = new DBHelper(ProfilCekPasscode.this);
                                db.open();

                                newpasscode = "";
                                boolean success = false;
                                try {
                                    // Insert Data into Database
                                    db.updateProfilPasscode(id, newpasscode);
                                    success = true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if (success) {
                                    Toast.makeText(getApplicationContext(), "Passcode Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Passcode Gagal Dihapus", Toast.LENGTH_LONG).show();
                                }

                                // Start Profil Activity
                                Intent passcode = new Intent(ProfilCekPasscode.this, Passcode.class);
                                lastActivity = System.currentTimeMillis();
                                passcode.putExtra("lastActivity", lastActivity);
                                passcode.putExtra("id", id);
                                passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(passcode);

                                // Close This Activity
                                finish();
                            } else {
                                Toast.makeText(ProfilCekPasscode.this, "Passcode Salah", Toast.LENGTH_SHORT).show();

                                // Close This Activity
                                finish();
                            }
                            break;
                    }
                }
            }
        });

        this.pass2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilCekPasscode.this.pass2.getText().length() == 0)) {
                    pass1.requestFocus();
                    pass1image.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass1.setText("");
                }

                return false;
            }
        });

        this.pass3.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilCekPasscode.this.pass3.getText().length() == 0)) {
                    pass2.requestFocus();
                    pass2image.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass2.setText("");
                }

                return false;
            }
        });

        this.pass4.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilCekPasscode.this.pass4.getText().length() == 0)) {
                    pass3.requestFocus();
                    pass3image.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass3.setText("");
                }

                return false;
            }
        });

        TextView lupa_passcode = (TextView) findViewById(R.id.lupa_passcode);
        lupa_passcode.setTypeface(typeface);
        lupa_passcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(ProfilCekPasscode.this);
                dialog.setContentView(R.layout.lupa_passcode);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dialog.show();

                // Load Dialog Widget
                TextView alert_lupa = (TextView) dialog.findViewById(R.id.alert_lupa);
                TextView alert_lupa_passcode = (TextView) dialog.findViewById(R.id.alert_lupa_passcode);
                ImageView button_batal = (ImageView) dialog.findViewById(R.id.button_batal);
                ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                // Set Custom Font Dialog
                alert_lupa.setTypeface(typeface);
                alert_lupa_passcode.setTypeface(typeface);

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

                            // Turn Off Alarm Imunisasi
                            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            if (PendingIntent.getBroadcast(ProfilCekPasscode.this, id,
                                    new Intent(ProfilCekPasscode.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE) != null) {
                                PendingIntent.getBroadcast(ProfilCekPasscode.this, id,
                                        new Intent(ProfilCekPasscode.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE).cancel();
                                alarmManager.cancel(PendingIntent.getBroadcast(ProfilCekPasscode.this, id,
                                        new Intent(ProfilCekPasscode.this, AlarmReceiver.class), PendingIntent.FLAG_NO_CREATE));
                            }

                            // Delete Folder Directory
                            File profilDirectory = new File(Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_"));
                            if (profilDirectory.isDirectory()) {
                                String[] children = profilDirectory.list();
                                for (String aChildren : children) {
                                    new File(profilDirectory, aChildren).delete();
                                }
                                profilDirectory.delete();
                            }

                            // Scan Gallery
                            File scanGallery = new File(Environment.getExternalStorageDirectory() + "/SIGITA/" + nama.replaceAll(" ", "_"));
                            MediaScannerConnection.scanFile(ProfilCekPasscode.this,
                                    new String[]{scanGallery.toString()}, null,
                                    new MediaScannerConnection.OnScanCompletedListener() {
                                        public void onScanCompleted(String path, Uri uri) {
                                        }
                                    });

                            // Delete From Database
                            // Load Database
                            DBHelper db = new DBHelper(ProfilCekPasscode.this);
                            db.open();
                            db.deleteDokumentasiProfilID(id);
                            db.deleteRiwayatProfilID(id);
                            db.deleteGaleriProfilID(id);
                            db.deleteMedisProfilID(id);
                            db.deleteProfil(id);

                            // Declare Condition
                            success = true;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Check Condition
                        if (success) {
                            // Show Toast Success
                            Toast.makeText(ProfilCekPasscode.this, "Profil Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show Toast Failed
                            Toast.makeText(ProfilCekPasscode.this, "Profil Gagal Terhapus", Toast.LENGTH_SHORT).show();
                        }

                        // Close Dialog
                        dialog.dismiss();

                        // Show Profil Activity
                        Intent profil = new Intent(ProfilCekPasscode.this, Profil.class);
                        lastActivity = System.currentTimeMillis();
                        profil.putExtra("lastActivity", lastActivity);
                        profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(profil);

                        // Clear Activity
                        finish();

                    }
                });
            }
        });
    }

    // prevent switch focus when user clicked tab
    public boolean dispatchKeyEvent(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN || super.dispatchKeyEvent(event);
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final String action = fetchID.getStringExtra("action");
        final int id = fetchID.getIntExtra("id", 0);

        switch (action) {
            case "pilihprofil":
            case "detailprofil":
                // Start Profil Activity
                Intent profil = new Intent(ProfilCekPasscode.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);

                // Close This Activity
                finish();
                break;
            case "passcode":
                // Start Profil Activity
                Intent detailprofil = new Intent(ProfilCekPasscode.this, DetailProfil.class);
                lastActivity = System.currentTimeMillis();
                detailprofil.putExtra("lastActivity", lastActivity);
                detailprofil.putExtra("id", id);
                detailprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailprofil);

                // Close This Activity
                finish();
                break;
            case "hapuspasscode":
            case "ubahpasscode":
                // Start Profil Activity
                Intent passcode = new Intent(ProfilCekPasscode.this, Passcode.class);
                lastActivity = System.currentTimeMillis();
                passcode.putExtra("lastActivity", lastActivity);
                passcode.putExtra("id", id);
                passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(passcode);

                // Close This Activity
                finish();
                break;
        }
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();

        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(ProfilCekPasscode.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        }
    }
}

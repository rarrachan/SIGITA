package com.syafira.SIGITA;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by syafira rarra on 05/30/2016.
 */

public class ProfilPasscodeCek extends ProfilPasscode {

    private TextView text_footer;
    private TextView profil_passcode;
    private EditText pass1;
    private EditText pass2;
    private EditText pass3;
    private EditText pass4;
    private EditText passcode;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.passcode_cek);

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
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Load Widget
        text_footer = (TextView) findViewById(R.id.text_footer);
        profil_passcode = (TextView) findViewById(R.id.profil_passcode);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        pass3 = (EditText) findViewById(R.id.pass3);
        pass4 = (EditText) findViewById(R.id.pass4);
        passcode = (EditText) findViewById(R.id.passcode);

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

        // when text change
        pass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (pass1.getText().toString().trim().length() == 1) {
                    pass1.clearFocus();
                    pass2.requestFocus();
                    pass1.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }
            }
        });

        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (pass2.getText().toString().trim().length() == 1) {
                    pass2.clearFocus();
                    pass3.requestFocus();
                    pass2.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }
            }
        });

        pass3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (pass3.getText().toString().trim().length() == 1) {
                    pass3.clearFocus();
                    pass4.requestFocus();
                    pass3.setBackgroundResource(R.drawable.pin_txt_bg_star);
                }

            }
        });

        pass4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (pass4.getText().toString().trim().length() == 1) {

                    pass4.clearFocus();
                    pass4.setBackgroundResource(R.drawable.pin_txt_bg_star);

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
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {
                                // Create Session
                                if (session.checkSession(ProfilPasscodeCek.this)) {
                                    session.clearSession(ProfilPasscodeCek.this);
                                }
                                session.createSession(ProfilPasscodeCek.this, "id", String.valueOf(id));
                                session.createSession(ProfilPasscodeCek.this, "nama", nama);
                                session.createSession(ProfilPasscodeCek.this, "gender", gender);
                                session.createSession(ProfilPasscodeCek.this, "tanggallahir", tanggallahir);

                                // Show Toast
                                Toast.makeText(ProfilPasscodeCek.this, "Profil " + nama + " Dipilih", Toast.LENGTH_SHORT).show();

                                // Start Home Activity
                                lastActivity = System.currentTimeMillis();
                                Intent index = new Intent(ProfilPasscodeCek.this, Home.class);
                                index.putExtra("lastActivity", lastActivity);
                                index.putExtra("pathbefore", pathbefore);
                                index.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(index);

                                // Close This Activity
                                finish();
                            
                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Home Activity
                                lastActivity = System.currentTimeMillis();
                                Intent profil = new Intent(ProfilPasscodeCek.this, Profil.class);
                                profil.putExtra("lastActivity", lastActivity);
                                profil.putExtra("pathbefore", pathbefore);
                                profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(profil);

                                // Close This Activity
                                finish();
                            }
                            break;

                        case "detailprofil":
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {
                                // Show Detail Profil Activity
                                lastActivity = System.currentTimeMillis();
                                Intent detail_profil = new Intent(ProfilPasscodeCek.this, ProfilDetail.class);
                                lastActivity = System.currentTimeMillis();
                                detail_profil.putExtra("lastActivity", lastActivity);
                                detail_profil.putExtra("id", id);
                                detail_profil.putExtra("pathbefore", pathbefore);
                                detail_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                startActivity(detail_profil);

                                // Close This Activity
                                finish();

                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Profil Activity
                                Intent profil = new Intent(ProfilPasscodeCek.this, Profil.class);
                                lastActivity = System.currentTimeMillis();
                                profil.putExtra("lastActivity", lastActivity);
                                profil.putExtra("pathbefore", pathbefore);
                                profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(profil);

                                // Close This Activity
                                finish();
                            }
                            break;

                        case "ubahpasscode":
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {

                                // Start Home Activity
                                lastActivity = System.currentTimeMillis();
                                Intent createnewpasscode = new Intent(ProfilPasscodeCek.this, ProfilPasscodeUbah.class);
                                createnewpasscode.putExtra("lastActivity", lastActivity);
                                createnewpasscode.putExtra("id", id);
                                createnewpasscode.putExtra("pass", pass);
                                createnewpasscode.putExtra("action", "ubahpasscode");
                                createnewpasscode.putExtra("pathbefore", pathbefore);
                                createnewpasscode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                createnewpasscode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(createnewpasscode);

                                // Close This Activity
                                finish();

                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Profil Activity
                                Intent passcode = new Intent(ProfilPasscodeCek.this, ProfilPasscode.class);
                                lastActivity = System.currentTimeMillis();
                                passcode.putExtra("lastActivity", lastActivity);
                                passcode.putExtra("id", id);
                                passcode.putExtra("pathbefore", pathbefore);
                                passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(passcode);

                                // Close This Activity
                                finish();
                            }
                            break;

                        case "hapuspasscode":
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {
                                // Start Profil Activity
                                Intent passcodehapus = new Intent(ProfilPasscodeCek.this, ProfilPasscodeHapus.class);
                                lastActivity = System.currentTimeMillis();
                                passcodehapus.putExtra("lastActivity", lastActivity);
                                passcodehapus.putExtra("id", id);
                                passcodehapus.putExtra("pathbefore", pathbefore);
                                passcodehapus.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                passcodehapus.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(passcodehapus);

                                // Close This Activity
                                finish();

                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Profil Activity
                                Intent passcode = new Intent(ProfilPasscodeCek.this, ProfilPasscode.class);
                                lastActivity = System.currentTimeMillis();
                                passcode.putExtra("lastActivity", lastActivity);
                                passcode.putExtra("id", id);
                                passcode.putExtra("pathbefore", pathbefore);
                                passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                passcode.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(passcode);

                                // Close This Activity
                                finish();
                            }
                            break;

                        case "ubahprofil":
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {
                                Intent ubah_profil = new Intent(ProfilPasscodeCek.this, ProfilUbah.class);
                                // Put Intent Extra
                                lastActivity = System.currentTimeMillis();
                                ubah_profil.putExtra("lastActivity", lastActivity);
                                ubah_profil.putExtra("id", id);
                                ubah_profil.putExtra("nama", nama);
                                ubah_profil.putExtra("pathbefore", pathbefore);
                                ubah_profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                ubah_profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(ubah_profil);
                                finish();

                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Profil Activity
                                Intent detailprofil = new Intent(ProfilPasscodeCek.this, ProfilDetail.class);
                                lastActivity = System.currentTimeMillis();
                                detailprofil.putExtra("lastActivity", lastActivity);
                                detailprofil.putExtra("id", id);
                                detailprofil.putExtra("pathbefore", pathbefore);
                                detailprofil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                detailprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(detailprofil);

                                // Close This Activity
                                finish();
                            }
                            break;

                        case "hapusprofil":
                            // ProfilPasscode True
                            if (newpasscode.equals(pass)) {
                                // Show Hapus Profil Activity
                                Intent hapusprofil = new Intent(ProfilPasscodeCek.this, ProfilHapus.class);
                                hapusprofil.putExtra("lastActivity", lastActivity);
                                hapusprofil.putExtra("nama", nama);
                                hapusprofil.putExtra("id", id);
                                hapusprofil.putExtra("pathbefore", pathbefore);
                                hapusprofil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                hapusprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(hapusprofil);
                                finish();

                            // ProfilPasscode False
                            } else {
                                Toast.makeText(ProfilPasscodeCek.this, "ProfilPasscode Salah", Toast.LENGTH_SHORT).show();

                                // Start Detail Profil Activity
                                Intent detailprofil = new Intent(ProfilPasscodeCek.this, ProfilDetail.class);
                                lastActivity = System.currentTimeMillis();
                                detailprofil.putExtra("lastActivity", lastActivity);
                                detailprofil.putExtra("id", id);
                                detailprofil.putExtra("pathbefore", pathbefore);
                                detailprofil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                                detailprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(detailprofil);

                                // Close This Activity
                                finish();
                            }
                            break;
                    }
                }
            }
        });

        // pressed backspace button
        this.pass2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeCek.this.pass2.getText().length() == 0)) {
                    pass1.requestFocus();
                    pass1.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass1.setText("");
                }
                return false;
            }
        });

        this.pass3.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeCek.this.pass3.getText().length() == 0)) {
                    pass2.requestFocus();
                    pass2.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass2.setText("");
                }
                return false;
            }
        });

        this.pass4.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeCek.this.pass4.getText().length() == 0)) {
                    pass3.requestFocus();
                    pass3.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass3.setText("");
                }
                return false;
            }
        });

        // Lupa ProfilPasscode
        TextView lupa_passcode = (TextView) findViewById(R.id.lupa_passcode);
        lupa_passcode.setTypeface(typeface);
        lupa_passcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Dialog
                final Dialog dialog = new Dialog(ProfilPasscodeCek.this);
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
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    }
                });

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show Hapus Profil Activity
                        Intent hapusprofil = new Intent(ProfilPasscodeCek.this, ProfilHapus.class);
                        hapusprofil.putExtra("lastActivity", lastActivity);
                        hapusprofil.putExtra("nama", nama);
                        hapusprofil.putExtra("id", id);
                        hapusprofil.putExtra("pathbefore", pathbefore);
                        hapusprofil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                        hapusprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(hapusprofil);
                        finish();
                        // Close Dialog
                        dialog.dismiss();
                    }
                });
            }
        });

        // Set OnTouchListener
        pass1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP != event.getAction()) {
                    if (pass1.getText().length() == 0) {
                        pass1.requestFocus();
                    } else if (pass2.getText().length() == 0) {
                        pass2.requestFocus();
                    } else if (pass3.getText().length() == 0) {
                        pass3.requestFocus();
                    } else if (pass4.getText().length() == 0) {
                        pass4.requestFocus();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                return true;
            }
        });

        pass2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP != event.getAction()) {
                    if (pass1.getText().length() == 0) {
                        pass1.requestFocus();
                    } else if (pass2.getText().length() == 0) {
                        pass2.requestFocus();
                    } else if (pass3.getText().length() == 0) {
                        pass3.requestFocus();
                    } else if (pass4.getText().length() == 0) {
                        pass4.requestFocus();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                return true;
            }
        });

        pass3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP != event.getAction()) {
                    if (pass1.getText().length() == 0) {
                        pass1.requestFocus();
                    } else if (pass2.getText().length() == 0) {
                        pass2.requestFocus();
                    } else if (pass3.getText().length() == 0) {
                        pass3.requestFocus();
                    } else if (pass4.getText().length() == 0) {
                        pass4.requestFocus();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                return true;
            }
        });

        pass4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP != event.getAction()) {
                    if (pass1.getText().length() == 0) {
                        pass1.requestFocus();
                    } else if (pass2.getText().length() == 0) {
                        pass2.requestFocus();
                    } else if (pass3.getText().length() == 0) {
                        pass3.requestFocus();
                    } else if (pass4.getText().length() == 0) {
                        pass4.requestFocus();
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                return true;
            }
        });
    }

    // prevent switch focus when user clicked tab
    public boolean dispatchKeyEvent(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_TAB || super.dispatchKeyEvent(event);
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final String action = fetchID.getStringExtra("action");
        int id = fetchID.getIntExtra("id", 0);
        String pathbefore = fetchID.getStringExtra("pathbefore");
        int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        switch (action) {
            case "pilihprofil":
            case "detailprofil":
                // Start Profil Activity
                Intent profil = new Intent(ProfilPasscodeCek.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", pathbefore);
                profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);

                // Close This Activity
                finish();
                break;

            case "hapusprofil":
            case "ubahprofil":
                // Start Profil Activity
                Intent detailprofil = new Intent(ProfilPasscodeCek.this, ProfilDetail.class);
                lastActivity = System.currentTimeMillis();
                detailprofil.putExtra("lastActivity", lastActivity);
                detailprofil.putExtra("id", id);
                detailprofil.putExtra("pathbefore", pathbefore);
                detailprofil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                detailprofil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailprofil);

                // Close This Activity
                finish();
                break;
                
            case "hapuspasscode":
            case "ubahpasscode":
                // Start Profil Activity
                Intent passcode = new Intent(ProfilPasscodeCek.this, ProfilPasscode.class);
                lastActivity = System.currentTimeMillis();
                passcode.putExtra("lastActivity", lastActivity);
                passcode.putExtra("id", id);
                passcode.putExtra("pathbefore", pathbefore);
                passcode.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
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
            session.clearSession(ProfilPasscodeCek.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}

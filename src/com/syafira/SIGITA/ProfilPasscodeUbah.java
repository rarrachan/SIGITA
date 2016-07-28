package com.syafira.SIGITA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by syafira rarra on 05/30/2016.
 */

public class ProfilPasscodeUbah extends ProfilPasscode {

    private TextView text_footer;
    private TextView profil_passcode;
    private EditText pass1;
    private EditText pass2;
    private EditText pass3;
    private EditText pass4;
    private EditText passcode;
    private DBHelper db;
    private SessionManager session;
    private long lastActivity;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load Layout
        setContentView(R.layout.passcode_tambah);

        // Load Session Manager
        session = new SessionManager();

        // Fetch Intent Extra
        final Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);
        final String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        // Intent Extra for Ubah Passcode
        final int id = fetchID.getIntExtra("id", 0);

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

                    // Load Database
                    db = new DBHelper(ProfilPasscodeUbah.this);
                    db.open();

                    boolean success = false;
                    try {
                        // Insert Data into Database
                        db.updateProfilPasscode(id, newpasscode);
                        success = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (success) {
                        Toast.makeText(getApplicationContext(), "Passcode Berhasil Diubah", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Passcode Gagal Diubah", Toast.LENGTH_LONG).show();
                    }

                    // Start Profil Activity
                    Intent profil = new Intent(ProfilPasscodeUbah.this, ProfilDetail.class);
                    lastActivity = System.currentTimeMillis();
                    profil.putExtra("lastActivity", lastActivity);
                    profil.putExtra("id", id);
                    profil.putExtra("pathbefore", pathbefore);
                    profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                    profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(profil);

                    // Close This Activity
                    finish();
                }
            }
        });

        // when pressed backspace
        this.pass2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeUbah.this.pass2.getText().length() == 0)) {
                    pass1.requestFocus();
                    pass1.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass1.setText("");
                }
                return false;
            }
        });

        this.pass3.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeUbah.this.pass3.getText().length() == 0)) {
                    pass2.requestFocus();
                    pass2.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass2.setText("");
                }
                return false;
            }
        });

        this.pass4.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
                if ((paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN) && (paramInt == 67) && (ProfilPasscodeUbah.this.pass4.getText().length() == 0)) {
                    pass3.requestFocus();
                    pass3.setBackgroundResource(R.drawable.pin_txt_bg);
                    pass3.setText("");
                }
                return false;
            }
        });

        // set OnTouchListener
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
        return event.getKeyCode() == KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN || super.dispatchKeyEvent(event);
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        final String action = fetchID.getStringExtra("action");
        final int id = fetchID.getIntExtra("id", 0);
        String pathbefore = fetchID.getStringExtra("pathbefore");
        final int detailJadwalImunisasiID = fetchID.getIntExtra("detailJadwalImunisasiID", 0);

        switch (action) {
            case "pilihprofil":
            case "detailprofil":
            case "tambahprofil":
                // Start Profil Activity
                Intent profil = new Intent(ProfilPasscodeUbah.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                profil.putExtra("pathbefore", pathbefore);
                profil.putExtra("detailJadwalImunisasiID", detailJadwalImunisasiID);
                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profil);

                // Close This Activity
                finish();
                break;

            case "passcode":
                // Start Profil Activity
                Intent detailprofil = new Intent(ProfilPasscodeUbah.this, ProfilDetail.class);
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
            case "tambahpasscode":
                // Start Profil Activity
                Intent passcode = new Intent(ProfilPasscodeUbah.this, ProfilPasscode.class);
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
            session.clearSession(ProfilPasscodeUbah.this);

            Intent splash = new Intent(this, Splash.class);
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
        }
    }
}